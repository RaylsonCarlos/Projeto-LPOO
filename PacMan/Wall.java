
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

    /**
     * Cria uma imagem invisível.
     */
    public final static GreenfootImage parede = new GreenfootImage("wall.png");

    /**
     * Cria uma imagem visível para debug.
     */
    public final static GreenfootImage parede_debugger = new GreenfootImage("wall_debugger.png");

    /**
     * Define a imagem que representa a Wall. parede_debugger permite a
     * visualização das paredes.
     */
    public Wall() {
        // Define a imagem usando o objeto parede.
        setImage(parede);
    }
}
