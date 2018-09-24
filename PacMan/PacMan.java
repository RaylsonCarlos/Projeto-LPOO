import greenfoot.*;
import java.util.List;
import java.util.Iterator;

/**
 * The PacMan class represents the classical hunger head that opens her mouth and eats whatever she finds in her way :) 
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class PacMan extends Personagem
{    
    private int offset = 0;
    private int contadorDirection = 0;
    private int delayDirection = 6;
    private int possibleDirection = Personagem.WEST;
    private final static int[] animationOffset = {0,1,2,1};
    private final static GreenfootImage[] spritesNORTH = new GreenfootImage []{            
            new GreenfootImage("north_0.png"),
            new GreenfootImage("north_1.png"),
            new GreenfootImage("north_2.png")
        };
    private final static GreenfootImage[] spritesSOUTH = new GreenfootImage []{
            new GreenfootImage("south_0.png"),
            new GreenfootImage("south_1.png"),
            new GreenfootImage("south_2.png")
        };
    private final static GreenfootImage[] spritesEAST = new GreenfootImage []{
            new GreenfootImage("east_0.png"),
            new GreenfootImage("east_1.png"),
            new GreenfootImage("east_2.png")
        };
    private final static GreenfootImage[] spritesWEST = new GreenfootImage []{
            new GreenfootImage("west_0.png"),
            new GreenfootImage("west_1.png"),
            new GreenfootImage("west_2.png")
        };

    public PacMan(){
        super(3);
        changeDirection(Personagem.WEST);
        setImage(spritesWEST[1]);
    }

    private boolean foundFood(){
        List<Actor> food = getObjectsInRange(1,Pastilha.class);
        if(food.size()>0){
            Iterator it = food.iterator();
            while(it.hasNext()){
                getWorld().removeObject((Actor)it.next());
            }
            return true;
        }
        return false;
    }

    private void verificarTeclado(){
        String key = Greenfoot.getKey();
        if(key != null){
            offset = 1;
            if(key.equals("up")){
                possibleDirection = Personagem.NORTH;
                contadorDirection = 0;
            }
            if(key.equals("down")){
                possibleDirection = Personagem.SOUTH;
                 contadorDirection = 0;
            }
            if(key.equals("right")){
                possibleDirection = Personagem.EAST;
                 contadorDirection = 0;
            }
            if(key.equals("left")){
                possibleDirection = Personagem.WEST;
                 contadorDirection = 0;
            }
        }
    }

    public void act()
    {
        
        if(foundFood()){

        }
        
        verificarTeclado();
        
        if(contadorDirection < delayDirection){
            if(canMove(possibleDirection)){
                changeDirection(possibleDirection);
            } else {
                contadorDirection++;
            }
        }
        
        
        
        if(timeToChangeSprite()){            
            switch(getDirection()) {
                case Personagem.NORTH:
                setImage(spritesNORTH[animationOffset[offset%4]]);
                break;
                case Personagem.SOUTH:
                setImage(spritesSOUTH[animationOffset[offset%4]]);
                break;
                case Personagem.EAST:
                setImage(spritesEAST[animationOffset[offset%4]]);
                break;
                case Personagem.WEST:
                setImage(spritesWEST[animationOffset[offset%4]]);
                break;
            }
            offset++;
        }
        
        super.act();
    }    
}
