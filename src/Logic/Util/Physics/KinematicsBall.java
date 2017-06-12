package Logic.Util.Physics;

import Logic.Collision.Collider.BallCollider;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;

import java.util.ArrayList;

import static Logic.Util.Physics.Kinematics.GRAVITY;

/**
 * Created by ${kboe} on 25.05.2017.
 */
public class KinematicsBall {


    //---------------------------------------------------------------------------------
    //Gradlining beschleunigte Bewegung

    /**
     * Sets position with with velocity, time and position
     *
     * @param
     * @param deltaTime
     * @param bc
     */
    public static void evenMovementPosition( DeltaTime deltaTime, BallCollider bc) {
        double pos = bc.getVelocity().getX() * deltaTime.getCurrentTime() + bc.getCenterX();
        bc.setCenterX(pos);
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
    public static void acceleratedMovementVelocity(double a, DeltaTime deltaTime, BallCollider bc) {
        double velocity = a * deltaTime.getCurrentTime();
        bc.setVelocity(new Vector2d(velocity, bc.getVelocity().getY()));
        //bc.setLayoutX(velocity);
    }

    /**
     * Calculates current position with time and acceleration
     *
     * @param acceleration
     * @param deltaTime
     * @return
     */
    public static void accleratedMovementPosition(double acceleration, DeltaTime deltaTime, BallCollider bc) {
        double s = 0.5 * acceleration * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        bc.setPosition(new Vector2d(s, bc.getPosition().getY()));
    }


    //----------------------------------------------------------------------------------------------------------------
    //Beschleunigte Bewegung mit Anfangsgeschwindigkeit

    /**
     * Calculates speed with acceleration and starting velocity
     *
     * @param a
     * @param deltaTime
     * @return
     */
    public static void acceleratedMovementVelocityWithStartingVelocity(double a, DeltaTime deltaTime, BallCollider bc) {
        double velocity = a * deltaTime.getCurrentTime() + bc.getVelocity().getX();
        bc.setVelocity(new Vector2d(velocity, bc.getVelocity().getY()));
    }

    /**
     * Calculates current position with starting position and starting velocity
     *
     * @param position0
     * @param velocity0
     * @param deltaTime
     * @param acceleration
     * @return
     */
    public static double acceleratedMovementPositionWithStartingSpeedAndPosition(double position0, double velocity0, DeltaTime deltaTime, double acceleration) {
        double position = position0 + velocity0 * deltaTime.getCurrentTime() + 0.5 * acceleration * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        return position;
    }

    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsgeschwindigkeit

    /**
     * Mean speed
     *
     * @param bc
     * @param deltaTime
     */
    public static void effectiveSpeed(BallCollider bc, DeltaTime deltaTime) {
        //double velocity = ((bc.getPosition().getX() - bc.getLastPosition().getX())) / (deltaTime.getCurrentTime() - deltaTime.getLastTime());
        //bc.setVelocity(new Vector2d(velocity, bc.getVelocity().getY()));

    }

    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsbeschleunigung

    /**
     * Mean acceleration
     *
     * @param bc
     * @param deltaTime
     * @return
     */
    /*public static double effectiveAcceleration(BallCollider bc, DeltaTime deltaTime) {
        double acceleration = (bc.getVelocity().getX() - bc.getLastVelocity().getX()) / (deltaTime.getCurrentTime() - deltaTime.getLastTime());
        return 0;
    }*/

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
        bc.setPosition(new Vector2d(bc.getPosition().getX(), height));
    }

    /**
     * Calculate Free Fall speed from height and gravity
     *
     * @param h
     * @return
     */
    public static void freeFallVelocity(double h, BallCollider bc) {
        double velocity = Math.sqrt(2 * h * GRAVITY);
        bc.setVelocity(new Vector2d(bc.getVelocity().getX(), velocity));
    }

    //-----------------------------------------------------------------------------------------------------------
    //Waagrechter Wurf

    public static void levelThrow(BallCollider bc, DeltaTime deltaTime) {
        double x = levelThrowXPos(bc, deltaTime);
        double y = levelThrowYPos(bc.getVelocity().getY(), deltaTime);
        bc.setPosition(new Vector2d(x, y));

    }

