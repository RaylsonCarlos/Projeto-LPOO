
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

    // Velocidade.
    private int speed = 3;

    // Direção que o personagem está encarando.
    private int direction;

    // Total de turns
    private int turns;

    // Conta os turns antes de mudar de sprite.
    private int tick = 0;

    // Quantidade de turns até o próximo sprite.
    private int howManyTurns;

    /**
     * Cria um personagem que muda de sprite em howManyTurns turns e com a
 direção norte
     *
     * @param howManyTurns A quantidade de turns até a próximo sprite
     */
    public Character(int howManyTurns) {
        turns = 0;
        direction = NORTH;

        if (howManyTurns < 0) {
            this.howManyTurns = 0;
        } else {
            this.howManyTurns = howManyTurns;
        }
    }

    /**
     * Modifica a velocidade com que o personagem se move pelo labirinto.
     *
     * @param speed assume valores inteiros entre [0..3].
     */
    public void setSpeed(int speed) {
        if (speed > 3 || speed < 0) {
            this.speed = 3;
        } else {
            this.speed = speed;
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
     * @param direction NORTH, SOUTH, EAST ou WEST
     */
    public void changeDirection(int direction) {
        if (this.direction == direction) {
            return;
        }

        this.direction = direction;
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

    /**
     * Verifica se o personagem pode se mover na direção NORTH.
     *
     * @return True se é possível se mover, false caso contrário.
     */
    public boolean canMoveNorth() {
        World myWorld = getWorld();
        int x = getX();
        // Verificando duas células acima
        int y = getY() - 2;

        if (myWorld.getObjectsAt(x - 1, y, Wall.class).size() > 0) {
            return false;
        }

        if (myWorld.getObjectsAt(x, y, Wall.class).size() > 0) {
            return false;
        }

        return myWorld.getObjectsAt(x + 1, y, Wall.class).size() <= 0;
    }

    /**
     * Verifica se o personagem pode se mover na direção SOUTH.
     *
     * @return True se é possível se mover, false caso contrário.
     */
    public boolean canMoveSouth() {
        World myWorld = getWorld();
        int x = getX();
        // Verificando duas células abaixo.
        int y = getY() + 2;

        if (myWorld.getObjectsAt(x + 1, y, Wall.class).size() > 0) {
            return false;
        }

        if (myWorld.getObjectsAt(x, y, Wall.class).size() > 0) {
            return false;
        }

        return myWorld.getObjectsAt(x - 1, y, Wall.class).size() <= 0;
    }

    /**
     * Verifica se o personagem pode se mover na direção EAST.
     *
     * @return True se é possível se mover, false caso contrário.
     */
    public boolean canMoveEast() {
        World myWorld = getWorld();
        // Verificando duas células à direita.
        int x = getX() + 2;
        int y = getY();

        if (myWorld.getObjectsAt(x, y + 1, Wall.class).size() > 0) {
            return false;
        }

        if (myWorld.getObjectsAt(x, y, Wall.class).size() > 0) {
            return false;
        }

        return myWorld.getObjectsAt(x, y - 1, Wall.class).size() <= 0;
    }

    /**
     * Verifica se o personagem pode se mover na direção WEST.
     *
     * @return True se é possível se mover, false caso contrário.
     */
    public boolean canMoveWest() {
        World myWorld = getWorld();
        // Verificando duas células à esquerda.
        int x = getX() - 2;
        int y = getY();

        if (myWorld.getObjectsAt(x, y + 1, Wall.class).size() > 0) {
            return false;
        }

        if (myWorld.getObjectsAt(x, y, Wall.class).size() > 0) {
            return false;
        }

        return myWorld.getObjectsAt(x, y - 1, Wall.class).size() <= 0;
    }

    /**
     * Verifica se o personagem pode se mover na direção que está olhando.
     *
     * @param direction direção que deseja se mover
     * @return false caso haja obstáculos ou esteja fora do mundo, true caso
     * contrário.
     */
    public boolean canMove(int direction) {
        switch (direction) {
            case EAST:
                return canMoveEast();

            case WEST:
                return canMoveWest();

            case NORTH:
                return canMoveNorth();

            case SOUTH:
                return canMoveSouth();
        }

        return true;
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
            case EAST:
                setLocation(getX() + 1, getY());
                break;

            case WEST:
                setLocation(getX() - 1, getY());
                break;

            case NORTH:
                setLocation(getX(), getY() - 1);
                break;

            case SOUTH:
                setLocation(getX(), getY() + 1);
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
