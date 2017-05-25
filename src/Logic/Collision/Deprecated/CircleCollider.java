package Logic.Collision.Deprecated;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/**
 * Created by Rocki on 09.05.2017.
 */
public class CircleCollider extends Collider {

    private Vector2d position;
    private int radius;
    private Image circleImage;
    private double rotationSpeed = 0.5;
    private Rotate rotation;

    //CONSTRUCTOR

    /**
     * Creates a Collider with a circular shape, be aware, that the x and y coords will be stored into a Vector2d, which you can access by getPositionVector()
     *
     * @param xCoord x Position
     * @param yCoord y Position
     * @param radius the radius of the circle
     */
    public CircleCollider(int xCoord, int yCoord, int radius) {
        init(xCoord, yCoord, radius);
    }

    //METHODS
    private void init(int xCoord, int yCoord, int radius) {
        this.setPositionVector(new Vector2d(xCoord, yCoord));
        setRadius(radius);
        setRotation(new Rotate(getAngle(), this.getPositionVector().getX(), this.getPositionVector().getY()));
    }

    public boolean checkCollision(CircleCollider collider) {          //TODO maybe create this method static, so it can be used on EVERY Object in the scene

        if (collider != null) {
            Vector2d distance = new Vector2d(collider.getPositionVector().getX() - this.getPositionVector().getX(), collider.getPositionVector().getY() - this.getPositionVector().getY());
            return distance.getLength() <= this.getCircleImage().getWidth() / 2 + collider.getCircleImage().getWidth() / 2;  //TODO use subtract method for Vector distance

        } else {
            //SAT COLLISION
            return false;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();          //else, rotation of the graphics context will be visible
        // radius * 2 because width and height need diameter
        //drawing the circle with radius as offset keeps collider and graphics in sync
        rotate(gc);
        gc.drawImage(circleImage, this.getPositionVector().getX() - circleImage.getWidth() / 2, this.getPositionVector().getY() - circleImage.getHeight() / 2);
        gc.restore();
    }

    /**
     * rotates the Graphics Context for spinning objects
     *
     * @param gc the Graphics Context which should be rotated
     */
    private void rotate(GraphicsContext gc) {
        getRotation().setAngle(getAngle());
        getRotation().setPivotX(getPositionVector().getX());
        getRotation().setPivotY(getPositionVector().getY());
        gc.setTransform(getRotation().getMxx(), getRotation().getMyx(), getRotation().getMxy(), getRotation().getMyy(), getRotation().getTx(), getRotation().getTy());
        setAngle(getAngle() + rotationSpeed); //TODO make it rotate by user input
    }


    //GETTER AND SETTER

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int newRadius) {
        this.radius = newRadius;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public Image getCircleImage() {
        return circleImage;
    }

    public void setCircleImage(Image circleImage) {
        this.circleImage = circleImage;
    }

    public Rotate getRotation() {
        return rotation;
    }

    public void setRotation(Rotate rotation) {
        this.rotation = rotation;
    }

    public Vector2d getPositionVector() {
        return position;
    }

    public void setPositionVector(Vector2d position) {
        this.position = position;
    }
}
