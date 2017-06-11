package Persistent.Game_Assets.AoE_Assets;

import Logic.Collision.Collider.BallCollider;
import Logic.Collision.TriggerArea.TriggerArea;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;
import javafx.scene.paint.Paint;

/**
 * Created by Rocki on 11.06.2017.
 */
public class BoostBox extends TriggerArea {

    private double boostStrength = 50;                           //DEFAULT: strength of 1
    private Vector2d direction = this.getVectorPoints()[1].subtract(this.getVectorPoints()[0]);      //DEFAULT: boost to the right

    public BoostBox(double x, double y, double width, double height, Paint paint) {
        super(x, y, width, height, paint);
        direction.normalize();
    }

    /**
     * if a ball hits this boostBox -> accelerate THIS ball
     * @param overlappingBall   the ball which is entering the "BoostBox"
     */
    public void boostBall(BallCollider overlappingBall){
        //scale the direction vector with the boostStrength and add this to the velocity of the ball
        direction.scale(boostStrength * DeltaTime.deltatime);
        overlappingBall.setVelocity(overlappingBall.getVelocity().add(direction));
        //keep the acceleration constantly on one specific value, else the vector will grow each time you call boostBall()
        direction.normalize();
    }

    /**
     * rotates the boosterBox and then refreshes the direction Vector
     * @param angle
     */
    public void rotateBooster(double angle){
        rotateBox(angle);
        rotateBoostVector(angle);
    }

    /**
     * instead of rotating the Vector after rotating the box, i only rotate the box and then refreshes this direction vector by subtracting the second and the first point of the box
     * @param angle
     */
    private void rotateBoostVector(double angle){
        direction = this.getVectorPoints()[1].subtract(this.getVectorPoints()[0]);
        direction.normalize();
    }

    public double getBoostStrength() {
        return boostStrength;
    }

    public void setBoostStrength(double boostStrength) {
        this.boostStrength = boostStrength;
    }
}
