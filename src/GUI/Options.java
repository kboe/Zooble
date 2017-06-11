package GUI;

/**
 * Created by Hilli on 26.05.2017.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;


/**
 *
 * @author Hilli
 */
public class Options extends Application {
//TEST


    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Options");

        //GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,50,50,50));
        grid.setVgap(10);
        grid.setHgap(10);


        // SLIDER FÜR HELLIGKEIT
        Slider helligkeit = new Slider(0, 1, 1);
        helligkeit.getStyleClass().add("helligkeitcss");
        GridPane.setConstraints(helligkeit, 30, 18, 25, 1);

        final Label helligkeitswert = new Label(Double.toString(helligkeit.getValue()));
        GridPane.setConstraints(helligkeitswert, 55, 18);

        Label label1 = new Label("HELLIGKEIT");
        label1.getStyleClass().add("label1");
        GridPane.setConstraints(label1, 25, 18);

        //SLIDER FÜR SOUND
        Slider sound = new Slider(0, 2, 1);
        sound.getStyleClass().add("soundcss");
        sound.setMin(0);
        sound.setMax(100);
        sound.setValue(100);
        GridPane.setConstraints(sound, 30, 14, 25, 25);

        final Label soundvolume = new Label(Double.toString(sound.getValue()));
        GridPane.setConstraints(soundvolume, 55, 25);

        Label label2 = new Label("SOUND");
        label2.getStyleClass().add("label2");
        GridPane.setConstraints(label2, 25, 25);



        //Button
        Button backButton = new Button("Back to menu");
        backButton.getStyleClass().add("backbutton");
        GridPane.setConstraints(backButton,25, 45, 40, 10);


        //Image auf dass die Helligkeitsveränderung vorerst wirkt->soll später auf das ganze Spiel wirken
        Image image  = new Image(getClass().getResourceAsStream("Persistent/Resources/ballImages/chloe.png"));

        final ImageView katze = new ImageView (image);
        GridPane.setConstraints(katze,25, 13);
        katze.setFitWidth(100);
        katze.setPreserveRatio(true);
        katze.setCache(true);
        GridPane.setColumnSpan(katze, 3);
        grid.getChildren().add(katze);


        helligkeit.valueProperty().addListener(new ChangeListener<Number>() {

            @Override

            public void changed(ObservableValue<? extends Number> ov,
                                Number old_hell, Number new_hell) {
                katze.setOpacity(new_hell.doubleValue());

                helligkeitswert.setText(String.format("%.2f", new_hell));
            }
        });

        sound.valueProperty().addListener(new ChangeListener<Number>() {

            @Override

            public void changed(ObservableValue<? extends Number> ov,
                                Number old_sound, Number new_sound) {
                katze.setOpacity(new_sound.doubleValue());

                soundvolume.setText(String.format("%.2f", new_sound));
            }
        });


        grid.getChildren().addAll(label1, label2, helligkeit, sound, helligkeitswert, backButton, soundvolume);


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
