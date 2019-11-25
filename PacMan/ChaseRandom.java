import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

//Chase Random é o único implementado!

public class ChaseRandom implements ChaseBehaviour {
    Ghost ghost;
    
    public ChaseRandom(Ghost ghost) {
        this.ghost = ghost;
    }

    public int chase(){
        List<Integer> routes = new ArrayList<>();
        int direction = ghost.getDirection();
        int oppositeDirection = ghost.oppositeDirection(direction);

        if (ghost.canMove(Character.NORTH)) {
            routes.add(Character.NORTH);
        }

        if (ghost.canMove(Character.SOUTH)) {
            routes.add(Character.SOUTH);
        }

        if (ghost.canMove(Character.EAST)) {
            routes.add(Character.EAST);
        }

        if (ghost.canMove(Character.WEST)) {
            routes.add(Character.WEST);
        }

        if (!routes.isEmpty()) {
            if (routes.size() >= 2) {
                routes.remove(Integer.valueOf(oppositeDirection));
            }

            int rand = Greenfoot.getRandomNumber(routes.size());
            return routes.get(rand);
        } else {
            return direction;
        }
    }
}
