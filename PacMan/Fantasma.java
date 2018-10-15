import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A Classe Fantasma representa os fantasmas do labirinto.
 * 
 * Os fantasmas se movem pelo labirinto em busca do pacman.
 * 
 * @author Raylson, Carlos, Weydson
 * @version 2.0
 */
public class Fantasma extends Personagem
{
    // Cor vermelha.
    public final static int RED = 0;

    // Cor rosa.
    public final static int PINK = 1;

    // Cor azul.
    public final static int BLUE = 2;
    
    // Cor marrom.
    public final static int BROWN = 3;
    
    // Variável para armazenar a cor do fantasma.
    private int color;
    
    // Armazena o deslocamento dentro do array de sprites do fantasma.
    private int offset = 0;

    // Sprites do fantasma vermelho.
    private final static GreenfootImage [][] spritesRed = new GreenfootImage[][]
    {
        {new GreenfootImage("ghost_red_east_0.png"),new GreenfootImage("ghost_red_east_1.png")},
        {new GreenfootImage("ghost_red_west_0.png"),new GreenfootImage("ghost_red_west_1.png")},
        {new GreenfootImage("ghost_red_north_0.png"),new GreenfootImage("ghost_red_north_1.png")},
        {new GreenfootImage("ghost_red_south_0.png"),new GreenfootImage("ghost_red_south_1.png")}
    };
                                                                                
    // Sprites do fantasma rosa.
    private final static GreenfootImage [][] spritesPink = new GreenfootImage[][]
    {
        {new GreenfootImage("ghost_pink_east_0.png"),new GreenfootImage("ghost_pink_east_1.png")},
        {new GreenfootImage("ghost_pink_west_0.png"),new GreenfootImage("ghost_pink_west_1.png")},
        {new GreenfootImage("ghost_pink_north_0.png"),new GreenfootImage("ghost_pink_north_1.png")},
        {new GreenfootImage("ghost_pink_south_0.png"),new GreenfootImage("ghost_pink_south_1.png")}
    };

    // Sprites do fantasma azul.
    private final static GreenfootImage [][] spritesBlue = new GreenfootImage[][]
    {
        {new GreenfootImage("ghost_blue_east_0.png"),new GreenfootImage("ghost_blue_east_1.png")},
        {new GreenfootImage("ghost_blue_west_0.png"),new GreenfootImage("ghost_blue_west_1.png")},
        {new GreenfootImage("ghost_blue_north_0.png"),new GreenfootImage("ghost_blue_north_1.png")},
        {new GreenfootImage("ghost_blue_south_0.png"),new GreenfootImage("ghost_blue_south_1.png")}
    };

   // Sprites do fantasma marrom.
    private final static GreenfootImage [][] spritesBrown = new GreenfootImage[][]
    {
        {new GreenfootImage("ghost_brown_east_0.png"),new GreenfootImage("ghost_brown_east_1.png")},
        {new GreenfootImage("ghost_brown_west_0.png"),new GreenfootImage("ghost_brown_west_1.png")},
        {new GreenfootImage("ghost_brown_north_0.png"),new GreenfootImage("ghost_brown_north_1.png")},
        {new GreenfootImage("ghost_brown_south_0.png"),new GreenfootImage("ghost_brown_south_1.png")}
    };

   // Sprites dos fantasmas.
    private final static GreenfootImage[][][] sprites = new GreenfootImage[][][]
    {
        spritesRed,
        spritesPink,
        spritesBlue,
        spritesBrown
    };
    
    /**
     * Construtor do fantasma.
     * 
     * Cria um fantasma com uma velocidade padrão de 3.
     * 
     * @param color cor do fantasma: RED, PINK, BLUE ou BROWN.
     */
    public Fantasma(int color)
    {
        // Chama o construtor da classe pai (Personagem).
        // Define quantos turnos o fantasma tem antes de mudar de sprite.
        super(3);

        // Se a cor do fantasma não for nem RED, PINK, BLUE ou BROWN, define a cor como RED.
        if(color != RED && color != PINK && color != BLUE && color != BROWN)
        {
            // Define a cor como RED.
            this.color = RED;
        }

        // Caso contrário, define a cor como a dada no parâmetro (RED, PINK, BLUE ou BROWN).
        else
        {
            // Define a cor como a dada no parâmetro do construtor.
            this.color = color;
        }

        // Chama o método que muda as imagens do fantasmas, produzindo animações.
        setSprite();
    }
    
