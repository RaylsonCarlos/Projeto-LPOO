import greenfoot.*;
import java.util.List;
import java.util.Iterator;

/**
 * The PacMan class represents the classical hunger head that opens her mouth and eats whatever she finds in her way :) 
 * 
 * @author Raylson, Carlos, Weydson
 * @version 1.1
 */
public class PacMan extends Personagem
{    
    private int turn = 0;
    private final static int[] animation = {0,1,2,1}; 
    private final static GreenfootImage[] sprites = new GreenfootImage []{ new GreenfootImage("east_0.png"),
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
                                                                           new GreenfootImage("south_2.png")
                                                                        };
    
    
    public PacMan(){
        direction = 0;
        setImage(sprites[1]);
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
       super.act();
       if(changeSprite()){
          setImage(sprites[3*direction+animation[turn%4]]);
          turn++;
       }            
       if(foundFood()){
           eatFood();
       }
       
    }    
}
