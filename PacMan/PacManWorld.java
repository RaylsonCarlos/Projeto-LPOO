
import greenfoot.*;
import java.util.List;

/**
 * PacManWorld representa o mundo onde o cenário e os objetos (PacMan, ghosts e
 * pellets) irão interagir.
 *
 * PacManWorld é filha da classe World, logo herda seus métodos, atributos e
 * construtores públicos.
 *
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class PacManWorld extends World {

    private final GreenfootImage background = new GreenfootImage("background.png");
    private final int defaultSpeed = 39;
    private final int jailTime = 6000;

    private long jailTimer = 0l;

    /**
     * Cria o cenário do mundo, define a velocidade padrão, estabelece a imagem
     * de fundo padrão e cria os objetos para as paredes, ghosts, pellets e o
     * PacMan.
     *
     * @param size tamanho do mundo
     */
    public PacManWorld(int size) {
        super(57, 68, size / 4);

        // Cria o plano de fundo do mundo.
        setBackground(background);

        populateWalls();

        addObject(Score.getInstance(), 40, 65);

        // Adiciona as vidas do pacman.
        addObject(new Life(), 3, 65);
        addObject(new Life(), 8, 65);
        addObject(new Life(), 13, 65);

        // Define a ordem que os objetos são criados no mundo.
        setPaintOrder(Ghost.class, PacMan.class, Pellet.class);

        // Reseta o mundo.
        reset(true);

        // Executa o som de início do jogo.
        SoundPlayer.getInstance().playSound(SoundPlayer.PACMAN_BEGINNING);
    }

    /**
     * Faz o mundo agir: transporta através do portal, calcula os pontos do
     * jogador e verifica se o jogo acabou.
     */
    public void act() {
        portal(0, 29, 56, 29);

        // Libera os fantasmas.
        releaseGhosts();

        // Configura a próxima fase, ao comer todas as pastilhas.
        nextPhase();

        // Ajusta os sons do jogo.
        adjustBackgroundSound();
    }

    /**
     * Para a reprodução de som quando o Greenfoot é pausado.
     */
    public void stopped() {
        SoundPlayer.getInstance().stopBackgroundSound();
    }

    /**
     * Reseta as condições do labirinto.
     *
     * @param resetPellets caso seja necessário resetar as pastilhas do
     * labirinto.
     */
    public void reset(boolean resetPellets) {
        try {
            List<Life> lifes = getObjects(Life.class);
            List<PacMan> pacmans = getObjects(PacMan.class);

            // Verifica se o pacman tem vidas disponíveis.
            if (lifes.size() <= 0) {
                // As vidas acabaram e o pacman morreu
                if (!resetPellets) {
                    // Finaliza o jogo.
                    end();

                    return;
                }
            } else {
                // As vidas não acabaram e o pacman morreu.
                if (!resetPellets) {
                    //Pega a vida mais à direita.
                    Life life = lifes.get(0);

                    for (Life lf : lifes) {
                        if (life.getX() < lf.getX()) {
                            life = lf;
                        }
                    }

                    // Remove uma vida do pacman.
                    removeObject(life);
                }
            }

            // Remove e reposiciona os fantasmas.
            removeObjects(getObjects(Ghost.class));
            populateGhosts();

            // Verifica se ainda existe algum pacman.
            if (pacmans.size() <= 0) {
                PacMan pacman = new PacMan();
                pacman.registerObserver(Score.getInstance());
                // Cria um novo pacman.
                addObject(pacman, 28, 47);
            } else {
                // Reposiciona o pacman existente.
                PacMan pacman = pacmans.get(0);

                pacman.setLocation(28, 47);
                pacman.setImage("images/west_1.png");
                pacman.setDirection(Character.WEST);
            }

            // Verifica se o pacman comeu todas as pellets.
            if (resetPellets) {
                populaterPellets();
            }

            repaint();

            // Aguarda um tempo.
            Thread.sleep(1500);

            // Reajusta a velocidade.
            Greenfoot.setSpeed(defaultSpeed);

            // Reseta o timer da cela.
            jailTimer = System.currentTimeMillis();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Ajusta o som de fundo à situação dos ghosts.
     */
    private void adjustBackgroundSound() {
        List<Ghost> ghosts = getObjects(Ghost.class);

        // Verifica se não há nenhum fantasma.
        if (ghosts.size() <= 0) {
            SoundPlayer.getInstance().stopBackgroundSound();

            return;
        }

        boolean fearGhost = false;
        boolean deadGhost = false;

        // Verifica o estado dos fantasmas.
        for (Ghost ghost : ghosts) {
            int ghostState = ghost.getStatus();

            if (ghostState == Ghost.FEAR || ghostState == Ghost.RECOVERING) {
                fearGhost = true;
            } else if (ghostState == Ghost.DEAD) {
                deadGhost = true;
            }
        }

        // Verifica se todos os fantasmas estão vivos.
        if (!fearGhost && !deadGhost) {
            if (!SoundPlayer.getInstance().getSound(SoundPlayer.BACKGROUND_NORMAL)) {
                SoundPlayer.getInstance().stopBackgroundSound();
                SoundPlayer.getInstance().playSound(SoundPlayer.BACKGROUND_NORMAL);
            }

            return;
        }

        // Verifica se há algum fantasma preso ou amedrontado.
        if (fearGhost && !deadGhost) {
            if (!SoundPlayer.getInstance().getSound(SoundPlayer.BACKGROUND_FRIGHTENED)) {
                SoundPlayer.getInstance().stopBackgroundSound();
                SoundPlayer.getInstance().playSound(SoundPlayer.BACKGROUND_FRIGHTENED);
            }

            return;
        }

        // Verifica se há algum fantasma preso.
        if (deadGhost) {
            if (!SoundPlayer.getInstance().getSound(SoundPlayer.BACKGROUND_EYES)) {
                SoundPlayer.getInstance().stopBackgroundSound();
                SoundPlayer.getInstance().playSound(SoundPlayer.BACKGROUND_EYES);
            }
        }
    }

    /**
     * Método que liberta os fantasmas se estiver no tempo.
     */
    private void releaseGhosts() {
        // Verifica se está no momento de soltar os fantasmas.
        if (System.currentTimeMillis() - jailTimer > jailTime) {
            List<Ghost> ghosts = getObjects(Ghost.class);

            // Libera os fantasmas que não podem sair da cela.
            for (Ghost ghost : ghosts) {
                if (!ghost.getCanLeave()) {
                    ghost.setCanLeave(true);

                    break;
                }
            }

            jailTimer = System.currentTimeMillis();
        }
    }

    /**
     * Quando o PacMan come todas as pellets do mundo, ele ganha o jogo.
     */
    private void nextPhase() {
        try {
            List<Pellet> pellets = getObjects(Pellet.class);

            // Verifica se há alguma pastilha no mundo.
            if (pellets.size() <= 0) {
                SoundPlayer.getInstance().stopBackgroundSound();

                Thread.sleep(2000);

                // Reseta o jogo.
                reset(true);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Cria os fantasmas e coloca-os no mundo.
     */
    public void populateGhosts() {
        //TODO: cada um tem suas próprias características.
        GhostFactory gf = new GhostFactory();

        Ghost blinky = gf.createGhost("blinky");
        Ghost pinky = gf.createGhost("pinky");
        Ghost inky = gf.createGhost("inky");
        Ghost clyde = gf.createGhost("clyde");

        // Coloca os fantasmas dentro da cela.
        addObject(pinky, 28, 30);
        addObject(inky, 32, 30);
        addObject(clyde, 24, 30);
        addObject(blinky, 28, 23);

        // Informa que os fantasmas blinky e pinky podem sair.
        blinky.setCanLeave(true);
        pinky.setCanLeave(true);
    }

    /**
     * Cria as pastilhas e coloca-os dentro do mundo.
     */
    public void populaterPellets() {
        // Pastilhas na posição vertical.
        verticalPellets(3, 3, 19);
        verticalPellets(3, 43, 49);
        verticalPellets(3, 55, 61);
        verticalPellets(7, 49, 55);
        verticalPellets(13, 5, 55);
        verticalPellets(19, 13, 19);
        verticalPellets(19, 49, 55);
        verticalPellets(25, 5, 13);
        verticalPellets(25, 43, 47);
        verticalPellets(31, 5, 11);
        verticalPellets(31, 43, 47);
        verticalPellets(37, 13, 19);
        verticalPellets(37, 49, 55);
        verticalPellets(43, 5, 11);
        verticalPellets(43, 13, 55);
        verticalPellets(49, 49, 55);
        verticalPellets(53, 5, 11);
        verticalPellets(53, 13, 19);
        verticalPellets(53, 43, 49);
        verticalPellets(53, 55, 61);

        // Pastilhas na posição horizontal.
        horizontalPellets(3, 5, 27);
        horizontalPellets(3, 31, 55);
        horizontalPellets(11, 5, 13);
        horizontalPellets(11, 15, 55);
        horizontalPellets(17, 5, 13);
        horizontalPellets(17, 21, 27);
        horizontalPellets(17, 31, 37);
        horizontalPellets(17, 45, 53);
        horizontalPellets(41, 3, 27);
        horizontalPellets(41, 31, 55);
        horizontalPellets(47, 15, 27);
        horizontalPellets(47, 29, 43);
        horizontalPellets(53, 21, 27);
        horizontalPellets(53, 31, 37);
        horizontalPellets(59, 5, 53);

        // Pastilhas em posições específicas.
        addObject(new Pellet(), 3, 9);
        addObject(new Pellet(), 3, 53);
        addObject(new Pellet(), 5, 47);
        addObject(new Pellet(), 5, 53);
        addObject(new Pellet(), 7, 47);
        addObject(new Pellet(), 9, 53);
        addObject(new Pellet(), 11, 53);
        addObject(new Pellet(), 25, 55);
        addObject(new Pellet(), 25, 57);
        addObject(new Pellet(), 31, 55);
        addObject(new Pellet(), 31, 57);
        addObject(new Pellet(), 45, 53);
        addObject(new Pellet(), 47, 53);
        addObject(new Pellet(), 49, 47);
        addObject(new Pellet(), 51, 47);
        addObject(new Pellet(), 51, 53);
        addObject(new Pellet(), 53, 9);
        addObject(new Pellet(), 53, 53);

        // Pastilhas especiais.
        populatePowerPellets();
    }

    /**
     * Cria as pastilhas especiais.
     */
    private void populatePowerPellets() {
        // Remove as pastilhas normais da posição das pastilhas especiais.
        removeObjects(getObjectsAt(3, 7, Pellet.class));
        removeObjects(getObjectsAt(3, 47, Pellet.class));
        removeObjects(getObjectsAt(53, 7, Pellet.class));
        removeObjects(getObjectsAt(53, 47, Pellet.class));

        // Pastilhas especiais.
        addObject(new PowerPellet(), 3, 7);
        addObject(new PowerPellet(), 3, 47);
        addObject(new PowerPellet(), 53, 7);
        addObject(new PowerPellet(), 53, 47);
    }

    /**
     * Cria linhas de pellets verticais.
     *
     * @param x célula de eixo X
     * @param y0 célula de eixo Y incial
     * @param y1 célula de eixo Y final
     */
    private void verticalPellets(int x, int y0, int y1) {
        // Ele começa no eixo y0 e vai até o eixo y1, pulando de 1 em 1.
        for (int i = y0; i < y1; i += 2) {
            addObject(new Pellet(), x, i);
        }
    }

    /**
     * Cria linhas de pellets horizontais.
     *
     * @param y célula de eixo Y
     * @param x0 célula de eixo X incial
     * @param x1 célula de eixo X final
     */
    private void horizontalPellets(int y, int x0, int x1) {
        // Ele começa no eixo x0 e vai até o eixo x1, pulando de 1 em 1.
        for (int i = x0; i < x1; i += 2) {
            addObject(new Pellet(), i, y);
        }
    }

    /**
     * Cria todas as paredes para criar o labirinto.
     */
    private void populateWalls() {
        // Cria as bordas, as quinas, a jail e os quadrados do labirinto.
        outerEdges();
        externalVertices();
        innerDefaultWalls();
        jail();
    }

    /**
     * Cria a cela, onde os Fanstasmas irão ficar presos no início do jogo.
     */
    private void jail() {
        // Cria um quadrado para a jail dos Fantasmas.
        squareWall(21, 25, 14, 8);
        squareWall(22, 26, 12, 6);
    }

    /**
     * Cria as bordas externas, para delimitar o mundo onde o PacMan, os
     * Fantasmas e as Pastilhas podem agir.
     */
    private void outerEdges() {
        // Paredes na posição vertical.
        verticalLine(1, 1, 19);
        verticalLine(1, 39, 49);
        verticalLine(1, 51, 61);
        verticalLine(11, 19, 27);
        verticalLine(11, 31, 39);
        verticalLine(45, 19, 27);
        verticalLine(45, 31, 39);
        verticalLine(55, 1, 19);
        verticalLine(55, 39, 49);
        verticalLine(55, 51, 61);

        // Paredes na posição horizontal.
        horizontalLine(1, 1, 27);
        horizontalLine(1, 29, 55);
        horizontalLine(19, 1, 11);
        horizontalLine(19, 45, 55);
        horizontalLine(27, -1, 11);
        horizontalLine(27, 45, 56);
        horizontalLine(31, -1, 11);
        horizontalLine(31, 45, 56);
        horizontalLine(39, 1, 11);
        horizontalLine(39, 45, 55);
        horizontalLine(61, 1, 55);
    }

    /**
     * Cria as paredes que representam as quinas externas do labirinto.
     */
    private void externalVertices() {
        // Paredes nas quinas do labirinto.
        addObject(new Wall(), 1, 1);
        addObject(new Wall(), 1, 19);
        addObject(new Wall(), 1, 39);
        addObject(new Wall(), 1, 61);
        addObject(new Wall(), 11, 19);
        addObject(new Wall(), 11, 27);
        addObject(new Wall(), 11, 31);
        addObject(new Wall(), 11, 39);
        addObject(new Wall(), 45, 19);
        addObject(new Wall(), 45, 27);
        addObject(new Wall(), 45, 31);
        addObject(new Wall(), 45, 39);
        addObject(new Wall(), 55, 1);
        addObject(new Wall(), 55, 19);
        addObject(new Wall(), 55, 39);
        addObject(new Wall(), 55, 61);
    }

    /**
     * Cria as paredes internas do labirinto.
     */
    private void innerDefaultWalls() {
        squareWall(0, 49, 5, 2);
        squareWall(5, 5, 6, 4);
        squareWall(5, 13, 6, 2);
        squareWall(5, 43, 6, 2);
        squareWall(5, 55, 18, 2);
        squareWall(9, 43, 2, 8);
        squareWall(15, 5, 8, 4);
        squareWall(15, 13, 2, 14);
        squareWall(15, 31, 2, 8);
        squareWall(15, 43, 8, 2);
        squareWall(15, 49, 2, 6);
        squareWall(17, 19, 6, 2);
        squareWall(21, 13, 14, 2);
        squareWall(21, 37, 14, 2);
        squareWall(21, 49, 14, 2);
        squareWall(27, 0, 2, 9);
        squareWall(27, 13, 2, 8);
        squareWall(27, 37, 2, 8);
        squareWall(21, 37, 14, 2);
        squareWall(27, 49, 2, 8);
        squareWall(33, 5, 8, 4);
        squareWall(33, 19, 6, 2);
        squareWall(33, 43, 8, 2);
        squareWall(33, 55, 18, 2);
        squareWall(39, 13, 2, 14);
        squareWall(39, 31, 2, 8);
        squareWall(39, 49, 2, 6);
        squareWall(45, 5, 6, 4);
        squareWall(45, 13, 6, 2);
        squareWall(45, 43, 2, 8);
        squareWall(45, 43, 6, 2);
        squareWall(51, 49, 5, 2);
    }

    /**
     * Cria linhas de paredes verticais.
     *
     * @param x célula de eixo X
     * @param y0 célula de eixo Y incial
     * @param y1 célula de eixo Y final
     */
    private void verticalLine(int x, int y0, int y1) {
        for (int i = y0 + 1; i < y1; i++) {
            addObject(new Wall(), x, i);
        }
    }

    /**
     * Cria linhas de paredes horizontais.
     *
     * @param y célula de eixo Y
     * @param x0 célula de eixo X incial
     * @param x1 célula de eixo X final
     */
    private void horizontalLine(int y, int x0, int x1) {
        for (int i = x0 + 1; i < x1; i++) {
            addObject(new Wall(), i, y);
        }
    }

    /**
     * Cria os quadrados, que serão usados para formar o labirinto.
     *
     * @param x0 eixo X incial
     * @param y0 eixo Y inicial
     * @param width largura do quadrado
     * @param height altura do quadrado
     */
    private void squareWall(int x0, int y0, int width, int height) {
        // Cria duas variáveis que recebem os valores dos eixos X final e
        // Y final.
        int xMax = x0 + width;
        int yMax = y0 + height;

        // Adiciona objetos do tipo Wall para criar as bordas dos quadrados.
        addObject(new Wall(), x0, y0);
        addObject(new Wall(), xMax, y0);
        addObject(new Wall(), x0, yMax);
        addObject(new Wall(), xMax, yMax);

        // Usa um laço para percorrer desde o X inicial até o X final.
        for (int i = x0 + 1; i < xMax; i++) {
            // Adiciona objetos do tipo Wall nos lados paralelos do quadrado.
            addObject(new Wall(), i, y0);
            addObject(new Wall(), i, yMax);
        }

        // Usa um laço para percorrer desde o Y inicial até o Y final
        for (int j = y0 + 1; j < yMax; j++) {
            // Adiciona objetos do tipo Wall nos lados paralelos do quadrado.
            addObject(new Wall(), x0, j);
            addObject(new Wall(), xMax, j);
            addObject(new Wall(), x0, y0);
            addObject(new Wall(), xMax, y0);
            addObject(new Wall(), x0, yMax);
            addObject(new Wall(), xMax, yMax);

            // Usa um laço para percorrer desde o X inicial até o X final.
            for (int i = x0 + 1; i < xMax; i++) {
                // Adiciona objetos do tipo Wall nos lados paralelos do quadrado.
                addObject(new Wall(), i, y0);
                addObject(new Wall(), i, yMax);
            }

            // Usa um laço para percorrer desde o Y inicial até o Y final
            for (j = y0 + 1; j < yMax; j++) {
                // Adiciona objetos do tipo Wall nos lados paralelos do quadrado.
                addObject(new Wall(), x0, j);
                addObject(new Wall(), xMax, j);
            }
        }
    }

    private void portal(int x0, int y0, int x1, int y1) {
        List<Character> characters = getObjects(Character.class);

        characters.forEach((character) -> {
            if (character.getX() == x0
                    && character.getY() == y0) {
                character.setLocation(x1, y1);
            } else if (character.getX() == x1
                    && character.getY() == y1) {
                character.setLocation(x0, y0);
            }
        });
    }

    /**
     * Finaliza o jogo.
     */
    private void end() {
        String msg = "Game Over!\n"
                + "Você fez \n"
                + Integer.toString(Score.getInstance().getPoints())
                + " pontos";
        ImageActor textGameOver = new ImageActor(new GreenfootImage(msg, 50, Color.WHITE, null));

        addObject(textGameOver, 28, 33);
        setPaintOrder(ImageActor.class);
        repaint();

        SoundPlayer.getInstance().playSound(SoundPlayer.PACMAN_ITERMISSION);
        Greenfoot.stop();
    }
}
