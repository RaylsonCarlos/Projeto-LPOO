
import greenfoot.*;
import java.util.List;
import java.util.Iterator;

/**
 * A classe PacMan representa o Characters do usuário no jogo, controlado pelo
 * teclado
 *
 * @author Raylson, Carlos, Weydson
 * @version 1.0
 */
public class PacMan extends Characters {

    // Variável para controlar o deslocamento no array de sprites.
    private int offset = 0;

    // Array para controlar a ordem de exibição dos sprites.
    private final static int[] animationOffset = {0, 1, 2, 1};

    // Armazena a quantidade de turnos que já se passaram desde que o jogador
    // tentou mudar de direção.
    private int counterDirection = 0;

    // Controla em até quantos turnos o comando do jogador de mudar de direção
    // será executado (se possível). Valores maiores suavizam a tomada de
    // direção do Characters, valores menores diminuem a janela de tempo para o
    // jogador apertar das teclas.
    private static final int delayDirection = 6;

    // Armazena o última direção escolhida pelo jogador
    private int possibleDirection = Characters.WEST;

    // Variável para controle do tempo do efeito de pastilha especial
    private long timer;

    // Tempo de duração do efeito da pastilha especial
    private final int effectiveTime = 6000;

    // Variavel que controla o número de ghosts comidos no efeito de uma
    // pastilha especial
    private int countEatGhosts;

    // Sprites do Pacman
    private static GreenfootImage[] spritesNORTH;
    private static GreenfootImage[] spritesSOUTH;
    private static GreenfootImage[] spritesEAST;
    private static GreenfootImage[] spritesWEST;
    private static GreenfootImage[] spritesDead;

    // Sprites da pontuação
    private static GreenfootImage[] spritesPoints;

    // Velocidade padrão dos ghosts
    private static final int defaultSpeed = 3;

    /**
     * Inicializa o Pacman com velocidade 3, e direção WEST
     *
     */
    public PacMan() {
        super(defaultSpeed);
        inicializaSprites();
        changeDirection(Characters.WEST);
        setImage(spritesWEST[0]);
    }

    /**
     * Inicializa as sprites.
     */
    private void inicializaSprites() {
        spritesNORTH = new GreenfootImage[]{
            new GreenfootImage("north_0.png"),
            new GreenfootImage("north_1.png"),
            new GreenfootImage("north_2.png")
        };
        spritesSOUTH = new GreenfootImage[]{
            new GreenfootImage("south_0.png"),
            new GreenfootImage("south_1.png"),
            new GreenfootImage("south_2.png")
        };
        spritesEAST = new GreenfootImage[]{
            new GreenfootImage("east_0.png"),
            new GreenfootImage("east_1.png"),
            new GreenfootImage("east_2.png")
        };
        spritesWEST = new GreenfootImage[]{
            new GreenfootImage("west_0.png"),
            new GreenfootImage("west_1.png"),
            new GreenfootImage("west_2.png")
        };
        spritesDead = new GreenfootImage[]{
            new GreenfootImage("dead_0.png"),
            new GreenfootImage("dead_1.png"),
            new GreenfootImage("dead_2.png"),
            new GreenfootImage("dead_3.png"),
            new GreenfootImage("dead_4.png"),
            new GreenfootImage("dead_5.png"),
            new GreenfootImage("dead_6.png"),
            new GreenfootImage("dead_7.png"),
            new GreenfootImage("dead_8.png"),
            new GreenfootImage("dead_9.png"),
            new GreenfootImage("dead_10.png")
        };
        spritesPoints = new GreenfootImage[]{
            new GreenfootImage("points_200.png"),
            new GreenfootImage("points_400.png"),
            new GreenfootImage("points_800.png"),
            new GreenfootImage("points_1600.png")
        };
    }

    /**
     * Consome os objetos tipo {@link Pellet} que estejam num raio de 1 célula
     * return true se o pac-man encontrou alguma comida
     */
    private int foundFood() {
        int points = 0;
        List<Pellet> food = getObjectsInRange(1, Pellet.class);

        if (food.size() > 0) {
            Iterator it = food.iterator();

            while (it.hasNext()) {
                if (it.next() instanceof SpecialPellet) {
                    if ((System.currentTimeMillis() - timer) > effectiveTime) {
                        countEatGhosts = 0;
                    }

                    points += 50;
                    timer = System.currentTimeMillis();
                    foundSpecialPellet();
                } else {
                    points += 10;
                }
            }

            getWorld().removeObjects(food);
        }

        return points;
    }

