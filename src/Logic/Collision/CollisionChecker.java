package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;

/**
 * Created by Rocki on 23.05.2017.
 */
public final class CollisionChecker {

    /**
     * checks if a ballCollider (circle Shape) or another Shape intersects
     * @param ball the ballCollider you want to investigate
     * @param otherCollider the other Shape you want to check
     * @return returns true if an Intersection has been found, false if there is no intersection
     */
    public static boolean checkCollision(BallCollider ball, Shape otherCollider){
        Shape intersection = Shape.intersect(ball,otherCollider);
        return intersection.getLayoutBounds().getHeight() > 0 || intersection.getLayoutBounds().getWidth() > 0;         //if the 2 Shapes Collide...
    }


    /**
     * Returns the CollisionPoint of the 2 Shapes, could lead to bugs, if the shapes intersect too far
     * @param ball  the ballCollider you want to check
     * @param otherCollider the other Collider you want to check
     * @return returns a Vector2d with the X and Y of the intersection point
     */
    public static Vector2d getCollisionPoint(BallCollider ball, Shape otherCollider){
        Shape intersection = Shape.intersect(ball,otherCollider);
        return new Vector2d(intersection.getBoundsInParent().getMinX(), intersection.getBoundsInParent().getMinY());
    }

    /**
     * checks if the ballCollider reaches the bounds of a canvas
     * @param canvas the canvas in which the ballCollider exists
     * @param ball the ballCollider you want to check
     */
    public static void checkSceneBoundsCollision(Canvas canvas, BallCollider ball){
        if (ball.getCenterX() + ball.getRadius() > canvas.getWidth()){
            System.out.println("ball outside of Bounds (right)");
            System.out.println("Out od bounds at: ("+ball.getCenterX()+","+ball.getCenterY()+")" );

            LoopStopped.setOut_of_bounds(true);
            //return Vector2D(-1,0)     //TODO maybe return normal vector depending of the length and height of the scene and velocity of ball? idk
        } /*else if (ball.getCenterX() - ball.getRadius() < 0){
            System.out.println("ball outside of Bounds (left)");
            LoopStopped.setOut_of_bounds(true);
            //return Vector2D(1,0)
        }*/ else if (ball.getCenterY() - ball.getRadius() < 0){
            LoopStopped.setOut_of_bounds(true);
            System.out.println("ball outside of Bounds (up)");
            //return Vector2D(0,-1)
        } else if (ball.getCenterY() + ball.getRadius() > canvas.getHeight()){
            LoopStopped.setOut_of_bounds(true);
            System.out.println("ball outside of Bounds (down)");
            //return Vector2D(0,1)
        }
    }
}
