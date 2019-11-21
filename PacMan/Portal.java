
import greenfoot.World;

public class Portal implements Observer {

    private final World world;
    private final int xDeparture, xDestination, yDeparture, yDestination;

    public Portal(World world, int xDeparture, int yDeparture, int xDestination, int yDestination) {
        this.world = world;
        this.xDeparture = xDeparture;
        this.xDestination = xDestination;
        this.yDeparture = yDeparture;
        this.yDestination = yDestination;
    }

    @Override
    public void update(Character character) {
        // Verifica se o personagem está dentro do portal.
        if (isInsidePortal(character)) {
            // Verifica em qual lado do portal o personagem está.
            if (character.getX() == xDeparture
                    && character.getY() == yDeparture) {
                character.setLocation(xDestination, yDestination);
            } else if (character.getX() == xDestination
                    && character.getY() == yDestination) {
                character.setLocation(xDeparture, yDeparture);
            }
        }
    }

    /**
     * Verifica se existe algum personagem dentro das células do portal (X: 0 ou
     * X: 56 & Y: 29).
     *
     * @param character um personagem
     * @return true se o personagem estiver dentro do portal e false se não
     * estiver.
     */
    private boolean isInsidePortal(Character character) {
        // Pega os pontos X e Y do personagem.
        int x = character.getX();
        int y = character.getY();

        // Verifica se ele está dentro do portal.
        return (y == yDeparture || y == yDestination)
                && (x == xDeparture || x == xDestination);
    }

}
