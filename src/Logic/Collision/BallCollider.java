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
    private Vector2d velocity;
    private double mass = 0;
    private double currentVelocity;
    private double lastVelocity;



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

    public double getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(double currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public double getLastVelocity() {
        return lastVelocity;
    }

    public void setLastVelocity(double lastVelocity) {
        this.lastVelocity = lastVelocity;
    }
}
