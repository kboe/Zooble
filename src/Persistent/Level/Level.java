package Persistent.Level;

import GUI.ZooRect;
import Logic.Collision.BallCollider;

import java.util.ArrayList;

/**
 * Created by Nikk1208 on 28.05.2017.
 */
public class Level {

        //TODO give the Level a List of items it already has in the scene + the items' values + the objects which can be placed in the scene by the player
    ArrayList<ZooRect> boxCollidersList = new ArrayList<>();
    ArrayList<BallCollider> ballCollidersList = new ArrayList<>();

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
