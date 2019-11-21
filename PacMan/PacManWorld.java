
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
    private MazeFactory maze;

    private PacMan pacman;
    private Ghost blinky;
    private Ghost pinky;
    private Ghost inky;
    private Ghost clyde;

    /**
     * Cria o cenário do mundo, define a velocidade padrão, estabelece a imagem
     * de fundo padrão e cria os objetos para as paredes, ghosts, pellets e o
     * PacMan.
     *
     * @param size tamanho do mundo
     */
    public PacManWorld(int size) {
        super(57, 68, size / 4);

        maze = new DefaultMaze(this);

        // Cria o plano de fundo do mundo.
        setBackground(background);

        // Cria as paredes do mundo.
        maze.createWall();
        maze.createPellet();
        maze.createPortal();

        pacman = new PacMan();
        blinky = new Ghost(Ghost.RED);
        pinky = new Ghost(Ghost.PINK);
        inky = new Ghost(Ghost.BLUE);
        clyde = new Ghost(Ghost.BROWN);

        pacman.registerObserver(maze.getPortal());
        blinky.registerObserver(maze.getPortal());
        pinky.registerObserver(maze.getPortal());
        inky.registerObserver(maze.getPortal());
        clyde.registerObserver(maze.getPortal());

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
                    GameController.end();

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
                maze.createPellet();
            }

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

        // Coloca os fantasmas dentro da cela.
        addObject(pinky, 28, 30);
        addObject(inky, 32, 30);
        addObject(clyde, 24, 30);
        addObject(blinky, 28, 23);

        // Informa que os fantasmas blinky e pinky podem sair.
        blinky.setCanLeave(true);
        pinky.setCanLeave(true);
    }

}
