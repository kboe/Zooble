package Persistent.Game_Assets.Balls;

import Logic.Collision.Collider.BallCollider;
import Persistent.Constants;
import javafx.scene.paint.ImagePattern;

/**
 * Created by Rocki on 11.06.2017.
 */
public class BlowfishBall extends BallCollider {
    public BlowfishBall(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius, new ImagePattern(Constants.BLOWFISH_IMAGE));
    }
}
