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

import static Logic.Util.DeltaTime.deltatime;

public class Main extends Application {

    public GridPane gameControlGrid = new GridPane();
    Boolean running = true;


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
        final BallCollider c2 = new BallCollider(200, 100, 50, new ImagePattern(new Image(getClass().getResource("chloe_small.png").toExternalForm())));
        final BallCollider c3 = new BallCollider(250, 100, 50, new ImagePattern(new Image(getClass().getResource("chloe_small.png").toExternalForm())));

        //KAREN TESTLAB
        c.setVelocityX(15);
        c.setVelocity(new Vector2d(25, c.getCenterY()));
        c.setS0(c.getCenterX());
        c.setMass(15);
        c2.setRotate(90);
        c2.setMass(10);
        c2.setVelocityX(0);
        c.setAcceleration(1);
        c3.setMass(5);
        c3.setCenterX(400);
        c3.setRotate(15);
        c3.setS0(c3.getCenterX());

        //KAREN TESTLAB ENDING

        //ImageView imageView = new ImageView(new Image(getClass().getResource("elephant_small.png").toExternalForm()));
        //StackPane stackPane = new StackPane(c, imageView);      //Circle (collider) AND Image are in one "Group"


        root.getChildren().add(canvas);
        root.getChildren().addAll(c2, c);
        root.getChildren().add(c3);

        //Roberts TESTFACTORY START


        Group allRectsGrp = new Group();
        ZooRect testRect = new ZooRect(allRectsGrp);
        allRectsGrp.getChildren().add(testRect.rectGrp);

        Button addRect = new Button("add");
        addRect.setOnAction(event -> {
            allRectsGrp.getChildren().add(new ZooRect(allRectsGrp).rectGrp);
        });


        GridPane gridPane = new GridPane();

        {
            GridPane uiGrid = new GridPane();
            {
                uiGrid.add(new Label("Test"), 0, 0);
                uiGrid.add(addRect, 0, 1);
            }
            gridPane.add(uiGrid, 0, 0);

            RowConstraints ui = new RowConstraints();
            ui.setMinHeight(500);
            gridPane.getRowConstraints().addAll(ui);
            gridPane.getStyleClass().add("gridpane");
            gridPane.setLayoutX(500 - 50);
        }



        allRectsGrp.getChildren().add(gridPane);



        root.getChildren().add(allRectsGrp);

        //Roberts TESTFACTORY END


