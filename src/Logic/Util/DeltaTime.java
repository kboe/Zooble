package Logic.Util;

/**
 * Created by Rocki on 16.05.2017.
 */
public class DeltaTime {

    public static double deltatime = 0.0167;


    //private double currentTime = 0.0167;
    private double currentTime = 0;
    private double previousTime = 0;

    private double lastTime;
    private double lastLastTime;

    /**
     * calculates the time between 2 Frames
     * can not be > 0.15
     */
    public void calcDeltaTime(){
        deltatime = (this.getCurrentTime() - this.getPreviousTime()) / 1000000000.0;
        if (deltatime > 0.15)       //restrict delta time (keep it low)
            deltatime = 0.15;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public double getLastTime() {
        return lastTime;
    }

    public void setLastTime(double lastTime) {
        this.lastTime = lastTime;
    }

    public double getLastLastTime() {
        return lastLastTime;
    }

    public void setLastLastTime(double lastLastTime) {
        this.lastLastTime = lastLastTime;
    }

    public double getPreviousTime() {
        return previousTime;
    }

    public void setPreviousTime(double previousTime) {
        this.previousTime = previousTime;
    }
}
