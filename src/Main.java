import Logic.Collision.BallCollider;
import Logic.Collision.BoxCollider;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
        final BallCollider c = new BallCollider(100,100,50,Color.BLACK);
        final BallCollider c2 = new BallCollider(100,100,30,Color.BLACK);
        final BoxCollider rect = new BoxCollider(200,100,100,50,Color.BLACK);

        System.out.println(rect.getMidpoint().getX());
        System.out.println(rect.getMidpoint().getY());

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

                stackPane.setRotate(stackPane.getRotate()-2);

                rect.setRotate(rect.getRotate()+1);
                if (CollisionChecker.checkCollision(c,c2)){
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c,c2);
                    System.out.println("collision Point with circle: " + "(" +(int)collPoint.getX() + "/" + (int)collPoint.getY() + ")");
                    Circle collisionPoint = new Circle(collPoint.getX(),collPoint.getY(),3,Color.RED);
                    root.getChildren().add(collisionPoint);
                } else if (CollisionChecker.checkCollision(c,rect)){
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c,rect);
                    System.out.println("collision Point with Rectangle: " + "(" +(int)collPoint.getX() + "/" + (int)collPoint.getY() + ")");
                    Circle collisionPoint = new Circle(collPoint.getX(),collPoint.getY(),3,Color.RED);
                    root.getChildren().add(collisionPoint);
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