    /**
     * Muda o sprite do fantasma.
     */
    private void setSprite()
    {
        // O switch recebe e avalia o retorno do método getDirection(), implementado
        // na classe pai (Personagem), e define uma instrução para cada valor de
        // retorno.
        switch(getDirection())
        {
            // NORTH, SOUTH, EAST e WEST são variáveis públicas com valor definido
            // na classe pai (Personagem).

            // Caso o valor seja NORTH (0).
            case Personagem.NORTH:
            {
                // Define a imagem como um sprite de um fantasma de determinada cor.
                setImage(sprites[color][2][offset%2]);
                
                // Sai do switch.
                break;
            }

            // Caso o valor seja SOUTH (1).
            case Personagem.SOUTH:
            {
                // Define a imagem como um sprite de um fantasma de determinada cor.
                setImage(sprites[color][3][offset%2]);

                // Sai do switch.
                break;
            }
            
            // Caso o valor seja EAST (2).
            case Personagem.EAST:
            {
                // Define a imagem como um sprite de um fantasma de determinada cor.
                setImage(sprites[color][0][offset%2]);

                // Sai do switch.
                break;
            }

            // Caso o valor seja WEST (3).
            case Personagem.WEST:
            {
                // Define a imagem como um sprite de um fantasma de determinada cor.
                setImage(sprites[color][1][offset%2]);

                // Sai do switch.
                break;
            }
        }
    }
    
    /**
     * Informa a direção oposta à direção que o personagem está encarando.
     * 
     * Direções: NORTH (0), SOUTH (1), EAST (2) ou WEST (3).
     * 
     * @param direction um inteiro que define a direção que o fantasma está olhando.
     * @return um inteiro que descreve a direção.
     */
    private int oppositeDirection(int direction)
    {
        // Define a direção oposta padrão como norte.
        int oppositeDirection = Personagem.NORTH;

        // O switch recebe e avalia a direção em que o fantasma está olhando, então
        // executa instruções para cada valor da variável direction.
        switch(direction)
        {
            // Caso o valor seja NORTH (0).
            case Personagem.NORTH:
            {
                // Define a direção oposta como SOUTH.
                oppositeDirection = Personagem.SOUTH;

                // Sai do switch.
                break;
            }

            // Caso o valor seja SOUTH (1).
            case Personagem.SOUTH:
            {
                // Define a direção oposta como NORTH.
                oppositeDirection = Personagem.NORTH;

                // Sai do switch.
                break;
            }

            // Caso o valor seja EAST (2).
            case Personagem.EAST:
            {
                // Define a direção oposta como WEST.
                oppositeDirection = Personagem.WEST;

                // Sai do switch.
                break;
            }

            // Caso o valor seja WEST (3).
            case Personagem.WEST:
            {
                // Define a direção oposta como EAST.
                oppositeDirection = Personagem.EAST;

                // Sai do switch.
                break;
            }
        }

        // Retorna a direção oposta.
        return oppositeDirection;
    }
    
    /**
     * Informa se o fantasma está dentro da cela do labirinto.
     * 
     * @return true se o fantasma estiver dentro da cela, false caso não esteja.
     */
    private boolean preso()
    {
        // Variáveis que armazenam as células X e Y do fantasma.
        int x = getX();
        int y = getY();
        
        // Se a célula X estiver entre 22 e 34, e a célula Y estiver entre
        // 26 e 32, retorna true.
        if(x > 22 && x < 34 && y > 26 && y < 32)
        {
            // Retorna true.
            return true;
        }
        
        // Caso contrário, retorna false.
        else
        {
            // Retorna false.
            return false;
        }
    }
    
