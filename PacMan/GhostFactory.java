
public class GhostFactory {

    public Ghost createGhost(String name) {
        Ghost ghost = null;

        if (name.equals("blinky")) {
            ghost = new Blinky();
        } else if (name.equals("pinky")) {
            ghost = new Pinky();
        } else if (name.equals("inky")) {
            ghost = new Inky();
        } else if (name.equals("clyde")) {
            ghost = new Clyde();
        }

        return ghost;
    }
}
