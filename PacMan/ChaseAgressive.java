/**
 * Write a description of class ChaseAgreessive here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Chase Random é o único implementado!

public class ChaseAgressive implements ChaseBehaviour {
    Ghost ghost;
    
    //Para ser reescrito!
    ChaseRandom chaseRandom;
    
    public ChaseAgressive(Ghost ghost) {
        this.ghost = ghost;
        
        //Para ser reescrito!
        chaseRandom = new ChaseRandom(ghost);
    }

    public int chase(){
        
        //Para ser reescrito
        return chaseRandom.chase();
    }
}
