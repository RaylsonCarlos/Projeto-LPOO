
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
        placar = new ImageActor(new GreenfootImage(String.valueOf(points), 6 * world.getCellSize(), Color.WHITE, null));
        world.addObject(placar, 40, 65);
        score(0);
    }

    /**
     * Finaliza o jogo.
     */
    public static void end() {
        String msg = "Game Over!\n"
                + "Você fez \n"
                + Integer.toString(points)
                + " pontos";
        ImageActor textGameOver = new ImageActor(new GreenfootImage(msg, 50, Color.WHITE, null));

        world.addObject(textGameOver, 28, 33);
        world.setPaintOrder(ImageActor.class);
        world.repaint();

        SoundPlayer.getInstance().playSound(SoundPlayer.PACMAN_ITERMISSION);
        Greenfoot.stop();
    }

    public static void score(int pointsToAdd) {
        points += pointsToAdd;
        String msg = "SCORE: " + points;
        GreenfootImage score = new GreenfootImage(msg, 6 * world.getCellSize(), Color.WHITE, null);
        placar.setImage(score);
        world.repaint();
    }

}
