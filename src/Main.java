import GUI.MainMenu;
import Logic.Collision.BallCollider;
import Logic.Collision.BoxCollider;
import Logic.Collision.CollisionChecker;
import Logic.Util.DeltaTime;
import Logic.Util.Physics.Kinematics;
import Logic.Util.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static Logic.Util.DeltaTime.deltatime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // ROBIN CODE BEGINNING

        //Parent root = FXMLLoader.load(getClass().getResource("Controller/sample.fxml"));
        primaryStage.setTitle("Hello World");

        /*TAMARA CODE ANFANG*/

        //MainMenu hauptmenue = new MainMenu();
        //Scene theScene = hauptmenue.getMainMenuScene();

        /*TAMARA CODE ENDE*/

        Group root = new Group();
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        Scene theScene = new Scene(root);

        primaryStage.setScene(theScene);


        //CenterX and CenterY are unnecessary if the colliders are inside a Pane
        final BallCollider c = new BallCollider(100, 100, 60, Color.BLACK);
        final BallCollider c2 = new BallCollider(100, 100, 30, Color.BLACK);
        final BoxCollider rect = new BoxCollider(231, 231, 100, 50, Color.BLACK);

        ImageView imageView = new ImageView(new Image(getClass().getResource("chloe_small.png").toExternalForm()));
        StackPane stackPane = new StackPane(c, imageView);      //Circle (collider) AND Image are in one "Group"
        StackPane stackPane2 = new StackPane(c2);

        root.setOnMouseMoved(event -> {
            rect.setX(event.getX() - rect.getWidth() / 2);
            rect.setY(event.getY() - rect.getHeight() / 2);
        });

        root.getChildren().add(canvas);
        //  root.getChildren().addAll(stackPane,c2, rect);
        root.getChildren().addAll(stackPane, rect,stackPane2,c2);


        new AnimationTimer() {

            DeltaTime dt = new DeltaTime();
            int x = 0;

            @Override
            public void handle(long now) {

                gc.clearRect(0, 0, 500, 500);
                dt.setCurrentTime(dt.getCurrentTime() + deltatime);

                stackPane.setLayoutX(x);
                x++;
                //System.out.println(dt.getCurrentTime());

                stackPane.setRotate(stackPane.getRotate() - Kinematics.radialAcceleration(12,c.getRadius()));

                // rect.setRotate(rect.getRotate()+1);
                if (CollisionChecker.checkCollision(c, c2)) {
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c, c2);
                    System.out.println("collision Point with circle: " + "(" + (int) collPoint.getX() + "/" + (int) collPoint.getY() + ")");
                } else if (CollisionChecker.checkCollision(c, rect)) {
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c, rect);
                    System.out.println("collision Point with Rectangle: " + "(" + (int) collPoint.getX() + "/" + (int) collPoint.getY() + ")");
                }

                dt.setLastTime(dt.getCurrentTime());

            }
        }.start();
        primaryStage.show();

        //ROBIN CODE ENDING
    }


    public static void main(String[] args) {
        launch(args);
    }
}
