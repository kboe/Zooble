package Logic;


/**
 * Created by Administrator on 14.05.2017.
 */
public class Physics {


    long t0;
    long t1;
    double s0;
    double s1 = 1;
    int time;

    double v;
    public final double gravity = 9.81;
    double y = 100;

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
     * Gleichmäßig beschleunigte Bewegung
     * Geschwindigkeit
     *
     * @param v
     * @param a
     * @param t
     */
    public void setV(double v, double a, int t) {
        this.v = a * t + v;
    }

    /**
     * Freier Fall Position
     *
     * @param h
     * @param time
     * @return
     */
    public double yPos(double h, double time) {
        double y = getY() + gravity / 2 * (time * time);
        System.out.println(y);
        setY(y);
        return y;
    }


    int radius = 50;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getT0() {
        return t0;
    }

    public void setT0(long t0) {
        this.t0 = t0;
    }

    public long getT1() {
        return t1;
    }

    public void setT1(long t1) {
        System.out.println(t1);
        this.t1 = t1;
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


    public double xSpeed(long t0, long t1, double s0, double s1) {
        double v = (s1 - s0) / (t1 - t0);
        System.out.println(v);
        return v;
    }

    public double xPos(double v, double t, double s0) {
        double s = (v * t + s0);
        //setS0(s);
        setY(s);
        System.out.println(s);
        return s;
    }

    public double freeFallSpeed(int time, double gravity) {
        double v = gravity * time;
        return v;
    }

    /**
     * Für erste Kugel die Geschwindigkeit bei Kugelkollision
     * @param m1
     * @param m2
     * @param v1
     * @param v2
     * @return
     */
    public double elStv0(double m1, double m2, double v1, double v2){
        double u1= ((m1-m2)*(v1*v1)+(2*m2*v2))/(m1+m2);
        return  u1;
    }
    /**
     * Für zweite Kugel die Geschwindigkeit bei Kugelkollision
     * @param m1
     * @param m2
     * @param v1
     * @param v2
     * @return
     */
    public double elStv1(double m1, double m2, double v1, double v2){
        double u2= ((m2-m1)*(v2*v2)+(2*m1*v1))/(m1+m2);
        return  u2;
    }

    /**
     * Kinetische Energie
     * @param m
     * @param v
     * @return
     */
    public double eKin(double m, double v){
        double eKin = 0.5*m*(v*v);
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
     * @param dv
     * @param dt
     * @return
     */
    public double cAcceleration(double dv, double dt) {
        double a = dv/dt;
        return 0;
    }

    public double wX(double v0,double time){
        double x= v0*time;
        return x;
    }
    public double wY(double gravity, double time){
        double y = gravity*(time*time);
        setY(y);
        return y;
    }


    /**
     * Konstante Beschleunigung
     * @param dv
     * @param t0
     * @param t1
     * @return
     */
    public double cAcceleration(double dv, double t0, double t1) {
        double a = dv/(t1-t0);
        return a;
    }


}
