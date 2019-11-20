
import greenfoot.*;

/**
 * A classe Personagem providencia os movimentos e animações básicas dos
 * personagens.
 *
 * Os personagens são formados por 16x16 pixels.
 *
 * @author Raylson, Carlos, Weydson
 * @version 1.0
 */
public class Character extends Actor {

    // Direções
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;

    // Velocidades
    public static final int SLOWLEST = 0;
    public static final int SLOW = 1;
    public static final int NORMAL = 2;
    public static final int FAST = 3;

    // Velocidade.
    private int speed;

    // Direção que o personagem está encarando.
    private int direction;

    // Total de turns
    private int turns;

    // Conta os turns antes de mudar de sprite.
    private int tick = 0;

    // Quantidade de turns até o próximo sprite.
    private final int howManyTurns;

    /**
     * Cria um personagem que muda de sprite em de turnos em turnos\
     *
     * @param howManyTurns quantidade de turnos até a próximo sprite
     * @param speed velocidade do personagem
     * @param direction direção que o personagem começa encarando
     */
    public Character(int howManyTurns, int speed, int direction) {
        if (howManyTurns < 0) {
            this.howManyTurns = 0;
        } else {
            this.howManyTurns = howManyTurns;
        }

        setSpeed(speed);
        setDirection(direction);

        turns = 0;
    }

    /**
     * Modifica a velocidade com que o personagem se move pelo labirinto.
     *
     * @param speed SLOWLEST, SLOW, NORMAL, FAST
     */
    public final void setSpeed(int speed) {
        switch (speed) {
            case SLOWLEST:
                this.speed = SLOWLEST;

                break;

            case SLOW:
                this.speed = SLOW;

                break;

            case NORMAL:
                this.speed = NORMAL;

                break;

            case FAST:
                this.speed = FAST;

                break;

            default:
                this.speed = FAST;

                break;
        }
    }

    /**
     * Retorna a velocidade do personagem.
     *
     * @return Um inteiro entre [0..3].
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Muda a direção que o personagem está encarando.
     *
     * @param direction NORTH, SOUTH, EAST, WEST
     */
    public final void setDirection(int direction) {
        switch (direction) {
            case NORTH:
                this.direction = NORTH;

                break;

            case SOUTH:
                this.direction = SOUTH;

                break;

            case EAST:
                this.direction = EAST;

                break;

            case WEST:
                this.direction = WEST;

                break;

            default:
                this.direction = NORTH;

                break;
        }

        tick = 0;
    }

    /**
     * Informa a direção que o personagem está encarando.
     *
     * @return NORTH, SOUTH, EAST OU WEST.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Verifica se está no momento de mudar o sprite
     *
     * @return true se está no momento de mudar de sprite, false caso contrário.
     */
    public boolean timeToChangeSprite() {
        return tick == 0;
    }

    private boolean canMoveDirection(int[] x, int[] y) {
        World myWorld = getWorld();

        if (x.length != 3 && y.length != 3) {
            return false;
        }

        if (myWorld.getObjectsAt(x[0], y[0], Wall.class).size() > 0) {
            return false;
        }

        if (myWorld.getObjectsAt(x[1], y[1], Wall.class).size() > 0) {
            return false;
        }

        return myWorld.getObjectsAt(x[2], y[2], Wall.class).size() <= 0;
    }

    /**
     * Verifica se o personagem pode se mover na direção que está olhando.
     *
     * @param direction direção que deseja se mover
     * @return false caso haja obstáculos ou esteja fora do mundo, true caso
     * contrário.
     */
    public boolean canMove(int direction) {
        int x, y;
        int[] xArray, yArray;

        switch (direction) {
            case NORTH:
                x = getX();
                y = getY() - 2;
                xArray = new int[]{x - 1, x, x + 1};
                yArray = new int[]{y, y, y};

                return canMoveDirection(xArray, yArray);

            case SOUTH:
                x = getX();
                y = getY() + 2;
                xArray = new int[]{x + 1, x, x - 1};
                yArray = new int[]{y, y, y};

                return canMoveDirection(xArray, yArray);

            case EAST:
                x = getX() + 2;
                y = getY();
                xArray = new int[]{x, x, x};
                yArray = new int[]{y + 1, y, y - 1};

                return canMoveDirection(xArray, yArray);

            case WEST:
                x = getX() - 2;
                y = getY();
                xArray = new int[]{x, x, x};
                yArray = new int[]{y + 1, y, y - 1};

                return canMoveDirection(xArray, yArray);

            default:
                return true;
        }
    }

    /**
     * Se for possível move o personagem na direção que o personagem está
     * olhando.
     *
     */
    private void move() {
        if (!canMove(this.direction)) {
            return;
        }

        switch (direction) {
            case NORTH:
                setLocation(getX(), getY() - 1);
                break;

            case SOUTH:
                setLocation(getX(), getY() + 1);
                break;

            case EAST:
                setLocation(getX() + 1, getY());
                break;

            case WEST:
                setLocation(getX() - 1, getY());
                break;
        }
    }

    /**
     * Faz o personagem se mover na direção que encara. Tenha certeza de chamar
     * esse método por último dentro do método act() da sua subclasse.
     */
    public void act() {
        if (speed > turns % 3) {
            move();
        }

        turns++;
        tick++;

        if (tick >= howManyTurns - 1) {
            tick = -1;
        }
    }
}
