package Logic.Util.Physics;


/**
 * Created by Karen on 14.05.2017.
 */
public class Physics {

    private double lastFrameTime; //time Stamp
    private double s0;
    private double s1 = 1;


    private double v; //velocity
    public final double gravity = 9.81; //gravity
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

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    /**
     * Position with consistent velocity
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

    /**
     * Free Fall from certain height
     *
     * @param h    y-Position
     * @param time time
     * @return current y Position
     */
    public double yPos(double h, double time) {
        double y = getY() + gravity / 2 * (time * time);
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
        double velocity = gravity * time;
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
     * @param mass1
     * @param mass2
     * @param velocity1
     * @param velocity2
     * @return velocity of object
     */
    public double elasticPushVel1(double mass1, double mass2, double velocity1, double velocity2) {
        double u1= (((mass1-mass2)*velocity1)+2*mass2*velocity2)/(mass1+mass2);
        return u1;
    }

    /**
     * Velocity of second object after Colliding
     * @param mass1
     * @param mass2
     * @param velocity1
     * @param velocity2
     * @return velocity of object
     */
    public double elasticPushVel2(double mass1, double mass2, double velocity1, double velocity2) {
        double u2= (((mass2-mass1)*velocity2)+2*mass2*velocity1)/(mass1+mass2);
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
     * Gleichmäßig beschleunigte Bewegung --> Position
     *
     * @param a
     * @param time
     * @param v0
     * @param s0
     * @return
     */
    public double gbBSpeed(double a, int time, double v0, double s0) {
        double s = a / 2 * (time * time) + v0 + time + s0;
        return s;
    }

    /**
     * Geschwindigkeit nach einem unelastischem Stoß
     *
     * @param m0
     * @param v0
     * @param m1
     * @param v1
     * @return
     */
    public double unelasticS(double m0, double v0, double m1, double v1) {
        double u = ((m1 * v1) + (m0 * v0)) / (m0 + m1);
        return u;
    }

    /**
     * Durchsnittsgeschwindigkeit in einem Intervall
     *
     * @param s0
     * @param s1
     * @param t0
     * @param t1
     * @return
     */
    public double averageSpeed(double s0, double s1, double t0, double t1) {
        double v = (s1 - s0) / (t1 - t0);
        return v;
    }

    /**
     * Konstante Beschleunigung
     *
     * @param dv
     * @param dt
     * @return
     */
    public double cAcceleration(double dv, double dt) {
        double a = dv / dt;
        return 0;
    }

    public double wX(double v0, double time) {
        double x = v0 * time;
        return x;
    }

    public double wY(double gravity, double time) {
        double y = gravity * (time * time);
        setY(y);
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
