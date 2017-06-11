package Logic.Collision.TriggerArea;

import Logic.Collision.Collider.BoxCollider;
import javafx.scene.paint.Paint;

/**
 * Created by Rocki on 11.06.2017.
 */
public class Goal extends BoxCollider{
    public Goal(double x, double y, double width, double height, Paint paint) {
        super(x, y, width, height, paint);
    }
}
