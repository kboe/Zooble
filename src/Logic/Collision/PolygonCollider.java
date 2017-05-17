package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Created by Rocki on 17.05.2017.
 */
public abstract class PolygonCollider extends Collider {

    Vector2d[] points;

    @Override
    public abstract boolean checkCollision(Collider collider);

    @Override
    public abstract void draw(GraphicsContext gc);
}
