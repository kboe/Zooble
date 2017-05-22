package Logic.Collision;

import Logic.Util.Vector2d;

/**
 * Created by Rocki on 17.05.2017.
 */
public class SAT {


    /**
     * Check if two PolygonCollider are actually colliding
     * @param p1 the first polygon collider
     * @param p2 the second polygon collider
     * @return the state of collision
     */
    public static boolean isColliding(PolygonCollider p1, PolygonCollider p2){

        //Will be used as Axis for the points
        Vector2d[] normals = new Vector2d[p1.getPoints().length + p2.getPoints().length];
        storeAllNormals(p1, p2, normals);

        /*for (Vector2d vector2d:
             normals) {
            System.out.println("normal: (" + vector2d.getX() + "/" + vector2d.getY() + ")");
        }*/

        return false;
    }

    private static void storeAllNormals(PolygonCollider p1, PolygonCollider p2, Vector2d[] normals){

        for (int i = 0; i < p1.getPoints().length; i++) {
            if (i < p1.getPoints().length-1){
                normals[i] = Vector2d.orthoCCW(Vector2d.subtract(p1.getPoints()[i+1], p1.getPoints()[i]));
            } else {
                normals[i] = Vector2d.orthoCCW(Vector2d.subtract(p1.getPoints()[0],p1.getPoints()[i]));
            }
        }

        for (int i = 0; i < p2.getPoints().length; i++) {
            if (i < p2.getPoints().length-1){
                normals[i + p1.getPoints().length] = Vector2d.orthoCCW(Vector2d.subtract(p2.getPoints()[i+1], p2.getPoints()[i]));
            } else {
                normals[i + p1.getPoints().length] = Vector2d.orthoCCW(Vector2d.subtract(p2.getPoints()[0],p2.getPoints()[i]));
            }
        }
    }

    private static void projectOnAxis(){

    }

}
