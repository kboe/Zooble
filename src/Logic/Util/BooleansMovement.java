package Logic.Util;

/**
 * Created by ${kboe} on 30.05.2017.
 */
public class BooleansMovement {

    private static boolean now_moving = false;

    //GETTTER AND SETTER


    public static boolean isNow_moving() {
        return now_moving;
    }

    public static void setNow_moving(boolean now_moving) {
        BooleansMovement.now_moving = now_moving;
    }
}
