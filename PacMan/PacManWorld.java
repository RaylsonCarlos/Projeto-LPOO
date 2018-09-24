import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * PacManWorld stands for the world where the PackMan will eat everything
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class PacManWorld extends World
{
    private static final GreenfootImage background = new GreenfootImage("background.png");
    private int points = 0;

    public PacManWorld()
    {    
        // Create a new world with 200x120 cells with a cell size of 4x4 pixels.
        super(57,63,4);
        setBackground(background);
        populateWall();
        populateFantasma();
        populatePastilha();
        addObject(new PacMan(),28,47);
        Greenfoot.setSpeed(39);
    }

    public void act(){
        portal();
    }
    
    private void portal(){
        List<Personagem> lista = getObjects(Personagem.class);
        for(Personagem per : lista){
            if(isInsidePortal(per)){
                if(per.getX()== 56){
                    per.setLocation(0,per.getY());
                } else {
                    per.setLocation(56,per.getY());
                }
            }
        }
    }
    
    private boolean isInsidePortal(Personagem per){
        int x = per.getX();
        int y = per.getY();
        if(y == 29 && (x == 0 || x == 56)){
            return true;
        } else {
            return false;
        }
    }
    
    private void populatePastilha(){
        
    }

    public void populateFantasma(){
        Fantasma blinky = new Fantasma(Fantasma.RED);        
        Fantasma pinky = new Fantasma(Fantasma.PINK);
        pinky.setSpeed(2);
        Fantasma inky = new Fantasma(Fantasma.BLUE);
        inky.setSpeed(2);
        Fantasma clyde = new Fantasma(Fantasma.BROWN);
        clyde.setSpeed(2);
        addObject(pinky,32,30);
        addObject(inky,28,30);
        addObject(clyde,24,30);
        addObject(blinky,28,23);
    }
    
    private void cela(){
        squareWall(21,25,14,8);
        squareWall(22,26,12,6);
    }
    
    private void bordasExternas(){
        duplaLinhaVertical(1,1,19);
        duplaLinhaVertical(55,1,19);
        duplaLinhaHorizontal(1,1,27);
        duplaLinhaHorizontal(1,29,55);
        duplaLinhaHorizontal(19,1,11);
        duplaLinhaHorizontal(19,45,55);
        duplaLinhaVertical(11,19,27);
        duplaLinhaVertical(45,19,27);
        duplaLinhaHorizontal(27,-1,11);
        duplaLinhaHorizontal(27,45,56);
        duplaLinhaHorizontal(31,-1,11);
        duplaLinhaHorizontal(31,45,56);
        duplaLinhaVertical(11,31,39);
        duplaLinhaVertical(45,31,39);
        duplaLinhaHorizontal(39,1,11);
        duplaLinhaHorizontal(39,45,55);
        duplaLinhaVertical(1,39,49);
        duplaLinhaVertical(55,39,49);
        duplaLinhaVertical(1,51,61);
        duplaLinhaVertical(55,51,61);
        duplaLinhaHorizontal(61,1,55);
    }
    
     private void quinasExternas(){
        addObject(new Wall(),1,1);
        addObject(new Wall(),55,61);
        addObject(new Wall(),1,61);
        addObject(new Wall(),55,1);
        addObject(new Wall(),1,19);
        addObject(new Wall(),55,19);
        addObject(new Wall(),11,19);
        addObject(new Wall(),45,19);
        addObject(new Wall(),11,27);
        addObject(new Wall(),45,27);        
        addObject(new Wall(),11,31);
        addObject(new Wall(),45,31);
        addObject(new Wall(),11,39);
        addObject(new Wall(),45,39);
        addObject(new Wall(),1,39);
        addObject(new Wall(),55,39);
    }
    
    private void duplaLinhaVertical(int x, int y0, int y1){
        for(int i = y0+1; i < y1; i++){
            addObject(new Wall(), x, i);
        }
    }
    
    private void duplaLinhaHorizontal(int y, int x0, int x1){
        for(int i = x0+1; i < x1; i++){
            addObject(new Wall(), i, y);
        }
    } 
    
    private void populateWall(){
        bordasExternas();
        quinasExternas();
        cela();
        squareWall(5,5,6,4);
        squareWall(15,5,8,4);
        squareWall(27,0,2,9);
        squareWall(33,5,8,4);
        squareWall(45,5,6,4);
        squareWall(5,13,6,2);
        squareWall(15,13,2,14);
        squareWall(21,13,14,2);
        squareWall(27,13,2,8);
        squareWall(39,13,2,14);
        squareWall(45,13,6,2);
        squareWall(17,19,6,2);
        squareWall(33,19,6,2);
        squareWall(15,31,2,8);
        squareWall(39,31,2,8);
        squareWall(21,37,14,2);
        squareWall(27,37,2,8);
        squareWall(5,43,6,2);
        squareWall(9,43,2,8);
        squareWall(15,43,8,2);
        squareWall(33,43,8,2);
        squareWall(45,43,2,8);
        squareWall(45,43,6,2);
        squareWall(0,49,5,2);
        squareWall(15,49,2,6);
        squareWall(21,49,14,2);
        squareWall(27,49,2,8);
        squareWall(39,49,2,6);
        squareWall(51,49,5,2);
        squareWall(5,55,18,2);
        squareWall(33,55,18,2);
    }

    private void squareWall(int x0, int y0, int width, int height){
        int xMax = x0 + width;
        int yMax = y0 + height;        
        addObject(new Wall(),x0,y0);
        addObject(new Wall(),xMax,y0);
        addObject(new Wall(),x0,yMax);
        addObject(new Wall(),xMax,yMax);
        for(int i = x0+1; i < xMax; i++){
            addObject(new Wall(),i,y0);
            addObject(new Wall(),i,yMax);
        }
        for(int j = y0+1; j < yMax; j++){
            addObject(new Wall(),x0,j);
            addObject(new Wall(),xMax,j);
        }
    }
}
