package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;

/**
 * Created by Rocki on 23.05.2017.
 */
public final class CollisionChecker {
    public static boolean floorContact =false;

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
     * Checks if the ball an the Collider have a contact or not.
     * Would recommend this order: if checkCollision == true -> if checkContact == true -> contact -> else -> collision
     * @param movementOfBall the current movement Vector of the Ball
     * @param normalOfBoxCollider the normal Vector of side of the box, where the ball collides with it
     * @return true if contact has been found or false if not
     */
    public static boolean checkContact(Vector2d movementOfBall, Vector2d normalOfBoxCollider){
        if (Vector2d.dot(movementOfBall, normalOfBoxCollider) <= 0.05 && Vector2d.dot(movementOfBall, normalOfBoxCollider) >= -0.05){       // ... == 0 maybe better?
            System.out.println("Contact!");
            return true;
        } else {
            System.out.println("no Contact, maybe collision");
            return false;
        }
    }

    /**
     * NOT YET READY, DO NOT USE FOR BOX COLLIDERS
     * gives the normal which can be added to the Velocity of the Ball, depending of the Collision Point of the colliders
     * @param playerBall    the Ball of the player
     * @param Collider      any collider in the scene (check for collision before using this)
     * @return              a new normalized Vector which can be modified and added to the current Velocity of the playerBall
     */
    @Deprecated
    public static Vector2d getNormalOfCollider(BallCollider playerBall, Shape Collider){
        if (Collider instanceof BallCollider){
            BallCollider ballCollider = (BallCollider) Collider;
            Vector2d normal = Vector2d.subtract(playerBall.getPosition(),ballCollider.getPosition());
            normal.normalize();
            return normal;

        } else if (Collider instanceof BoxCollider){
            //TODO find Side of the Box Collider where the Ball is colliding -> give normal of that side
            return null;
        } else {
            return null;
        }
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
     * checks if the ballCollider reaches the bounds of a canvas and inverts the Velocity of it
     * @param canvas the canvas in which the ballCollider exists
     * @param ball the ballCollider you want to check
     */
    public static void checkSceneBoundsCollision(Canvas canvas, BallCollider ball){
        if (ball.getCenterX() + ball.getRadius() > canvas.getWidth()){

            System.out.println("ball outside of Bounds (right)");
            System.out.println("Out od bounds at: ("+ball.getCenterX()+","+ball.getCenterY()+")" );
            ball.setPosition(new Vector2d(canvas.getWidth() - ball.getRadius(), ball.getPosition().getY()));            //Correct Ball position -> prevents bugs
            ball.getVelocity().invertX();

        } else if (ball.getCenterX() - ball.getRadius() < 0){
            System.out.println("ball outside of Bounds (left)");
            ball.setPosition(new Vector2d(0 + ball.getRadius(), ball.getPosition().getY()));                            //Correct Ball position -> prevents bugs
            ball.getVelocity().invertX();

        } else if (ball.getCenterY() - ball.getRadius() < 0){
            System.out.println("ball outside of Bounds (up)");
            ball.setPosition(new Vector2d(ball.getPosition().getX(), 0 + ball.getRadius()));                            //Correct Ball position -> prevents bugs
            ball.getVelocity().invertY();

        } else if (ball.getCenterY() + ball.getRadius() > canvas.getHeight()){
            floorContact=true;

            System.out.println("ball outside of Bounds (down)");
            ball.setPosition(new Vector2d(ball.getPosition().getX(), canvas.getHeight() - ball.getRadius()));           //Correct Ball position -> prevents bugs
            ball.getVelocity().invertY();


        }
        ball.setPosition(Vector2d.add(ball.getPosition(),ball.getVelocity()));
    }
}
