
public class Pinky extends Ghost {
    
    public Pinky() {
        super(Ghost.PINK);
        super.cb = new ChaseAmbush(this);
    }
}