    public static Vector2d levelThrowVector(BallCollider bc, DeltaTime deltaTime) {
        double x = levelThrowXPos(bc, deltaTime);
        double y = levelThrowYPos(bc.getVelocity().getY(), deltaTime);
        return new Vector2d(x, y);

    }

    /**
     * Calculates the x-Position of a level throw
     *
     * @param deltaTime
     * @return
     */
    public static double levelThrowXPos(BallCollider bc, DeltaTime deltaTime) {
        double xPos = bc.getVelocity().getX()* deltaTime.getCurrentTime()+bc.getStartingPoint().getX();
        return xPos;
    }

    /**
     * Calculates the y-Position of a level throw
     *
     * @param height
     * @param deltaTime
     * @return
     */

    public static double levelThrowYPos(double height, DeltaTime deltaTime) {
        double yPos =height - 0.5 *- GRAVITY * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        return yPos;
    }

    //--------------------------------------------------------------------------------------
    //unelastischer Stoß

    /**
     * Calculates new velocity after an unelastic push
     *
     * @param bc1
     * @param bc2
     */
    public static void unelasticPushVelocity(BallCollider bc1, BallCollider bc2) {
        double v = (bc1.getMass() * bc1.getVelocity().getX() + bc2.getMass() * bc2.getVelocity().getX()) / (bc1.getMass() + bc2.getMass());
        bc1.setVelocity(new Vector2d(v, bc1.getVelocity().getY()));
        bc2.setVelocity(new Vector2d(v, bc2.getVelocity().getY()));
    }

    /**
     * Calculates new velocity after an unelastic push
     *
     * @param bc1
     * @param bc2
     */
    public static double unelasticPushVelocityReturn(BallCollider bc1, BallCollider bc2) {
        double v = (bc1.getMass() * bc1.getVelocity().getX() + bc2.getMass() * bc2.getVelocity().getX()) / (bc1.getMass() + bc2.getMass());
        return v;
    }

    //---------------------------------------------------------------------------------
    //elastischer Stoß

    public static ArrayList<Double> elasticPush(BallCollider bc1, BallCollider bc2) {
        double v1 = elasticPushVelocity1(bc1.getMass(), bc2.getMass(), bc1.getVelocity().getX(), bc2.getVelocity().getX());
        double v2 = elasticPushVelocity2(bc1.getMass(), bc2.getMass(), bc1.getVelocity().getX(), bc2.getVelocity().getX());

        bc1.setVelocity(new Vector2d(v1, bc1.getVelocity().getY()));
        bc2.setVelocity(new Vector2d(v2, bc2.getVelocity().getY()));
        ArrayList<Double> vs = new ArrayList();
        vs.add(v1);
        vs.add(v2);
        return vs;
    }

    /**
     * Calculates new velocity of first colliding object
     *
     * @param mass0
     * @param mass1
     * @param velocity0
     * @param velocity1
     * @return
     */
    public static double elasticPushVelocity1(double mass0, double mass1, double velocity0, double velocity1) {
        double velocity = ((mass0 - mass1) * (velocity0 * velocity0) + 2 * mass1 * velocity1) / (mass0 + mass1);
        return velocity;
    }

    /**
     * Calculates starting velocity of object that is pushed
     *
     * @param mass0
     * @param mass1
     * @param velocity0
     * @param velocity1
     * @return
     */
    public static double elasticPushVelocity2(double mass0, double mass1, double velocity0, double velocity1) {
        double velocity = ((mass1 - mass0) * velocity1 + 2 * mass1 * velocity1) / (mass0 + mass1);
        return velocity;
    }


    //------------------------------------------------------------------------------------------------------------------
    //Radialbeschleunigung

    /**
     * Radial acceleration
     *
     * @param bc
     * @return
     */
    public static double radialAcceleration(BallCollider bc) {
        //double acceleration=(velocity*velocity)/radius;
        double a = (bc.getVelocity().getX() * bc.getVelocity().getX()) / bc.getRadius();
        a =( bc.getVelocity().getX()*bc.getVelocity().getX())/bc.getRadius();
        return a;
    }


}
