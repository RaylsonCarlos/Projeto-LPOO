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
    private static GreenfootSound backgroundNormal;
    private static GreenfootSound backgroundEyes;
    private static GreenfootSound backgroundFrightened;
    private static Clip effectPillEaten;
    private static Clip effectGhostEaten;
    private static Clip effectPacmanDeath;
    private static Clip effectPacmanBeginning;
    private static Clip effectPacmanIntermission;
    private static Clip effectYouCegoMan;
    private static Clip effectJaAvisei;    
    private static final File pacmanDeath = new File("sounds/pacman_death.wav");
    private static final File ghostEaten = new File("sounds/ghost_eaten.wav");
    private static final File pillEaten = new File("sounds/pill_eaten.wav");
    private static final File pacmanBeginning = new File("sounds/pacman_beginning.wav");
    private static final File pacmanIntermission = new File("sounds/pacman_intermission.wav");
    private static final File youCegoMan = new File("sounds/you_cego_man.wav");
    private static final File jaAvisei = new File("sounds/ja_avisei.wav");    

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
        backgroundNormal = new GreenfootSound("sounds/background_normal.wav");
        backgroundNormal.playLoop();
    }

    /**
     * Executa o som de fundo em que um fantasma foi capturado.
     */    
    public static void playBackgroundEyes(){
        backgroundEyes = new GreenfootSound("sounds/background_eyes.wav");
        backgroundEyes.playLoop();
    }

    /**
     * Executa o som de fundo em que os fantasmas estão amedrontados.
     */
    public static void playBackgroundFrightened(){
        backgroundFrightened = new GreenfootSound("sounds/background_ghosts_frightened.wav");
        backgroundFrightened.playLoop();
    }

    /**
     * Executa o efeito sonoro da morte do pacman.
     */
    public static void playEffectPacmanDeath(){      
        if(effectPacmanDeath==null){
            try{
                effectPacmanDeath = AudioSystem.getClip();
                effectPacmanDeath.open(AudioSystem.getAudioInputStream(pacmanDeath));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(!effectPacmanDeath.isRunning()){
            effectPacmanDeath.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro quando do pacman comendo um fantasma.
     */
    public static void playEffectGhostEaten(){
        if(effectGhostEaten==null){
            try{
                effectGhostEaten = AudioSystem.getClip();
                effectGhostEaten.open(AudioSystem.getAudioInputStream(ghostEaten));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(!effectGhostEaten.isRunning()){            
            effectGhostEaten.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro de uma pastilha sendo comida.
     */
    public static void playEffectPillEaten(){       
        if(effectPillEaten==null){
            try{
                effectPillEaten = AudioSystem.getClip();
                effectPillEaten.open(AudioSystem.getAudioInputStream(pillEaten));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(!effectPillEaten.isRunning()){            
            effectPillEaten.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro do início do jogo.
     */
    public static void playEffectPacmanBeginning(){     
        if(effectPacmanBeginning==null){
            try{
                effectPacmanBeginning = AudioSystem.getClip();
                effectPacmanBeginning.open(AudioSystem.getAudioInputStream(pacmanBeginning));

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(!effectPacmanBeginning.isRunning()){

            effectPacmanBeginning.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro do final do jogo.
     */
    public static void playEffectPacmanIntermission(){
        if(effectPacmanIntermission==null){
            try{
                effectPacmanIntermission = AudioSystem.getClip();
                effectPacmanIntermission.open(AudioSystem.getAudioInputStream(pacmanIntermission));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(!effectPacmanIntermission.isRunning()){
            effectPacmanIntermission.loop(1);
        }
    }

    /**
     * Executa o efeito sonoro YOU CEGO MAN!
     */
    public static void playEffectYouCegoMan(){
        if(effectYouCegoMan==null){
            try{
                effectYouCegoMan = AudioSystem.getClip();
                effectYouCegoMan.open(AudioSystem.getAudioInputStream(youCegoMan));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(!effectYouCegoMan.isRunning()){
            effectYouCegoMan.loop(1);
        }
    }
    /**
     * Executa o efeito sonoro JÁ AVISEI!
     */
    public static void playEffectJaAvisei(){
        if(effectJaAvisei == null){
            try {
                effectJaAvisei = AudioSystem.getClip();
                effectJaAvisei.open(AudioSystem.getAudioInputStream(jaAvisei));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!effectJaAvisei.isRunning()){
            effectJaAvisei.loop(1);
        }
    }
}
