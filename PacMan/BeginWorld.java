import greenfoot.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.RenderingHints;

/**
 * Write a description of class BeginWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BeginWorld extends World
{
    private Actor pacman;
    private int size;
    private GreenfootImage pacmanSprite;

    /**
     * Constructor for objects of class BeginWorld.
     */
    public BeginWorld()
    {
        super(50, 50, 4);

        try{
            File[] allFilesAtImage = new File("images").listFiles();
            for(int i = 0; i < allFilesAtImage.length; i++){
                allFilesAtImage[i].delete();
            }
        }
        catch (Exception e){e.printStackTrace();}

        size = 16;
        setBackground("sprites/cell.png");
        String msg1 = "Pressione [F1/F2]\npara aumentar/diminuir \no tamanho";
        String msg2 = "[Enter] para prosseguir";
        GreenfootImage img1 = new GreenfootImage(msg1,20,Color.GREEN,null);
        GreenfootImage img2 = new GreenfootImage(msg2,20,Color.GREEN,null);
        pacmanSprite = new GreenfootImage("sprites/west_1.png");
        Actor actor1 = new Actor(){public void act(){}};
        Actor actor2 = new Actor(){public void act(){}};
        pacman = new Actor(){public void act(){}};
        actor1.setImage(img1);
        actor2.setImage(img2);
        pacman.setImage(pacmanSprite);
        addObject(actor1,25,7);
        addObject(actor2,25,45);
        addObject(pacman,25,27);
    }

    public void act(){
        if(size == 64){
            showText("maior ainda?", 25, 40);
        } else {
            showText("",25,40);
        }        
        if(Greenfoot.isKeyDown("F1")){
            if(size >= 64 ){
                SoundPlayer.playEffectYouCegoMan();
            } else {
                size += 4;
                scaleSprite(size,size);                
            }
            try {
                Thread.sleep(200);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(Greenfoot.isKeyDown("F2")){
            if(size <= 16){
                SoundPlayer.playEffectJaAvisei();
            } else {
                size -= 4;
                scaleSprite(size,size);
            }
            try {
                Thread.sleep(200);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(Greenfoot.isKeyDown("enter")){
            try {
                File[] spriteFiles = new File("sprites").listFiles();                
                for(int i = 0; i < spriteFiles.length; i++){
                    BufferedImage bfiSprite = ImageIO.read(spriteFiles[i]);
                    int width = bfiSprite.getWidth();
                    int height = bfiSprite.getHeight();                    
                    int newWidth = width*size/16;
                    int newHeight = height*size/16;
                    int typeImageSprite = bfiSprite.getType();
                    BufferedImage bfiSpriteResized = new BufferedImage(newWidth,newHeight,typeImageSprite);
                    Graphics2D gph2D = bfiSpriteResized.createGraphics();                    
                    gph2D.drawImage(bfiSprite,0,0,newWidth,newHeight,null);
                    gph2D.dispose();
                    File spriteFileResized = new File("images/"+spriteFiles[i].getName());
                    int lastPonctuation = spriteFiles[i].getName().lastIndexOf(".");
                    String format = spriteFiles[i].getName().substring(lastPonctuation+1);
                    ImageIO.write(bfiSpriteResized,format,spriteFileResized);                    
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            PacManWorld pw = new PacManWorld(size);
            new GameController(pw);
            Greenfoot.setWorld(pw);
        }
    }

    private void scaleSprite(int sizeX, int sizeY){
        BufferedImage biPacmanSprite = pacmanSprite.getAwtImage();
        int typeOfPacmanSprite = biPacmanSprite.getType();        
        BufferedImage bi = new BufferedImage(sizeX,sizeY,typeOfPacmanSprite);
        Graphics2D gph2D = bi.createGraphics();        
        gph2D.drawImage(biPacmanSprite,0,0,sizeX,sizeY,null);
        gph2D.dispose();
        try{
            File newSprite = new File("pacman_sprite_resized" +Integer.toString(size)+ ".png");            
            ImageIO.write(bi,"png",newSprite);
        } catch (Exception e){
            e.printStackTrace();
        }
        pacman.setImage("pacman_sprite_resized" +Integer.toString(size)+ ".png");
    }

}
