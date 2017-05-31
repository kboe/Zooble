package Logic.Util.Physics;

import Logic.Collision.BallCollider;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;

import static Logic.Util.Physics.Kinematics.GRAVITY;

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

    public static Vector2d averageSpeed(BallCollider bc, DeltaTime deltaTime) {
        Vector2d averageSpeed = (bc.getVelocity().subtract(bc.getLastPos(), bc.getLastLastPos()));
        averageSpeed.divide(averageTime(deltaTime));
        bc.setVelocity(averageSpeed);
        return bc.getVelocity();
    }

    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsbeschleunigung


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
    //Freier Fall

    /**
     * Free Fall y-Pos
     *
     * @param deltaTime
     * @return
     */
    public static void freeFallHeight(DeltaTime deltaTime, BallCollider bc) {
        double height = 0.5 * GRAVITY * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        bc.setPosition(new Vector2d(bc.getPosition().getX(), height + bc.getStartingPoint().getY()));

    }

    /**
     * Calculate Free Fall speed from height and gravity
     *
     * @param
     * @return
     */
    public static void freeFallVelocity(BallCollider bc) {
        bc.setVelocity(new Vector2d(bc.getVelocity().getX(), Math.sqrt(2 * bc.getPosition().getY() * GRAVITY)));
    }

    //-----------------------------------------------------------------------------------------------------------
    //Waagrechter Wurf

    /**
     * Sets position during a level throw
     * @param bc
     * @param deltaTime
     */
    public static void levelThrow(BallCollider bc, DeltaTime deltaTime){
        bc.setPosition(new Vector2d((bc.getVelocity().getX()*deltaTime.getCurrentTime())+bc.getStartingPoint().getX(),bc.getPosition().getY()-0.5*-GRAVITY*(deltaTime.getCurrentTime()*deltaTime.getCurrentTime())));
        System.out.println("X: "+bc.getPosition().getX()+"  Y: "+bc.getPosition().getY());
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
    //--------------------------------------------------------------------------------------
    //unelastischer Stoß

    /**
     * Calculates new velocity after an unelastic push
     *

     * @return
     */
    public static void unelasticPushVelocityCollider(BallCollider ballCollider, BallCollider ballCollider2) {
        Vector2d b1 = ballCollider.getVelocity().multiply(ballCollider.getMass());
        Vector2d b2 =  ballCollider2.getVelocity().multiply(ballCollider2.getMass());
        Vector2d v = b1.add(b1,b2);
        v.divide(ballCollider.getMass()+ballCollider2.getMass());
        ballCollider.setVelocity(v);
        ballCollider2.setVelocity(v);
    }
    //---------------------------------------------------------------------------------
    //elastischer Stoß

    /**
     * elastic Push
     * @param bc1
     * @param bc2
     */
    public static void elasticPush(BallCollider bc1, BallCollider bc2){
        double dmass= bc1.getMass()+bc2.getMass();
        Vector2d a = bc1.getVelocity().multiply(bc1.getMass()-bc2.getMass());
        Vector2d a2 = bc2.getVelocity().multiply(2*bc2.getMass());
        Vector2d b = bc2.getVelocity().multiply(bc2.getMass()-bc1.getMass());
        Vector2d b2 = bc1.getVelocity().multiply(2*bc1.getMass());
        a.add(a,a2);
        a.divide(dmass);
        b.add(b,b2);
        b.divide(dmass);

        bc1.setVelocity(a);
        bc2.setVelocity(b);



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
