
import greenfoot.*;

/**
 * A classe Wall representa as paredes no mundo do PacManWorld.
 *
 * Elas implementam as restrições do labirinto, impedindo os Personagens de
 * atravessarem as paredes
 *
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class Wall extends Actor {

    // Cria uma parede invisível.
    private final GreenfootImage wall = new GreenfootImage("wall.png");

    // Cria uma parede visível para debug.
    private final GreenfootImage debugger = new GreenfootImage("wall_debugger.png");

    /**
     * Define a imagem que representa a Wall. parede_debugger permite a
     * visualização das paredes.
     */
    public Wall() {
        // Define a imagem usando o objeto parede.
        setImage(debugger);
    }
}
