package GUI;

        import javafx.application.Application;
        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.geometry.Insets;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.control.Slider;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.GridPane;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;


/**
 *
 * @author Hilli
 */

public class HelligkeitOptionen extends Application {

    final Slider helligkeit = new Slider(0, 1, 1);
    final Image image  = new Image(getClass().getResourceAsStream("katze.png"));

    final Label helligkeitlabel = new Label("Helligkeit:");


    final Label helligkeitswert = new Label(
            Double.toString(helligkeit.getValue()));


    final static Color textColor = Color.BLACK;


    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(50);


        Group root = new Group();
        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
        stage.setTitle("Helligkeit für Optionen");
        scene.setFill(Color.BLACK);

        final ImageView katze = new ImageView (image);
        GridPane.setConstraints(katze, 0, 0);
        katze.setFitWidth(500);
        katze.setPreserveRatio(true);
        katze.setCache(true);
        GridPane.setColumnSpan(katze, 3);
        grid.getChildren().add(katze);
        scene.setRoot(grid);

        helligkeitlabel.setTextFill(textColor);

        GridPane.setConstraints(helligkeitlabel, 0, 1);
        grid.getChildren().add(helligkeitlabel);


        helligkeit.valueProperty().addListener(new ChangeListener<Number>() {

            @Override

            public void changed(ObservableValue<? extends Number> ov,
                                Number old_hell, Number new_hell) {
                katze.setOpacity(new_hell.doubleValue());

                helligkeitswert.setText(String.format("%.0f", new_hell));
            }
        });

        GridPane.setConstraints(helligkeit, 1, 1);
        grid.getChildren().add(helligkeit);

        helligkeitswert.setTextFill(textColor);
        GridPane.setConstraints(helligkeitswert, 2, 1);
        grid.getChildren().add(helligkeitswert);


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

