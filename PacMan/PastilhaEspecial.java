
import greenfoot.*;

/**
 * Uma pastilha grande que pisca aleatoriamente
 *
 * @author Carlos, Raylson, Weydson
 * @version 1.0
 */
public class PastilhaEspecial extends Pastilha {

    private int chance = 90;

    private static GreenfootImage sprite;

    private static GreenfootImage spriteVazio;

    public PastilhaEspecial() {
        sprite = new GreenfootImage("images/pastilha_especial.png");
        spriteVazio = new GreenfootImage("wall.png");
        setImage(sprite);
    }

    /**
     *
     */
    public void act() {
        if (getImage().equals(spriteVazio)) {
            setImage(sprite);
            getWorld().repaint();
        }
        int rand = Greenfoot.getRandomNumber(100);
        if (chance < rand) {
            setImage(spriteVazio);
            getWorld().repaint();
        }
    }
}
