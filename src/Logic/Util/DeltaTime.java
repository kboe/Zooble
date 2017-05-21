package Logic.Util;

/**
 * Created by Rocki on 16.05.2017.
 */
public class DeltaTime {

    public static double deltatime = 0.016667;
    private double currentTime = 0.01667;

    public double getDeltatime() {
        return deltatime;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }
}
