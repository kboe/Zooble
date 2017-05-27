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

public class ZooRect {

    private double width = 200;
    private double height = 35;

    private double angle = 0;
    private double addAngle = 22.5;

    private static  final double startCoordX = 100;
    private static final double startCoordY = 200;

    BoxCollider rect;

    public Group rectGrp;

    private GridPane manipulators = new GridPane();

    private Button controlPlus;
    private Button controlMinus;
    private Button controlRotPlus;
    private Button controlRotMinus;
    private Button controlDelete;

    private double sceneX;
    private double sceneY;

    private double translateX;
    private double translateY;

    private Boolean selected = false;

    private int hitCounterP = 0;
    private int hitCounterM = 3;

    public ZooRect(Group allRectsGrp) {

        rect = new BoxCollider(startCoordX, startCoordY, width, height, Color.ORANGE);

        manipulators.setVisible(false);
        updateManipulator();

        controlPlus = new Button("+");
        {
            manipulators.add(controlPlus, 4, 0);
            controlPlus.setOnAction(event -> {
                if (hitCounterM > 0)
                    hitCounterM--;
                if (hitCounterP < 3) {
                    rect.scaleWidth(50);
                    updateManipulator();
                    hitCounterP++;
                } else {
                    System.out.println("max length");
                }

            });
        }

        controlMinus = new Button("-");
        {
            manipulators.add(controlMinus, 0, 0);
            controlMinus.setOnAction(event -> {
                if (hitCounterP > 0)
                    hitCounterP--;

                if (hitCounterM < 3) {
                    rect.scaleWidth(-50);
                    updateManipulator();
                    hitCounterM++;
                } else {
                    System.out.println("min length");
                }

            });
        }

        controlRotPlus = new Button("rot +");
        {
            manipulators.add(controlRotPlus, 3, 0);
            controlRotPlus.setOnAction(event -> {
                if (angle < 67.5) {
                    rect.rotatePoints(addAngle);
                    angle += addAngle;
                    updateManipulator();
                } else {
                    System.out.println("max angle");
                }

            });
        }


        controlRotMinus = new Button("rot -");
        {
            manipulators.add(controlRotMinus, 1, 0);
            controlRotMinus.setOnAction(event -> {
                if (angle > -67.5) {
                    rect.rotatePoints(-addAngle);
                    angle -= addAngle;
                    updateManipulator();
                } else {
                    System.out.println("min angle");
                }

            });
        }

        controlDelete = new Button("X");
        {
            manipulators.add(controlDelete,2,1);
            controlDelete.setOnAction(event -> {
                allRectsGrp.getChildren().remove(rectGrp);
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

            translateX = ((Group) (event.getSource())).getTranslateX();
            translateY = ((Group) (event.getSource())).getTranslateY();

            if (!selected) {
                selected = true;
                showManipulator(selected);

            } else {
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

            ((Group) (event.getSource())).setTranslateX(nTranslateX);
            ((Group) (event.getSource())).setTranslateY(nTranslateY);

        }
    };

    private void showManipulator(Boolean b) {
        manipulators.setVisible(b);
    }

    private void updateManipulator() {
        manipulators.setLayoutX(rect.getMidpoint().getX() - 70);
        manipulators.setLayoutY(rect.getMidpoint().getY()- 10);
    }

}
