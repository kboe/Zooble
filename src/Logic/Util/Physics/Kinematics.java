package Logic.Util.Physics;

import Logic.Util.DeltaTime;

/**
 * Created by ${kboe} on 24.05.2017.
 */
public class Kinematics {

    public static final double GRAVITY = 9.81;

    //----------------------------------------------------------------------------------------------------------------
    //Beschleunigte Bewegung

    /**
     * Calculates speed with acceleration
     * @param a
     * @param deltaTime
     * @return
     */
    public static double acceleratedMovementVelocity(double a, DeltaTime deltaTime){
        double velocity = a*deltaTime.getCurrentTime();
        return velocity;
    }

    public static double accleratedMovementPosition(double acceleration, DeltaTime deltaTime){
        double s = 0.5*acceleration*(deltaTime.getCurrentTime()*deltaTime.getCurrentTime());
        return 0;
    }

    //----------------------------------------------------------------------------------------------------------------
    //Beschleunigte Bewegung mit Anfangsgeschwindigkeit

    /**
     * Position with consistent velocity with beginning velocity
     *
     * @param v velocity
     * @param a acceleration
     * @param t time
     * @return current position
     */
    public static double consistentVelocity(double v, double a, int t) {
        double pos = a * t + v;
        return pos;
    }

    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsgeschwindigkeit

    //----------------------------------------------------------------------------------------------------------------
    //Durchschnittsbeschleunigung

    //----------------------------------------------------------------------------------------------------------------
    //Freier Fall

    /**
     * Free Fall y-Pos
     * @param deltaTime
     * @return
     */
    public static double freeFallHeight(DeltaTime deltaTime) {
        double height = 0.5 * GRAVITY * (deltaTime.getCurrentTime() * deltaTime.getCurrentTime());
        return height;
    }

    /**
     * Calculate Free Fall speed from height and gravity
     * @param h
     * @return
     */
    public static double freeFallVelocity(double h){
        double velocity = Math.sqrt(2*h*GRAVITY);
        return velocity;
    }
}
