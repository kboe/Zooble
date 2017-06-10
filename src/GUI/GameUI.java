package GUI;

/**
 * Created by TheRop on 25/05/17.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;



public class GameUI extends Application {

    double height = 600;
    double width = 600;


    @Override
    public void start(Stage primaryStage) throws Exception{


        Boolean running = false;

        Group allRectsGrp = new Group();

        Button addRect = new Button("add");
        addRect.setOnAction(event -> {
          //  allRectsGrp.getChildren().add(new ZooRect(allRectsGrp).rectGrp);
        });

        Button removeRect = new Button("remove");
        removeRect.setOnAction(event ->{
        } );


        GridPane gridPane = new GridPane();

        {
            GridPane uiGrid = new GridPane();
            {
                uiGrid.add(new Label("Test"),0,0);
                uiGrid.add(addRect,0,1);
            }
            gridPane.add(uiGrid,0,0);

            RowConstraints ui = new RowConstraints();
            ui.setMinHeight(height);
            gridPane.getRowConstraints().addAll(ui);
            gridPane.getStyleClass().add("gridpane");
            gridPane.setLayoutX(width-50);
        }


        allRectsGrp.getChildren().add(gridPane);

        Scene scene = new Scene(allRectsGrp,width,height);
        scene.getStylesheets().add("GameUI.css");

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
