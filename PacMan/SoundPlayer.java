
import greenfoot.GreenfootSound;

/**
 * Classe que prepara e executa os efeitos sonoros do jogo.
 *
 * @author Carlos, Raylson, Weydson
 * @version 1.1
 */
public class SoundPlayer {

    // Sons e efeitos sonoros do jogo.
    private final GreenfootSound soundOfWind;
    private final GreenfootSound backgroundNormal;
    private final GreenfootSound backgroundEyes;
    private final GreenfootSound backgroundFrightened;
    private final GreenfootSound pacmanDeath;
    private final GreenfootSound ghostEaten;
    private final GreenfootSound pelletEaten;
    private final GreenfootSound pacmanBeginning;
    private final GreenfootSound pacmanIntermission;

    // Representação pública de todos os sons e efeitos sonoros.
    public static final String SOUND_OF_WIND = "soundOfWind";
    public static final String BACKGROUND_NORMAL = "backgroundNormal";
    public static final String BACKGROUND_EYES = "backgroundEyes";
    public static final String BACKGROUND_FRIGHTENED = "backgroundFrightened";
    public static final String PACMAN_DEATH = "pacmanDeath";
    public static final String GHOST_EATEN = "ghostEaten";
    public static final String PELLET_EATEN = "pelletEaten";
    public static final String PACMAN_BEGINNING = "pacmanBeginning";
    public static final String PACMAN_ITERMISSION = "pacmanIntermission";

    //volatile to avoid cache reading this variable.
    private static volatile SoundPlayer instance;

    /**
     * Inicia os sons do jogo.
     */
    private SoundPlayer() {
        soundOfWind = new GreenfootSound("sounds/wind.wav");
        backgroundNormal = new GreenfootSound("sounds/background_normal.wav");
        backgroundEyes = new GreenfootSound("sounds/background_eyes.wav");
        backgroundFrightened = new GreenfootSound("sounds/background_ghosts_frightened.wav");
        pacmanDeath = new GreenfootSound("sounds/pacman_death.wav");
        ghostEaten = new GreenfootSound("sounds/ghost_eaten.wav");
        pelletEaten = new GreenfootSound("sounds/pellet_eaten.wav");
        pacmanBeginning = new GreenfootSound("sounds/pacman_beginning.wav");
        pacmanIntermission = new GreenfootSound("sounds/pacman_intermission.wav");
    }

    public static SoundPlayer getInstance() {
        //double lock checking!
        if (instance == null) {
            synchronized (SoundPlayer.class) {
                if (instance == null) {
                    instance = new SoundPlayer();
                }
            }
        }
        return instance;
    }

    /**
     * Reproduz um som do jogo.
     *
     * @param sound som disponível pelo SoundPlayer
     */
    public void playSound(String sound) {
        switch (sound) {
            case SOUND_OF_WIND:
                soundOfWind.playLoop();

                break;

            case BACKGROUND_NORMAL:
                backgroundNormal.playLoop();

                break;

            case BACKGROUND_EYES:
                backgroundEyes.playLoop();

                break;

            case BACKGROUND_FRIGHTENED:
                backgroundFrightened.playLoop();

                break;

            case PACMAN_DEATH:
                pacmanDeath.play();

                break;

            case GHOST_EATEN:
                ghostEaten.play();

                break;

            case PELLET_EATEN:
                pelletEaten.play();

                break;

            case PACMAN_BEGINNING:
                pacmanBeginning.play();

                break;

            case PACMAN_ITERMISSION:
                pacmanIntermission.play();

                break;

            default:
                break;
        }
    }

    /**
     * Verifica se um som do jogo está tocando.
     *
     * @param sound som disponível pelo SoundPlayer
     * @return true se o som está tocando, false caso não
     */
    public boolean getSound(String sound) {
        switch (sound) {
            case SOUND_OF_WIND:
                return soundOfWind.isPlaying();

            case BACKGROUND_NORMAL:
                return backgroundNormal.isPlaying();

            case BACKGROUND_EYES:
                return backgroundEyes.isPlaying();

            case BACKGROUND_FRIGHTENED:
                return backgroundFrightened.isPlaying();

            case PACMAN_DEATH:
                return pacmanDeath.isPlaying();

            case GHOST_EATEN:
                return ghostEaten.isPlaying();

            case PELLET_EATEN:
                return pelletEaten.isPlaying();

            case PACMAN_BEGINNING:
                return pacmanBeginning.isPlaying();

            case PACMAN_ITERMISSION:
                return pacmanIntermission.isPlaying();

            default:
                return false;
        }
    }

    /**
     * Pausa um som do jogo.
     *
     * @param sound som disponível pelo SoundPlayer
     */
    public void stopSound(String sound) {
        switch (sound) {
            case SOUND_OF_WIND:
                soundOfWind.stop();

                break;

            case BACKGROUND_NORMAL:
                backgroundNormal.stop();

                break;

            case BACKGROUND_EYES:
                backgroundEyes.stop();

                break;

            case BACKGROUND_FRIGHTENED:
                backgroundFrightened.stop();

                break;

            case PACMAN_DEATH:
                pacmanDeath.stop();

                break;

            case GHOST_EATEN:
                ghostEaten.stop();

                break;

            case PELLET_EATEN:
                pelletEaten.stop();

                break;

            case PACMAN_BEGINNING:
                pacmanBeginning.stop();

                break;

            case PACMAN_ITERMISSION:
                pacmanIntermission.stop();

                break;

            default:
                break;
        }
    }

    /**
     * Para a execução do som de fundo.
     */
    public void stopBackgroundSound() {
        stopSound(BACKGROUND_NORMAL);
        stopSound(BACKGROUND_EYES);
        stopSound(BACKGROUND_FRIGHTENED);
    }
}
