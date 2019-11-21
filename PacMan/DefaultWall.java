
import greenfoot.GreenfootImage;

public class DefaultWall extends Wall {

    // Cria uma parede invisível.
    private final GreenfootImage wall = new GreenfootImage("wall.png");

    // Cria uma parede visível para debug.
    private final GreenfootImage debugger = new GreenfootImage("wall_debugger.png");

    /**
     * Define a imagem que representa a Wall. parede_debugger permite a
     * visualização das paredes.
     */
    public DefaultWall() {
        // Define a imagem usando o objeto parede.
        setImage(wall);
    }

}
