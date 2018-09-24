import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Just a Fantasma class that will haunt the PackMan
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class Fantasma extends Personagem
{
    public final static int RED = 0;
    public final static int PINK = 1;
    public final static int BLUE = 2;
    public final static int BROWN = 3;
    
    private int color;
    private int offset = 0;
    
    private final static GreenfootImage [][] spritesRed = new GreenfootImage[][]{
                                                                                 {new GreenfootImage("ghost_red_east_0.png"),new GreenfootImage("ghost_red_east_1.png")},
                                                                                 {new GreenfootImage("ghost_red_west_0.png"),new GreenfootImage("ghost_red_west_1.png")},
                                                                                 {new GreenfootImage("ghost_red_north_0.png"),new GreenfootImage("ghost_red_north_1.png")},
                                                                                 {new GreenfootImage("ghost_red_south_0.png"),new GreenfootImage("ghost_red_south_1.png")}
                                                                                };
    private final static GreenfootImage [][] spritesPink = new GreenfootImage[][]{
                                                                                 {new GreenfootImage("ghost_pink_east_0.png"),new GreenfootImage("ghost_pink_east_1.png")},
                                                                                 {new GreenfootImage("ghost_pink_west_0.png"),new GreenfootImage("ghost_pink_west_1.png")},
                                                                                 {new GreenfootImage("ghost_pink_north_0.png"),new GreenfootImage("ghost_pink_north_1.png")},
                                                                                 {new GreenfootImage("ghost_pink_south_0.png"),new GreenfootImage("ghost_pink_south_1.png")}
                                                                                };
    private final static GreenfootImage [][] spritesBlue = new GreenfootImage[][]{
                                                                                 {new GreenfootImage("ghost_blue_east_0.png"),new GreenfootImage("ghost_blue_east_1.png")},
                                                                                 {new GreenfootImage("ghost_blue_west_0.png"),new GreenfootImage("ghost_blue_west_1.png")},
                                                                                 {new GreenfootImage("ghost_blue_north_0.png"),new GreenfootImage("ghost_blue_north_1.png")},
                                                                                 {new GreenfootImage("ghost_blue_south_0.png"),new GreenfootImage("ghost_blue_south_1.png")}
                                                                                };
    private final static GreenfootImage [][] spritesBrown = new GreenfootImage[][]{
                                                                                 {new GreenfootImage("ghost_brown_east_0.png"),new GreenfootImage("ghost_brown_east_1.png")},
                                                                                 {new GreenfootImage("ghost_brown_west_0.png"),new GreenfootImage("ghost_brown_west_1.png")},
                                                                                 {new GreenfootImage("ghost_brown_north_0.png"),new GreenfootImage("ghost_brown_north_1.png")},
                                                                                 {new GreenfootImage("ghost_brown_south_0.png"),new GreenfootImage("ghost_brown_south_1.png")}
                                                                                };    
    private final static GreenfootImage[][][] sprites = new GreenfootImage[][][]{spritesRed,spritesPink,spritesBlue,spritesBrown};
    
    public Fantasma(int color){
        super(3);
        if(color != RED && color != PINK && color != BLUE && color != BROWN ){
            this.color = RED;
        } else {
            this.color = color;
        }
        setSprite();
    }
    
    private void setSprite(){
        switch(getDirection()){
            case Personagem.NORTH:
                setImage(sprites[color][2][offset%2]);
            break;
            case Personagem.SOUTH:
                setImage(sprites[color][3][offset%2]);
            break;
            case Personagem.EAST:
                setImage(sprites[color][0][offset%2]);
            break;
            case Personagem.WEST:
                setImage(sprites[color][1][offset%2]);
            break;
        }
    }
    
    private int oppositeDirection(int direction){
        int oppositeDirection = Personagem.NORTH;
        switch(direction){
            case Personagem.NORTH:
            oppositeDirection = Personagem.SOUTH;
            break;
            case Personagem.SOUTH:
            oppositeDirection = Personagem.NORTH;
            break;
            case Personagem.EAST:
            oppositeDirection = Personagem.WEST;
            break;
            case Personagem.WEST:
            oppositeDirection = Personagem.EAST;
            break;
        }
        return oppositeDirection;
    }
    
    private boolean preso(){
        int x = getX();
        int y = getY();        
        if(x > 22  && x < 34 && y > 26 && y < 32){
            return true;
        } else {
            return false;
        }
    }
    
    private void rotaPreso(){
        if(!canMoveNorth()){
            changeDirection(Personagem.SOUTH);
        }
        if(!canMoveSouth()){
            changeDirection(Personagem.NORTH);
        }
    }
    
    private void rotaNormal(){
        List<Integer> rotas = new ArrayList<Integer>();
        int direction = getDirection();
        int oppositeDirection = oppositeDirection(direction);
        
        if(canMoveNorth()){
            rotas.add(Personagem.NORTH);
        }
        if(canMoveSouth()){
            rotas.add(Personagem.SOUTH);
        }
        if(canMoveEast()){
            rotas.add(Personagem.EAST);
        }
        if(canMoveWest()){
            rotas.add(Personagem.WEST);
        }
        if(rotas.size() != 0){
            if(rotas.size() >= 2){
                rotas.remove(Integer.valueOf(oppositeDirection));
            }            
            int rand = Greenfoot.getRandomNumber(rotas.size());
            changeDirection(rotas.get(rand));
        }
    }
    
    public void act()
    {
        if(preso()){
            rotaPreso();
        } else {
            rotaNormal();
        }
        if(timeToChangeSprite()){
            setSprite();
            offset++;
        }
        super.act();
    }    
}
