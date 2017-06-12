package Logic.Collision.Collider;

import Logic.Util.Vector2d;
import com.sun.istack.internal.Nullable;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Created by Rocki on 24.05.2017.
 */
public class BallCollider extends Circle {

    private Vector2d position;          //will be the center of the circle!
    private double eKin;
    private double ePot;
    private Vector2d velocity = new Vector2d(0, 0);
    private Vector2d acceleration = new Vector2d(1, 0);
    private Vector2d startingPoint;
    private Vector2d velocity0;
    private Vector2d lastPos = new Vector2d(0, 0);
    private Vector2d lastLastPos = new Vector2d(0, 0);
    private Vector2d pulse;
    private double angle;
    private double gForce;
    private double hForce;
    private double nForce;
    private double rForce;
    private double lastSpeed;
    private double lastLastSpeed;
    private double mass = 1;


    //CONSTRUCTOR

    public BallCollider(double centerX, double centerY, double radius, @Nullable Paint paint) {
        super(centerX, centerY, radius, paint);
        position = new Vector2d(centerX, centerY);
        if (paint != null) {
            this.setFill(paint);
        }
    }
    public BallCollider(double centerX, double centerY, double radius, @Nullable Paint paint, double mass) {
        super(centerX, centerY, radius, paint);
        position = new Vector2d(centerX, centerY);
        startingPoint=position;
        this.mass=mass;
        if (paint != null) {
            this.setFill(paint);
        }
    }


    //GETTER & SETTER

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
        setCenterX(position.getX());
        setCenterY(position.getY());
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getLastSpeed() {
        return lastSpeed;
    }

    public void setLastSpeed(double lastSpeed) {
        this.lastSpeed = lastSpeed;
    }

    public double getLastLastSpeed() {
        return lastLastSpeed;
    }

    public void setLastLastSpeed(double lastLastSpeed) {
        this.lastLastSpeed = lastLastSpeed;
    }

    public Vector2d getVelocity0() {
        return velocity0;
    }

    public void setVelocity0(Vector2d velocity0) {
        this.velocity0 = velocity0;
    }

    public Vector2d getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Vector2d startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Vector2d getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2d acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2d getLastPos() {
        return lastPos;
    }

    public void setLastPos(Vector2d lastPos) {
        this.lastPos = lastPos;
    }

    public Vector2d getLastLastPos() {
        return lastLastPos;
    }

    public void setLastLastPos(Vector2d lastLastPos) {
        this.lastLastPos = lastLastPos;
    }

    public double geteKin() {
        return eKin;
    }

    public void seteKin(double eKin) {
        this.eKin = eKin;
    }

    public double getePot() {
        return ePot;
    }

    public void setePot(double ePot) {
        this.ePot = ePot;
    }

    public Vector2d getPulse() {
        return pulse;
    }

    public void setPulse(Vector2d pulse) {
        this.pulse = pulse;
    }

    public double getgForce() {
        return gForce;
    }

    public void setgForce(double gForce) {
        this.gForce = gForce;
    }

    public double gethForce() {
        return hForce;
    }

    public void sethForce(double hForce) {
        this.hForce = hForce;
    }

    public double getnForce() {
        return nForce;
    }

    public void setnForce(double nForce) {
        this.nForce = nForce;
    }

    public double getrForce() {
        return rForce;
    }

    public void setrForce(double rForce) {
        this.rForce = rForce;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
