import Logic.Collision.CollisionChecker;
import Logic.Util.DeltaTime;
import Logic.Util.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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

        Group root = new Group();
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);



        final Circle c = new Circle();
        c.setRadius(50);
        c.setCenterX(100);
        c.setCenterY(100);
        c.setFill(Color.BLACK);
        final Circle c2 = new Circle();
        c2.setRadius(30);
        c2.setCenterX(100);
        c2.setCenterY(100);
        c2.setFill(Color.BLACK);
        final Rectangle rect = new Rectangle(100,50, Color.AQUA);
        rect.setX(231);
        rect.setY(231);
        rect.setRotate(32);

        ImageView imageView = new ImageView(new Image(getClass().getResource("chloe_small.png").toExternalForm()));
        StackPane stackPane = new StackPane(c, imageView);      //Circle (collider) AND Image are in one "Group"

        root.setOnMouseMoved(event -> {
            stackPane.setLayoutX(event.getX() - c.getRadius());
            stackPane.setLayoutY(event.getY() - c.getRadius());
        });

        root.getChildren().add(canvas);
        root.getChildren().addAll(stackPane,c2, rect);

        new AnimationTimer() {

            DeltaTime dt = new DeltaTime();


            @Override
            public void handle(long now) {

                gc.clearRect(0, 0, 500, 500);
                dt.setCurrentTime(dt.getCurrentTime() + deltatime);

                //System.out.println(dt.getCurrentTime());

                stackPane.setRotate(stackPane.getRotate()+1);

                rect.setRotate(rect.getRotate()+1);
                if (CollisionChecker.checkCollision(c,c2)){
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c,c2);
                    System.out.println("collision Point with circle: " + "(" +(int)collPoint.getX() + "/" + (int)collPoint.getY() + ")");
                } else if (CollisionChecker.checkCollision(c,rect)){
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c,rect);
                    System.out.println("collision Point with Rectangle: " + "(" +(int)collPoint.getX() + "/" + (int)collPoint.getY() + ")");
                }

               // gc.fillOval(dt.getCurrentTime(), 0, 100, 100);




            }
        }.start();
        primaryStage.show();

        //ROBIN CODE ENDING
    }


    public static void main(String[] args) {
        launch(args);
    }
}
