import greenfoot.*;
import java.util.List;
import java.util.Iterator;

/**
 * A classe PacMan representa o personagem do usuário no jogo, controlado pelo teclado
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class PacMan extends Personagem
{
    // Variável para controlar o deslocamento no array de sprites.
    private int offset = 0;

    // Array para controlar a ordem de exibição dos sprites.
    private final static int[] animationOffset = { 0, 1, 2, 1 };
    
    // Armazena a quantidade de turnos que já se passaram desde que o jogador tentou mudar de direção.
    private int contadorDirection = 0;
    
    // Controla em até quantos turnos o comando do jogador de mudar de direção será executado (se possível).
    // Valores maiores suavizam a tomada de direção do personagem, valores menores diminuem a janela de tempo para o jogador apertar das teclas.
    private static final int delayDirection = 6;
    
    // Armazena o última direção escolhida pelo jogador.
    private int possibleDirection = Personagem.WEST;
    
    // Sprites do pacman olhando para o norte.
    private final static GreenfootImage[] spritesNORTH = new GreenfootImage[] 
    {   
        new GreenfootImage("north_0.png"),
        new GreenfootImage("north_1.png"), 
        new GreenfootImage("north_2.png") 
    };

    // Sprites do pacman olhando para o sul.
    private final static GreenfootImage[] spritesSOUTH = new GreenfootImage[] 
    {   
        new GreenfootImage("south_0.png"),
        new GreenfootImage("south_1.png"), 
        new GreenfootImage("south_2.png") 
    };

    // Sprites do pacman olhando para o leste.
    private final static GreenfootImage[] spritesEAST = new GreenfootImage[] 
    {   
        new GreenfootImage("east_0.png"),
        new GreenfootImage("east_1.png"), 
        new GreenfootImage("east_2.png") 
    };

    // Sprites do pacman olhando para o oeste.
    private final static GreenfootImage[] spritesWEST = new GreenfootImage[] 
    {   
        new GreenfootImage("west_0.png"),
        new GreenfootImage("west_1.png"), 
        new GreenfootImage("west_2.png") 
    };
        
    /**
     * Inicializa o pacman com velocidade 3 e direção WEST.
     */
    public PacMan()
    {
        // Define a velocidade do pacman com o construtor da classe pai (Personagem).
        super(3);

        // Muda a direção que o pacman está olhando para o leste.
        changeDirection(Personagem.WEST);

        // Define a imagem como o sprite west_1.
        setImage(spritesWEST[1]);
    }
    
    /**
     * Consome os objetos tipo Pastilha que estejam num raio de 1 célula da
     * localização do pacman.
     * 
     * @return true se o pacman encontrar alguma comida, false caso não.
     */
    private boolean foundFood()
    {
        // Cria uma lista que recebe objetos do tipo Actor e insere todos os
        // objetos do tipo pastilha em um raio de 1 célula de distância.
        List<Actor> food = getObjectsInRange(1, Pastilha.class);
        
        // Se o tamanho da lista, ou seja, a quantidade de pastilhas próximas
        // for maior que 0, entra no if.
        if(food.size() > 0)
        {
            // Cria um iterator da lista food e armazena na variável it.
            Iterator it = food.iterator();
            
            // Enquanto houver próximo elemento no iterator, remove o Actor,
            // referente a esse elemento, do mundo.
            while (it.hasNext())
            {
                // Remove o Actor referente ao elemento do iterator.
                getWorld().removeObject((Actor) it.next());
            }
            
            // Retorna true se todas as pastilhas ao redor do pacman tiverem
            // sido removidas.
            return true;
        }

        // Retorn false se não houver pastilhas próximas do pacman.
        return false;
    }
    
    /**
     * Verifica o teclado em busca de direções para cima, baixo, esqueda ou direita.
     */
    private void verificarTeclado()
    {
        // Armazena a direção que a tecla sendo pressionada aponta.
        // up, down, riqht, left.
        String key = Greenfoot.getKey();

        // Se a tecla pressionada não for null, executa o if.
        if (key != null)
        {
            // Define o sprite do pacman para 1.
            offset = 1;

            // Se a tecla sendo pressionada é para cima, armazena a direção para
            // que o pacman olha como o norte.
            if (key.equals("up"))
            {
                // Armazena a direção que o pacman olha como norte.
                possibleDirection = Personagem.NORTH;
                
                // Zera a quantidade de turnos que o personagem passou desde
                // que apertou a tecla.
                contadorDirection = 0;
            }

            // Se a tecla sendo pressionada é para baixo, armazena a direção para
            // que o pacman olha como o sul.
            if (key.equals("down"))
            {
                // Armazena a direção que o pacman olha como sul.
                possibleDirection = Personagem.SOUTH;

                // Zera a quantidade de turnos que o personagem passou desde
                // que apertou a tecla.
                contadorDirection = 0;
            }
            
            // Se a tecla sendo pressionada é para direita, armazena a direção para
            // que o pacman olha como o leste.
            if (key.equals("right"))
            {
                // Armazena a direção que o pacman olha como leste.
                possibleDirection = Personagem.EAST;

                // Zera a quantidade de turnos que o personagem passou desde
                // que apertou a tecla.
                contadorDirection = 0;
            }

            // Se a tecla sendo pressionada é para esquerda, armazena a direção para
            // que o pacman olha como o oeste.
            if (key.equals("left"))
            {
                // Armazena a direção que o pacman olha como oeste.
                possibleDirection = Personagem.WEST;

                // Zera a quantidade de turnos que o personagem passou desde
                // que apertou a tecla.
                contadorDirection = 0;
            }
        }
    }
    
    /**
     * Faz o pac-man agir: consome objetos pastilha, aplica efeito sonoro, verifica mudança de direção, move e muda os sprites do personagem.
     */
    @Override
    public void act()
    {
        // Se o pacman encontrar pastilhas e comê-las, toca um efeito sonoro.
        if (foundFood())
        {
            // Efeito sonoro.
            SoundPlayer.playEffectPillEaten();
            //Greenfoot.playSound("pill_eaten.wav");
        }

        // Verifica se alguma tecla foi pressionada para mudar a direção do pacman.
        verificarTeclado();

        // Se a quantidade de turnos que passou desde que o usuário apertou a tecla
        // for menor que o tempo de delay (6), verifica se o pacman pode mover para
        // essa direção e, caso sim, muda de direção, caso não, aumenta em mais um
        // turno desde que o usuário apertou a tecla.
        if (contadorDirection < delayDirection)
        {
            // Se o pacman puder se mover para uma direção escolhida pelo usuário,
            // ele muda para essa direção.
            if (canMove(possibleDirection))
            {
                // Muda a direção que o pacman está olhando para uma direção
                // escolhida pelo usuário.
                changeDirection(possibleDirection);
            }
            
            // Caso o pacman não possa se mover para a direção escolhida pelo usuário,
            // o número de turnos que passou desde que o usuário apertou a tecla aumenta em um.
            else
            {
                // Aumenta o número de turnos que passou desde que o usuário apertou a tecla.
                contadorDirection++;
            }
        }

        // Se estiver na hora de mudar o sprite do pacman, verifica se a direção que o pacman está
        // olhando e define a imagem por um sprite.
        if (timeToChangeSprite())
        {
            // Aplica diferentes sprites para cada direção que o pacman pode olhar.
            switch (getDirection())
            {
                // Caso o pacman esteja olhando para o norte, define um imagem, utilizando os
                // spritesNORTH do pacman.
                case Personagem.NORTH:
                {
                    // Define a imagem do pacman como um spriteNORTH.
                    setImage(spritesNORTH[animationOffset[offset % 4]]);

                    // Sai do switch.
                    break;
                }

                // Caso o pacman esteja olhando para o sul, define um imagem, utilizando os
                // spritesSOUTH do pacman.
                case Personagem.SOUTH:
                {
                    // Define a imagem do pacman como um spriteSOUTH.
                    setImage(spritesSOUTH[animationOffset[offset % 4]]);

                    // Sai do switch.
                    break;
                }
                
                // Caso o pacman esteja olhando para o leste, define um imagem, utilizando os
                // spritesEAST do pacman.
                case Personagem.EAST:
                {
                    // Define a imagem do pacman como um spriteEAST.
                    setImage(spritesEAST[animationOffset[offset % 4]]);

                    // Sai do switch.
                    break;
                }
                
                // Caso o pacman esteja olhando para o oeste, define um imagem, utilizando os
                // spritesWEST do pacman.
                case Personagem.WEST:
                {
                    // Define a imagem do pacman como um spriteWEST.
                    setImage(spritesWEST[animationOffset[offset % 4]]);

                    // Sai do switch.
                    break;
                }
            }

            // Aumenta o controlador de deslocamento de sprites em 1.
            offset++;
        }

        // Utiliza o método act() da classe pai (Personagem), para mover o pacman
        // na direção em que ele está olhando.
        super.act();
    }
}
