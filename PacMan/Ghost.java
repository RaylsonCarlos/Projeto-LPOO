
import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A Classe Ghost representa os fantasmas do labirinto.
 *
 * @author Raylson, Carlos, Weydson
 * @version 1.0
 */
public class Ghost extends Characters {

    // Cores dos fantasmas
    public final static int RED = 0;
    public final static int PINK = 1;
    public final static int BLUE = 2;
    public final static int BROWN = 3;

    // Variável para armazenar a cor do Ghost
    private int color;

    // Estados dos fantasmas
    public final static int ALIVE = 0;
    public final static int DEAD = 1;
    public final static int FEAR = 2;
    public final static int RECOVERING = 3;

    // Variável para armazenar o estado atual do Ghost
    private int status;

    // Timer interno de estado do Ghost
    private long timer;

    // Tempo de efeito das pastilhas
    private final int effectiveTime = 3000;

    // Sinaliza se o Ghost pode sair da cela
    boolean canLeave = false;

    // Armazena o deslocamento dentro do array de sprites do Ghost
    private int offset = 0;

    // Sprites dos fantasmas
    private static GreenfootImage[][] spritesRed;
    private static GreenfootImage[][] spritesPink;
    private static GreenfootImage[][] spritesBlue;
    private static GreenfootImage[][] spritesBrown;
    private static GreenfootImage[][] spritesFear;
    private static GreenfootImage[][] spritesDead;
    private static GreenfootImage[][][] sprites;

    // Velocidade padrão dos fantasmas
    private static final int defaultSpeed = 3;

    /**
     * Cria um Ghost com velocidade padrão
     *
     * @param color do Ghost: RED, PINK, BLUE ou BROWN.
     */
    public Ghost(int color) {
        super(defaultSpeed);
        spritesRed = new GreenfootImage[][]{
            {new GreenfootImage("ghost_red_east_0.png"), new GreenfootImage("ghost_red_east_1.png")},
            {new GreenfootImage("ghost_red_west_0.png"), new GreenfootImage("ghost_red_west_1.png")},
            {new GreenfootImage("ghost_red_north_0.png"), new GreenfootImage("ghost_red_north_1.png")},
            {new GreenfootImage("ghost_red_south_0.png"), new GreenfootImage("ghost_red_south_1.png")}
        };
        spritesPink = new GreenfootImage[][]{
            {new GreenfootImage("ghost_pink_east_0.png"), new GreenfootImage("ghost_pink_east_1.png")},
            {new GreenfootImage("ghost_pink_west_0.png"), new GreenfootImage("ghost_pink_west_1.png")},
            {new GreenfootImage("ghost_pink_north_0.png"), new GreenfootImage("ghost_pink_north_1.png")},
            {new GreenfootImage("ghost_pink_south_0.png"), new GreenfootImage("ghost_pink_south_1.png")}
        };
        spritesBlue = new GreenfootImage[][]{
            {new GreenfootImage("ghost_blue_east_0.png"), new GreenfootImage("ghost_blue_east_1.png")},
            {new GreenfootImage("ghost_blue_west_0.png"), new GreenfootImage("ghost_blue_west_1.png")},
            {new GreenfootImage("ghost_blue_north_0.png"), new GreenfootImage("ghost_blue_north_1.png")},
            {new GreenfootImage("ghost_blue_south_0.png"), new GreenfootImage("ghost_blue_south_1.png")}
        };
        spritesBrown = new GreenfootImage[][]{
            {new GreenfootImage("ghost_brown_east_0.png"), new GreenfootImage("ghost_brown_east_1.png")},
            {new GreenfootImage("ghost_brown_west_0.png"), new GreenfootImage("ghost_brown_west_1.png")},
            {new GreenfootImage("ghost_brown_north_0.png"), new GreenfootImage("ghost_brown_north_1.png")},
            {new GreenfootImage("ghost_brown_south_0.png"), new GreenfootImage("ghost_brown_south_1.png")}
        };
        spritesFear = new GreenfootImage[][]{
            {new GreenfootImage("frightened_0.png"),
                new GreenfootImage("frightened_1.png"),
                new GreenfootImage("recovering_1.png"),
                new GreenfootImage("recovering_0.png")}
        };
        spritesDead = new GreenfootImage[][]{
            {new GreenfootImage("ghost_eye_east.png"),
                new GreenfootImage("ghost_eye_west.png"),
                new GreenfootImage("ghost_eye_north.png"),
                new GreenfootImage("ghost_eye_south.png")}
        };
        sprites = new GreenfootImage[][][]{spritesRed, spritesPink, spritesBlue, spritesBrown, spritesFear, spritesDead};

        if (color != RED && color != PINK && color != BLUE && color != BROWN) {
            this.color = RED;
        } else {
            this.color = color;
        }
        status = ALIVE;
        setSprite();
    }

