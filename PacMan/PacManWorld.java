import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class PacManWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PacManWorld extends World
{
    /**
     * Constructor for objects of class PacManWorld.
     * 
     */
    public PacManWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(200, 120, 4);
        setBackground("cell.png");
        populate();
    }
    
    public void act(){
        String key = Greenfoot.getKey();
        if(key == null){return;}
        if(key.equals("up")){
           ((PacMan)getObjects(PacMan.class).get(0)).changeDirection(2);            
        }
        if(key.equals("down")){
           ((PacMan)getObjects(PacMan.class).get(0)).changeDirection(3);            
        }
        if(key.equals("right")){
           ((PacMan)getObjects(PacMan.class).get(0)).changeDirection(0);            
        }
        if(key.equals("left")){
           ((PacMan)getObjects(PacMan.class).get(0)).changeDirection(1);            
        }
    }
    
    public void populate(){
       addObject(new Wall(0),0,0);
       addObject(new Wall(2),199,0);
       addObject(new Wall(5),0,119);
       addObject(new Wall(7),199,119);
       for(int i = 1; i < 199; i++){
           addObject(new Wall(1),i,0);
           addObject(new Wall(6),i,119);
       }
       for(int j = 1; j < 119; j++){
           addObject(new Wall(3),0,j);
           addObject(new Wall(4),199,j);
       }
       addObject(new PacMan(),2,2);
    }
}
