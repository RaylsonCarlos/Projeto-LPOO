
import greenfoot.*;

/**
 * Representa uma vida do pacman
 *
 * @author Raylson, Carlos, Weydson
 * @version 1.0
 */
public class Life extends Actor {

    private static GreenfootImage life;

    public Life() {
        life = new GreenfootImage("images/east_1.png");
        setImage(life);
    }
}
