import greenfoot.*;

/**
 * A classe Wall representa as paredes no mundo do PacManWorld.
 * 
 * Elas delimitam o mundo e o labirinto, impedindo os Personagens
 * de andarem livremente.
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class Wall extends Actor
{       
    // Define uma imagem para um objeto parede.
    public final static GreenfootImage parede = new GreenfootImage("wall.png");
    
    // Parede para debugger.
    public final static GreenfootImage parede_debugger = new GreenfootImage("wall_debugger.png");
    
    /**
     * Construtor da Wall, define a imagem que representa a Wall.
     */
    public Wall()
    {  
        // Define a imagem usando o objeto parede.
        setImage(parede);
    }      
}
