import greenfoot.*;
import java.awt.Color;

/**
 * Wall class represents the walls in the PacMan's maze. Do nothing outside stay in the way...
 * 
 * @author Raylson, Carlos, Weydson
 * @version 1.0
 */
public class Wall extends Actor
{   /*
    0 -> esquina superior esquerda
    1 -> parede superior
    2 -> esquina superior direita
    3 -> parede esquerda
    4 -> parede direita
    5 -> esquina inferior esquerda
    6 -> parede inferior
    7 -> esquina inferior direita
    */
    public Wall(int pos){
        switch(pos){
            case 0:
            setImage("wall_top_left.png");
            break;
            case 1:
            setImage("wall_top.png");
            break;
            case 2:
            setImage("wall_top_right.png");
            break;
            case 3:
            setImage("wall_left.png");
            break;
            case 4:
            setImage("wall_right.png");
            break;
            case 5:
            setImage("wall_bottom_left.png");
            break;
            case 6:
            setImage("wall_bottom.png");
            break;
            case 7:
            setImage("wall_bottom_right.png");
            break;
            default:
            break;
        }
    }    
    public void act() 
    {
        // Add your action code here.
    }    
}
