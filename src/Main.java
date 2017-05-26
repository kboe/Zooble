import GUI.MainMenu;
import GUI.ZooRect;
import Logic.Collision.BallCollider;
import Logic.Collision.BoxCollider;
import Logic.Collision.CollisionChecker;
import Logic.Collision.LoopStopped;
import Logic.Util.DeltaTime;
import Logic.Util.Physics.Kinematics;
import Logic.Util.Physics.KinematicsBall;
import Logic.Util.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        final BallCollider c = new BallCollider(100, 100, 50, new ImagePattern(new Image(getClass().getResource("owl_small.png").toExternalForm())));
        final BallCollider c2 = new BallCollider(300, 100, 50, new ImagePattern(new Image(getClass().getResource("chloe_small.png").toExternalForm())));
        final BoxCollider rect = new BoxCollider(231, 231, 100, 50, Color.BLACK);

        c.setVelocity(new Vector2d(10, 0));
        c2.setVelocity(new Vector2d(0, 0));
        c.setMass(0.2);

        //ImageView imageView = new ImageView(new Image(getClass().getResource("elephant_small.png").toExternalForm()));
        //StackPane stackPane = new StackPane(c, imageView);      //Circle (collider) AND Image are in one "Group"

      /*  root.setOnMouseMoved(event -> {
            rect.setX(event.getX() - rect.getWidth() / 2);
            rect.setY(event.getY() - rect.getHeight() / 2);
        });*/

        root.getChildren().add(canvas);
        //  root.getChildren().addAll(stackPane,c2, rect);
        //root.getChildren().addAll(stackPane, rect,stackPane2,c2);
        root.getChildren().addAll(rect, c2, c);

        /*theScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A){
                System.out.println(rect.getPoints().get(2));
                rect.scaleWidth(10);
                System.out.println(rect.getPoints().get(2));
            } else if (event.getCode() == KeyCode.S){
                rect.rotatePoints(45);
            }
        });*/


        //Roberts TESTFACTORY START
        ZooRect testRect = new ZooRect();
        root.getChildren().add(testRect.rectGrp);



        //Roberts TESTFACTORY END


        new AnimationTimer() {

            DeltaTime dt = new DeltaTime();
            int x = 0;
            double x2 = 300;
            boolean collided = false;

            @Override
            public void handle(long now) {
                /*if (LoopStopped.out_of_bounds == true) {
                    System.out.println("stopped");
                    stop();
                }*/

                gc.clearRect(0, 0, 500, 500);
                dt.setCurrentTime(dt.getCurrentTime() + deltatime);

                //KAREN CODE BEGINNING

                //BASISEFFEKT 1

                c.setCenterX(x+c.getRadius());
                x++;

                /*if (collided == true) {
                    c2.setCenterX(Kinematics.evenMovementPosition(5,dt,c2.getCenterX()));
                    c2.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c2));
                    c.setCenterX(Kinematics.evenMovementPosition(5, dt, c.getCenterX()));

                    //  c.setCenterX(x + c.getRadius());
                } else {
                    System.out.println(c.getCenterX());
                    c.setCenterX(Kinematics.evenMovementPosition(5, dt, c.getCenterX()));
                }

                x2++;

                x++;

                c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));*/

                //KAREN CODE ENDING

                // rect.setRotate(rect.getRotate()+1);
                if (CollisionChecker.checkCollision(c, c2)) {
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c, c2);
                    System.out.println("collision Point with circle: " + "(" + (int) collPoint.getX() + "/" + (int) collPoint.getY() + ")");

                    //KAREN CODE BEGINNING
                    //collided = true;
                    //KAREN CODE ENDING
                }/* else if (CollisionChecker.checkCollision(c, rect)) {
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c, rect);
                    System.out.println("collision Point with Rectangle: " + "(" + (int) collPoint.getX() + "/" + (int) collPoint.getY() + ")");
                }*/

                CollisionChecker.checkSceneBoundsCollision(canvas, c);

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
