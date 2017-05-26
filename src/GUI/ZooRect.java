package GUI;

/**
 * Created by TheRop on 25/05/17.
 */

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;


public class ZooRect {

    double width = 200;
    double height = 30;

    double coordX = 100;
    double coordY = 200;

    public Group rectGrp;

    Button controlPlus;
    Button controlMinus;
    Button controlRotPlus;

    double sceneX;
    double sceneY;

    double translateX;
    double translateY;

    Boolean selected = false;

    public ZooRect(){

        Rectangle rect = new Rectangle();
        {
            rect.setX(coordX);
            rect.setY(coordY);
            rect.setWidth(width);
            rect.setHeight(height);
            rect.setFill(Color.ORANGE);
        }

        controlPlus = new Button("+");
        {
            controlPlus.setVisible(false);
            controlPlus.setLayoutX((coordY+width)/2+25);
            controlPlus.setLayoutY(coordY);
            controlPlus.setOnAction(event -> {
                if (rect.getWidth() < 400){
                    width += 100;
                    controlPlus.setLayoutX((coordY+width)/2+25);
                    controlMinus.setLayoutX((coordY+ width)/2-5);
                    rect.setWidth(width);
                }else{
                    System.out.println("nope");
                }

            });
        }

        controlMinus = new Button("-");
        {
            controlMinus.setVisible(false);
            controlMinus.setLayoutX((coordY+ width)/2-5);
            controlMinus.setLayoutY(coordY);
            controlMinus.setOnAction(event -> {
                if (rect.getWidth() > 200){
                    width -= 100;
                    controlPlus.setLayoutX((coordY+width)/2+25);
                    controlMinus.setLayoutX((coordY+ width)/2-5);
                    rect.setWidth(width);
                }else{
                    System.out.println("nope");
                }

            });
        }

        controlRotPlus = new Button("rot +");
        {
            controlRotPlus.setVisible(false);
            controlRotPlus.setLayoutX((coordY+ width)/2);
            controlRotPlus.setLayoutY(coordY-30);
            controlRotPlus.setOnAction(event -> {
                rect.getTransforms().add(new Rotate(45,coordX+100,coordY));
            });
        }

        rectGrp = new Group();
        rectGrp.getChildren().add(rect);
        rectGrp.getChildren().add(controlPlus);
        rectGrp.getChildren().add(controlMinus);
        rectGrp.getChildren().add(controlRotPlus);
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
        controlPlus.setVisible(b);
        controlMinus.setVisible(b);
        controlRotPlus.setVisible(b);
    }

}