    /**
     * Informa se o Ghost pode ser liberado da cela.
     *
     * @return true caso deva ser liberado da cela, false caso contrário
     */
    public boolean getCanLeave() {
        return canLeave;
    }

    /**
     * Altera a variavel que informa se o Ghost pode ser liberado da cela.
     *
     * @param canLeave novo valor
     */
    public void setCanLeave(boolean canLeave) {
        this.canLeave = canLeave;
    }

    /**
     * Informa o estado do Ghost.
     *
     * @return um inteiro que representa um dos estados: ALIVE, DEAD, FEAR ou
     * RECOVERING.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Modifica o estado do Characters .
     *
     * @param status dos estados possíveis: ALIVE, DEAD, FEAR ou RECOVERING.
     * Parâmetro inválido gera o estado ALIVE.
     */
    private void setStatus(int status) {
        if (status < 0 || status > 3) {
            this.status = ALIVE;
        } else {
            this.status = status;
        }
    }

    /**
     * Muda o sprite do Ghost.
     *
     */
    private void setSprite() {
        switch (status) {
            case ALIVE:
                switch (getDirection()) {
                    case Characters.NORTH:
                        setImage(sprites[color][2][offset % 2]);
                        break;

                    case Characters.SOUTH:
                        setImage(sprites[color][3][offset % 2]);
                        break;

                    case Characters.EAST:
                        setImage(sprites[color][0][offset % 2]);
                        break;

                    case Characters.WEST:
                        setImage(sprites[color][1][offset % 2]);
                        break;
                }
                break;

            case FEAR:
                setImage(sprites[4][0][offset % 2]);
                break;

            case RECOVERING:
                setImage(sprites[4][0][offset % 4]);
                break;

            case DEAD:
                switch (getDirection()) {
                    case Characters.NORTH:
                        setImage(sprites[5][0][2]);
                        break;

                    case Characters.SOUTH:
                        setImage(sprites[5][0][3]);
                        break;

                    case Characters.EAST:
                        setImage(sprites[5][0][0]);
                        break;

                    case Characters.WEST:
                        setImage(sprites[5][0][1]);
                        break;
                }
                break;

            default:
                // Do nothing...
                break;
        }
    }

    /**
     * Informa a direção oposto a que o Ghost está olhando.
     *
     * @param direction a direção que o Ghost está olhando
     * @return a direção oposta
     */
    private int oppositeDirection(int direction) {
        int oppositeDirection = Characters.NORTH;

        switch (direction) {
            case Characters.NORTH:
                oppositeDirection = Characters.SOUTH;
                break;

            case Characters.SOUTH:
                oppositeDirection = Characters.NORTH;
                break;

            case Characters.EAST:
                oppositeDirection = Characters.WEST;
                break;

            case Characters.WEST:
                oppositeDirection = Characters.EAST;
                break;
        }

        return oppositeDirection;
    }

    /**
     * Informa se o Ghost está dentro da cela do labirinto.
     *
     * @return true se o Ghost está dentro da cela do labirinto, false caso
     * contrário.
     */
    private boolean isJailed() {
        int x = getX();
        int y = getY();

        return x > 22 && x < 34 && y > 26 && y < 32;
    }

    /**
     * Define como o Ghost deve se movimentar se estiver preso na cela do
     * labirinto
     *
     */
    private void jailedRoute() {
        if (canLeave) {
            int x = getX();

            if (x != 28) {
                setSpeed(defaultSpeed);

                if (x < 28 && getDirection() != Characters.EAST) {
                    changeDirection(Characters.EAST);
                } else if (x > 28 && getDirection() != Characters.WEST) {
                    changeDirection(Characters.WEST);
                }
            } else {
                if (getDirection() != Characters.NORTH) {
                    changeDirection(Characters.NORTH);
                }

                setLocation(x, getY() - 1);
            }
        } else {
            setSpeed(defaultSpeed - 1);

            if (!canMoveNorth()) {
                changeDirection(Characters.SOUTH);
            }

            if (!canMoveSouth()) {
                changeDirection(Characters.NORTH);
            }
        }
    }

