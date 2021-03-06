package GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * Created by Hilli on 24.05.2017.
 */
public class MainMenu extends Application {

    private Scene mainMenuScene;

    public MainMenu(Stage primaryStage, Scene gameScene){
        Stage window = primaryStage;
        window.setTitle("Zooble");


        //GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,50,50,50));
        grid.setVgap(10);
        grid.setHgap(10);

        Image image = new Image(getClass().getResource("logo.png").toExternalForm()) {};

        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(550);
        iv1.setPreserveRatio(true);
        iv1.setCache(true);

        //Play
        Button playButton = new Button("PLAY");
        playButton.getStyleClass().add("play-red");
        GridPane.setConstraints(playButton,1,5);


        //Options
        Button optionsButton = new Button("OPTIONS");
        optionsButton.getStyleClass().add("options-blue");
        GridPane.setConstraints(optionsButton,2,5);

        //Filler
        // Button fillButton = new Button();
        //fillButton.getStyleClass().add("fill-gray");
        //GridPane.setConstraints(fillButton, 1, 6, 2, 1);

        //Highscore
        Button highscoreButton = new Button("HIGHSCORE");
        highscoreButton.getStyleClass().add("highscore-gray");
        GridPane.setConstraints(highscoreButton, 1, 6);

        //EXIT
        Button exitButton = new Button("EXIT");
        exitButton.getStyleClass().add("exit-black");
        GridPane.setConstraints(exitButton, 2, 6);
        exitButton.setOnAction(e -> {
            Platform.exit();
        });

        playButton.setOnAction(event -> {
            primaryStage.setScene(gameScene);
        });


        //Füge alles zum Grid hinzu
        grid.getStylesheets().addAll(getClass().getResource("look.css").toExternalForm(),"https://fonts.googleapis.com/css?family=Quicksand");
        grid.getChildren().addAll(playButton,optionsButton,highscoreButton, exitButton, iv1);

        /**
         * @param args the command line arguments
         */
        final String look = this.getClass().getResource("look.css").toExternalForm();

        Scene scene = new Scene(grid, 1300, 750);
        scene.getStylesheets().add(look);
        window.setScene(scene);
        window.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}