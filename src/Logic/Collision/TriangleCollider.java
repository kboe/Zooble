package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Rocki on 11.05.2017.
 */
public class TriangleCollider extends PolygonCollider {

    public TriangleCollider(){
        init();
    }

    private void init(){
        points = new Vector2d[3];
    }

    @Override
    public boolean checkCollision(Collider collider) {
        return false;                                           //TODO Implement stuff :D
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
