import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A Classe Fantasma representa os fantasmas do labirinto.
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class Fantasma extends Personagem
{
    /** Cor vermelha */    
    public final static int RED = 0;
    /** Cor rosa */
    public final static int PINK = 1;
    /** Cor azul */
    public final static int BLUE = 2;
    /** Cor marrom */
    public final static int BROWN = 3;
    /** Variável para armazenar a cor do fantasma */
    private int color;
    /** Armazena o deslocamento dentro do array de sprites do fantasma */
    private int offset = 0;
    /** Sprites do fantasma vermelho */
    private static GreenfootImage [][] spritesRed;
    /** Sprites do fantasma rosa */
    private static GreenfootImage [][] spritesPink;
    /** Sprites do fantasma azul */
    private static GreenfootImage [][] spritesBlue;
    /** Sprites do fantasma marrom */
    private static GreenfootImage [][] spritesBrown ; 
    /** Sprites dos fantasmas */
    private static GreenfootImage[][][] sprites;

    /**
     * Cria um fantasma com velocidade 3
     * @param Cor do fantasma: RED, PINK, BLUE ou BROWN.
     */
    public Fantasma(int color){        
        super(3);
        spritesRed = new GreenfootImage[][]{
            {new GreenfootImage("ghost_red_east_0.png"),new GreenfootImage("ghost_red_east_1.png")},
            {new GreenfootImage("ghost_red_west_0.png"),new GreenfootImage("ghost_red_west_1.png")},
            {new GreenfootImage("ghost_red_north_0.png"),new GreenfootImage("ghost_red_north_1.png")},
            {new GreenfootImage("ghost_red_south_0.png"),new GreenfootImage("ghost_red_south_1.png")}
        };
        spritesPink = new GreenfootImage[][]{
            {new GreenfootImage("ghost_pink_east_0.png"),new GreenfootImage("ghost_pink_east_1.png")},
            {new GreenfootImage("ghost_pink_west_0.png"),new GreenfootImage("ghost_pink_west_1.png")},
            {new GreenfootImage("ghost_pink_north_0.png"),new GreenfootImage("ghost_pink_north_1.png")},
            {new GreenfootImage("ghost_pink_south_0.png"),new GreenfootImage("ghost_pink_south_1.png")}
        };
        spritesBlue = new GreenfootImage[][]{
            {new GreenfootImage("ghost_blue_east_0.png"),new GreenfootImage("ghost_blue_east_1.png")},
            {new GreenfootImage("ghost_blue_west_0.png"),new GreenfootImage("ghost_blue_west_1.png")},
            {new GreenfootImage("ghost_blue_north_0.png"),new GreenfootImage("ghost_blue_north_1.png")},
            {new GreenfootImage("ghost_blue_south_0.png"),new GreenfootImage("ghost_blue_south_1.png")}
        };
        spritesBrown = new GreenfootImage[][]{
            {new GreenfootImage("ghost_brown_east_0.png"),new GreenfootImage("ghost_brown_east_1.png")},
            {new GreenfootImage("ghost_brown_west_0.png"),new GreenfootImage("ghost_brown_west_1.png")},
            {new GreenfootImage("ghost_brown_north_0.png"),new GreenfootImage("ghost_brown_north_1.png")},
            {new GreenfootImage("ghost_brown_south_0.png"),new GreenfootImage("ghost_brown_south_1.png")}
        }; 
        sprites = new GreenfootImage[][][]{spritesRed,spritesPink,spritesBlue,spritesBrown};

        if(color != RED && color != PINK && color != BLUE && color != BROWN ){
            this.color = RED;
        } else {
            this.color = color;
        }
        setSprite();
    }

    /**
     * Muda o sprite do fantasma
     */

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

    /**Informa a direção oposta à direção que o personagem está encarando.
     * return Um Inteiro que descreve a direção: NORTH, SOUTH, EAST ou WEST
     */

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

    /** Informa se o fantasma está dentro da cela do labirinto.
     * return true se o fantasma está dentro da cela do labirinto, false caso contrário.
     */

    private boolean preso(){
        int x = getX();
        int y = getY();        
        if(x > 22  && x < 34 && y > 26 && y < 32){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Define como o fantasma deve se movimentar se estiver preso na cela do labirinto
     */

    private void rotaPreso(){
        if(!canMoveNorth()){
            changeDirection(Personagem.SOUTH);
        }
        if(!canMoveSouth()){
            changeDirection(Personagem.NORTH);
        }
    }

    /**
     * Define como o fantasma deve se movimentar se estiver fora da cela.
     */
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

    /**
     * Faz o fantasma se mover e mudar os sprites do personagem.
     */

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
