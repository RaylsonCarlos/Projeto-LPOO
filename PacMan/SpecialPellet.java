
import greenfoot.*;

/**
 * Uma pastilha grande que pisca aleatoriamente
 *
 * @author Carlos, Raylson, Weydson
 * @version 1.0
 */
public class SpecialPellet extends Pellet {

    private int chance = 90;

    private static GreenfootImage sprite;
    private static GreenfootImage emptySprite;

    public SpecialPellet() {
        sprite = new GreenfootImage("images/pastilha_especial.png");
        emptySprite = new GreenfootImage("wall.png");
        setImage(sprite);
    }

    public void act() {
        int rand = Greenfoot.getRandomNumber(100);

        if (getImage().equals(emptySprite)) {
            setImage(sprite);
            getWorld().repaint();
        }

        if (chance < rand) {
            setImage(emptySprite);
            getWorld().repaint();
        }
    }
}
