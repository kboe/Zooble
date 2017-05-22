package Logic.Collision;

import Logic.Util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Rocki on 11.05.2017.
 */
public class RectangleCollider extends PolygonCollider {

    private double[] xPositions = new double[4];
    private double[] yPositions = new double[4];
    private Vector2d midpoint = new Vector2d();

    public RectangleCollider(double x, double y, double width, double height){
        setPoints(new Vector2d[4]);                     //Store points into the Vector2d Points array
        getPoints()[0] = new Vector2d(x,y);
        getPoints()[1] = new Vector2d(x+width,y);
        getPoints()[2] = new Vector2d(x+width,y+height);
        getPoints()[3] = new Vector2d(x,y+height);
        midpoint.setX(x + width/2);
        midpoint.setY(y + height/2);

        splitIntoArrays(getPoints());                   //Prepare the Vector2d array for being able to be drawn with the fillPolygon Method (needs double[] for x positions and double[] for y positions)
    }
    private void splitIntoArrays(Vector2d[] points){
        for (int x = 0; x < xPositions.length; x++) {
            xPositions[x] = points[x].getX();
        }

        for (int y = 0; y < yPositions.length; y++) {
            yPositions[y] = points[y].getY();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillPolygon(xPositions,yPositions,getPoints().length);
    }
}
