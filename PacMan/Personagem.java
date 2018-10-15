import greenfoot.*;

/**
 * A classe Personagem providencia os movimentos e animações básicas dos personagens.
 * 
 * @author Raylson, Carlos, Weydson
 * @version 1.3
 */
public class Personagem extends Actor
{
    // Armazena a direção norte como o inteiro 0.
    public static final int NORTH = 0;
    
    // Armazena a direção sul como o inteiro 1.
    public static final int SOUTH = 1;
    
    // Armazena a direção leste como o inteiro 2.
    public static final int EAST = 2;
    
    // Armazena a direção oeste como o inteiro 3.
    public static final int WEST = 3;
    
    // Armazena e define a velocidade do personagem como 3.
    private int speed = 3;

    // Armazena a direção que o personagem está olhando
    // (NORTH, SOUTH, EAST, WEST).
    private int direction;

    // Armazena a quantidade de turnos que o personagem já passou.
    private int turnos;

    // Armazena o turno que o personagem está atualmente.
    private int tick = 0;
    
    // Armazena a quantidade de turnos necessários para passar ao
    // próximo sprite.
    private int howManyTurns;
    
    /** 
     * Cria um personagem que muda de sprite em howManyTurns turnos e com a direção norte
     * 
     * @param howManyTurns quantidade de turnos necessários para passr para o próximo sprite
     */
    public Personagem(int howManyTurns)
    {
        // Zera a quantidade de turnos que o personagem já passou.
        turnos = 0;

        // Define a direção inicial do personagem como norte.
        this.direction = NORTH;

        // Se a quantidade de turnos necessários para passar ao próximo sprite for menor que 0,
        // a quantidade desses turnos é definida como 0.
        if(howManyTurns < 0)
        {
            // Define a quantidade de turnos para o próximo sprite como 0.
            this.howManyTurns = 0;
        }

        // Caso essa quantidade de turnos for maior, ou igual a 0, define essa quantidade de
        // turnos como o valor passado como argumento.
        else
        {
            // Define a quantidade de turnos para o próximo sprite como ao valor
            // passado como argumento.
            this.howManyTurns = howManyTurns;
        }
    }
    
    /**
     * Modifica a velocidade com que o personagem se move pelo labirinto.
     * 
     * @param speed assume valores inteiros entre [0..3].
     */
    public void setSpeed(int speed)
    {
        // Se a velocidade for menor que 0, ou maior que 3, define a velocidade do
        // personagem como 3.
        if(speed > 3 || speed < 0)
        {
            // Define a velocidade como 3.
            speed = 3;
        }
        
        // Caso a velocidade esteja entre 0 e 3, define a velocidade como o valor
        // passado como argumento.
        else
        {
            // Define a velocidade do personagem como o valor passado como argumento.
            this.speed = speed;
        }
    }
    
    
    /**
     * Retorna a velocidade do personagem.
     * 
     * @return Um inteiro entre [0..3].
     */
    public int getSpeed()
    {
        // retorna a velocidade do personagem.
        return this.speed;
    }

    /**
     * Muda a direção que o personagem está olhando.
     * 
     * @param direction NORTH (0), SOUTH (1), EAST (2) ou WEST (3).
     */
    public void changeDirection(int direction)
    {
        // Muda a direção que o personagem olha de acordo com o argumento
        // passado.
        this.direction = direction;

        // Zera o turno que o personagem está atualmente.
        tick = 0;
    }

    /**
     * Informa a direção que o personagem está olhando.
     * 
     * @return NORTH (0), SOUTH (1), EAST (2) OU WEST (3).
     */
    public int getDirection()
    {
        // Retorna a direção que o personagem está olhando.
        return direction;
    }

    /** 
     * Verifica se está na hora de mudar o sprite.
     * 
     * @return true se está na hora de mudar de sprite, false caso contrário.
     */
    public boolean timeToChangeSprite()
    {
        // Se o turno atual do personagem for igual a 0, retorna true.
        if(tick == 0)
        {
            // Retorna true.
            return true;
        }

        // Caso o turno atual seja diferente de 0, retorna false.
        else
        {
            // Retorna false.
            return false;
        }
    }
    
