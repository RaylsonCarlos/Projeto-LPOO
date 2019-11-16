
import greenfoot.GreenfootSound;

/**
 * Classe que prepara e executa os efeitos sonoros do jogo.
 *
 * @author Carlos, Raylson, Weydson
 * @version 1.1
 */
public class SoundPlayer {

    private GreenfootSound soundOfWind;
    private GreenfootSound backgroundNormal;
    private GreenfootSound backgroundEyes;
    private GreenfootSound backgroundFrightened;
    private GreenfootSound pacmanDeath;
    private GreenfootSound ghostEaten;
    private GreenfootSound pillEaten;
    private GreenfootSound pacmanBeginning;
    private GreenfootSound pacmanIntermission;
    private GreenfootSound youCegoMan;
    private GreenfootSound jaAvisei;
    
    //volatile to avoid cache reading this variable.
    private static volatile SoundPlayer instance;
    
    private SoundPlayer(){
        soundOfWind = new GreenfootSound("sounds/wind.wav");
        backgroundNormal = new GreenfootSound("sounds/background_normal.wav");
        backgroundEyes = new GreenfootSound("sounds/background_eyes.wav");
        backgroundFrightened = new GreenfootSound("sounds/background_ghosts_frightened.wav");
        pacmanDeath = new GreenfootSound("sounds/pacman_death.wav");
        ghostEaten = new GreenfootSound("sounds/ghost_eaten.wav");
        pillEaten = new GreenfootSound("sounds/pill_eaten.wav");
        pacmanBeginning = new GreenfootSound("sounds/pacman_beginning.wav");
        pacmanIntermission = new GreenfootSound("sounds/pacman_intermission.wav");
        youCegoMan = new GreenfootSound("sounds/you_cego_man.wav");
        jaAvisei = new GreenfootSound("sounds/ja_avisei.wav");
    }
    
    public static SoundPlayer getInstance(){
        //double lock checking!
        if(instance == null){
            synchronized(SoundPlayer.class){
                if(instance == null){
                    instance = new SoundPlayer();
                }
            }
        }
        return instance;
    }

    /**
     * Para a execução do som de fundo.
     */
    public void stop() {
        if (backgroundNormal != null) {
            backgroundNormal.stop();
        }
        if (backgroundEyes != null) {
            backgroundEyes.stop();
        }
        if (backgroundFrightened != null) {
            backgroundFrightened.stop();
        }
    }

    public void playSoundOfWind() {
        soundOfWind.playLoop();
    }

    /**
     * Informa se o som de fundo normal está sendo executado
     *
     * @return true se o som de fundo normal está sendo executado, false caso
     * contrário.
     */
    public boolean backgroundNormalIsPlaying() {
        return backgroundNormal.isPlaying();
    }

    /**
     * Informa se o som de fundo dos fantasmas amedrontados está sendo executado
     *
     * @return true se o som de fundo dos fantasmas amedrontados está sendo
     * executado, false caso contrário.
     */
    public boolean backgroundFrightenedIsPlaying() {
        return backgroundFrightened.isPlaying();
    }

    /**
     * Informa se o som de fundo quando um dos fantasmas foi capturado está
     * sendo executado
     *
     * @return true se o som de fundo quando um dos fantasmas foi capturado está
     * sendo executado, false caso contrário.
     */
    public boolean backgroundEyesIsPlaying() {
        return backgroundEyes.isPlaying();
    }

    /**
     * Executa o som de fundo normal do labirinto.
     */
    public void playBackgroundNormal() {
        backgroundNormal.playLoop();
    }

    /**
     * Executa o som de fundo em que um fantasma foi capturado.
     */
    public void playBackgroundEyes() {
        backgroundEyes.playLoop();
    }

    /**
     * Executa o som de fundo em que os fantasmas estão amedrontados.
     */
    public void playBackgroundFrightened() {
        backgroundFrightened.playLoop();
    }

    /**
     * Executa o efeito sonoro da morte do pacman.
     */
    public void playEffectPacmanDeath() {
        pacmanDeath.play();
    }

    /**
     * Executa o efeito sonoro quando do pacman comendo um fantasma.
     */
    public void playEffectGhostEaten() {
        ghostEaten.play();
    }

    /**
     * Executa o efeito sonoro de uma pastilha sendo comida.
     */
    public void playEffectPillEaten() {
        pillEaten.play();
    }

    /**
     * Executa o efeito sonoro do início do jogo.
     */
    public void playEffectPacmanBeginning() {
        pacmanBeginning.play();
    }

    /**
     * Executa o efeito sonoro do final do jogo.
     */
    public void playEffectPacmanIntermission() {
        pacmanIntermission.play();
    }

    /**
     * Executa o efeito sonoro YOU CEGO MAN!
     */
    public void playEffectYouCegoMan() {
        youCegoMan.play();
    }

    /**
     * Executa o efeito sonoro JÁ AVISEI!
     */
    public void playEffectJaAvisei() {
        jaAvisei.play();
    }
}
