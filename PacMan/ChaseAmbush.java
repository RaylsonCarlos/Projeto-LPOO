//Chase Random é o único implementado!

public class ChaseAmbush implements ChaseBehaviour {

    Ghost ghost;

    //Para ser reescrito!
    ChaseRandom chaseRandom;

    public ChaseAmbush(Ghost ghost) {
        this.ghost = ghost;

        //Para ser reescrito!
        chaseRandom = new ChaseRandom(ghost);
    }

    public int chase() {

        //Para ser reescrito
        return chaseRandom.chase();
    }
}
