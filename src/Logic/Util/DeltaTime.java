package Logic.Util;

/**
 * Created by Rocki on 16.05.2017.
 */
public class DeltaTime {

    public static double deltatime = 0.0167;
    private double currentTime = 0.0167;

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }
}
