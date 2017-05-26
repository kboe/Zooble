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
    private Vector2d[] vectorPoints = new Vector2d[this.getPoints().size()/2];              //upper left corner of the box
    private double width;
    private double height;
    private double mass = 0;
    private int angle = 0;



    //CONSTRUCTOR

    public BoxCollider(double x, double y, double width, double height, @Nullable Paint paint){
        super(x,y,x+width,y,x+width,y+height,x,y+height);

        //Store the points from the observable ArrayList into an Array called points, so they can be converted to Vector

        convertPointsObservableArrayIntoPointsArray();
        storePointsInVector();

        this.width = width;
        this.height = height;
        if (paint != null){
            this.setFill(paint);
        }
       midpoint = new Vector2d(vectorPoints[0].getX() + width/2, vectorPoints[0].getY() + height/2);
    }

    public void scaleWidth(double newWidth){        //KLAPPT NICHT RICHTIG... vllt. etwas mit Rotiere zurück, skaliere und rotiere wieder hin!
        double[] points = convertPointsObservableArrayIntoPointsArray();
        double[] storeTo = new double[this.getPoints().size()];

        AffineTransform.getScaleInstance(1.2,1).transform(points,0,storeTo,0,this.getPoints().size()/2);

        for (int i = 0; i < this.getPoints().size(); i++) {
            this.getPoints().set(i,storeTo[i]);
        }
    }

    //METHODS

    /**
     * rotates the points array of the BoxCollider
     * @param angle the angle you want the Box to be rotated
     * @return returns a double[] with the x,y points
     */
    public void rotatePoints(int angle){
        this.angle = angle;
        double midpointX = this.getMidpoint().getX();
        double midpointY = this.getMidpoint().getY();
        double[] points = convertPointsObservableArrayIntoPointsArray();
        double[] storeTo = new double[this.getPoints().size()];

        AffineTransform.getRotateInstance(Math.toRadians(angle), midpointX, midpointY).transform(points,0,storeTo,0,this.getPoints().size()/2);

        for (int i = 0; i < this.getPoints().size(); i++) {
            this.getPoints().set(i,storeTo[i]);
        }
    }

    /**
     * converts a double array with points into a vector array
     */
    private void storePointsInVector(){
        int indexCounter = 0;
        for (int i = 0; i < this.getPoints().size(); i += 2) {
            vectorPoints[indexCounter] = new Vector2d(this.getPoints().get(i), this.getPoints().get(i+1));
            indexCounter++;
        }
    }

    private void storeVectorInPoints(double[] points, Vector2d[] vectorPoints){

    }

    private void updatePolygonPointsArrayList(){

    }

    /**
     * converts the points Observable ArrayList into a normal double Array
     */
    private double[] convertPointsObservableArrayIntoPointsArray(){
        double[] points = new double[this.getPoints().size()];

        for (int i = 0; i < this.getPoints().size(); i++) {
            points[i] = this.getPoints().get(i);
        }

        return points;
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
}
