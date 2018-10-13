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
    private static Clip backgroundNormal;
    private static Clip backgroundEyes;
    private static Clip backgroundFrightened;
    private static Clip effectPillEaten;
    private static Clip effectGhostEaten;
    private static Clip effectPacmanDeath;
    private static Clip effectPacmanBeginning;
    private static Clip effectPacmanIntermission;    
    private static final File normal = new File("sounds/background_normal.wav");
    private static final File eyes = new File("sounds/background_eyes.wav");
    private static final File frightened = new File("sounds/background_ghosts_frightened.wav");
    private static final File pacmanDeath = new File("sounds/pacman_death.wav");
    private static final File ghostEaten = new File("sounds/ghost_eaten.wav");
    private static final File pillEaten = new File("sounds/pill_eaten.wav");
    private static final File pacmanBeginning = new File("sounds/pacman_beginning.wav");
    private static final File pacmanIntermission = new File("sounds/pacman_intermission.wav");

    /**
     * Cria um objeto que executa os efeitos sonoros do jogo.
     */
    public SoundPlayer(){
        try {
            backgroundNormal = AudioSystem.getClip();
            backgroundNormal.open(AudioSystem.getAudioInputStream(normal));
            backgroundEyes = AudioSystem.getClip();
            backgroundEyes.open(AudioSystem.getAudioInputStream(eyes));
            backgroundFrightened = AudioSystem.getClip();
            backgroundFrightened.open(AudioSystem.getAudioInputStream(frightened));
            effectPillEaten = AudioSystem.getClip();
            effectPillEaten.open(AudioSystem.getAudioInputStream(pillEaten));
            effectGhostEaten = AudioSystem.getClip();
            effectGhostEaten.open(AudioSystem.getAudioInputStream(ghostEaten));
            effectPacmanDeath = AudioSystem.getClip();
            effectPacmanDeath.open(AudioSystem.getAudioInputStream(pacmanDeath));
            effectPacmanBeginning = AudioSystem.getClip();
            effectPacmanBeginning.open(AudioSystem.getAudioInputStream(pacmanBeginning));
            effectPacmanIntermission = AudioSystem.getClip();
            effectPacmanIntermission.open(AudioSystem.getAudioInputStream(pacmanIntermission));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Para a execução do som de fundo.
     */
    public static void stop(){
        backgroundNormal.stop();
        backgroundNormal.setFramePosition(0);
        backgroundEyes.stop();
        backgroundEyes.setFramePosition(0);
        backgroundFrightened.stop();
        backgroundFrightened.setFramePosition(0);
    }

    /**
     * Executa o som de fundo normal do labirinto.
     */
    public static void playBackgroundNormal(){
        backgroundNormal.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Executa o som de fundo quando algum fantasma é comido.
     */    
    public static void playBackgroundEyes(){
        backgroundEyes.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Executa o som de fundo quando os fantasmas estão com medo.
     */
    public static void playBackgroundFrightened(){
        backgroundFrightened.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Executa o efeito sonoro quando o pacman morre.
     */
    public static void playEffectPacmanDeath(){
        if(!effectPacmanDeath.isRunning()){
            effectPacmanDeath.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro quando o pacman come um fantasma.
     */
    public static void playEffectGhostEaten(){
        if(!effectGhostEaten.isRunning()){            
            effectGhostEaten.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro quando uma pastilha é comida.
     */
    public static void playEffectPillEaten(){
        if(!effectPillEaten.isRunning()){            
            effectPillEaten.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro quando o jogo vai iniciar.
     */
    public static void playEffectPacmanBeginning(){
        if(!effectPacmanBeginning.isRunning()){           
            effectPacmanBeginning.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro quando o jogo termina.
     */
    public static void playEffectPacmanIntermission(){
        if(!effectPacmanIntermission.isRunning()){
            effectPacmanIntermission.loop(1);
        }
    }
}
