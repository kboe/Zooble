package Logic.Collision;

import Logic.Util.Vector2d;
import com.sun.istack.internal.Nullable;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Created by Rocki on 24.05.2017.
 */
public class BallCollider extends Circle {

    private Vector2d position;          //will be the center of the circle!
    //private Vector2d lastPosition;
    private double lastPosition;
    private double lastLastPosition;
    private Vector2d velocity = new Vector2d(2, 0);
    private Vector2d accelerationV=new Vector2d(1,0);
    private Vector2d currentVelocity;
    private Vector2d lastVelocity;
    private Vector2d startingPoint;
    private Vector2d velocity0;
    private Vector2d lastPos;
    private Vector2d lastLastPos;
    private double lastSpeed;
    private double lastLastSpeed;
    private double mass = 1;
    private double acceleration=0;
    private double velocityX;

    private double velocityY;
    private double velocityX2;
    private double velocityY2;
    private double s0;
    private double s1;


    //CONSTRUCTOR

    public BallCollider(double centerX, double centerY, double radius, @Nullable Paint paint) {
        super(centerX, centerY, radius, paint);
        position = new Vector2d(centerX, centerY);
        if (paint != null) {
            this.setFill(paint);
        }
    }


    //GETTER & SETTER

    private void position(Vector2d vector2d) {
        setCenterX(vector2d.getX());
        setCenterY(vector2d.getY());
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
        position(position);
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

    //TODO wo sollen wir das hintun?


    public void setCurrentVelocity(Vector2d currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public Vector2d getLastVelocity() {
        return lastVelocity;
    }


   /* public Vector2d getLastPosition() {
        return lastPosition;
    }*/

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getS0() {
        return s0;
    }

    public void setS0(double s0) {
        this.s0 = s0;
    }

    public void setLastLastPosition(double lastLastPosition) {
        this.lastLastPosition = lastLastPosition;
    }

    public void setLastPosition(double lastPosition) {
        this.lastPosition = lastPosition;
    }

    public double getLastPosition() {
        return lastPosition;
    }

    public double getLastLastPosition() {
        return lastLastPosition;
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

    public Vector2d getAccelerationV() {
        return accelerationV;
    }

    public void setAccelerationV(Vector2d accelerationV) {
        this.accelerationV = accelerationV;
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
}
