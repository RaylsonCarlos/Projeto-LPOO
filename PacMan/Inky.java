
public class Inky extends Ghost {
    
    public Inky() {
        super(Ghost.BLUE);
        super.cb = new ChasePatrol(this);
    }
}
