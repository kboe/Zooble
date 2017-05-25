package Logic.Util.Physics;

import Logic.Collision.BallCollider;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;

/**
 * Created by ${kboe} on 25.05.2017.
 */
public class KinematicsBall {




    //---------------------------------------------------------------------------------
    //Gradlining beschleunigte Bewegung

    /**
     * Sets position with with velocity, time and position
     * @param velocity
     * @param deltaTime
     * @param bc
     */
    public static void evenMovementPosition(double velocity, DeltaTime deltaTime, BallCollider bc) {
        double pos = velocity * deltaTime.getCurrentTime() + bc.getLayoutX();
        bc.setLayoutX(pos);
    }

    //----------------------------------------------------------------------------------------------------------------
    //Beschleunigte Bewegung

    /**
     * Calculates speed with acceleration
     *
     * @param a
     * @param deltaTime
     * @return
     */
    public static void acceleratedMovementVelocity(double a, DeltaTime deltaTime,BallCollider bc) {
        double velocity = a * deltaTime.getCurrentTime();
        bc.setCurrentVelocity(new Vector2d(velocity,0));
        //bc.setLayoutX(velocity);
    }

    /**
     * Calculates current position with time and acceleration
     *
     * @param acceleration
     * @param deltaTime
     * @return
     */
    public static double accleratedMovementPosition(double acceleration, DeltaTime deltaTime) {
        double s = 0.5 * acceleration * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        return s;
    }

}
