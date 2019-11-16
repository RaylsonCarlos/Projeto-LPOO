
import greenfoot.*;

import greenfoot.World;

/**
 * Classe para controlar o fluxo do jogo.
 *
 * @author Carlos, Raylson, Weydson
 * @version 1.0
 */
public class GameController {

    //Armazena a instância do mundo controlada por essa classe.
    private static World world;
    //Armazena a quantidade de pontos do jogador.
    private static int points;
    private static Actor placar;

    /**
     * Cria o objeto que vai gerenciar o jogo.
     *
     * @param world
     */
    public GameController(World world) {
        this.world = world;
        points = 0;
        placar = new Actor() {
            public void act() {
            }
        };
        world.addObject(placar, 40, 65);
        score(0);
        inicio();
    }

    /**
     * Providencia a animação inicial do jogo.
     */
    private void inicio() {
        jogo();
    }

    /**
     * Providencia o ambiente do jogo.
     */
    private void jogo() {

    }

    /**
     * Finaliza o jogo.
     */
    public static void fim() {
        String msg = "O jogo acabou!\n tá okey?!\n você fez \n";
        msg += Integer.toString(points);
        msg += "\n pontos.";
        world.showText(msg, 28, 33);
        world.repaint();
        SoundPlayer.getInstance().playEffectPacmanIntermission();
        Greenfoot.stop();
    }

    public static void score(int pointsToAdd) {
        points += pointsToAdd;
        String msg1 = "SCORE: " + points;
        GreenfootImage score = new GreenfootImage(msg1, 6 * world.getCellSize(), Color.WHITE, null);
        placar.setImage(score);
        world.repaint();
    }

}
