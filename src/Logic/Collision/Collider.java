package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

/**
 * Created by Rocki on 09.05.2017.
 */
public abstract class Collider{

    private Vector2d position;
    private Rotate rotation;
    private double angle = 0;

    //CONSTRUCTOR
    public Collider(){}


    //METHODS

    public abstract boolean checkCollision(Collider collider);

    public abstract void draw(GraphicsContext gc);

    //GETTER AND SETTER
    public Vector2d getPositionVector() {
        return position;
    }

    public void setPositionVector(Vector2d position) {
        this.position = position;
    }

    public Rotate getRotation() {
        return rotation;
    }

    public void setRotation(Rotate rotation) {
        this.rotation = rotation;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
