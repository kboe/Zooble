package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Created by Rocki on 17.05.2017.
 */
public abstract class PolygonCollider extends Collider {

    private Vector2d[] points;

    @Override
    public abstract boolean checkCollision(Collider collider);

    @Override
    public abstract void draw(GraphicsContext gc);

    public Vector2d[] getPoints() {
        return points;
    }

    public void setPoints(Vector2d[] points) {
        this.points = points;
    }
}
