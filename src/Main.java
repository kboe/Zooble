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
import static Logic.Util.Physics.Kinematics.GRAVITY;

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
        final BallCollider c = new BallCollider(50, 100, 50, new ImagePattern(new Image(getClass().getResource("owl_small.png").toExternalForm())));
        final BallCollider c2 = new BallCollider(300, 100, 50, new ImagePattern(new Image(getClass().getResource("chloe_small.png").toExternalForm())));
        final BoxCollider rect = new BoxCollider(231, 231, 100, 50, Color.BLACK);

        c.setVelocityX(25);
        c.setVelocity(new Vector2d(25, c.getCenterY()));
        c.setS0(c.getCenterX());
        c.setMass(6);
        c2.setMass(2);
        c2.setVelocityX(0);
        c.setAcceleration(1);

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

        theScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) {
                rect.rotatePoints(-45);
            } else if (event.getCode() == KeyCode.D) {
                rect.rotatePoints(45);
            } else if (event.getCode() == KeyCode.W) {
                rect.scaleWidth(20);
            } else if (event.getCode() == KeyCode.S) {
                rect.scaleWidth(-20);
            }
        });


        //Roberts TESTFACTORY START
        ZooRect testRect = new ZooRect();
        root.getChildren().add(testRect.rectGrp);


        //Roberts TESTFACTORY END


        new AnimationTimer() {

            DeltaTime dt = new DeltaTime();
            int x = 0;
            int i = 0;
            int x_switch = 2;
            int b = 0;
            boolean contact = false;
            boolean collided_2 = false;

            @Override
            public void handle(long now) {
                //KAREN CODE BEGINNING


                if (LoopStopped.out_of_bounds == true) {
                    System.out.println("stopped");
                    stop();
                }

                dt.setLastTime(dt.getCurrentTime());
                dt.setCurrentTime(dt.getLastTime() + deltatime);


                //BASISEFFEKT 1
                //TODO Collison and Contact with rotated Rectangle

                //gleichförmige Bewegung

                c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                switch (x_switch) {
                    case 1:
                        c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY() + Kinematics.freeFallHeight(dt)));
                        break;
                    case 2:
                        if (!collided_2) {
                            c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));
                        } else {
                            if ((c.getVelocity().getX() == 0)) {
                                c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));

                            }
                            c2.position(new Vector2d(c2.getVelocityX() * dt.getCurrentTime() + c2.getS0(), c2.getCenterY()));

                        }
                        break;


                }


                //KAREN CODE ENDING
                if (CollisionChecker.checkCollision(c, c2)) {
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c, c2);

                    System.out.println("collision Point with circle: " + "(" + (int) collPoint.getX() + "/" + (int) collPoint.getY() + ")");

                    //KAREN CODE BEGINNING
                    //collided = true;
                    if (b == 0) {
                        b = 1;
                        collided_2 = true;
                        c.setS0(collPoint.getX() - 2 * c.getRadius());
                        c2.setS0(c2.getCenterX());
                        c.setVelocity(new Vector2d(Kinematics.elasticPushVelocity1(c.getMass(), c2.getMass(), c.getVelocityX(), c2.getVelocityX()), c.getCenterY()));
                        c2.setVelocityX(Kinematics.elasticPushVelocity2(c.getMass(), c2.getMass(), c.getVelocityX(), c2.getVelocityX()));
                        System.out.println("C2: " + c2.getVelocity().getX());
                    }
                    //KAREN CODE ENDING
                } else if (CollisionChecker.checkCollision(c, rect)) {
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c, rect);
                    System.out.println("collision Point with Rectangle: " + "(" + (int) collPoint.getX() + "/" + (int) collPoint.getY() + ")");
                }

                CollisionChecker.checkSceneBoundsCollision(canvas, c);
                i++;

            }
        }.start();

        primaryStage.show();

        //ROBIN CODE ENDING
    }


    public static void main(String[] args) {
        launch(args);
    }
}