    /**
     * Define como o Ghost deve se movimentar se estiver fora da cela.
     *
     */
    private void defaultRoute() {
        List<Integer> routes = new ArrayList<>();
        int direction = getDirection();
        int oppositeDirection = oppositeDirection(direction);

        if (canMoveNorth()) {
            routes.add(Characters.NORTH);
        }

        if (canMoveSouth()) {
            routes.add(Characters.SOUTH);
        }

        if (canMoveEast()) {
            routes.add(Characters.EAST);
        }

        if (canMoveWest()) {
            routes.add(Characters.WEST);
        }

        if (!routes.isEmpty()) {
            if (routes.size() >= 2) {
                routes.remove(Integer.valueOf(oppositeDirection));
            }

            int rand = Greenfoot.getRandomNumber(routes.size());
            changeDirection(routes.get(rand));
        }
    }

    /**
     * Controla o efeito das pastilhas.
     *
     */
    private void controlPelletsEffect() {
        if (status == Ghost.RECOVERING) {
            if ((System.currentTimeMillis() - timer) > effectiveTime) {
                setAlive();
            }
        }
        if (status == Ghost.FEAR) {
            if ((System.currentTimeMillis() - timer) > effectiveTime) {
                setRecovering();
            }
        }
    }

    /**
     * Inicia o efeito das pastilhas.
     *
     */
    public void pelletEffect() {
        setFear();
    }

    /**
     * Altera o estado para FEAR.
     *
     */
    public void setFear() {
        if (status == DEAD) {
            return;
        }

        status = Ghost.FEAR;
        timer = System.currentTimeMillis();
        setSpeed(defaultSpeed - 1);
    }

    /**
     * Altera o estado para RECOVERING.
     *
     */
    public void setRecovering() {
        status = Ghost.RECOVERING;
        timer = System.currentTimeMillis();
        setSpeed(defaultSpeed - 1);
    }

    /**
     * Altera o estado para ALIVE.
     *
     */
    public void setAlive() {
        status = Ghost.ALIVE;
        setSpeed(defaultSpeed);
    }

    /**
     * Altera o estado para DEAD.
     *
     */
    public void setDead() {
        status = Ghost.DEAD;
        setSpeed(defaultSpeed);
    }

    /**
     * Rota do Ghost no estado ALIVE.
     */
    private void aliveRoute() {
        if (isJailed()) {
            jailedRoute();
        } else {
            defaultRoute();
        }
    }

    /**
     * Calcula a distância entre dois pontos.
     *
     * @param x0 eixo x do ponto 0
     * @param y0 eixo y do ponto 0
     * @param x1 eixo x do ponto 1
     * @param y1 eixo y do ponto 1
     * @return distância entre esses dois pontos
     */
    private int dist(int x0, int y0, int x1, int y1) {
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;
        return (deltaX * deltaX) + (deltaY * deltaY);
    }

