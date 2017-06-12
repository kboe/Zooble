package Logic.Util.Physics;

import GUI.ZooRect;
import Logic.Collision.Collider.BallCollider;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;

import static Logic.Util.Physics.Kinematics.FRICTION;
import static Logic.Util.Physics.Kinematics.GRAVITY;

import static Logic.Util.Physics.Kinematics.GRAVITYVECTOR;

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
       // newPosition= bc.getVelocity().multiply(deltaTime.getCurrentTime());
        bc.setPosition(newPosition.add(bc.getPosition(), bc.getVelocity().multiply(deltaTime.getCurrentTime())));
    }

    /**
     * Sets new Velocity through acceleration and ball collider
     *
     * @param deltaTime current Time which is used
     * @param bc        ballCollider which is used
     */
    public static void acceleratedMovementVelocity(DeltaTime deltaTime, BallCollider bc) {
        bc.setVelocity(bc.getAcceleration().multiply(deltaTime.getCurrentTime()));
    }

    /**
     * Calculates current position with time and acceleration
     *
     * @param deltaTime current Time
     * @param bc        ballCollider
     */
    public static void accleratedMovementPosition(DeltaTime deltaTime, BallCollider bc) {
        double x = bc.getAcceleration().getX()*(0.5*(deltaTime.getCurrentTime()*deltaTime.getCurrentTime()));
        Vector2d position = new Vector2d(x,0);
        //Vector2d position = bc.getAcceleration().multiply(0.5 * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime()));
        //bc.setPosition(position.add(position, bc.getStartingPoint()));
        bc.setPosition(position.add(position, bc.getPosition()));

    }

    public static void accleratedMovementPosition(DeltaTime deltaTime, BallCollider bc, ZooRect zooRect) {
        if(zooRect.getRect().getAngle()<0){
            double x = bc.getAcceleration().getX()*(-0.5*(deltaTime.getCurrentTime()*deltaTime.getCurrentTime()));
            Vector2d position = new Vector2d(x,0);
            //Vector2d position = bc.getAcceleration().multiply(0.5 * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime()));
            //bc.setPosition(position.add(position, bc.getStartingPoint()));
            bc.setPosition(position.add(position, bc.getPosition()));
        }
        else {
            double x = bc.getAcceleration().getX()*(0.5*(deltaTime.getCurrentTime()*deltaTime.getCurrentTime()));
            Vector2d position = new Vector2d(x,0);
            //Vector2d position = bc.getAcceleration().multiply(0.5 * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime()));
            //bc.setPosition(position.add(position, bc.getStartingPoint()));
            bc.setPosition(position.add(position, bc.getPosition()));
        }


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

        bc.setVelocity(bc.getVelocity().add(bc.getAcceleration().multiply(deltaTime.getCurrentTime()), bc.getVelocity()));
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
                ballCollider.getAcceleration().multiply(0.5 * deltaTime.getCurrentTime() * deltaTime.getCurrentTime())));
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
        bc.setAcceleration(averageAcceleration);
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
        bc.setPosition(new Vector2d(bc.getPosition().getX(), height + bc.getPosition().getY()));

    }

    /**
     * Free Fall y-Pos using the velocity Vector of the ball.
     *
     * @param deltaTime
     * @return
     */
    public static void freeFallHeightWithVelocity(DeltaTime deltaTime, BallCollider bc) {
        /* double height = 0.5 * GRAVITY / 100 * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        boolean toSlow = false;
        if ((bc.getVelocity().getX() < 0.1 & bc.getVelocity().getX() > -0.1) && floorContact) {
            System.out.println(bc.getVelocity());
            bc.setVelocity(new Vector2d(bc.getVelocity().getX(), 0));
            toSlow = true;
        }
        if (floorContact) {
            if (toSlow) {
                //can't import the Canvas Height
                //bc.setPosition(new Vector2d(bc.getPosition().getX(), 750 - bc.getRadius()));
                floorContact = false;
                //LoopStopped.out_of_bounds = true;
            } else {

                //bc.setVelocity(bc.getVelocity().add(bc.getAcceleration().multiply(DeltaTime.deltatime)));
                //bc.setVelocity(bc.getVelocity().multiply(0.85));
                //bc.setPosition(bc.getPosition().add(bc.getVelocity()));
                //floorContact = false;
            }


        } else {*/
            bc.setVelocity(bc.getVelocity().add(bc.getAcceleration().multiply(DeltaTime.deltatime)));
            bc.setPosition(bc.getPosition().add(bc.getVelocity()));


        //bc.getVelocity().setY(bc.getVelocity().getY() + GRAVITY / 50 + 1);
        //TODO ball muss noch Energie abgeben -> Velocity muss abnehmen (?)
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
     *
     * @param bc
     * @param deltaTime
     */
    public static void levelThrow(BallCollider bc, DeltaTime deltaTime) {
        bc.setPosition(new Vector2d((bc.getVelocity().getX() * deltaTime.getCurrentTime()) + bc.getStartingPoint().getX(), bc.getPosition().getY() - 0.5 * -GRAVITY * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime())));
        System.out.println("X: " + bc.getPosition().getX() + "  Y: " + bc.getPosition().getY());
    }

    //-------------------------------------------------------------------------------------------------------------------
    //Hang Geschwindigkeit
    public static void hillVelocity(BallCollider ballCollider) {
        double hv = Math.sqrt(2 * GRAVITY * ballCollider.getStartingPoint().getY() -
                ballCollider.getPosition().getY());
        Vector2d v = new Vector2d(ballCollider.getVelocity().getX(), hv);
        ballCollider.setVelocity(ballCollider.getVelocity().add(ballCollider.getVelocity(), v));
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
        Vector2d b2 = ballCollider2.getVelocity().multiply(ballCollider2.getMass());
        Vector2d v = b1.add(b2);
        v.divide(ballCollider.getMass() + ballCollider2.getMass());
        ballCollider.setVelocity(v);
        ballCollider2.setVelocity(v);
        System.out.println(v.getX());
        ballCollider2.setVelocity0(ballCollider2.getVelocity());
    }
    //---------------------------------------------------------------------------------
    //elastischer Stoß

    /**
     * elastic Push
     *
     * @param bc1
     * @param bc2
     */
    public static void elasticPush(BallCollider bc1, BallCollider bc2) {
        double dmass = bc1.getMass() + bc2.getMass();
        System.out.println("BC 1: " + bc1.getVelocity());
        Vector2d v1 = bc1.getVelocity();
        Vector2d a2 = new Vector2d();
        Vector2d a = bc1.getVelocity().multiply(bc1.getMass() - bc2.getMass());
        a2 = a2.add(a, bc2.getVelocity().multiply(2 * bc2.getMass()));
        Vector2d v2 = bc2.getVelocity();
        Vector2d b = bc2.getVelocity().multiply(bc2.getMass() - bc1.getMass());
        Vector2d b2 = new Vector2d();
        b2 = b2.add(b, bc1.getVelocity().multiply(2 * bc1.getMass()));
        v2 = v2.add(v2, b2);
        v1 = v1.multiply(bc1.getMass() - bc2.getMass());
        v1 = v1.add(v1, a2);
        v1.divide(dmass);
        v2.divide(dmass);

        bc1.setVelocity(v1);
        bc2.setVelocity(v2);


    }

    //------------------------------------------------------------------------------------------------------------------
    //Kinetische Energie

    public static void kineticEnergy(BallCollider ballCollider) {
        ballCollider.seteKin((ballCollider.getVelocity().dot(ballCollider.getVelocity(), ballCollider.getVelocity())) * 0.5 * ballCollider.getMass());
    }

    //------------------------------------------------------------------------------------------------------------------
    //Potentielle Energie

    public static void potentialEnergy(BallCollider ballCollider) {
        ballCollider.setePot(ballCollider.getMass() * GRAVITY * ballCollider.getCenterY());
    }

    //------------------------------------------------------------------------------------------------------------------
    //Impuls

    public static void pulse(BallCollider ballCollider) {
        ballCollider.setPulse(ballCollider.getVelocity().multiply(ballCollider.getMass()));
    }

    //------------------------------------------------------------------------------------------------------------------
    // Gewichtskraft

    public static void gForce(BallCollider ballCollider) {
        ballCollider.setgForce(ballCollider.getMass() * GRAVITY);
    }

    //------------------------------------------------------------------------------------------------------------------
    //Hangabtriebskraft

    public static void hForce(BallCollider ballCollider) {
        gForce(ballCollider);
        ballCollider.sethForce(ballCollider.getgForce() * Math.sin(ballCollider.getAngle()));
    }
    public static Vector2d hillForce(BallCollider ballCollider) {
        System.out.println(Math.sin(ballCollider.getAngle()));
        System.out.println("Math.sin(ballCollider.getAngle() "+Math.sin(ballCollider.getAngle()));
        Vector2d gf= GRAVITYVECTOR.multiply(ballCollider.getMass());
        //Vector2d h = gf.multiply(Math.sin(ballCollider.getAngle()));
        Vector2d h = Vector2d.rotateVector(gf,ballCollider.getAngle());

        return h;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Normalkraft

    public static void nForce(BallCollider ballCollider) {
        ballCollider.setnForce(ballCollider.getgForce() * Math.cos(ballCollider.getAngle()));
    }

    //------------------------------------------------------------------------------------------------------------------
    //Reibungszahl

    public static void rForce(BallCollider ballCollider) {
        ballCollider.setrForce(FRICTION * ballCollider.getnForce());
    }

    /**
     * sets radial Rotation
     *
     * @param bc ballCollider
     * @return
     */
    public static void radialAcceleration(BallCollider bc) {
        if(bc.getVelocity().getX()>=0){
            bc.setRotate(bc.getRotate() + (bc.getVelocity().getX() * bc.getVelocity().getX()) / bc.getRadius());

        }
        else {
            bc.setRotate(bc.getRotate() + (-bc.getVelocity().getX() * bc.getVelocity().getX()) / bc.getRadius());

        }
    }
}
