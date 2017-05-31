package Logic.Util.Physics;

import Logic.Collision.BallCollider;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;

/**
 * Created by ${kboe} on 31.05.2017.
 */
public class KinematicsVectors {
    //Gradlining beschleunigte Bewegung

    /**
     * Calculate position with unchanging velocity
     *
     * @return
     */

    public static void evenMovementPosition(BallCollider bc, DeltaTime deltaTime) {
        Vector2d newPosition = new Vector2d();
        bc.setPosition(newPosition.add(bc.getStartingPoint(), bc.getVelocity().multiply(deltaTime.getCurrentTime())));
    }

    /**
     * Sets new Velocity through acceleration and ball collider
     *
     * @param deltaTime current Time which is used
     * @param bc        ballCollider which is used
     */
    public static void acceleratedMovementVelocity(DeltaTime deltaTime, BallCollider bc) {
        bc.setVelocity(bc.getAccelerationV().multiply(deltaTime.getCurrentTime()));
    }

    /**
     * Calculates current position with time and acceleration
     *
     * @param deltaTime current Time
     * @param bc        ballCollider
     */
    public static void accleratedMovementPosition(DeltaTime deltaTime, BallCollider bc) {
        Vector2d position = bc.getAccelerationV().multiply(0.5 * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime()));
        bc.setPosition(position.add(position, bc.getStartingPoint()));
    }
    //----------------------------------------------------------------------------------------------------------------
    //Beschleunigte Bewegung mit Anfangsgeschwindigkeit

    /**
     * Calculates speed with acceleration and starting velocity
     *
     * @param
     * @param deltaTime
     * @return
     */
    public static void acceleratedMovementVelocityWithStartingVelocity(DeltaTime deltaTime, BallCollider bc) {

        bc.setVelocity(bc.getVelocity().add(bc.getAccelerationV().multiply(deltaTime.getCurrentTime()), bc.getVelocity()));
    }

    /**
     * Sets position with starting position and starting velocity
     *
     * @param deltaTime
     * @param ballCollider
     */
    public static void acceleratedMovementPositionWithStartingSpeedAndPosition(DeltaTime deltaTime, BallCollider ballCollider) {

        ballCollider.setPosition(ballCollider.getPosition().add(ballCollider.getPosition().
                        add(ballCollider.getStartingPoint(), ballCollider.getVelocity0().multiply(deltaTime.getCurrentTime())),
                ballCollider.getAccelerationV().multiply(0.5 * deltaTime.getCurrentTime() * deltaTime.getCurrentTime())));
    }
    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsgeschwindigkeit

    /**
     * Mean speed
     *
     * @param deltaTime
     * @return
     */
    /*public static double effectiveSpeed(double pos0, double pos1, DeltaTime deltaTime) {
        //double velocity = ((pos1 - pos0) / (effectiveTime(deltaTime.getCurrentTime(), deltaTime.getLastTime())));
       // return velocity;
        return 0;
    }*/
    public static double effectiveSpeed(BallCollider ballCollider, DeltaTime deltaTime) {
        //double velocity = ((pos1 - pos0) / (effectiveTime(deltaTime.getCurrentTime(), deltaTime.getLastTime())));
        // return velocity;
        //double velocity =(ballCollider.getLastPosition()-ballCollider.getLastLastPosition())/(effectiveTime(deltaTime));
        double velocity = (ballCollider.getLastPosition() - ballCollider.getLastLastPosition());

        return velocity;
    }

    public static Vector2d averageSpeed(BallCollider bc, DeltaTime deltaTime) {
        Vector2d averageSpeed = (bc.getVelocity().subtract(bc.getLastPos(), bc.getLastLastPos()));
        averageSpeed.divide(averageTime(deltaTime));
        bc.setVelocity(averageSpeed);
        return bc.getVelocity();
    }

    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsbeschleunigung

    /* /**
      * Mean acceleration
      *
      * @param velocity0
      * @param velocity1
      * @param deltaTime
      * @return
      */
    /*public static double effectiveAcceleration(double velocity0, double velocity1, DeltaTime deltaTime) {
        //double acceleration = (velocity1 - velocity0) / (effectiveTime(deltaTime.getCurrentTime(), deltaTime.getLastTime()));
        //return acceleration;
        return 0;
    }*/
    public static double effectiveAcceleration(BallCollider ballCollider, DeltaTime deltaTime) {
        //double acceleration = (velocity1 - velocity0) / (effectiveTime(deltaTime.getCurrentTime(), deltaTime.getLastTime()));
        //double accleration=(ballCollider.getLastSpeed()-ballCollider.getLastLastSpeed())/effectiveTime(deltaTime);
        //double accleration=(ballCollider.getLastSpeed()-ballCollider.getLastLastSpeed())/DeltaTime.deltatime;
        double accleration = effectiveSpeed(ballCollider, deltaTime) / 60;

        //without this it goes really early negative
        if (accleration < 0) {
            accleration = -accleration;
        }
        System.out.println("Acceleration: " + accleration);
        return accleration;
    }

    //TODO die Beschleunigung steigt viel viel zu schnell an. Wäre nett, wenn da mal jemand schauen würde. Ich komme da nicht mehr weiter!

    /**
     * Calculates average acceleration between frames
     *
     * @param bc        ballCollider
     * @param deltaTime deltatime
     */
    public static void averageAcceleration(BallCollider bc, DeltaTime deltaTime) {
        bc.setVelocity(averageSpeed(bc, deltaTime));
        Vector2d averageAcceleration = bc.getVelocity();
        averageAcceleration.divide(averageTime(deltaTime));
        bc.setAccelerationV(averageAcceleration);
        System.out.println(bc.getAcceleration());
    }


    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittszeit

    /**
     * Calculates the time in an interval
     *
     * @param dt time
     * @return
     */
    public static double averageTime(DeltaTime dt) {
        double averageTime = dt.getLastTime() - dt.getLastLastTime();
        return averageTime;
    }


    /**
     * sets radial Rotation
     *
     * @param bc ballCollider
     * @return
     */
    public static void radialAcceleration(BallCollider bc) {
        bc.setRotate(bc.getRotate() + (bc.getVelocity().getX() * bc.getVelocity().getX()) / bc.getRadius());
    }
}
