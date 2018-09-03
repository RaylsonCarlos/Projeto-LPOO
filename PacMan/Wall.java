import greenfoot.*;

/**
 * Wall class represents the walls in the PacMan's maze. Do nothing besides stay in the way...
 * 
 * @author Raylson, Carlos, Weydson
 * @version 1.1
 */
public class Wall extends Actor
{   
    /*Tornar os sprites como static melhorou o desempenho do jogo*/
    private final static GreenfootImage[] sprites = new GreenfootImage[]{ new GreenfootImage("wall_top_left.png"),
                                                                          new GreenfootImage("wall_top.png"),
                                                                          new GreenfootImage("wall_top_right.png"),
                                                                          new GreenfootImage("wall_left.png"),
                                                                          new GreenfootImage("wall_right.png"),
                                                                          new GreenfootImage("wall_bottom_left.png"),
                                                                          new GreenfootImage("wall_bottom.png"),
                                                                          new GreenfootImage("wall_bottom_right.png")
                                                                        };
    
    /*
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
        /*Usar switch eh ruim e feio?!*/
        switch(pos){
            case 0:
            setImage(sprites[0]);
            break;
            case 1:
            setImage(sprites[1]);
            break;
            case 2:
            setImage(sprites[2]);
            break;
            case 3:
            setImage(sprites[3]);
            break;
            case 4:
            setImage(sprites[4]);
            break;
            case 5:
            setImage(sprites[5]);
            break;
            case 6:
            setImage(sprites[6]);
            break;
            case 7:
            setImage(sprites[7]);
            break;
            default:
            break;
        }
    }      
}
