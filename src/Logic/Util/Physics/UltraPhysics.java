package Logic.Util.Physics;

import Logic.Collision.Collider;
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
    public static void freeFall(Collider collider,DeltaTime deltaTime) {
        collider.getPositionVector().setY(collider.getPositionVector().getY() + (GRAVITY / 2 * (deltaTime.getCurrentTime()* deltaTime.getCurrentTime())));
    }

}
