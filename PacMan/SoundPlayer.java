import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import greenfoot.GreenfootSound;

/**
 * Classe que prepara e executa os efeitos sonoros do jogo.
 * 
 * @author Carlos, Raylson, Weydson
 * @version 1.1
 */
public class SoundPlayer  {
    private static final GreenfootSound backgroundNormal = new GreenfootSound("sounds/background_normal.wav");
    private static final GreenfootSound backgroundEyes = new GreenfootSound("sounds/background_eyes.wav");
    private static final GreenfootSound backgroundFrightened = new GreenfootSound("sounds/background_ghosts_frightened.wav");    
    private static final GreenfootSound pacmanDeath = new GreenfootSound("sounds/pacman_death.wav");
    private static final GreenfootSound ghostEaten = new GreenfootSound("sounds/ghost_eaten.wav");
    private static final GreenfootSound pillEaten = new GreenfootSound("sounds/pill_eaten.wav");
    private static final GreenfootSound pacmanBeginning = new GreenfootSound("sounds/pacman_beginning.wav");
    private static final GreenfootSound pacmanIntermission = new GreenfootSound("sounds/pacman_intermission.wav");
    private static final GreenfootSound youCegoMan = new GreenfootSound("sounds/you_cego_man.wav");
    private static final GreenfootSound jaAvisei = new GreenfootSound("sounds/ja_avisei.wav");

    /**
     * Para a execução do som de fundo.
     */
    public static void stop(){
        if(backgroundNormal != null){
            backgroundNormal.stop();
        }
        if(backgroundEyes != null){
            backgroundEyes.stop();
        }
        if(backgroundFrightened != null){
            backgroundFrightened.stop();
        }
    }

    /**
     * Executa o som de fundo normal do labirinto.
     */
    public static void playBackgroundNormal(){       
        backgroundNormal.playLoop();
    }

    /**
     * Executa o som de fundo em que um fantasma foi capturado.
     */    
    public static void playBackgroundEyes(){
        backgroundEyes.playLoop();
    }

    /**
     * Executa o som de fundo em que os fantasmas estão amedrontados.
     */
    public static void playBackgroundFrightened(){
        backgroundFrightened.playLoop();
    }

    /**
     * Executa o efeito sonoro da morte do pacman.
     */
    public static void playEffectPacmanDeath(){
        pacmanDeath.play();        
    }

    /**
     * Executa o efeito sonoro quando do pacman comendo um fantasma.
     */
    public static void playEffectGhostEaten(){
        ghostEaten.play();
    }

    /**
     * Executa o efeito sonoro de uma pastilha sendo comida.
     */
    public static void playEffectPillEaten(){
        pillEaten.play();        
    }

    /**
     * Executa o efeito sonoro do início do jogo.
     */
    public static void playEffectPacmanBeginning(){     
        pacmanBeginning.play();
    }

    /**
     * Executa o efeito sonoro do final do jogo.
     */
    public static void playEffectPacmanIntermission(){
        pacmanIntermission.play();
    }

    /**
     * Executa o efeito sonoro YOU CEGO MAN!
     */
    public static void playEffectYouCegoMan(){
        youCegoMan.play();
    }

    /**
     * Executa o efeito sonoro JÁ AVISEI!
     */
    public static void playEffectJaAvisei(){
        jaAvisei.play();
    }
}