    /**
     * Verifica se o personagem pode se mover na direção NORTH.
     * 
     * @return true se for possível se mover, false caso contrário.
     */
    public boolean canMoveNorth()
    {
        // Cria um objeto do tipo World, que recebe o mundo em que o personagem
        // está atualmente.
        World myWorld = getWorld();
        
        // Armazena a célula do eixo X do personagem.
        int x = getX();

        // Armazena duas células acima do eixo Y do personagem.
        int y = getY() - 2;

        // Se o número de objetos do tipo Wall, uma célula à esquerda do eixo X e 
        // duas células acima do eixo Y do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x-1,y,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }
        
        // Se o número de objetos do tipo Wall, no eixo X e duas células acima do eixo Y
        // do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x,y,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }
        
        // Se o número de objetos do tipo Wall, uma célula à direita do eixo X e
        // duas células acima do eixo Y do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x+1,y,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }

        // Caso nenhum dos if seja válido, retorna true.
        return true;
    }
    
    /**
     * Verifica se o personagem pode se mover na direção SOUTH.
     * 
     * @return true se é possível se mover, false caso contrário.
     */
    public boolean canMoveSouth()
    {
        // Cria um objeto do tipo World, que recebe o mundo em que o personagem
        // está atualmente.
        World myWorld = getWorld();
        // Armazena a célula do eixo X do personagem.
        int x = getX();

        // Armazena duas células abaixo do eixo Y do personagem.
        int y = getY() + 2;
        
        // Se o número de objetos do tipo Wall, uma célula à esquerda do eixo X e 
        // duas células abaixo do eixo Y do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x+1,y,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }

        // Se o número de objetos do tipo Wall, no eixo X e duas células abaixo do eixo Y
        // do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x,y,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }

        // Se o número de objetos do tipo Wall, uma célula à direita do eixo X e
        // duas células abaixo do eixo Y do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x-1,y,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }

        // Caso nenhum dos if seja válido, retorna true.
        return true;
    }
    
    /**
     * Verifica se o personagem pode se mover na direção EAST.
     * 
     * @return true se é possível se mover, false caso contrário.
     */
    public boolean canMoveEast()
    {
        // Cria um objeto do tipo World, que recebe o mundo em que o personagem
        // está atualmente.
        World myWorld = getWorld();

        // Armazena duas células à direita do eixo X do personagem.
        int x = getX() + 2;
        
        // Armazena a célula do eixo Y do personagem.
        int y = getY();

        // Se o número de objetos do tipo Wall, duas células à direita do eixo X e 
        // uma célula abaixo do eixo Y do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x,y+1,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }

        // Se o número de objetos do tipo Wall, duas células à direita do eixo X e 
        // no eixo Y do personagem for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x,y,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }

        // Se o número de objetos do tipo Wall, duas células à direita do eixo X e 
        // uma célula acima do eixo Y do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x,y-1,Wall.class).size() > 0)
        {
            // Retorna false.
            return false;
        }

        // Caso nenhum dos if seja válido, retorna true.
        return true;
    }
    
    /**
     * Verifica se o personagem pode se mover na direção WEST.
     * 
     * @return true se é possível se mover, false caso contrário.
     */
    public boolean canMoveWest()
    {
        // Cria um objeto do tipo World, que recebe o mundo em que o personagem
        // está atualmente.
        World myWorld = getWorld();

        // Armazena duas células à esquerda do eixo X do personagem.
        int x = getX() - 2;
        
        // Armazena a célula do eixo Y do personagem.
        int y = getY();

        // Se o número de objetos do tipo Wall, duas células à esquerda do eixo X e 
        // uma célula abaixo do eixo Y do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x,y+1,Wall.class).size() > 0)
        {
            return false;
        }

        // Se o número de objetos do tipo Wall, duas células à esquerda do eixo X e 
        // no eixo Y do personagem for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x,y,Wall.class).size() > 0)
        {
            return false;
        }

        // Se o número de objetos do tipo Wall, duas células à esquerda do eixo X e 
        // uma célula acima do eixo Y do personagem, for maior que 0, retorna false.
        if(myWorld.getObjectsAt(x,y-1,Wall.class).size() > 0)
        {
            return false;
        }

        // Caso nenhum dos if seja válido, retorna true.
        return true;
    }
    
