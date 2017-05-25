package Logic.Util.Physics;

import Logic.Collision.Deprecated.CircleCollider;
import Logic.Collision.Deprecated.Collider;
import Logic.Util.DeltaTime;

import static Logic.Util.Physics.Physics.GRAVITY;

/**
 * Created by ${kboe} on 22.05.2017.
 */
public class UltraPhysics {

    /**
     *
     * @param collider
     */
    public static void freeFall(CircleCollider collider, DeltaTime deltaTime) {
        collider.getPositionVector().setY(collider.getPositionVector().getY() + (GRAVITY / 2 * (deltaTime.getCurrentTime()* deltaTime.getCurrentTime())));
    }

    /**
     *
     * @param collider
     * @param v
     * @param a
     * @param deltaTime
     */
    public static void consistentVelocityPosition(CircleCollider collider,double v, double a, DeltaTime deltaTime) {
        double pos = a * deltaTime.getCurrentTime() + v;
        collider.getPositionVector().setX(collider.getPositionVector().getX() + pos);

    }

    /**
     *
     * @param collider
     * @param v
     * @param deltaTime
     * @return
     */
    public static double evenAcceleratedVelocity(CircleCollider collider, double v, DeltaTime deltaTime){
        double v1 = v*deltaTime.getCurrentTime();
        return v1;
    }

}
