import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A Classe Fantasma representa os fantasmas do labirinto.
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.1
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
    /** Estado do fantasma vivo */
    public final static int ALIVE = 0;
    /** Estado do fantasma morto */
    public final static int DEAD = 1;
    /** Estado do fantasma com medo */
    public final static int FEAR = 2;
    /** Estado do fantasma se recuperando */
    public final static int RECOVERING = 3;
    /** Estado do fantasma: DEAD, ALIVE,FEAR ou RECOVERING */
    private int estado;
    /** Timer interno de estado do fantasma */
    private long timer;
    /** Tempo de efeito das pastilhas */
    private int tempoEfeito = 3000;
    /** Armazena se o fantasma deve sair da cela*/
    boolean liberdade = false;
    /** Armazena o deslocamento dentro do array de sprites do fantasma */
    private int offset = 0;
    /** Sprites do fantasma vermelho */
    private static GreenfootImage [][] spritesRed;
    /** Sprites do fantasma rosa */
    private static GreenfootImage [][] spritesPink;
    /** Sprites do fantasma azul */
    private static GreenfootImage [][] spritesBlue;
    /** Sprites do fantasma marrom */
    private static GreenfootImage [][] spritesBrown;
    /** Sprites do fantasma amendrontado */
    private static GreenfootImage [][] spritesAmedrontado;
    /** Sprites do fantasmam morto */
    private static GreenfootImage [][] spritesMorto;
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
        spritesAmedrontado = new GreenfootImage [][] {
            {new GreenfootImage("frightened_0.png"),
             new GreenfootImage("frightened_1.png"),
             new GreenfootImage("recovering_1.png"),
             new GreenfootImage("recovering_0.png")}
        };
        spritesMorto = new GreenfootImage [][] {
            {new GreenfootImage("ghost_eye_east.png"),
             new GreenfootImage("ghost_eye_west.png"),
             new GreenfootImage("ghost_eye_north.png"),
             new GreenfootImage("ghost_eye_south.png")}
        };
        sprites = new GreenfootImage[][][]{spritesRed,spritesPink,spritesBlue,spritesBrown,spritesAmedrontado,spritesMorto};

        if(color != RED && color != PINK && color != BLUE && color != BROWN ){
            this.color = RED;
        } else {
            this.color = color;
        }
        estado = ALIVE;
        setSprite();
    }

    /**
     * Informa se o fantasma deve ser liberado da cela.
     * @return true caso deva ser liberado da cela, false caso contrário
     */
    public boolean liberdade(){
        return liberdade;
    }

    /**
     * Altera se o fantasma deve ser liberado da cela ou não.
     * @param liberar valor a ser alterado
     */
    public void setLiberdade(boolean liberdade){
        this.liberdade = liberdade;
    }

    /**
     * Informa o estado do fantasma.
     * 
     * @return um inteiro que representa um dos estados: ALIVE, DEAD, FEAR ou RECOVERING.
     */
    public int getEstado(){
        return estado;
    }

    /**
     * Modifica o estado do personagem.
     * 
     * @param algum dos estados possíveis: ALIVE, DEAD, FEAR ou RECOVERING. Parâmetro inválido gera o estado ALIVE.
     */
    private void setEstado(int estado){
        if(estado < 0 || estado > 3) {
            this.estado = ALIVE;
        } else {
            this.estado = estado;
        }
    }

    /**
     * Muda o sprite do fantasma.
     */
    private void setSprite(){
        if(estado == ALIVE){
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
        } else if (estado == FEAR){
            setImage(sprites[4][0][offset%2]);
        } else if (estado == RECOVERING) {
            setImage(sprites[4][0][offset%4]);
        } else if (estado == DEAD) {
            switch(getDirection()){
                case Personagem.NORTH:
                setImage(sprites[5][0][2]);
                break;
                case Personagem.SOUTH:
                setImage(sprites[5][0][3]);
                break;
                case Personagem.EAST:
                setImage(sprites[5][0][0]);
                break;
                case Personagem.WEST:
                setImage(sprites[5][0][1]);
                break;
            }
        } else {
            //do nothing...
        }
    }

    /**
     * Informa a direção oposta à direção que o personagem está encarando.
     * @return Um Inteiro que descreve a direção: NORTH, SOUTH, EAST ou WEST
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

    /** 
     * Informa se o fantasma está dentro da cela do labirinto.
     * @return true se o fantasma está dentro da cela do labirinto, false caso contrário.
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
        if(liberdade){
            int x = getX();
            if(x != 28){
                setSpeed(3);
                if(x < 28 && getDirection() != Personagem.EAST){
                    changeDirection(Personagem.EAST);
                } else if(x > 28 && getDirection() != Personagem.WEST){
                    changeDirection(Personagem.WEST);
                }
            } else {
                if(getDirection() != Personagem.NORTH){
                    changeDirection(Personagem.NORTH);
                }
                setLocation(x,getY() - 1);
            }
        } else {
            setSpeed(2);
            if(!canMoveNorth()){
                changeDirection(Personagem.SOUTH);
            }
            if(!canMoveSouth()){
                changeDirection(Personagem.NORTH);
            }
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
     * Controla o efeito das pastilhas.
     */
    private void controlEfeitoPastilha(){
        if(estado == Fantasma.RECOVERING){
            if((System.currentTimeMillis() - timer) > tempoEfeito){
                setAlive();
            }
        }
        if(estado == Fantasma.FEAR){
            if((System.currentTimeMillis() - timer) > tempoEfeito ){
                setRecovering();
            }
        }
    }

    /**
     * Inicia o efeito das pastilhas.
     */
    public void efeitoPastilha(){
        setFear();
    }

    /**
     * Altera o estado para FEAR.
     */
    public void setFear(){
        if(estado == DEAD){
            return;
        }
        estado = Fantasma.FEAR;
        timer = System.currentTimeMillis();
        setSpeed(2);
    }

    /**
     * Altera o estado para RECOVERING.
     */
    public void setRecovering(){
        estado = Fantasma.RECOVERING;
        timer = System.currentTimeMillis();
        setSpeed(2);
    }

    /**
     * Altera o estado para ALIVE.
     */
    public void setAlive(){        
        estado = Fantasma.ALIVE;        
        setSpeed(3);
    }

    /**
     * Altera o estado para DEAD.
     */
    public void setDead(){
        estado = Fantasma.DEAD;
        setSpeed(3);
    }

    /**
     * Rota se o fantasma estiver em estado ALIVE.
     */
    private void rotaAlive(){
        if(preso()){
            rotaPreso();
        } else {
            rotaNormal();
        }
    }

    private int dist(int x0, int y0, int x1, int y1){
        int deltaX = x1-x0;
        int deltaY = y1-y0;        
        return (deltaX*deltaX) + (deltaY*deltaY);
    }

    /**
     * Rota se o fantasma estiver em estado DEAD. 
     * O fantasma se desloca até o sistema prisional e sai recuperado (glória a deuxxx!).
     */
    private void rotaDead(){
        //(23,28) é a coordenada da entrada da cela.
        int x = getX();
        int y = getY();
        
        //põe o fantasma pra dentro da cela caso ele esteja na entrada da cela.
        if(x == 28 && y <= 30 && y >= 23){
            switch(y){
                case 23:
                    setSpeed(0);
                    setLocation(x, y + 1);
                    break;
                case 30:
                    setAlive();
                    break;
                default:
                    setLocation(x, y + 1);
            }
            return;
        }

        //Calcula a direção que deve seguir para a entrada da cela.

        List<Integer> rotas = new ArrayList<Integer>();
        List<Integer> distancias = new ArrayList<Integer>();
        if(canMoveNorth() && getDirection() != Personagem.SOUTH){
            rotas.add(Personagem.NORTH);
            distancias.add(dist(x,y-1,23,28));
        }
        if(canMoveSouth() && getDirection() != Personagem.NORTH){
            rotas.add(Personagem.SOUTH);
            distancias.add(dist(x,y+1,23,28));
        }
        if(canMoveEast() && getDirection() != Personagem.WEST){
            rotas.add(Personagem.EAST);
            distancias.add(dist(x+1,y,23,28));
        }
        if(canMoveWest() && getDirection() != Personagem.EAST){
            rotas.add(Personagem.WEST);
            distancias.add(dist(x-1,y,23,28));
        }

        //caso não haja uma direção que lhe seja mais favorável continua seguindo a mesma direção.
        //caso esteja bloqueado procura outra direção possível.
        if(distancias.size() <= 0){
            if(!canMove(getDirection())){
                if(canMoveNorth()){
                    changeDirection(Personagem.NORTH);
                } else if(canMoveSouth()){
                    changeDirection(Personagem.SOUTH);
                } else if (canMoveEast()){
                    changeDirection(Personagem.EAST);
                } else if(canMoveWest()){
                    changeDirection(Personagem.WEST);
                }
            }
            return;
        }

        int indexMin = 0;
        for(int i = 0; i < distancias.size(); i++){
            if(distancias.get(indexMin) > distancias.get(i)){
                indexMin = i;
            }
        }

        changeDirection(rotas.get(indexMin));

    }

    /**
     * Rota se o fantasma estiver em estado FEAR.
     */
    private void rotaFear(){
        World world = getWorld();
        PacMan pacman = (PacMan)world.getObjects(PacMan.class).get(0);
        
        //componentes do vetor desse fantasma até o pacman
        int x = getX();
        int y = getY();
        int pacmanX = pacman.getX();
        int pacmanY = pacman.getY();
        
        //lista das possíveis rotas a seguir e de suas respectivas distancias até o pacman.
        List<Integer> rotas = new ArrayList<Integer>();
        List<Integer> distancias = new ArrayList<Integer>();
        
        if(canMoveNorth()  && getDirection()!= Personagem.SOUTH){//
            rotas.add(Personagem.NORTH);
            distancias.add(dist(x,y-1,pacmanX,pacmanY));
        }
        if(canMoveSouth() && getDirection() != Personagem.NORTH){// 
            rotas.add(Personagem.SOUTH);
            distancias.add(dist(x,y+1,pacmanX,pacmanY));
        }
        if(canMoveEast() && getDirection()!= Personagem.WEST){// 
            rotas.add(Personagem.EAST);
            distancias.add(dist(x+1,y,pacmanX,pacmanY));
        }
        if(canMoveWest() && getDirection() != Personagem.EAST){// 
            rotas.add(Personagem.WEST);
            distancias.add(dist(x-1,y,pacmanX,pacmanY));
        }
        
        //caso não haja uma direção que lhe seja mais favorável continua seguindo a mesma direção.
        //caso esteja bloqueado procura outra direção possível.
        if(distancias.size() <= 0){
            if(!canMove(getDirection())){
                if(canMoveNorth()){
                    changeDirection(Personagem.NORTH);
                } else if(canMoveSouth()){
                    changeDirection(Personagem.SOUTH);
                } else if (canMoveEast()){
                    changeDirection(Personagem.EAST);
                } else if(canMoveWest()){
                    changeDirection(Personagem.WEST);
                }
            }
            return;
        }
        
        //determina a direção que mais se afasta do pacman.
        int indexMax = 0;
        for(int i = 0; i < distancias.size(); i++){
            if(distancias.get(indexMax) < distancias.get(i)){
                indexMax = i;
            }
        }
        
        //escolhe essa direção.
        changeDirection(rotas.get(indexMax));
    }

    /**
     * Rota se o fantasma estiver em estado RECOVERING.
     */
    private void rotaRecovering(){
        rotaFear();
    }

    /**
     * Faz o fantasma se mover e mudar os sprites do personagem.
     */
    public void act(){
        controlEfeitoPastilha();
        switch(estado){
            case ALIVE:
                rotaAlive();
                break;
            case FEAR:
                rotaFear();
                break;
            case RECOVERING:
                rotaRecovering();
                break;
            case DEAD:
                rotaDead();
                break;
        }

        if(timeToChangeSprite()){
            setSprite();
            offset++;
        }
        super.act();
    }    
}
