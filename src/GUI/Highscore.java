package GUI;


import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Hilli
 */
public class Highscore extends Application {


    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Highscore");


        //GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 50, 50, 50));
        grid.setVgap(10);
        grid.setHgap(10);

        Button backButton = new Button("Back to menu");
        backButton.getStyleClass().add("backbutton");
        GridPane.setConstraints(backButton, 25, 60, 2, 1);


        //Füge alles zum Grid hinzu
        grid.getChildren().addAll(backButton);


        /**
         * @param args the command line arguments
         */
        final String look = this.getClass().getResource("look2.css").toExternalForm();

        Scene scene = new Scene(grid, 1300, 750);
        scene.getStylesheets().add(look);
        window.setScene(scene);
        window.show();
    }

}