package Logic.Util.Physics;

import Logic.Collision.BallCollider;
import Logic.Collision.BoxCollider;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;

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
    public static void acceleratedMovementVelocity(double a, DeltaTime deltaTime, BallCollider bc) {
        double velocity = a * deltaTime.getCurrentTime();
        bc.setCurrentVelocity(new Vector2d(velocity, bc.getVelocity().getY()));
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
        double velocity = ((bc.getPosition().getX() - bc.getLastPosition().getX())) / (deltaTime.getCurrentTime() - deltaTime.getLastTime());
        bc.setVelocity(new Vector2d(velocity, bc.getVelocity().getY()));
    }

    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsbeschleunigung

    /**
     * Mean acceleration
     * @param bc
     * @param deltaTime
     * @return
     */
    public static double effectiveAcceleration(BallCollider bc, DeltaTime deltaTime) {
        double acceleration = (bc.getVelocity().getX()-bc.getLastVelocity().getX()) / (deltaTime.getCurrentTime()-deltaTime.getLastTime());
        return 0;
    }

    //----------------------------------------------------------------------------------------------------------------
    //Freier Fall

    /**
     * Free Fall y-Pos
     *
     * @param deltaTime
     * @return
     */
    public static void freeFallHeight(DeltaTime deltaTime,BallCollider bc) {
        double height = 0.5 * GRAVITY * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        bc.setPosition(new Vector2d(bc.getPosition().getX(),height));
    }

    /**
     * Calculate Free Fall speed from height and gravity
     *
     * @param h
     * @return
     */
    public static void freeFallVelocity(double h, BallCollider bc) {
        double velocity = Math.sqrt(2 * h * GRAVITY);
        bc.setVelocity(new Vector2d(bc.getVelocity().getX(),velocity));
    }

    //-----------------------------------------------------------------------------------------------------------
    //Waagrechter Wurf

    public static void levelThrow(BallCollider bc, DeltaTime deltaTime){
        double x = levelThrowXPos(bc.getVelocity().getX(),deltaTime);
        double y = levelThrowYPos(bc.getVelocity().getY(),deltaTime);
        bc.setPosition(new Vector2d(x,y));

    }

    /**
     * Calculates the x-Position of a level throw
     *
     * @param velocityX
     * @param deltaTime
     * @return
     */
    public static double levelThrowXPos(double velocityX, DeltaTime deltaTime) {
        double xPos = velocityX * deltaTime.getCurrentTime();
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
        double yPos = height - 0.5 * GRAVITY * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        return yPos;
    }

    //--------------------------------------------------------------------------------------
    //unelastischer Stoß

    /**
     * Calculates new velocity after an unelastic push
     * @param bc1
     * @param bc2
     */
    public static void unelasticPushVelocity(BallCollider bc1, BallCollider bc2){
        double v =(bc1.getMass()*bc1.getVelocity().getX()+bc2.getMass()*bc2.getVelocity().getX())/(bc1.getMass()+bc2.getMass());
        bc1.setVelocity(new Vector2d(v,bc1.getVelocity().getY()));
        bc2.setVelocity(new Vector2d(v,bc2.getVelocity().getY()));

    }




}
