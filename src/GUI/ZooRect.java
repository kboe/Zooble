package GUI;

/**
 * Created by TheRop on 25/05/17.
 */

import Logic.Collision.BoxCollider;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;


public class ZooRect {

    double width = 200;
    double height = 30;

    double coordX = 100;
    double coordY = 200;

    public Group rectGrp;

    private GridPane manipulators = new GridPane();

    Button controlPlus;
    Button controlMinus;
    Button controlRotPlus;
    Button controlRotMinus;

    double sceneX;
    double sceneY;

    double translateX;
    double translateY;

    Boolean selected = false;

    public ZooRect(){
/*
        Rectangle rect = new Rectangle();
        {
            rect.setX(coordX);
            rect.setY(coordY);
            rect.setWidth(width);
            rect.setHeight(height);
            rect.setFill(Color.ORANGE);
        }
        */

        BoxCollider rect = new BoxCollider(coordX, coordY, width, height, Color.BLUE);

        manipulators.setVisible(false);
        updateManipulator();

        controlPlus = new Button("+");
        {
            manipulators.add(controlPlus,3,0);
            controlPlus.setOnAction(event -> {
                if (rect.getWidth() < 400){
                    width += 100;
                    updateManipulator();
                    rect.setWidth(width);
                }else{
                    System.out.println("nope");
                }

            });
        }

        controlMinus = new Button("-");
        {
            manipulators.add(controlMinus,0,0);
            controlMinus.setOnAction(event -> {
                if (rect.getWidth() > 200){
                    width -= 100;
                    updateManipulator();
                    rect.setWidth(width);
                }else{
                    System.out.println("nope");
                }

            });
        }

        controlRotPlus = new Button("rot +");
        {
            manipulators.add(controlRotPlus,2,0);
            controlRotPlus.setOnAction(event -> {
                rect.getTransforms().add(new Rotate(22.5,coordX+width/2,coordY+height/2));
                updateManipulator();
            });
        }


        controlRotMinus = new Button("rot -");
        {
            manipulators.add(controlRotMinus,1,0);
            controlRotMinus.setOnAction(event -> {
                rect.getTransforms().add(new Rotate(-22.5,coordX+width/2,coordY+height/2));
                updateManipulator();
            });
        }



        rectGrp = new Group();
        rectGrp.getChildren().add(rect);
        rectGrp.getChildren().add(manipulators);
        rectGrp.setOnMousePressed(rectMousePressEvent);
        rectGrp.setOnMouseDragged(rectMouseDragEvent);

    }

    EventHandler<MouseEvent> rectMousePressEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            sceneX = event.getSceneX();
            sceneY = event.getSceneY();

            translateX = ((Group)(event.getSource())).getTranslateX();
            translateY = ((Group)(event.getSource())).getTranslateY();

            if (!selected){
                selected = true;
                showManipulator(selected);

            }else{
                selected = false;
                showManipulator(selected);
            }
        }
    };

    EventHandler<MouseEvent> rectMouseDragEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            selected = false;
            showManipulator(selected);

            double offsetX = event.getSceneX() - sceneX;
            double offsetY = event.getSceneY() - sceneY;
            double nTranslateX = translateX + offsetX;
            double nTranslateY = translateY + offsetY;

            ((Group)(event.getSource())).setTranslateX(nTranslateX);
            ((Group)(event.getSource())).setTranslateY(nTranslateY);

        }
    };

    public void showManipulator(Boolean b){
        manipulators.setVisible(b);
    }

    public void updateManipulator(){
        manipulators.setLayoutX(coordX + width/4);
        manipulators.setLayoutY(coordY + height/4);
    }

}
