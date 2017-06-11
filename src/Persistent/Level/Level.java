package Persistent.Level;

import GUI.ZooRect;
import Logic.Collision.Collider.BallCollider;
import Logic.Collision.Collider.BoxCollider;

import java.util.ArrayList;

/**
 * Created by Rocki on 28.05.2017.
 */
public class Level {

        //TODO give the Level a List of items it already has in the scene + the items' values + the objects which can be placed in the scene by the player

        private ArrayList<Object> otherCollidersList = new ArrayList<>();
        private ArrayList<BallCollider> ballCollidersList = new ArrayList<>();

    protected Level(){
        //CSVManager -> parse CSV into Level
    }

    public ArrayList<Object> getOtherCollidersList() {
        return otherCollidersList;
    }

    public ArrayList<BallCollider> getBallCollidersList() {
        return ballCollidersList;
    }
}
