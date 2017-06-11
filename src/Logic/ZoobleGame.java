package Logic;

import GUI.ZooRect;
import Logic.Collision.Collider.BallCollider;
import Logic.Util.DeltaTime;
import Persistent.Level.Level;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Created by Rocki on 11.06.2017.
 */
public class ZoobleGame {

    private ArrayList<Object> boxColliderList;         //store all Box Collider into this ArrayList.
    private ArrayList<BallCollider> ballColliderList;   //store all Balls into this ArrayList.

    public ZoobleGame(Level level){
        storeFromLevelIntoGame(level);


        //GAME LOOP
        new AnimationTimer() {
            DeltaTime dt = new DeltaTime();

            @Override
            public void handle(long now) {
                //CALCULATE THE DELTA TIME
                //SET CURRENT TIME ON THE BEGINNING OF THE NEXT FRAME (currentTime - previousTime -> DeltaTime)
                dt.setCurrentTime(now);

                //If previous time is not set yet (special case -> this is the first frame)
                if (dt.getPreviousTime() == 0.0) {
                    dt.setPreviousTime(dt.getCurrentTime());
                } else {
                    dt.calcDeltaTime();
                }

                //----------PLACE GAME CODE BETWEEN THESE COMMENTS-----------


                //----------PLACE GAME CODE BETWEEN THESE COMMENTS-----------

                //SET PREVIOUS TIME OF THE FIRST FRAME SO WE CAN CALCULATE DELTA TIME
                dt.setPreviousTime(now);
            }
        };
    }

    //--------METHODS--------

        /**
         * looks, what the level should have (how many Items and places them into the Game)
         */
    private void storeFromLevelIntoGame(Level level){
        checkForErrorsInlevelArrayLists(level);
        this.ballColliderList = level.getBallCollidersList();
        this.boxColliderList = level.getOtherCollidersList();
    }

    /**
     * checks if the level ArrayLists are empty or aren't even instantiated.
     * @param level
     */
    private void checkForErrorsInlevelArrayLists(Level level){
        if (level.getBallCollidersList().isEmpty() || level.getOtherCollidersList().isEmpty()){
            throw new EmptyStackException();
        } else if (level.getBallCollidersList() == null || level.getOtherCollidersList() == null){
            throw new NullPointerException();
        }
    }
}