    /**
     * Método chamado quando o pacman encontra uma pastilha especial
     */
    private void foundSpecialPellet() {
        List<Ghost> ghosts = getWorld().getObjects(Ghost.class);

        ghosts.forEach((fan) -> {
            fan.pelletEffect();
        });

        SoundPlayer.getInstance().stop();
        SoundPlayer.getInstance().playBackgroundFrightened();
    }

    /**
     * Verifica o teclado em busca de direções para cima, baixo, esquerda ou
     * direita.
     */
    private void verificarTeclado() {
        String key = Greenfoot.getKey();
        if (key != null) {
            offset = 1;
            if (key.equals("up")) {
                possibleDirection = Characters.NORTH;
                counterDirection = 0;
            }
            if (key.equals("down")) {
                possibleDirection = Characters.SOUTH;
                counterDirection = 0;
            }
            if (key.equals("right")) {
                possibleDirection = Characters.EAST;
                counterDirection = 0;
            }
            if (key.equals("left")) {
                possibleDirection = Characters.WEST;
                counterDirection = 0;
            }
        }
    }

    /**
     * Verifica a colisão com ghosts.
     */
    private void colisaoComFantasmas() throws InterruptedException {

        World world = getWorld();
        List<Ghost> ghosts = getObjectsInRange(2, Ghost.class);

        //Lista vazia de ghosts 
        if (ghosts.size() <= 0) {
            return;
        }

        int points = 0;

        for (Ghost fan : ghosts) {
            switch (fan.getStatus()) {
                case Ghost.ALIVE:
                    fan.setImage("blank_image.png");
                    SoundPlayer.getInstance().stop();
                    Thread.sleep(500);
                    getWorld().repaint();
                    dead();
                    return;

                case Ghost.RECOVERING:
                    this.setImage("blank_image.png");
                    points = pontuacaoFantasma(fan);
                    GameController.score(points);
                    getWorld().repaint();
                    SoundPlayer.getInstance().playEffectGhostEaten();
                    timer += 500;
                    Thread.sleep(500);
                    fan.setDead();
                    break;

                case Ghost.FEAR:
                    this.setImage("blank_image.png");
                    points = pontuacaoFantasma(fan);
                    GameController.score(points);
                    getWorld().repaint();
                    SoundPlayer.getInstance().playEffectGhostEaten();
                    timer += 500;
                    Thread.sleep(500);
                    fan.setDead();
                    break;
            }
        }
    }

    private int pontuacaoFantasma(Ghost fan) {
        fan.setImage(spritesPoints[countEatGhosts]);

        if (countEatGhosts < 3) {
            countEatGhosts++;
            return ((int) Math.pow(2, countEatGhosts)) * 100;
        } else {
            return ((int) Math.pow(2, countEatGhosts + 1)) * 100;
        }
    }

    public void dead() throws InterruptedException {
        SoundPlayer.getInstance().playEffectPacmanDeath();

        for (int i = 0; i < 11; i++) {
            setImage(spritesDead[i]);
            getWorld().repaint();
            Thread.sleep(150);
        }

        Thread.sleep(1000);
        ((PacManWorld) getWorld()).resetar(false);
    }

    /**
     * Faz o pac-man agir: consome objetos pastilha, aplica efeito sonoro,
     * verifica mudança de direção, move e muda os sprites do Characters.
     */
    @Override
    public void act() {
        int points = foundFood();
        GameController.score(points);

        if (points > 0) {
            SoundPlayer.getInstance().playEffectPillEaten();
        }

        verificarTeclado();

        if (counterDirection < delayDirection) {
            if (canMove(possibleDirection)) {
                changeDirection(possibleDirection);
            } else {
                counterDirection++;
            }
        }

        if (timeToChangeSprite()) {
            switch (getDirection()) {
                case Characters.NORTH:
                    setImage(spritesNORTH[animationOffset[offset % 4]]);
                    break;
                case Characters.SOUTH:
                    setImage(spritesSOUTH[animationOffset[offset % 4]]);
                    break;
                case Characters.EAST:
                    setImage(spritesEAST[animationOffset[offset % 4]]);
                    break;
                case Characters.WEST:
                    setImage(spritesWEST[animationOffset[offset % 4]]);
                    break;
            }
            offset++;
        }

        try {
            colisaoComFantasmas();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.act();
    }
}
