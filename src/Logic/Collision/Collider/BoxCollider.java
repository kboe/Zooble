package Logic.Collision.Collider;

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
    private Vector2d[] vectorPoints = new Vector2d[this.getPoints().size() / 2];              //upper left corner of the box
    private Vector2d[] normals = new Vector2d[this.getPoints().size() / 2];
    private double width;
    private double height;
    private double mass = 0;
    private int angle = 0;


    //CONSTRUCTOR

    public BoxCollider(double x, double y, double width, double height, @Nullable Paint paint) {
        super(x, y, x + width, y, x + width, y + height, x, y + height);

        storePointsInVector();

        this.width = width;
        this.height = height;
        if (paint != null) {
            this.setFill(paint);
        }
        calculateMidpoint();        //update Midpoint
        calculateNormals();         //update normals
    }

    //METHODS
    //TRANSFORM METHODS


    /**
     * translates the points of the box by a specific Vector
     */
    public void translateBox(Vector2d translationVector){
        for (int i = 0; i < vectorPoints.length; i++) {
            vectorPoints[i] = Vector2d.add(vectorPoints[i], translationVector);
        }
        storeVectorInPoints();      //update polygon points
        calculateMidpoint();        //Update Midpoint
    }

    /**
     * Scales the Box by a specified value
     *
     * @param newWidth the value you want to add to the width of the Box
     */
    public void scaleWidth(double newWidth) {
        int actualAngle = this.angle;

        rotateBox(-this.angle);
        this.getPoints().set(2, this.getPoints().get(2) + newWidth);
        this.getPoints().set(4, this.getPoints().get(4) + newWidth);
        rotateBox(actualAngle);

        storePointsInVector();      //update vectorPoints
        calculateMidpoint();        //Update Midpoint
        setWidth(getWidth() + newWidth);
    }

    /**
     * rotates the points array of the BoxCollider. Always rotates around the current midpoint!
     *
     * @param angle the angle you want the Box to be rotated
     */
    public void rotateBox(double angle) {
        this.angle += angle;
        double midpointX = this.getMidpoint().getX();
        double midpointY = this.getMidpoint().getY();
        double[] points = convertPointsObservableArrayIntoPointsArray();
        double[] storeTo = new double[this.getPoints().size()];

        AffineTransform.getRotateInstance(Math.toRadians(angle), midpointX, midpointY).transform(points, 0, storeTo, 0, this.getPoints().size() / 2);

        for (int i = 0; i < this.getPoints().size(); i++) {
            this.getPoints().set(i, storeTo[i]);
        }

        storePointsInVector();  //Update vectorPoints
        calculateNormals();     //Update normals
        calculateMidpoint();    //Update Midpoint
        validateAngle();        //Keep angle between 0 and 359
    }

    //UTIL METHODS

    /**
     * (re-)calculates the midpoint of the box shape
     */
    private void calculateMidpoint() {
        double midX = (vectorPoints[0].getX() + vectorPoints[1].getX() + vectorPoints[2].getX() + vectorPoints[3].getX()) / vectorPoints.length;
        double midY = (vectorPoints[0].getY() + vectorPoints[1].getY() + vectorPoints[2].getY() + vectorPoints[3].getY()) / vectorPoints.length;

        midpoint = new Vector2d(midX, midY);
    }

    /**
     * (re-)calculates normals of a box shape
     */
    private void calculateNormals() {
        for (int i = 0; i < this.vectorPoints.length; i++) {
            if (i < this.vectorPoints.length-1){
                normals[i] = Vector2d.orthoCCW(Vector2d.subtract(this.vectorPoints[i + 1], this.vectorPoints[i]));
                normals[i].normalize();
            } else {
                normals[i] = Vector2d.orthoCCW(Vector2d.subtract(this.vectorPoints[0], vectorPoints[i]));
                normals[i].normalize();
            }
        }
    }

    /**
     * converts the Observable ArrayList into the Vector2d array. Keeps the vector point array and the Observable List in sync.
     */
    private void storePointsInVector() {
        int indexCounter = 0;
        for (int i = 0; i < this.getPoints().size(); i += 2) {
            vectorPoints[indexCounter] = new Vector2d(this.getPoints().get(i), this.getPoints().get(i + 1));
            indexCounter++;
        }
    }

    /**
     * stores the Vector2d array into the observable point list of the polygon. Keeps the vector point array and the Observable List in sync.
     */
    private void storeVectorInPoints() {
        int indexCounter = 0;
        for (Vector2d vectorPoint : vectorPoints) {
            this.getPoints().set(indexCounter, vectorPoint.getX());
            this.getPoints().set(indexCounter + 1, vectorPoint.getY());
            indexCounter += 2;
        }
    }

    /**
     * converts the points Observable ArrayList into a normal double Array
     */
    private double[] convertPointsObservableArrayIntoPointsArray() {
        double[] points = new double[this.getPoints().size()];

        for (int i = 0; i < this.getPoints().size(); i++) {
            points[i] = this.getPoints().get(i);
        }

        return points;
    }

    /**
     * keeps the angle of an object between 0 and 359 (0 == 360)
     */
    private void validateAngle() {
        this.angle %= 360;
    }

    //GETTER & SETTER


    public Vector2d[] getVectorPoints() {
        return vectorPoints;
    }

    public void setVectorPoints(Vector2d[] vectorPoints) {
        this.vectorPoints = vectorPoints;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
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

    public Vector2d[] getNormals() {
        return normals;
    }
}
