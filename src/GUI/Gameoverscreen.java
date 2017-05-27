package GUI;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Hilli
 */
        public class Gameoverscreen extends Application {

            Stage window;
            final Image image  = new Image(getClass().getResource("Button1.png").toExternalForm()) {};
            final Image image2 = new Image(getClass().getResource("Button2.png").toExternalForm()) {};

            @Override
            public void start(Stage primaryStage) throws Exception {
                window = primaryStage;
                window.setTitle("Game Over");


                //GridPane
                GridPane grid = new GridPane();
                grid.setPadding(new Insets(10,50,50,50));
                grid.setVgap(12);
                grid.setHgap(12);


                ImageView homebutton = new ImageView();
                homebutton.setImage(image);
                homebutton.setFitWidth(150);
                homebutton.setPreserveRatio(true);
                homebutton.setCache(true);
                GridPane.setConstraints(homebutton,5, 4);

                ImageView restartbutton = new ImageView();
                restartbutton.setImage(image2);
                restartbutton.setFitWidth(150);
                restartbutton.setPreserveRatio(true);
                restartbutton.setCache(true);
                GridPane.setConstraints(restartbutton,6, 4);

                Label Gameover = new Label("GAME OVER");
                Gameover.getStyleClass().add("gameover");
                GridPane.setConstraints(Gameover, 5, 0, 2, 1);


                //Füge alles zum Grid hinzu
                grid.getChildren().addAll(Gameover, homebutton, restartbutton);


                /**
                 * @param args the command line arguments
                 */
                final String look = this.getClass().getResource("look3.css").toExternalForm();

                Scene scene = new Scene(grid, 535, 280);
                scene.getStylesheets().add(look);
                window.setScene(scene);
                window.show();
            }

        }

