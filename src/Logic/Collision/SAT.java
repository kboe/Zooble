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

        Vector2d[] normals = new Vector2d[p1.getPoints().length + p2.getPoints().length];

        getAllNormals(p1, p2);

        return false;
    }

    private static void getAllNormals(PolygonCollider p1, PolygonCollider p2){

        for (int i = 0; i < p1.getPoints().length-1; i++) {

        }
    }

}
