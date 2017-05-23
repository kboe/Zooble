package Logic.Collision;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Created by Rocki on 23.05.2017.
 */
public final class CollisionChecker {

    public static boolean checkCollision(Shape shape1, Shape shape2){
        Shape intersection = Shape.intersect(shape1,shape2);
        return intersection.getLayoutBounds().getHeight() > 0 || intersection.getLayoutBounds().getWidth() > 0;         //if the 2 Shapes Collide...
    }

}
