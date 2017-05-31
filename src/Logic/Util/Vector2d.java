package Logic.Util;

/**
 * Created by Rocki on 09.05.2017.
 */
public class Vector2d {

    private double x;
    private double y;
    private double length;

    /**
     * Creates a 0 Vector x & y = 0
     */
    public Vector2d() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Creates a Vector with the specified x and y coords
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Vector2d(double x, double y) {
        init(x, y);
    }


    /**
     * will calculate the length of a Vector and store it into the Variable length which can be read by getLength();
     *
     * @return the length value of the on called Vector
     */
    public double calcLength() {
        this.length = Math.sqrt(this.x * this.x + this.y * this.y);
        return getLength();
    }

    /**
     * normalizes the on called Vector (length = 1 &&  -> x & y chords will be divided by it's previous length)
     */
    public void normalize() {
        double length = this.getLength();
        this.x /= length;      //get Length will calculate the length, if not already initialized
        this.y /= length;
        this.calcLength();          //length has to be recalculated
    }

    /**
     * divide the vector with a scalar
     *
     * @param scalar the scalar (int) with which you want to divide the vector
     */
    public void divide(int scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    /**
     * divide the vector with a scalar
     *
     * @param scalar the scalar (double) with which you want to divide the vector
     */
    public void divide(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }



    /**
     * Adds vector1 with vector2 and returns a new vector with
     *
     * @param vector1
     * @param vector2
     * @return
     */
    public static Vector2d add(Vector2d vector1, Vector2d vector2) {       //TODO think about making add and subtract return a Vector2d
        return new Vector2d(vector1.x + vector2.x, vector1.y + vector2.y);
    }

    /**
     * @param vector1
     * @param vector2
     * @return
     */
    public static Vector2d subtract(Vector2d vector1, Vector2d vector2) {
        return new Vector2d(vector1.x - vector2.x, vector1.y - vector2.y);
    }

    /**
     * @param vector1
     * @param vector2
     * @return
     */
    public static double dot(Vector2d vector1, Vector2d vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y;
    }

    /**
     * Scales the Vector by a specified scale value
     *
     * @param scale the scalar value you want to scale the on called Vector with
     */
    public void scale(int scale) {
        this.setX(this.getX() * scale);
        this.setY(this.getY() * scale);
    }

    /**
     * Scales the Vector by a specified scale value
     *
     * @param scale the scalar value you want to scale the on called Vector with
     */
    public void scale(double scale) {
        this.setX(this.getX() * scale);
        this.setY(this.getY() * scale);
    }

    public Vector2d multiply(double scale){
        return new Vector2d(this.getX()*scale,this.getY()*scale);
    }

    /**
     * Ortho (if verts are connected in a) Counter Clockwise (order)
     *
     * @param vector the vector you want to have the orthogonal vector from
     * @return returns a Vector which is orthogonal (in a 90° angle) to the on called vector (only works correctly if verts are linked in a CCW order)
     */
    public static Vector2d orthoCCW(Vector2d vector) {
        return new Vector2d(vector.getY(), vector.getX() * -1);
    }

    /**
     * Ortho (if verts are connected in a) Clockwise (order)
     *
     * @param vector the vector you want to have the orthogonal vector from
     * @return returns a Vector which is orthogonal (in a 90° angle) to the on called vector (only works correctly if verts are linked in a CW order)
     */
    public static Vector2d orthoCW(Vector2d vector) {
        return new Vector2d(vector.getY() * -1, vector.getX());
    }

    /**
     * inverts the on called Vector (x & y Coordinate)
     */
    public void invert() {
        this.x *= -1;
        this.y *= -1;
    }

    /**
     * inverts the on called Vector on the X-Axis
     */
    public void invertX() {
        this.x *= -1;
    }

    /**
     * inverts the on called Vector on the Y-Axis
     */
    public void invertY() {
        this.y *= -1;
    }

    private void init(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //GETTER AND SETTER


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getLength() {
        if (this.length == 0) {          //If the length is not already calculated...
            this.calcLength();
        }
        return length;
    }

    @Override
    public String toString() {

        return "Vector: (" + this.getX() + "/" + this.getY() + ")";
    }
}
