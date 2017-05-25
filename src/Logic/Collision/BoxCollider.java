package Logic.Collision;

import Logic.Util.Vector2d;
import com.sun.istack.internal.Nullable;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.awt.geom.AffineTransform;

/**
 * Created by Rocki on 24.05.2017.
 */
public class BoxCollider extends Polygon {

    private Vector2d midpoint;              //Center of the Box
    private Vector2d position;              //upper left corner of the box
    private double width;
    private double height;
    private double mass = 0;


    //CONSTRUCTOR

    public BoxCollider(double x, double y, double width, double height, @Nullable Paint paint){
        super(x,y,x+width,y,x+width,y+height,x,y+height);
        position = new Vector2d(x,y);

        this.width = width;
        this.height = height;
        if (paint != null){
            this.setFill(paint);
        }
        midpoint = new Vector2d(position.getX() + width/2, position.getY() + height/2);
    }

    //METHODS

    /**
     * rotates the points array of the BoxCollider
     * @param angle the angle you want the Box to be rotated
     * @return returns a double[] with the x,y points
     */
    public void rotatePoints(double angle){
        double midpointX = this.getMidpoint().getX();
        double midpointY = this.getMidpoint().getY();
        double[] points = new double[this.getPoints().size()];
        double[] storeTo = new double[this.getPoints().size()];

        for (int i = 0; i < this.getPoints().size(); i++) {
            points[i] = this.getPoints().get(i);
        }

        AffineTransform.getRotateInstance(Math.toRadians(angle), midpointX, midpointY).transform(points,0,storeTo,0,this.getPoints().size()/2);

        for (int i = 0; i < this.getPoints().size(); i++) {
            this.getPoints().set(i,storeTo[i]);
        }
    }

    //GETTER & SETTER

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vector2d getMidpoint() {
        return midpoint;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
