package Logic.Util.Physics;

import Logic.Util.DeltaTime;

/**
 * Created by ${kboe} on 24.05.2017.
 */
public class Kinematics {

    public static final double GRAVITY = 9.81;

    //---------------------------------------------------------------------------------
    //Gradlining beschleunigte Bewegung

    /**
     * Calculate position with unchanging velocity
     *
     * @param velocity
     * @param deltaTime
     * @param position0
     * @return
     */
    public static double evenMovementPosition(double velocity, DeltaTime deltaTime, double position0) {
        double pos = velocity * deltaTime.getCurrentTime() + position0;
        return 0;
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
    public static double acceleratedMovementVelocity(double a, DeltaTime deltaTime) {
        double velocity = a * deltaTime.getCurrentTime();
        return velocity;
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

    //----------------------------------------------------------------------------------------------------------------
    //Beschleunigte Bewegung mit Anfangsgeschwindigkeit

    /**
     * Calculates speed with acceleration and starting velocity
     *
     * @param a
     * @param deltaTime
     * @return
     */
    public static double acceleratedMovementVelocityWithStartingVelocity(double a, DeltaTime deltaTime, double velocity0) {
        double velocity = a * deltaTime.getCurrentTime() + velocity0;
        return velocity;
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
     * @param pos0
     * @param pos1
     * @param deltaTime0
     * @param deltaTime1
     * @return
     */
    public static double effectiveSpeed(double pos0, double pos1, DeltaTime deltaTime0, DeltaTime deltaTime1) {
        double velocity = ((pos1 - pos0) / (deltaTime1.getCurrentTime() - deltaTime0.getCurrentTime()));
        return velocity;
    }

    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsbeschleunigung

    /**
     * Mean acceleration
     *
     * @param velocity0
     * @param velocity1
     * @param deltaTime0
     * @param deltaTime1
     * @return
     */
    public static double effectiveAcceleration(double velocity0, double velocity1, DeltaTime deltaTime0, DeltaTime deltaTime1) {
        double acceleration = (velocity1 - velocity0) / (deltaTime1.getCurrentTime() - deltaTime0.getCurrentTime());
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
    public static double freeFallHeight(DeltaTime deltaTime) {
        double height = 0.5 * GRAVITY * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        return height;
    }

    /**
     * Calculate Free Fall speed from height and gravity
     *
     * @param h
     * @return
     */
    public static double freeFallVelocity(double h) {
        double velocity = Math.sqrt(2 * h * GRAVITY);
        return velocity;
    }

    //-----------------------------------------------------------------------------------------------------------
    //Waagrechter Wurf

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
     * @param mass0
     * @param mass1
     * @param velocity0
     * @param velocity1
     * @return
     */
    public static double unelasticPushVelocity(double mass0, double mass1, double velocity0, double velocity1){
        double velocity = (mass0*velocity0+mass1*velocity1)/(mass0+mass1);
        return velocity;
    }

    //---------------------------------------------------------------------------------
    //elastischer Stoß

    /**
     * Calculates new velocity of first colliding object
     * @param mass0
     * @param mass1
     * @param velocity0
     * @param velocity1
     * @return
     */
    public static double elasticPushVelocity1(double mass0, double mass1, double velocity0, double velocity1){
        double velocity =((mass0-mass1)*(velocity0*velocity0)+2*mass1*velocity1)/(mass0+mass1);
        return velocity;
    }

    /**
     * Calculates starting velocity of object that is pushed
     * @param mass0
     * @param mass1
     * @param velocity0
     * @param velocity1
     * @return
     */
    public static double elasticPushVelocity2(double mass0, double mass1, double velocity0, double velocity1){
        double velocity =((mass1-mass0)*velocity1+2*mass1*velocity1)/(mass0+mass1);
        return velocity;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Radialbeschleunigung

    /**
     * Radial acceleration
     * @param velocity
     * @param radius
     * @return
     */
    public static double radialAcceleration(double velocity, double radius){
        double acceleration=(velocity*velocity)/radius;

        return acceleration;
    }

}
