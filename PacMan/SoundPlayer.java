import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

/**
 * Write a description of class SoundPlayer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundPlayer  {  
    private Clip clipBackground;
    private Clip clipEffect;
    private static final File backgroundNormal = new File("sounds/background_normal.wav");
    private static final File backgroundEyes = new File("sounds/background_eyes.wav");
    private static final File backgroundFrightened = new File("sounds/background_ghosts_frightened");

    /**
     * Constructor for objects of class SoundPlayer
     */
    public SoundPlayer(){
        try {
            clipBackground = AudioSystem.getClip();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Para a execução do som de fundo.
     */
    public void stop(){
        clipBackground.stop();
        clipBackground.setFramePosition(0);
    }
    
    /**
     * Executa o som de fundo normal do labirinto.
     */
    public void playBackgroundNormal(){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(backgroundNormal);
            clipBackground.open(ais);
            clipBackground.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
