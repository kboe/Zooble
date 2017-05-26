package GUI;

/**
 * Created by Hilli on 26.05.2017.
 */
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Hilli
 */
public class Options {

    private Scene mainMenuScene;

    public Options(){


        //GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 50, 50, 50));
        grid.setVgap(10);
        grid.setHgap(10);

        Button backButton = new Button("Back to menu");
        backButton.getStyleClass().add("backbutton");
        GridPane.setConstraints(backButton, 25, 60, 2, 1);


        grid.getChildren().addAll(backButton);


        /**
         * @param args the command line arguments
         */
        final String look = this.getClass().getResource("look.css").toExternalForm();

        mainMenuScene = new Scene(grid, 1300, 750);
        mainMenuScene.getStylesheets().addAll(this.getClass().getResource("look2.css").toExternalForm());
    }

    public Scene getMainMenuScene() {
        return mainMenuScene;
    }
}