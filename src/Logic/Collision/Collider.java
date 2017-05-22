package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

/**
 * Created by Rocki on 09.05.2017.
 */
public abstract class Collider {

    private double angle = 0;
    private double mass;

    //CONSTRUCTOR
    public Collider() {
    }


    //METHODS

    public abstract void draw(GraphicsContext gc);

    //GETTER AND SETTER

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
}
