package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Rocki on 11.05.2017.
 */
public class RectangleCollider extends PolygonCollider {

    public RectangleCollider(){
     init();
    }

    private void init(){
        points = new Vector2d[4];
    }

    @Override
    public boolean checkCollision(Collider collider) {
        return false;                                       //TODO Implement stuff :D
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
