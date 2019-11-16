
import greenfoot.*;

/**
 * Representa uma pastilha no mundo do PacManWorld.
 *
 * O PacMan pode comê-la para adicionar pontos a sua pontuação.
 *
 * @author Raylson, Carlon, Weydson
 * @version 1.0
 */
public class Pellet extends Actor {

    private static GreenfootImage sprite;

    public Pellet() {
        sprite = new GreenfootImage("images/pastilha.png");
        setImage(sprite);
    }
}
