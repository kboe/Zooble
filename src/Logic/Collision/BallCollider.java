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
    private Vector2d lastPosition;
    private Vector2d velocity=new Vector2d(2,0);
    private Vector2d currentVelocity;
    private Vector2d lastVelocity;
    private double mass = 1;
    private double acceleration;
    private double velocityX;
    private double velocityY;
    private double velocityX2;
    private double velocityY2;
    //private double currentVelocity;
    //private double lastVelocity;



    //CONSTRUCTOR

    public BallCollider(double centerX, double centerY, double radius, @Nullable Paint paint){
        super(centerX, centerY, radius, paint);
        position = new Vector2d(centerX,centerY);
        if (paint != null){
            this.setFill(paint);
        }
    }



    //GETTER & SETTER

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
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

    public Vector2d getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(Vector2d currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public Vector2d getLastVelocity() {
        return lastVelocity;
    }

    public void setLastVelocity(Vector2d lastVelocity) {
        this.lastVelocity = lastVelocity;
    }

    public Vector2d getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Vector2d lastPosition) {
        this.lastPosition = lastPosition;
    }

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

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getVelocityX2() {
        return velocityX2;
    }

    public void setVelocityX2(double velocityX2) {
        this.velocityX2 = velocityX2;
    }

    public double getVelocityY2() {
        return velocityY2;
    }

    public void setVelocityY2(double velocityY2) {
        this.velocityY2 = velocityY2;
    }
}