    /**
     * Define como o fantasma deve se movimentar se estiver preso na cela do labirinto
     */
    private void rotaPreso()
    {
        // Caso o fantasma não possa se mover para o NORTH, muda sua direção para o SOUTH.
        if(!canMoveNorth())
        {
            // Muda sua direção para o SOUTH.
            changeDirection(Personagem.SOUTH);
        }

        // Caso o fantasma não possa se mover para o SOUTH, muda sua direção para o NORTH.
        if(!canMoveSouth())
        {
            // Muda sua direção para o NORTH.
            changeDirection(Personagem.NORTH);
        }
    }
    
    /**
     * Define como o fantasma deve se movimentar se estiver fora da cela.
     */
    private void rotaNormal()
    {
        // Cria uma lista de inteiros, chamada rotas.
        // Essa lista irá armazenar os valores para cada direção
        // em que o fantasma pode se mover.
        List<Integer> rotas = new ArrayList<Integer>();

        // Armazena a direção que o fantasma está olhando.
        int direction = getDirection();

        // Armazena a direção oposta à que o fantasma está olhando.
        int oppositeDirection = oppositeDirection(direction);
        
        // Se o fanstasma puder se mover para o norte, adiciona o valor
        // que referencia ao personagem olhando para o norte.
        if(canMoveNorth())
        {
            // Adiciona o valor 0 (NORTH) à lista de rotas possivéis
            rotas.add(Personagem.NORTH);
        }
        
        // Se o fanstasma puder se mover para o sul, adiciona o valor
        // que referencia ao personagem olhando para o sul.
        if(canMoveSouth())
        {
            // Adiciona o valor 1 (SOUTH) à lista de rotas possivéis
            rotas.add(Personagem.SOUTH);
        }
        
        // Se o fanstasma puder se mover para o leste, adiciona o valor
        // que referencia ao personagem olhando para o leste.
        if(canMoveEast())
        {
            // Adiciona o valor 2 (EAST) à lista de rotas possivéis
            rotas.add(Personagem.EAST);
        }
        
        // Se o fanstasma puder se mover para o oeste, adiciona o valor
        // que referencia ao personagem olhando para o oeste.
        if(canMoveWest())
        {
            // Adiciona o valor 3 (WEST) à lista de rotas possivéis
            rotas.add(Personagem.WEST);
        }
        
        // Se o tamanho da lista de rotas for diferente de 0, entra no if.
        if(rotas.size() != 0)
        {
            // Se o tamanho da lista de rotas for maior ou igual a 2,
            // remove o elemento que está na posição de valor da direção
            // oposta ao que o fantasma está olhando.
            if(rotas.size() >= 2)
            {
                // Remove da lista de rotas o elemento que está no índice da
                // direção oposta ao que o fanstasma está olhando.
                rotas.remove(Integer.valueOf(oppositeDirection));
            }            
            
            // Armazena um número aleatório entre 0 e o tamanho da lista de
            // rotas.
            int rand = Greenfoot.getRandomNumber(rotas.size());
            
            // Muda a direção do fantasma para a direção que está num índice
            // aleatório, definido por rand.
            changeDirection(rotas.get(rand));
        }
    }
    
    /**
     * Faz o fantasma se mover e mudar os sprites do personagem.
     */
    public void act()
    {
        // Se o fantasma está preso, ele se move conforme o método preso() permite.
        if(preso())
        {
            // Define o movimento do fantasma preso.
            rotaPreso();
        }
        
        // Caso o fantasma não esteja preso, ele se move conforme o método rotaNormal 
        // permite.
        else
        {
            // Define o movimento do fanstasma solto.
            rotaNormal();
        }

        // Se estiver na hora de mudar o turno, define o próximo sprite do fantasma
        // e incrementa mais uma mudança de sprites dos fantasmas.
        if(timeToChangeSprite())
        {
            // Define o sprite do fantasma, dependendo da direção em que ele
            // está olhando.
            setSprite();

            // Registra mais uma mudança de sprite dos fantasmas.
            offset++;
        }
        
        // Utiliza o método act() da classe pai (Personagem), para mover o fantasma
        // na direção em que ele está olhando.
        super.act();
    }    
}
