package Logic.Util.Physics.Deprecated;


/**
 * Created by Karen on 14.05.2017.
 */
public class Physics {

    private double lastFrameTime; //time Stamp
    private double s0;
    private double s1 = 1;


    private double v; //velocity
    public final static double GRAVITY = 9.81; //gravity
    private double y = 100;

    boolean collided = false;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getV() {
        return v;
    }



    /**
     * Position with consistent velocity with beginning velocity
     *
     * @param v velocity
     * @param a acceleration
     * @param t time
     * @return current position
     */
    public double consistentVelocity(double v, double a, int t) {
        double pos = a * t + v;
        return pos;
    }
    //________________________________________________________________________________________________________________

    /**
     * Free Fall from certain height
     *
     * @param h    y-Position
     * @param time time
     * @return current y Position
     */
    public double yPos(double h, double time) {
        double y = getY() + GRAVITY / 2 * (time * time);
        System.out.println(y);
        setY(y);
        return y;
    }


    /**
     * Get time from last Frame
     *
     * @return time of last Frame
     */
    public double getLastFrameTime() {
        return lastFrameTime;
    }

    /**
     * Set current time for next frame
     *
     * @param lastFrameTime
     */
    public void setLastFrameTime(double lastFrameTime) {
        this.lastFrameTime = lastFrameTime;
    }

    public double getS0() {
        return s0;
    }

    public void setS0(double s0) {
        this.s0 = s0;
    }

    public double getS1() {
        return s1;
    }

    public void setS1(double s1) {
        this.s1 = s1;
    }

    //TODO doesnt really make sense yet

    /**
     * Calculate instaneous Velocity
     *
     * @param lastFrameTime
     * @param currentTime
     * @param lastPos
     * @param currentPos
     * @return velocity
     */
    public double instantaneousVelocity(double lastFrameTime, double currentTime, double lastPos, double currentPos) {
        double v = (currentPos - lastPos) / (currentTime - lastFrameTime);
        return v;
    }

    /**
     * Calculate current position with consistent velocity
     *
     * @param velocity
     * @param time
     * @param lastPosition
     * @return current position
     */
    public double linearEvenlyMoving(double velocity, double time, double lastPosition) {
        double position = (velocity * time) + lastPosition;
        return position;
    }

    /**
     * Free Fall velocity
     *
     * @param time
     * @return current velocity
     */
    public double freeFallSpeed(double time) {
        double velocity = GRAVITY * time;
        return velocity;
    }

    /**
     * Calculates velocity of an unelastic push
     *
     * @param mass1     Mass 1
     * @param mass2     Mass 2
     * @param velocity1 Velocity 1
     * @param velocity2 Velocity 2
     * @return velocity after push
     */
    public double unelasticPush(double mass1, double mass2, double velocity1, double velocity2) {
        double v = ((mass1 * velocity1) + (mass2 * velocity2)) / (mass1 + mass2);
        return v;
    }

    /**
     * Velocity of first object after Colliding
     *
     * @param mass1
     * @param mass2
     * @param velocity1
     * @param velocity2
     * @return velocity of object
     */
    public double elasticPushVel1(double mass1, double mass2, double velocity1, double velocity2) {
        double u1 = (((mass1 - mass2) * velocity1) + 2 * mass2 * velocity2) / (mass1 + mass2);
        return u1;
    }

    /**
     * Velocity of second object after Colliding
     *
     * @param mass1
     * @param mass2
     * @param velocity1
     * @param velocity2
     * @return velocity of object
     */
    public double elasticPushVel2(double mass1, double mass2, double velocity1, double velocity2) {
        double u2 = (((mass2 - mass1) * velocity2) + 2 * mass2 * velocity1) / (mass1 + mass2);
        return u2;
    }


    /**
     * Kinetic Energy
     *
     * @param mass
     * @param velocity
     * @return kinetic Energy
     */
    public double eKin(double mass, double velocity) {
        double eKin = 0.5 * mass * (velocity * velocity);
        return eKin;
    }

    /**
     * Evenly accelerated Movement velocity
     *
     * @param acceleration
     * @param time
     * @param lastVelocity
     * @return velocity
     */
    public double evenAcceleratedMovement(double acceleration, double time, double lastVelocity) {
        double velocity = acceleration * time + lastVelocity;
        return velocity;
    }

    /**
     * Even acceleration
     *
     * @param dv velocity
     * @param dt time
     * @return acceleration
     */
    public double evenAcceleration(double dv, double dt) {
        double a = dv / dt;
        return a;
    }

    /**
     * Position with even accelerated Movement
     *
     * @param acceleration
     * @param time
     * @param velocity0
     * @param position0
     * @return current Position
     */
    public double evenAcceleratedMovementPosition(double acceleration, double time, double velocity0, double position0) {
        double pos = ((acceleration / 2) * (time * time)) + (velocity0 * time) + position0;
        return pos;
    }

    /**
     * Level Toss
     *
     * @param velocity
     * @param time
     * @return x Position
     */
    public double levelTossX(double velocity, double time) {
        double x = velocity * time;
        return x;
    }

    /**
     * Level Toss
     *
     * @param gravity
     * @param time
     * @return y Position
     */
    public double LevelTossY(double gravity, double time) {
        double y = gravity * (time * time);
        return y;
    }


    /**
     * Konstante Beschleunigung
     *
     * @param dv
     * @param t0
     * @param t1
     * @return
     */
    public double cAcceleration(double dv, double t0, double t1) {
        double a = dv / (t1 - t0);
        return a;
    }


}
