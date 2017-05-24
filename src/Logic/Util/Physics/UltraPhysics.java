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


    public static void consistentVelocity(CircleCollider collider,double v, double a, DeltaTime deltaTime) {
        double pos = a * deltaTime.getCurrentTime() + v;
        collider.getPositionVector().setX(collider.getPositionVector().getX() + pos);

    }

    public static double move(Collider collider, double v, DeltaTime deltaTime){
        double p = v*deltaTime.getCurrentTime();
        return p;
    }

}
