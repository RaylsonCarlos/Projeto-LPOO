
import greenfoot.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * A classe PacMan representa o personagem do usuário no jogo, controlado pelo
 * teclado
 *
 * @author Raylson, Carlos, Weydson
 * @version 1.0
 */
public class PacMan extends Character implements Subject {

    private ArrayList<Observer> observers;

    private int points;

    // Controla o deslocamento no array de sprites.
    private int offset = 0;

    // Array para controlar a ordem de exibição dos sprites.
    private final int[] animationOffset = {0, 1, 2, 1};

    // Quantidade de turnos passados desde a última mudança de direção.
    private int counterDirection = 0;

    // Tempo de espera na mudança de direção do pacman.
    private final int delayDirection = 6;

    // Última direção escolhida pelo jogador.
    private int possibleDirection = Character.WEST;

    // Tempo atual de efeito da pastilha especial.
    private long timer;

    // Tempo de duração do efeito da pastilha especial
    private final int effectiveTime = 6000;

    // Número de fantasmas comidos.
    private int countEatGhosts;

    // Sprites do Pacman
    private static GreenfootImage[] spritesNORTH;
    private static GreenfootImage[] spritesSOUTH;
    private static GreenfootImage[] spritesEAST;
    private static GreenfootImage[] spritesWEST;
    private static GreenfootImage[] spritesDead;

    // Sprites dos pontos
    private static GreenfootImage[] spritesPoints;

    // Velocidade padrão do pacman
    private static final int DEFAULT_SPEED = 3;

    /**
     * Inicializa o Pacman com velocidade 3, e direção WEST
     *
     */
    public PacMan() {
        super(DEFAULT_SPEED, DEFAULT_SPEED, Character.WEST);
        initSprites();
        setImage(spritesWEST[0]);

        observers = new ArrayList<>();
    }

    /**
     * Faz o pac-man agir: consome objetos pastilha, aplica efeito sonoro,
     * verifica mudança de direção, move e muda os sprites do Character.
     */
    @Override
    public void act() {
        foundFood();

        if (points > 0) {
            SoundPlayer.getInstance().playSound(SoundPlayer.PELLET_EATEN);
        }

        verifyKeyboard();

        if (counterDirection < delayDirection) {
            if (canMove(possibleDirection)) {
                setDirection(possibleDirection);
            } else {
                counterDirection++;
            }
        }

        if (timeToChangeSprite()) {
            switch (getDirection()) {
                case Character.NORTH:
                    setImage(spritesNORTH[animationOffset[offset % 4]]);
                    break;
                case Character.SOUTH:
                    setImage(spritesSOUTH[animationOffset[offset % 4]]);
                    break;
                case Character.EAST:
                    setImage(spritesEAST[animationOffset[offset % 4]]);
                    break;
                case Character.WEST:
                    setImage(spritesWEST[animationOffset[offset % 4]]);
                    break;
            }
            offset++;
        }

        ghostCollisions();

        super.act();
    }

    /**
     * Reproduz a morte do pacman, desenhando os sprites e resetando o mundo.
     */
    public void dead() {
        try {
            SoundPlayer.getInstance().playSound(SoundPlayer.PACMAN_DEATH);

            for (int i = 0; i < spritesDead.length; i++) {
                setImage(spritesDead[i]);
                getWorld().repaint();
                Thread.sleep(150);
            }

            Thread.sleep(1000);
            ((PacManWorld) getWorld()).reset(false);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inicializa os sprites.
     */
    private void initSprites() {
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
     * Consome as pastilhas que estejam num raio de 1 célula
     *
     * @return o total de pontos
     */
    private void foundFood() {
        List<Pellet> pellets = getObjectsInRange(1, Pellet.class);
        int pointsLocal = 0;

        // Verifica se existe alguma pastilha próxima ao pacman.
        if (pellets.size() > 0) {
            Iterator it = pellets.iterator();

            while (it.hasNext()) {
                // Verifica se a pastilha é uma pastilha especial.
                if (it.next() instanceof PowerPellet) {
                    if ((System.currentTimeMillis() - timer) > effectiveTime) {
                        countEatGhosts = 0;
                    }

                    pointsLocal += 50;
                    timer = System.currentTimeMillis();
                    foundPowerPellet();
                } else {
                    pointsLocal += 10;
                }
            }

            // Remove as pastilhas do mundo.
            getWorld().removeObjects(pellets);
        }

        addPoints(pointsLocal);
    }

    /**
     * Método chamado quando o pacman encontra uma pastilha especial
     */
    private void foundPowerPellet() {
        List<Ghost> ghosts = getWorld().getObjects(Ghost.class);

        ghosts.forEach((ghost) -> {
            ghost.pelletEffect();
        });

        SoundPlayer.getInstance().stopBackgroundSound();
        SoundPlayer.getInstance().playSound(SoundPlayer.BACKGROUND_FRIGHTENED);
    }

    /**
     * Verifica se o usuário está utilizando alguma tecla de movimento do jogo.
     */
    private void verifyKeyboard() {
        String key = Greenfoot.getKey();

        if (key != null) {
            offset = 1;
            switch (key) {
                case "up":
                    possibleDirection = Character.NORTH;
                    counterDirection = 0;
                    break;
                case "down":
                    possibleDirection = Character.SOUTH;
                    counterDirection = 0;
                    break;
                case "right":
                    possibleDirection = Character.EAST;
                    counterDirection = 0;
                    break;
                case "left":
                    possibleDirection = Character.WEST;
                    counterDirection = 0;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Gerencia a colisão com fantasmas.
     */
    private void ghostCollisions() {
        try {
            World world = getWorld();
            List<Ghost> ghosts = getObjectsInRange(2, Ghost.class);
            int pointsLocal = 0;

            if (ghosts.size() <= 0) {
                return;
            }

            for (Ghost ghost : ghosts) {
                switch (ghost.getStatus()) {
                    case Ghost.ALIVE:
                        ghost.setImage("blank_image.png");
                        SoundPlayer.getInstance().stopBackgroundSound();
                        Thread.sleep(500);
                        getWorld().repaint();
                        dead();
                        return;

                    case Ghost.RECOVERING:
                        this.setImage("blank_image.png");
                        pointsLocal = ghostPoints(ghost);
                        addPoints(pointsLocal);
                        getWorld().repaint();
                        SoundPlayer.getInstance().playSound(SoundPlayer.GHOST_EATEN);
                        timer += 500;
                        Thread.sleep(500);
                        ghost.setDead();
                        break;

                    case Ghost.FEAR:
                        this.setImage("blank_image.png");
                        pointsLocal = ghostPoints(ghost);
                        addPoints(pointsLocal);
                        getWorld().repaint();
                        SoundPlayer.getInstance().playSound(SoundPlayer.GHOST_EATEN);
                        timer += 500;
                        Thread.sleep(500);
                        ghost.setDead();
                        break;
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Calcular a pontuação ganha ao comer fanstasmas.
     *
     * @param ghost o fanstasma comido
     * @return a pontuação ganha
     */
    private int ghostPoints(Ghost ghost) {
        ghost.setImage(spritesPoints[countEatGhosts]);

        if (countEatGhosts < 3) {
            countEatGhosts++;
            return ((int) Math.pow(2, countEatGhosts)) * 100;
        } else {
            return ((int) Math.pow(2, countEatGhosts + 1)) * 100;
        }
    }

    private void addPoints(int points) {
        this.points += points;
        notifyObservers();
        this.points = 0;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach((observer) -> {
            observer.update(points);
        });
    }

}
