
public class Blinky extends Ghost {
    
    public Blinky() {
        super(Ghost.RED);
        super.cb = new ChaseAgressive(this);
    }
}
