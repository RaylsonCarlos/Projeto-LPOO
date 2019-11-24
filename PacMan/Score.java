
import greenfoot.*;

public class Score extends Actor implements Observer {

    private int points;

    //volatile to avoid cache reading this variable.
    private static volatile Score instance;

    private Score() {
    }

    public static Score getInstance() {
        //double lock checking!
        if (instance == null) {
            synchronized (Score.class) {
                if (instance == null) {
                    instance = new Score();
                }
            }
        }
        return instance;
    }

    @Override
    public void update(int pointsToAdd) {
        points += pointsToAdd;
        String msg = "SCORE: " + points;
        GreenfootImage scoreImage = new GreenfootImage(msg, 6 * getWorld().getCellSize(), Color.WHITE, null);
        setImage(scoreImage);
        getWorld().repaint();
    }

    public int getPoints() {
        return points;
    }

}
