import greenfoot.*;
import java.awt.Color;
import greenfoot.World;


/**
 * Classe para controlar o fluxo do jogo.
 * 
 * @author Carlos, Raylson, Weydson
 * @version 1.0
 */
public class GameController {
    //Armazena a instância do mundo controlada por essa classe.
    private World world;
    //Armazena a quantidade de pontos do jogador.
    private int pontos;

    /**
     * Cria o objeto que vai gerenciar o jogo.
     */
    public GameController(World world){
        this.world = world;
        pontos = 0;
        inicio();

        score();


    }
    /**
     * Providencia a animação inicial do jogo.
     */
    private void inicio(){
        jogo();
    }
    /**
     * Providencia o ambiente do jogo.
     */
    private void jogo(){
        
    }
    /**
     * Finaliza o jogo.
     */
    private void fim(){
        
    }
    

    public void score(){
        String msg1 = "SCORE:";
        GreenfootImage score = new GreenfootImage(msg1,6*world.getCellSize(),Color.WHITE,null);
        Actor actor1 = new Actor(){public void act(){}};
        actor1.setImage(score);
        this.world.addObject(actor1,40,65);
    }
}

