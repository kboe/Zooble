package Logic.Collision;

import Logic.Util.Vector2d;
import com.sun.istack.internal.Nullable;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Created by Rocki on 24.05.2017.
 */
public class BoxCollider extends Rectangle {

    private Vector2d midpoint;              //Center of the Box
    private Vector2d position;              //upper left corner of the box
    private double mass = 0;


    //CONSTRUCTOR

    public BoxCollider(double x, double y, double width, double height, @Nullable Paint paint){
        super(x, y, width, height);
        position = new Vector2d(x,y);
        if (paint != null){
            this.setFill(paint);
        }
        midpoint = new Vector2d(position.getX() + width/2, position.getY() + height/2);
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
}