    /** 
     * Verifica se o personagem pode se mover na direção que está olhando.
     * 
     * @return false caso haja obstáculos ou esteja fora do mundo, true caso contrário.
     */
    public boolean canMove(int direction)
    {
        // Testa se o personagem pode se mover na direção que está olhando.
        switch(direction)
        {
            // Caso esteja olhando para o leste, testa se pode mover para o leste.
            case EAST:
            {
                // Testa se pode mover para o leste e retorna um boolean.
                return canMoveEast();
            }
            
            // Caso esteja olhando para o oeste, testa se pode mover para o oeste.
            case WEST:
            {
                // Testa se pode mover para o oeste e retorna um boolean.
                return canMoveWest();
            }
            
            // Caso esteja olhando para o norte, testa se pode mover para o norte.
            case NORTH:            
            {
                // Testa se pode mover para o norte e retorna um boolean.
                return canMoveNorth();
            }
            
            // Caso esteja olhando para o sul, testa se pode mover para o sul.
            case SOUTH:
            {
                // Testa se pode mover para o sul e retorna um boolean.
                return canMoveSouth();
            }
        }

        // Retorna true, se não estiver olhando nem para o norte, sul, leste e oeste.
        return true;
    }

    /**
     * Testa se o personagem pode se mover, e caso sim, move o personagem.
     */
    private void move()
    {
        // Se o personagem não puder se mover na direção que está olhando, não move o personagem.
        if(!canMove(this.direction))
        {
            return;
        }
        
        // Caso ele poss se mover na direção que está olhando, ele irá se mover
        else
        {
            // Move o personagem dependendo da direção em que ele está olhando.
            switch(direction)
            {
                // Caso esteja olhando para o leste, move para direita.
                case EAST:
                {
                    // Muda a localização do personagem uma célula para a direita.
                    setLocation(getX()+1,getY());

                    // Sai do switch.
                    break;
                }
                
                // Caso esteja olhando para o oeste, move para esquerda.
                case WEST:
                {
                    // Muda a localização do personagem uma célula para a esquerda.
                    setLocation(getX()-1,getY());

                    // Sai do switch.
                    break;
                }
                
                // Caso esteja olhando para o norte, move para frente.
                case NORTH:
                {
                    // Muda a localização do personagem uma célula para frente.
                    setLocation(getX(),getY()-1);

                    // Sai do switch.
                    break;
                }

                // Caso esteja olhando para o sul, move para trás.
                case SOUTH:
                {
                    // Muda a localização do personagem uma célula para trás.
                    setLocation(getX(),getY()+1);

                    // Sai do switch.
                    break;
                }
            }
        }
    }

    /**
     * Faz o personagem se mover na direção que está olhando. 
     * 
     * PS: Tenha certeza de chamar esse método por último dentro do método act() da sua subclasse.
     * Afinal ele é necessário para fazer o personagem se movimentar.
     */
    public void act()
    {
        // Se a velocidade do personagem for maior que o resto entre a quantidade de turnos que 
        // o personagem já passou e 3, move o personagem, se possível.
        if(speed > turnos%3)
        {
            // Move o personagem na direção que está olhando, se possível.
            move();
        }

        // Adiciona mais um turno que o personagem passou.
        turnos++;
        
        // Incrementa em mais um o turno atual do personagem.
        tick++;
        
        // Se o turno atual do personagem for maior ou igual ou maior à quantidade de turnos
        // necessários para passar ao próximo sprite, menos um, redefine o turno atual do
        // personagem como -1, para assim reiniciar o ciclo de mudança de sprites.
        if(tick >= howManyTurns-1)
        {
            // Define o turno atual do personagem como -1.
            tick = -1;
        }
    }
}
