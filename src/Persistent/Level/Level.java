package Persistent.Level;

import GUI.ZooRect;
import Logic.Collision.Collider.BallCollider;

import java.util.ArrayList;

/**
 * Created by Rocki on 28.05.2017.
 */
public class Level {

        //TODO give the Level a List of items it already has in the scene + the items' values + the objects which can be placed in the scene by the player

        private ArrayList<ZooRect> boxCollidersList = new ArrayList<>();
        private ArrayList<BallCollider> ballCollidersList = new ArrayList<>();

    public Level(){
        //CSVManager -> parse CSV into Level
    }

    public ArrayList<ZooRect> getBoxCollidersList() {
        return boxCollidersList;
    }

    public ArrayList<BallCollider> getBallCollidersList() {
        return ballCollidersList;
    }
}
