package Logic.Util.Physics;

import Logic.Collision.Collider;

import static Logic.Util.Physics.Physics.GRAVITY;

/**
 * Created by ${kboe} on 22.05.2017.
 */
public class UltraPhysics {


    /**
     *
     * @param collider
     * @param deltatime
     */
    public static void freeFall(Collider collider, double deltatime) {
        collider.getPositionVector().setY(collider.getPositionVector().getY() + (GRAVITY / 2 * (deltatime*deltatime)));
    }
}
