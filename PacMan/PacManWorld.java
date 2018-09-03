import greenfoot.*;
import java.awt.Color;

/**
 * PacManWorld stands for the world where the PackMan will eat everything 
 * 
 * @author Raylson, Carlos, Weydson
 * @version 1.0
 */
public class PacManWorld extends World
{
    private static final GreenfootImage cell = new GreenfootImage("cell.png");
    private int points = 0;
    
    public PacManWorld()
    {    
        // Create a new world with 200x120 cells with a cell size of 4x4 pixels.
        super(200, 120, 4);
        setBackground(cell);
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
       addObject(new Fantasma(0),2,2);
       addObject(new Fantasma(1),2,2);
       addObject(new Fantasma(2),2,2);
       addObject(new Fantasma(3),2,2);
       addObject(new Wall(0),80,51);
       for(int i = 81; i < 120; i++){ addObject(new Wall(6),i,50);}
       addObject(new Wall(2),120,51);
       for(int i = 52; i < 69; i++){addObject(new Wall(4),120,i);}
       addObject(new Wall(5),80,69);
       for(int i = 81; i < 120; i++){addObject(new Wall(1),i,70);}
       addObject(new Wall(7),120,69);
       for(int i = 52; i < 69; i++){addObject(new Wall(3),80,i);}
       addObject(new Fantasma(0),82,52);
       addObject(new Fantasma(1),82,52);
       addObject(new Fantasma(2),82,52);
       addObject(new Fantasma(3),82,52);
       addObject(new Pastilha(),10,10);
       addObject(new Pastilha(),190,10);
       addObject(new Pastilha(),10,30);
       addObject(new Pastilha(),10,50);
       addObject(new Pastilha(),80,72);
       addObject(new Pastilha(),40,90);
       addObject(new Pastilha(),30,70);
       addObject(new Pastilha(),100,60);
    }
}