        new AnimationTimer() {

            DeltaTime dt = new DeltaTime();
            DeltaTime dt2 = new DeltaTime();
            DeltaTime dt3 = new DeltaTime();

            int i = 0;
            int x_switch = 2;
            int b = 0;
            int b2 = 0;
            boolean collided_2 = false;
            boolean collided_3 = false;
            boolean collided_5 = false;

            @Override
            public void handle(long now) {
                //KAREN CODE BEGINNING
                if (LoopStopped.out_of_bounds == true) {
                    System.out.println("stopped");
                    Highscore.setH1(dt.getCurrentTime());
                    System.out.println("Highscore: "+Highscore.getH1());
                    stop();
                }

                dt.setLastTime(dt.getCurrentTime());
                dt.setCurrentTime(dt.getLastTime() + deltatime);
                if (collided_2) {
                    dt2.setLastTime(dt.getCurrentTime());
                    dt2.setCurrentTime(dt.getLastTime() + deltatime);
                }
                if (collided_3) {

                    dt3.setLastTime(dt.getCurrentTime());
                    dt3.setCurrentTime(dt.getLastTime() + deltatime);

                }


                //TODO Collision and Contact with rotated Rectangle

                //gleichförmige Bewegung

                switch (x_switch) {
                    //BASISEFFEKT 1
                    case 1:
                        c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY() + Kinematics.freeFallHeight(dt)));

                        break;
                    case 2:

                        if (!collided_2) {
                            c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                            c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));
                        } else if (collided_2 & !collided_3) {
                            c2.setRotate(c2.getRotate() + KinematicsBall.radialAcceleration(c2));
                            c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                            c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));
                            c2.position(new Vector2d(c2.getVelocityX() * dt.getCurrentTime() + c2.getS0(), c2.getCenterY()));
                        } else if (collided_3 | (collided_3 & !collided_2)) {
                            c2.setRotate(c2.getRotate() + KinematicsBall.radialAcceleration(c2));
                            c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                            c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));
                            c2.position(new Vector2d(c2.getVelocityX() * dt.getCurrentTime() + c2.getS0(), c2.getCenterY()));
                            c3.setRotate(c3.getRotate() + KinematicsBall.radialAcceleration(c3));
                            c3.position(new Vector2d(c3.getVelocityX() * dt.getCurrentTime() + c3.getS0(), c3.getCenterY()));
                        }
                        break;

                    //BASISEFFEKT 3
                    case 3:


                        if (!collided_2) {
                            c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                            c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));
                        } else {
                            if (!(c.getVelocity().getX() == 0)) {
                                c2.setRotate(c2.getRotate() + KinematicsBall.radialAcceleration(c2));
                                if (c2.getVelocityX() <= 0) {
                                    c.setRotate(c.getRotate() - KinematicsBall.radialAcceleration(c));

                                    c.position(new Vector2d(c.getS0() + c.getVelocityX() * dt.getCurrentTime(), c.getCenterY()));
                                    //Vom Denken her ist das richtig, aber von der Physik bin  ich mir nicht sicher
                                    c2.position(new Vector2d(-c2.getVelocityX() * dt2.getCurrentTime() + c2.getS0() - c2.getRadius(), c2.getCenterY()));

                                } else {
                                    c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));

                                    c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));
                                    c2.position(new Vector2d(c2.getVelocityX() * dt2.getCurrentTime() + c2.getS0() - c2.getRadius(), c2.getCenterY()));
                                }

                            } else
                                c2.position(new Vector2d(c2.getVelocityX() * dt.getCurrentTime() + c2.getS0(), c2.getCenterY()));

                        }
                        break;

                    //BASISEFFEKT 5 (ähnelt vielleicht ein bisschen daran)
                    case 5:
                        if (collided_3) {
                            c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                            c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));
                            c2.setRotate(c2.getRotate() + KinematicsBall.radialAcceleration(c2));
                            c2.position(new Vector2d(c2.getVelocityX() * dt.getCurrentTime() + c2.getS0(), c2.getCenterY()));
                        } else {
                            c.setRotate(c.getRotate() + KinematicsBall.radialAcceleration(c));
                            c.position(new Vector2d(c.getVelocityX() * dt.getCurrentTime() + c.getS0(), c.getCenterY()));
                        }


                        break;

                    //BASISEFFEKT 9??????
                    case 9:
                        break;

                    //BASISEFFEKT 10??????
                    case 10:
                        break;

                    //WAAGRECHTER WURF
                    case 12:
                        c.position(KinematicsBall.levelThrowVector(c, dt));
                        break;


                }


                //KAREN CODE ENDING
                if (CollisionChecker.checkCollision(c, c2)) {
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c, c2);

                    System.out.println("collision Point with circle: " + "(" + (int) collPoint.getX() + "/" + (int) collPoint.getY() + ")");

                    //KAREN CODE BEGINNING
                    //collided = true;
                    switch (x_switch) {
                        case 2:
                            //BASISEFFEKT 2

                            if (b == 0) {
                                double p = c2.getCenterX() - c.getCenterX();
                                collided_2 = true;
                                c2.setS0(c2.getCenterX() + c2.getRadius());
                                c.setS0(c.getCenterX());
                                c.setVelocityX(Kinematics.elasticPushVelocity1Collider(c, c2));
                                c2.setVelocityX(Kinematics.elasticPushVelocity2Collider(c, c2));
                                System.out.println("V1: " + c.getVelocityX());
                                System.out.println("V2: " + c2.getVelocityX());

                            }
                            if (b == 5) {
                                collided_2 = false;
                                b = -1;
                            }
                            b++;
                            break;

                        case 3:
                            //BASISEFFEKT 3
                            if (b == 0) {
                                collided_2 = true;
                                c2.setS0(c2.getCenterX() + c2.getRadius());
                                c.setS0(c.getCenterX());
                                c.setVelocityX(Kinematics.elasticPushVelocity1Collider(c, c2));
                                c2.setVelocityX(Kinematics.elasticPushVelocity2Collider(c, c2));
                                System.out.println("V1: " + c.getVelocityX());
                                System.out.println("V2: " + c2.getVelocityX());

                            }
                            if (b == 5) {
                                collided_2 = false;
                                b = -1;
                            }
                            b++;
                            break;
                        //BASISEFFEKT 5
                        case 5:
                            if (!collided_3) {
                                // c.setS0(c.getCenterX() - c.getRadius());
                                //  c2.setS0(c2.getCenterX() + c2.getRadius());
                                c.setS0(c.getCenterX());
                                c2.setS0(c2.getCenterX());
                                c.setVelocityX(Kinematics.unelasticPushVelocityCollider(c, c2));
                                c2.setVelocityX(c.getVelocityX());
                                collided_3 = true;
                            }


                            break;

                    }

                }
                if (CollisionChecker.checkCollision(c2, c3)) {
                    Vector2d collPoint = CollisionChecker.getCollisionPoint(c2, c3);
                    collided_3 = true;
                    //c3.setRotate(c3.getRotate() + KinematicsBall.radialAcceleration(c3));

                    System.out.println("collision Point with circle: " + "(" + (int) collPoint.getX() + "/" + (int) collPoint.getY() + ")");


                    switch (x_switch) {
                        case 2:
                            //BASISEFFEKT 2
                            if (b2 == 0) {
                                c3.setS0(c3.getCenterX() + c3.getRadius());
                                c2.setS0(c2.getCenterX());
                                c2.setVelocityX(Kinematics.elasticPushVelocity1Collider(c2, c3));

                                c3.setVelocityX(Kinematics.elasticPushVelocity2Collider(c2, c3));
                                System.out.println("V2: " + c2.getVelocityX());
                                System.out.println("V3: " + c3.getVelocityX());

                            }
                            if (b2 == 5) {
                                collided_3 = false;
                                b2 = -1;
                            }
                            b2++;
                            break;
                    }

                }
                //KAREN CODE ENDING

                CollisionChecker.checkSceneBoundsCollision(canvas, c);
                i++;

            }

            {
                Button playSim = new Button();
                playSim.setStyle("-fx-background-image: url('/GUI/playBTN.jpg');" + "-fx-background-size: 50px;");
                playSim.setMinWidth(50);
                playSim.setMinHeight(50);
                gameControlGrid.add(playSim,0,0);
                playSim.setOnAction(event -> {
                    this.start();
                    running = true;
                });

                Button pauseSim = new Button();
                pauseSim.setStyle("-fx-background-image: url('/GUI/pauseBTN.jpg');" + "-fx-background-size: 50px;");
                pauseSim.setMinWidth(50);
                pauseSim.setMinHeight(50);


                gameControlGrid.add(pauseSim,1,0);
                pauseSim.setOnAction(event -> {
                    this.stop();
                    running = false;
                });
            }

        }.start();

        gameControlGrid.setLayoutX(500/2 -50);
        gameControlGrid.setLayoutY(450);

        root.getChildren().add(gameControlGrid);



        primaryStage.show();

        //ROBIN CODE ENDING
    }



    public static void main(String[] args) {
        launch(args);
    }
}
