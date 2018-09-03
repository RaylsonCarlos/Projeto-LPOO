import greenfoot.*;

/**
 * Just a Fantasma class that will haunt the PackMan
 * 
 * @author Raylson, Carlos, Weydson 
 * @version 1.0
 */
public class Fantasma extends Personagem
{
    private final static int RED = 0;
    private final static int PINK = 1;
    private final static int BLUE = 2;
    private final static int BROWN = 3;
    
    private int color;
    private int turn = 0;
    
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
        if(color < 0 || color > 3){
            color = 0;
        }
        this.color = color;
        direction = 0;
        setImage(sprites[color][direction][0]);
    }
    
    public void act() 
    {
        super.act();
        if(changeSprite()){
            setImage(sprites[color][direction][turn%2]);
            turn++;
        }
        if(!canMove()){
            direction = Greenfoot.getRandomNumber(4);
        }
    }    
}
