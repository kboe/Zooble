package Persistent.Game_Items;

import Logic.Collision.Collider.BallCollider;
import Persistent.Constants;
import javafx.scene.paint.ImagePattern;

/**
 * Created by Rocki on 11.06.2017.
 */
public class CatBall extends BallCollider {
    public CatBall(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius, new ImagePattern(Constants.CAT_IMAGE));
    }
}
