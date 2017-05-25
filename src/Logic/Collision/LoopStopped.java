package Logic.Collision;

/**
 * Created by ${kboe} on 25.05.2017.
 */
public class LoopStopped {

    public static boolean out_of_bounds = false;

    public static boolean isOut_of_bounds() {
        return out_of_bounds;
    }

    public static void setOut_of_bounds(boolean out_of_bounds) {
        LoopStopped.out_of_bounds = out_of_bounds;
    }
}
