package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Created by Hilli on 24.05.2017.
 */
public class MainMenu {

    private Scene mainMenuScene;

    public MainMenu(){




        //GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,50,50,50));
        grid.setVgap(10);
        grid.setHgap(10);

        Image image = new Image(getClass().getResource("logo.png").toExternalForm()) {};

        // simple displays ImageView the image as is
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(550);
        iv1.setPreserveRatio(true);
        iv1.setCache(true);

        //Play
        Button loginButton = new Button("PLAY");
        loginButton.getStyleClass().add("play-red");
        GridPane.setConstraints(loginButton,1,5);

        //Options
        Button optionsButton = new Button("OPTIONS");
        optionsButton.getStyleClass().add("options-blue");
        GridPane.setConstraints(optionsButton,2,5);

        //Filler
        Button fillButton = new Button();
        fillButton.getStyleClass().add("fill-gray");
        GridPane.setConstraints(fillButton, 1, 6, 2, 1);

        //Highscore
        Button highscoreButton = new Button("HIGHSCORE");
        highscoreButton.getStyleClass().add("highscore-gray");
        GridPane.setConstraints(highscoreButton, 1, 6, 2, 1);




        //Füge alles zum Grid hinzu
        grid.getStylesheets().addAll(getClass().getResource("look.css").toExternalForm(),"https://fonts.googleapis.com/css?family=Quicksand");
        grid.getChildren().addAll(loginButton,optionsButton,highscoreButton, fillButton, iv1);

        /**
         * @param args the command line arguments
         */
        final String look = this.getClass().getResource("look.css").toExternalForm();

        mainMenuScene = new Scene(grid, 1300, 750);
        mainMenuScene.getStylesheets().addAll(this.getClass().getResource("look.css").toExternalForm());
    }

    public Scene getMainMenuScene() {
        return mainMenuScene;
    }
}