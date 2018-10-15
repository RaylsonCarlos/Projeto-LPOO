import greenfoot.World;

/**
 * Classe para controlar o fluxo do jogo.
 * 
 * @author Carlos, Raylson, Weydson
 * @version 1.0
 */
public class GameController
{
    //Armazena a instância do mundo controlada por essa classe.
    private World world;

    //Armazena a quantidade de pontos do jogador.
    private int pontos;

    /**
     * Cria o objeto que vai gerenciar o jogo.
     * 
     * @param world o mundo que o GameController vai gerenciar.
     */
    public GameController(World world)
    {
        // Define o mundo a ser controlado.
        this.world = world;

        // Zera a quantidade de pontos.
        pontos = 0;

        // Animação inicial do jogo
        inicio();
    }

    /**
     * Providencia a animação inicial do jogo.
     */
    private void inicio()
    {
        jogo();

        // TODO fazer depois.
    }

    /**
     * Providencia o ambiente do jogo.
     */
    private void jogo()
    {
        // TODO fazer depois.
    }
    /**
     * Finaliza o jogo.
     */
    private void fim()
    {
        // TODO fazer depois.
    }
    
   
    
    
}