    /**
     * Rota do Ghost no estado DEAD. O Ghost se desloca até o sistema
     * prisional e sai recuperado (glória a deuxxx!).
     */
    private void deadRoute() {
        //(23,28) é a coordenada da entrada da cela.
        int x = getX();
        int y = getY();

        //põe o Ghost pra dentro da cela caso ele esteja na entrada da cela.
        if (x == 28 && y <= 30 && y >= 23) {
            switch (y) {
                case 23:
                    setSpeed(0);
                    setLocation(x, y + 1);
                    break;

                case 30:
                    setAlive();
                    break;

                default:
                    setLocation(x, y + 1);
            }

            return;
        }

        //Calcula a direção que deve seguir para a entrada da cela.
        List<Integer> routes = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();

        if (canMoveNorth() && getDirection() != Characters.SOUTH) {
            routes.add(Characters.NORTH);
            distance.add(dist(x, y - 1, 23, 28));
        }

        if (canMoveSouth() && getDirection() != Characters.NORTH) {
            routes.add(Characters.SOUTH);
            distance.add(dist(x, y + 1, 23, 28));
        }

        if (canMoveEast() && getDirection() != Characters.WEST) {
            routes.add(Characters.EAST);
            distance.add(dist(x + 1, y, 23, 28));
        }

        if (canMoveWest() && getDirection() != Characters.EAST) {
            routes.add(Characters.WEST);
            distance.add(dist(x - 1, y, 23, 28));
        }

        // caso não haja uma direção que lhe seja mais favorável continua seguindo a mesma direção.
        // caso esteja bloqueado procura outra direção possível.
        if (distance.size() <= 0) {
            if (!canMove(getDirection())) {
                if (canMoveNorth()) {
                    changeDirection(Characters.NORTH);
                } else if (canMoveSouth()) {
                    changeDirection(Characters.SOUTH);
                } else if (canMoveEast()) {
                    changeDirection(Characters.EAST);
                } else if (canMoveWest()) {
                    changeDirection(Characters.WEST);
                }
            }
            return;
        }

        int indexMin = 0;

        for (int i = 0; i < distance.size(); i++) {
            if (distance.get(indexMin) > distance.get(i)) {
                indexMin = i;
            }
        }

        changeDirection(routes.get(indexMin));
    }

    /**
     * Rota se o Ghost estiver em estado FEAR.
     */
    private void fearRout() {
        World world = getWorld();
        PacMan pacman = (PacMan) world.getObjects(PacMan.class).get(0);

        //componentes do vetor desse Ghost até o pacman
        int x = getX();
        int y = getY();
        int pacmanX = pacman.getX();
        int pacmanY = pacman.getY();

        //lista das possíveis rotas a seguir e de suas respectivas distancias até o pacman.
        List<Integer> rotas = new ArrayList<Integer>();
        List<Integer> distancias = new ArrayList<Integer>();

        if (canMoveNorth() && getDirection() != Characters.SOUTH) {//
            rotas.add(Characters.NORTH);
            distancias.add(dist(x, y - 1, pacmanX, pacmanY));
        }
        if (canMoveSouth() && getDirection() != Characters.NORTH) {// 
            rotas.add(Characters.SOUTH);
            distancias.add(dist(x, y + 1, pacmanX, pacmanY));
        }
        if (canMoveEast() && getDirection() != Characters.WEST) {// 
            rotas.add(Characters.EAST);
            distancias.add(dist(x + 1, y, pacmanX, pacmanY));
        }
        if (canMoveWest() && getDirection() != Characters.EAST) {// 
            rotas.add(Characters.WEST);
            distancias.add(dist(x - 1, y, pacmanX, pacmanY));
        }

        //caso não haja uma direção que lhe seja mais favorável continua seguindo a mesma direção.
        //caso esteja bloqueado procura outra direção possível.
        if (distancias.size() <= 0) {
            if (!canMove(getDirection())) {
                if (canMoveNorth()) {
                    changeDirection(Characters.NORTH);
                } else if (canMoveSouth()) {
                    changeDirection(Characters.SOUTH);
                } else if (canMoveEast()) {
                    changeDirection(Characters.EAST);
                } else if (canMoveWest()) {
                    changeDirection(Characters.WEST);
                }
            }
            return;
        }

        //determina a direção que mais se afasta do pacman.
        int indexMax = 0;
        for (int i = 0; i < distancias.size(); i++) {
            if (distancias.get(indexMax) < distancias.get(i)) {
                indexMax = i;
            }
        }

        //escolhe essa direção.
        changeDirection(rotas.get(indexMax));
    }

    /**
     * Rota se o Ghost estiver em estado RECOVERING.
     *
     */
    private void recoveringRoute() {
        fearRout();
    }

    /**
     * Faz o Ghost se mover e mudar os sprites do Characters
     *
     */
    public void act() {
        controlPelletsEffect();

        switch (status) {
            case ALIVE:
                aliveRoute();
                break;

            case FEAR:
                fearRout();
                break;

            case RECOVERING:
                recoveringRoute();
                break;

            case DEAD:
                deadRoute();
                break;
        }

        if (timeToChangeSprite()) {
            setSprite();
            offset++;
        }
        super.act();
    }
}
