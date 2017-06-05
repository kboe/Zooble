import GUI.MainMenu;
import Logic.Util.BooleansMovement;
import Logic.Util.Physics.KinematicsVectors;
import Persistent.Highscore.Highscore;
import GUI.ZooRect;
import Logic.Collision.BallCollider;
import Logic.Collision.CollisionChecker;
import Logic.Collision.LoopStopped;
import Logic.Util.DeltaTime;
import Logic.Util.Physics.Kinematics;
import Logic.Util.Physics.KinematicsBall;
import Logic.Util.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.ArrayList;

import static Logic.Util.DeltaTime.deltatime;

public class Main extends Application {

    public GridPane gameControlGrid = new GridPane();
    Boolean running = true;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;

    ArrayList<ZooRect> zooRectList = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception {


        // ROBIN CODE BEGINNING

        //Parent root = FXMLLoader.load(getClass().getResource("Controller/sample.fxml"));
        primaryStage.setTitle("Zooble");

        /*TAMARA CODE ANFANG*/

        //MainMenu hauptmenue = new MainMenu();
        //Scene theScene = hauptmenue.getMainMenuScene();

        /*TAMARA CODE ENDE*/

        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        //Scene menuScene = ma.getMainMenuScene();

        Scene theScene = new Scene(root);
        MainMenu mm = new MainMenu(primaryStage, theScene);

        //primaryStage.setScene(theScene);

        //Favicon
        primaryStage.getIcons().add(new Image(getClass().getResource("chloe_small.png").toExternalForm()));

        //CenterX and CenterY are unnecessary if the colliders are inside a Pane
        final BallCollider c = new BallCollider(75, 100, 50, new ImagePattern(new Image(getClass().getResource("owl_small.png").toExternalForm())));
        final BallCollider c2 = new BallCollider(200, 100, 50, new ImagePattern(new Image(getClass().getResource("chloe_small.png").toExternalForm())));
        final BallCollider c3 = new BallCollider(250, 100, 50, new ImagePattern(new Image(getClass().getResource("chloe_small.png").toExternalForm())));

        //KAREN TESTLAB

        //with Vectors
        c.setStartingPoint(new Vector2d(c.getCenterX(), c.getCenterY()));
        c.setVelocity(new Vector2d(5, 0));
        c.setVelocity0(c.getVelocity());
        c.setAccelerationV(new Vector2d(1, 0));
        c.setMass(1);
        c2.setStartingPoint(new Vector2d(c2.getCenterX(), c2.getCenterY()));
        c2.setVelocity(new Vector2d(0, 0));
        c2.setVelocity0(c2.getVelocity());
        c2.setMass(1);


        c.setVelocityX(10);
        //c.setVelocity(new Vector2d(25, c.getCenterY()));
        c.setS0(c.getCenterX());

        c2.setRotate(90);
        // c2.setMass(9);
        c2.setVelocityX(0);
        //c3.setMass(15);
        c3.setCenterX(400);
        c3.setS0(c3.getCenterX());
        //c.setVelocity0(c.getVelocityX());
        c.setLastSpeed(10);
        c.setLastLastSpeed(8);
        // c.setLastLastPosition(0);
        // c.setLastPosition(c.getCenterX()-1);

        //KAREN TESTLAB ENDING

        //ImageView imageView = new ImageView(new Image(getClass().getResource("elephant_small.png").toExternalForm()));
        //StackPane stackPane = new StackPane(c, imageView);      //Circle (collider) AND Image are in one "Group"


        root.getChildren().add(canvas);
        root.getChildren().addAll(c2, c);
        root.getChildren().add(c3);

        //Roberts TESTFACTORY START


        Group allRectsGrp = new Group();
        ZooRect testRect = new ZooRect(allRectsGrp);
        zooRectList.add(0, testRect);
        allRectsGrp.getChildren().add(testRect.rectGrp);

        Button addRect = new Button();
        addRect.setStyle("-fx-background-image: url('/GUI/newRect.jpg');" + "-fx-background-size: 125px;");
        addRect.setMinWidth(125);
        addRect.setMinHeight(62.5);
        addRect.setOnAction(event -> {
            int i = 1;
            zooRectList.add(i, new ZooRect(allRectsGrp));
            allRectsGrp.getChildren().add(zooRectList.get(i).rectGrp);
            i++;
        });


        GridPane gridPane = new GridPane();

        {
            GridPane uiGrid = new GridPane();
            {
                Label levelname = new Label("Level 1");
                Label add = new Label("add");
                uiGrid.add(levelname, 0, 1);
                uiGrid.add(add, 0, 3);
                uiGrid.add(addRect, 0, 4);
                uiGrid.setVgap(5);
                uiGrid.getStyleClass().add("uiGrid");
                levelname.getStyleClass().add("label");

            }
            gridPane.add(uiGrid, 0, 0);

            RowConstraints ui = new RowConstraints();
            ui.setMinHeight(HEIGHT);
            gridPane.getRowConstraints().addAll(ui);
            gridPane.getStyleClass().add("gridpane");
            gridPane.setLayoutX(WIDTH - 50);
        }


        allRectsGrp.getChildren().add(gridPane);


        root.getChildren().add(allRectsGrp);

        //Roberts TESTFACTORY END

        new AnimationTimer() {

            DeltaTime dt = new DeltaTime();


            int h = 0;

            boolean now_counting = false;
            boolean collided = false;
            boolean collided_3 = false;
            boolean first_contact = false;
            Vector2d coll = new Vector2d();


            public int getH() {
                return h;
            }

            public void setH(int h) {
                this.h = h;
            }

            public boolean isNow_counting() {
                return now_counting;
            }

            @Override
            public void handle(long now) {
                CollisionChecker.checkSceneBoundsCollision(canvas, c);
                //System.out.println("Testrect Angle: "+testRect.getRect().getAngle());

                //KAREN CODE BEGINNING
                if (LoopStopped.out_of_bounds == true) {
                    System.out.println("stopped");
                    Highscore.setH1(dt.getCurrentTime());
                    System.out.println("Highscore: " + Highscore.getH1());
                    stop();
                }


                dt.setLastLastTime(dt.getLastTime());
                dt.setLastTime(dt.getCurrentTime());
                dt.setCurrentTime(dt.getLastTime() + deltatime);

                // }

                //for BallCollider c
                c.setLastLastPosition(c.getLastPosition());
                c.setLastPosition(c.getPosition().getX());

                c.setLastLastPos(c.getLastPos());
                c.setLastPos(c.getPosition());


                c.setLastLastSpeed(c.getLastSpeed());
                c.setLastSpeed(c.getVelocity().getX());


                /*System.out.println("LLS: " + c.getLastLastSpeed());
                System.out.println("LS: " + c.getLastSpeed());

                System.out.println("LP: " + c.getLastPosition());
                System.out.println("LLP: " + c.getLastLastPosition());*/


                //TODO Collision and Contact with rotated Rectangle

                //gleichförmige Bewegung
                int x_switch = -2;

                switch (x_switch) {
                    //Vectors
                    case -1:
                        //KinematicsVectors.averageSpeed(c,dt);

                        //KinematicsVectors.averageTime(dt);
                        //KinematicsVectors.averageAcceleration(c,dt);
                        KinematicsVectors.acceleratedMovementVelocity(dt, c);
                        //KinematicsVectors.freeFallVelocity(c);
                        //KinematicsVectors.acceleratedMovementVelocityWithStartingVelocity(dt,c);
                        KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);
                        KinematicsVectors.radialAcceleration(c);
                        // KinematicsVectors.evenMovementPosition(c,dt);
                        // KinematicsVectors.freeFallHeight(dt,c);
                        //KinematicsVectors.levelThrow(c,dt);


                        //KinematicsVectors.accleratedMovementPosition(dt, c);
                        break;
                    case 0: {
                        KinematicsVectors.acceleratedMovementVelocity(dt, c);
                        KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);
                        KinematicsVectors.radialAcceleration(c);


                        break;
                    }
                    //BASISEFFEKT 1

                    case 1:
                        System.out.println("Mean Time: " + Kinematics.effectiveTime(dt));
                        System.out.println("Effective Velocity: " + Kinematics.effectiveSpeed(c, dt));
                        System.out.println("Effective Acceleration: " + Kinematics.effectiveAcceleration(c, dt));
                        c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                        if (((CollisionChecker.checkCollision(c, testRect.getRect())) == true) & (isNow_counting() == false)) {

                            double xPos = CollisionChecker.getCollisionPoint(c, testRect.getRect()).getX();
                            double yPos = CollisionChecker.getCollisionPoint(c, testRect.getRect()).getY();
                            System.out.println(xPos);
                            if (xPos >= testRect.getEndCoordX() - c.getRadius() - 1) {
                                c.setPosition(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), yPos - 0.5 * c.getRadius()));
                                c.setS0(testRect.getEndCoordX());
                                dt.setCurrentTime(0);
                                now_counting = true;
                            } else {
                                if (!first_contact) {
                                    c.setS0(xPos);
                                    first_contact = true;
                                }
                                c.setPosition(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), yPos - 0.5 * c.getRadius()));

                            }

                        } else if (now_counting == true) {
                            c.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c, dt), c.getCenterY() + Kinematics.freeFallHeight(dt)));
                        } else {
                            c.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c, dt), c.getCenterY() + Kinematics.freeFallHeight(dt)));
                        }
                        break;
                    //COlLLISION
                    case -2:
                        Vector2d sP = new Vector2d();

                        if (CollisionChecker.checkCollision(c, testRect.getRect()) & !collided) {
                            Vector2d collision = CollisionChecker.getCollisionPoint(c, testRect.getRect());
                            collided = true;
                            coll = collision;
                            sP = collision;
                            //Hang
                            c.setAlpha(testRect.getRect().getAngle());
                            KinematicsVectors.gForce(c);
                            KinematicsVectors.hForce(c);
                            //c.setStartingPoint(new Vector2d(collision.getX(), c.getCenterY()+0.25*c.getRadius()+c.gethForce()));
                            c.setStartingPoint(new Vector2d(collision.getX()+0.25*c.getRadius(),collision.getY()+c.getRadius()*0.25));
                            //c.setStartingPoint(new Vector2d(collision.getX(), c.gethForce()));


                            KinematicsVectors.radialAcceleration(c);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);


                        } else if (CollisionChecker.checkCollision(c, testRect.getRect())) {
                            Vector2d collision = CollisionChecker.getCollisionPoint(c, testRect.getRect());

                            KinematicsVectors.gForce(c);
                            KinematicsVectors.hForce(c);
                            // c.setStartingPoint(new Vector2d(collision.getX(), c.getCenterY()+0.25*c.getRadius()+c.gethForce()));
                           // c.setStartingPoint(collision);
                            c.setStartingPoint(new Vector2d(collision.getX(),collision.getY()+c.getRadius()*0.25));

                            //c.setPosition(sP.add(sP,collision));
                            //KinematicsVectors.freeFallVelocity(c);

                            KinematicsVectors.radialAcceleration(c);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);
                        } else {
                            KinematicsVectors.radialAcceleration(c);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);
                            KinematicsVectors.freeFallHeight(dt, c);
                        }
                        break;

                    //BASISEFFEKT 2
                    case 2:
                        c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                        if (CollisionChecker.checkCollision(c2, c3) & !collided_3) {
                            collided_3 = true;
                            c2.setS0(c.getCenterX());
                            c3.setS0(c2.getCenterX());
                            c2.setVelocityX(KinematicsBall.unelasticPushVelocityReturn(c, c2));
                            c3.setVelocityX(KinematicsBall.unelasticPushVelocityReturn(c, c2));

                            System.out.println("V1: " + c.getVelocityX());
                            System.out.println("V2: " + c2.getVelocityX());
                        } else if (CollisionChecker.checkCollision(c, c2) & !collided) {
                            collided = true;
                            c.setS0(c.getCenterX());
                            c2.setS0(c2.getCenterX());
                            c.setVelocityX(KinematicsBall.unelasticPushVelocityReturn(c, c2));
                            c2.setVelocityX(KinematicsBall.unelasticPushVelocityReturn(c, c2));

                            System.out.println("V1: " + c.getVelocityX());
                            System.out.println("V2: " + c2.getVelocityX());

                        } else {
                            c.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c, dt), c.getCenterY()));
                            if (collided) {
                                c2.setRotate(c2.getRotate() + KinematicsBall.radialAcceleration(c2));
                                c2.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c2, dt), c.getCenterY()));
                            }
                            if (collided_3) {
                                c3.setRotate(c3.getRotate() + KinematicsBall.radialAcceleration(c3));
                                c3.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c3, dt), c.getCenterY()));
                            }
                        }
                        break;

                    //BASISEFFEKT 3
                    //functions only when first animal is much heavier than the second
                    case 3:

                        c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                        if (CollisionChecker.checkCollision(c, c2) & !collided) {
                            collided = true;
                            c.setS0(c.getCenterX());
                            c2.setS0(c2.getCenterX());
                            c.setVelocityX(Kinematics.elasticPushVelocity1Collider(c, c2));
                            c2.setVelocityX(Kinematics.elasticPushVelocity2Collider(c, c2));
                            if (c2.getVelocityX() < 0) {
                                c2.setVelocityX(-c2.getVelocityX());
                            }
                        } else {
                            c.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c, dt), c.getCenterY()));

                            if (collided) {
                                c2.setRotate(c2.getRotate() + KinematicsBall.radialAcceleration(c2));
                                c2.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c2, dt), c2.getCenterY()));
                            }
                        }


                        break;

                    //BASISEFFEKT 5 (ähnelt vielleicht ein bisschen daran)
                    case 5:
                        if (!collided)
                            c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));

                        if (CollisionChecker.checkCollision(c, c2) & !collided) {
                            collided = true;
                            c.setS0(c.getCenterX());
                            c2.setS0(c2.getCenterX());
                            c.setVelocityX(KinematicsBall.unelasticPushVelocityReturn(c, c2));
                            c2.setVelocityX(KinematicsBall.unelasticPushVelocityReturn(c, c2));

                            System.out.println("V1: " + c.getVelocityX());
                            System.out.println("V2: " + c2.getVelocityX());

                        } else {
                            if (!collided)
                                c.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c, dt), c.getCenterY()));
                            if (collided) {
                                c2.setRotate(c2.getRotate() + KinematicsBall.radialAcceleration(c2));
                                c2.setPosition(new Vector2d(Kinematics.evenMovementPositionCollider(c2, dt), c.getCenterY()));
                            }

                        }
                        break;
                    //BASISEFFEKT 9??????
                    case 9:
                        break;

                    //BASISEFFEKT 10??????
                    case 10:
                        break;
                    //Unelastischer Stoß geupdatet (Basiseffekt 2)
                    case 11:
                        if ((CollisionChecker.checkCollision(c, c2)) & !collided) {
                            System.out.println(c.getVelocity().getX());
                            collided = true;
                            KinematicsVectors.unelasticPushVelocityCollider(c, c2);
                            KinematicsVectors.acceleratedMovementVelocity(dt, c);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);

                            KinematicsVectors.radialAcceleration(c2);
                            KinematicsVectors.acceleratedMovementVelocity(dt, c2);
                            KinematicsVectors.accleratedMovementPosition(dt, c2);

                        } else if (collided) {
                            KinematicsVectors.acceleratedMovementVelocity(dt, c);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);
                            KinematicsVectors.radialAcceleration(c);

                            KinematicsVectors.acceleratedMovementVelocity(dt, c2);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c2);
                            KinematicsVectors.radialAcceleration(c2);


                        } else {
                            KinematicsVectors.acceleratedMovementVelocity(dt, c);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);
                            KinematicsVectors.radialAcceleration(c);
                        }
                        break;

                    //WAAGRECHTER WURF
                    case 12:
                        c.setPosition(KinematicsBall.levelThrowVector(c, dt));
                        c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                        break;
                    case 14:
                        if ((CollisionChecker.checkCollision(c, c2)) & !collided) {
                            System.out.println(c.getVelocity().getX());
                            collided = true;
                            KinematicsVectors.elasticPush(c, c2);
                            KinematicsVectors.acceleratedMovementVelocity(dt, c);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);

                            KinematicsVectors.radialAcceleration(c2);
                            KinematicsVectors.acceleratedMovementVelocity(dt, c2);
                            KinematicsVectors.accleratedMovementPosition(dt, c2);

                        } else if (collided) {
                            if (c.getVelocity() == new Vector2d(0, 0)) {
                                KinematicsVectors.acceleratedMovementVelocity(dt, c);
                                KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);
                                KinematicsVectors.radialAcceleration(c);
                            }


                            KinematicsVectors.acceleratedMovementVelocity(dt, c2);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c2);
                            KinematicsVectors.radialAcceleration(c2);


                        } else {
                            KinematicsVectors.acceleratedMovementVelocity(dt, c);
                            KinematicsVectors.acceleratedMovementPositionWithStartingSpeedAndPosition(dt, c);
                            KinematicsVectors.radialAcceleration(c);
                        }
                        break;


                }


                CollisionChecker.checkSceneBoundsCollision(canvas, c);
                //i++;

            }

            {
                Button playSim = new Button();
                playSim.setStyle("-fx-background-image: url('/GUI/playBTN.jpg');" + "-fx-background-size: 50px;");
                playSim.setMinWidth(50);
                playSim.setMinHeight(50);
                gameControlGrid.add(playSim, 0, 0);
                playSim.setOnAction(event -> {
                    this.start();
                    for (int j = 0; j < zooRectList.size(); j++) {
                        zooRectList.get(j).showManipulator(false);
                    }
                    running = true;
                });

                Button pauseSim = new Button();
                pauseSim.setStyle("-fx-background-image: url('/GUI/pauseBTN.jpg');" + "-fx-background-size: 50px;");
                pauseSim.setMinWidth(50);
                pauseSim.setMinHeight(50);


                gameControlGrid.add(pauseSim, 1, 0);
                pauseSim.setOnAction(event -> {
                    this.stop();
                    running = false;
                });
            }

        }.

                start();

        gameControlGrid.setLayoutX(WIDTH / 2 - 75);
        gameControlGrid.setLayoutY(HEIGHT - 50);

        root.getChildren().

                add(gameControlGrid);

        theScene.getStylesheets().

                addAll(this.getClass().

                        getResource("/GUI/gameUI.css").

                        toExternalForm());


        primaryStage.show();

        //ROBIN CODE ENDING
    }


    public static void main(String[] args) {
        launch(args);
    }
}
