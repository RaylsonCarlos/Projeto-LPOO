import greenfoot.*;

/**
 * Wall class represents the walls in the PacMan's maze. Do nothing besides stay in the way...
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class Wall extends Actor
{       
    
    public final static GreenfootImage parede = new GreenfootImage("wall.png");
    
    public final static GreenfootImage parede_debugger = new GreenfootImage("wall_debugger.png");
    
    public Wall(){        
        setImage(parede);
    }      
}
