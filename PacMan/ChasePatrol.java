/**
 * Write a description of class ChasePatrol here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//Chase Random é o único implementado!

public class ChasePatrol implements ChaseBehaviour {    
    Ghost ghost;
    
    //Para ser reescrito!
    ChaseRandom chaseRandom;
        
    public ChasePatrol(Ghost ghost){
        this.ghost = ghost;
        
        //Para ser reescrito!
        chaseRandom = new ChaseRandom(ghost);
    }
    
    public int chase(){
        
        //Para ser reescrito
        return chaseRandom.chase();
    }
}
