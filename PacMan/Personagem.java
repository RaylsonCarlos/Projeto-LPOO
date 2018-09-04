import greenfoot.*;

/**
 * Personagem class provide the basic motion and animation of the PacMan's characters
 * 
 * @author Raylson, Carlos, Weydson 
 * @version 1.0
 */
public class Personagem extends Actor
{
    private static final int EAST = 0;
    private static final int WEST = 1;
    private static final int NORTH = 2;
    private static final int SOUTH = 3;
    
    protected int direction;
    /*Vai contar os turnos do jogo para a animação*/
    private int tick = 0;
    
    public void changeDirection(int direction){
        this.direction = direction;
        tick = -1;
    }
    
    public boolean changeSprite(){        
        if(tick <= 0){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean canMove(){
        World myWorld = getWorld();
        int x = getX();
        int y = getY();
        /*To which cell?*/
        switch(direction){
            case EAST:
            x = x+2;            
            break;
            case WEST:
            x = x-3;           
            break;
            case NORTH:
            y = y-3;            
            break;
            case SOUTH:
            y = y+2;            
            break;
        }        
        
        boolean outsideOfWorld = x < 0 || y < 0 || x > myWorld.getWidth() || y > myWorld.getHeight();
        if(outsideOfWorld){
            
            return false;}
        
        /*checks if there is a wall there*/
        
        switch(direction){
            case EAST:
            if(myWorld.getObjectsAt(x,y+1,null).size() > 0){ return false;}
            if(myWorld.getObjectsAt(x,y,null).size() > 0){ return false;}
            if(myWorld.getObjectsAt(x,y-1,null).size() > 0){return false;}
            if(myWorld.getObjectsAt(x,y-2,null).size() > 0){return false;}
            break;
            case WEST:
            if(myWorld.getObjectsAt(x,y+1,null).size() > 0){return false;}
            if(myWorld.getObjectsAt(x,y,null).size() > 0){return false;}
            if(myWorld.getObjectsAt(x,y-1,null).size() > 0){ return false;}
            if(myWorld.getObjectsAt(x,y-2,null).size() > 0){return false;}
            break;
            case NORTH:            
            if(myWorld.getObjectsAt(x-2,y,null).size() > 0){return false;}
            if(myWorld.getObjectsAt(x-1,y,null).size() > 0){ return false;}
            if(myWorld.getObjectsAt(x,y,null).size() > 0){return false;}
            if(myWorld.getObjectsAt(x+1,y,null).size() > 0){return false;}
            break;
            case SOUTH:
            if(myWorld.getObjectsAt(x+1,y,null).size() > 0){return false;}
            if(myWorld.getObjectsAt(x,y,null).size() > 0){return false;}
            if(myWorld.getObjectsAt(x-1,y,null).size() > 0){ return false;}
            if(myWorld.getObjectsAt(x-2,y,null).size() > 0){return false;}
            break;
        }       
        return true;        
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
    
    public void act() 
    {       
       if(tick == 2){
           tick = 0;
       } else {
           tick++;
       }
       move();
    }    
}
