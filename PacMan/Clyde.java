
public class Clyde extends Ghost {

    public Clyde() {
        super(Ghost.BROWN);
        super.cb = new ChaseRandom(this);
    }
}
