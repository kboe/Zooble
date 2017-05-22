package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Rocki on 11.05.2017.
 */
public class TriangleCollider extends PolygonCollider {

    public TriangleCollider(Vector2d[] points){
        setPoints(points);
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
