import greenfoot.*;
import java.util.List;
import java.util.Iterator;

/**
 * The PacMan class represents the classical hunger head that opens her mouth and eats whatever she finds in her way :) 
 * 
 * @author Raylson, Carlos, Weydson
 * @version 1.0
 */
public class PacMan extends Actor
{
    private static final int EAST = 0;
    private static final int WEST = 1;
    private static final int NORTH = 2;
    private static final int SOUTH = 3;
    
    private int direction;   
    private int turn = 0;
    private GreenfootImage[] sprites;
    
    public PacMan(){
        direction = SOUTH;
        sprites = new GreenfootImage []{ new GreenfootImage("east_0.png"),
                                         new GreenfootImage("east_1.png"),
                                         new GreenfootImage("east_2.png"),
                                         new GreenfootImage("west_0.png"),
                                         new GreenfootImage("west_1.png"),
                                         new GreenfootImage("west_2.png"),
                                         new GreenfootImage("north_0.png"),
                                         new GreenfootImage("north_1.png"),
                                         new GreenfootImage("north_2.png"),
                                         new GreenfootImage("south_0.png"),
                                         new GreenfootImage("south_1.png"),
                                         new GreenfootImage("south_2.png")};
    }
    
    public void changeDirection(int d){
        direction = d;
        turn = 0;
    }
    
    public boolean canMove(){
        World myWorld = getWorld();
        int x = getX();
        int y = getY();
        switch(direction){
            case EAST:
            x = x+2;
            break;
            case WEST:
            x = x-2;
            break;
            case NORTH:
            y = y-2;
            break;
            case SOUTH:
            y = y+2;
            break;
        }        
        if(x < 0 || y < 0 || x > myWorld.getWidth() || y > myWorld.getHeight()){
            return false;
        }
        
        if(myWorld.getObjectsAt(x,y,Wall.class).size() > 0){
            return false;
        } else {
            return true;
        }
    }
    
    public void move(){
        //System.out.println(canMove());
        if(!canMove()){
            return;
        } else {
            switch(direction){
                case EAST:
                setLocation(getX()+1,getY());
                break;
                case WEST:
                setLocation(getX()-1,getY());
                break;
                case NORTH:
                setLocation(getX(),getY()-1);
                break;
                case SOUTH:
                setLocation(getX(),getY()+1);
                break;
            }
        }
    }
    
    public boolean foundFood(){
       List<Actor> food = getObjectsInRange(1,Pastilha.class);
       if(food.size()>0){
           return true;
        }
        return false;
    }
    
    public void eatFood(){
        Iterator it = getObjectsInRange(1,Pastilha.class).iterator();
        while(it.hasNext()){
            getWorld().removeObject((Actor)it.next());
        }
    }
    
    public void act() 
    {
       switch(turn){
           case 0:
           turn++;
           setImage(sprites[3*direction]);
           break;
           case 3:
           turn++;
           setImage(sprites[3*direction+1]);
           break;
           case 6:
           turn++;
           setImage(sprites[3*direction+2]);
           break;
           case 9:
           turn = -2;
           setImage(sprites[3*direction+1]);
           break;
           default:
           turn++;
           break;
       }
       move();
       if(foundFood()){
           eatFood();
       }
    }    
}
