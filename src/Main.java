import Logic.Collision.CircleCollider;
import Logic.Collision.RectangleCollider;
import Logic.Collision.SAT;
import Logic.Util.Physics.Physics;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static Logic.Util.DeltaTime.deltatime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // ROBIN CODE BEGINNING

        //Parent root = FXMLLoader.load(getClass().getResource("Controller/sample.fxml"));
        primaryStage.setTitle("Hello World");

        Group root = new Group();
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        RectangleCollider rect = new RectangleCollider(50, 50, 200, 200);
        RectangleCollider rect2 = new RectangleCollider(100,100,200,200);

        SAT.isColliding(rect,rect2);

        root.getChildren().add(canvas);

        new AnimationTimer() {

            DeltaTime dt = new DeltaTime();


            @Override
            public void handle(long now) {

                gc.clearRect(0, 0, 500, 500);
                dt.setCurrentTime(dt.getCurrentTime() + deltatime);

                System.out.println(dt.getCurrentTime());

                // rect.draw(gc);
                gc.fillOval(dt.getCurrentTime(), 0, 100, 100);




            }
        }.start();
        primaryStage.show();

        //ROBIN CODE ENDING
    }


    public static void main(String[] args) {
        launch(args);
    }
}
