package GUI;

/**
 * Created by TheRop on 25/05/17.
 */

import Logic.Collision.Collider.BoxCollider;
import Logic.Util.Vector2d;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ZooRect {

    private double width = 200;
    private double height = 17.5;

    private double angle = 0;
    private double addAngle = 22.5;
    BoxCollider rect;
    BoxCollider visualRect;

    private static final double startCoordX = 100;
    private static final double startCoordY = 200;


    public Group rectGrp;

    private GridPane manipulators = new GridPane();

    private Button controlPlus, controlPlus2;
    private Button controlMinus, controlMinus2;
    private Button controlRotPlus, controlRotPlus2;
    private Button controlRotMinus,controlRotMinus2;
    private Button controlDelete;

    private double sceneX;
    private double sceneY;

    private double translateX;
    private double translateY;

    private Boolean selected = false;

    private int hitCounterP = 0;
    private int hitCounterM = 3;

    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private boolean hasBeenTranslated;

    TransferRectData transferRectData;



    public final double endCoordX = getStartCoordX() + 200; //exchange 200 through width


    public ZooRect(Group allRectsGrp, TransferRectData transferRectData) {


        this.transferRectData = transferRectData;
        rect = new BoxCollider(startCoordX, startCoordY, width, height/2, Color.TRANSPARENT);
        rect.translateBox(new Vector2d(0, height/4));
        visualRect = new BoxCollider(startCoordX, startCoordY, width, height + height, Color.ORANGE);
        visualRect.translateBox(new Vector2d(0, -height/2));


        manipulators.setVisible(false);

        updateManipulator();

        controlPlus = new Button("+");
        {
            manipulators.add(controlPlus, 4, 0);
            controlPlus.setOnAction(event -> {
                if (rect.getWidth() < 350) {
                    rect.scaleWidth(50);
                    visualRect.scaleWidth(50);
                    width += 50;
                    updateManipulator();
                    transferRectData.width.setText(Double.toString(width));

                } else {
                    System.out.println("max length");
                }

            });
        }


        controlMinus = new Button("-");
        {
            manipulators.add(controlMinus, 0, 0);
            controlMinus.setOnAction(event -> {

                if (rect.getWidth() > 200) {
                    rect.scaleWidth(-50);
                    visualRect.scaleWidth(-50);
                    width -= 50;
                    updateManipulator();
                    transferRectData.width.setText(Double.toString(width));
                } else {
                    System.out.println("min length");
                }

            });
        }

        controlRotPlus = new Button();
        {
            controlRotPlus.setStyle("-fx-background-image: url('/GUI/addrot.jpg')");
            controlRotPlus.setMinWidth(25);

            manipulators.add(controlRotPlus, 3, 0);
            controlRotPlus.setOnAction(event -> {
                if (angle < 67.5) {
                    rect.rotateBox(addAngle);
                    visualRect.rotateBox(addAngle);
                    angle += addAngle;
                    updateManipulator();

                    transferRectData.angle.setText(Double.toString(rect.getAngle()));


                } else {
                    System.out.println("max angle");
                }

            });
        }

        controlRotMinus = new Button();
        {
            controlRotMinus.setStyle("-fx-background-image: url('/GUI/removerot.jpg')");
            controlRotMinus.setMinWidth(25);

            manipulators.add(controlRotMinus, 1, 0);
            controlRotMinus.setOnAction(event -> {
                if (angle > -67.5) {
                    rect.rotateBox(-addAngle);
                    visualRect.rotateBox(-addAngle);
                    angle -= addAngle;
                    updateManipulator();
                    transferRectData.angle.setText(Double.toString(rect.getAngle()));

                } else {
                    System.out.println("min angle");
                }

            });
        }


        controlDelete = new Button("X");
        {
            manipulators.add(controlDelete, 2, 1);
            controlDelete.setOnAction(event -> {
                allRectsGrp.getChildren().remove(rectGrp);
            });
        }





        rectGrp = new Group();
        rectGrp.getChildren().addAll(visualRect, rect);
      //  rectGrp.getChildren().add(displayButtonUi);
        rectGrp.getChildren().add(manipulators);
        rectGrp.setOnMousePressed(rectMousePressEvent);
        rectGrp.setOnMouseDragged(rectMouseDragEvent);
        rectGrp.setOnMouseReleased(rectMouseReleaseEvent);

    }

    EventHandler<MouseEvent> rectMousePressEvent = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {


            sceneX = event.getSceneX(); //x coordinates of node in the scene
            sceneY = event.getSceneY(); //y coordinates of node in the scene

            translateX = ((Group) (event.getSource())).getTranslateX();
            translateY = ((Group) (event.getSource())).getTranslateY();

            startX = sceneX;
            startY = sceneY;

            System.out.println("start: " + startX + " " + startY);

            transferRectData.rect = rect;
            transferRectData.hitCounterM = hitCounterM;
            transferRectData.hitCounterP = hitCounterP;
            transferRectData.manipulator = manipulators;

            transferRectData.angle.setText(Double.toString(rect.getAngle()));
            transferRectData.width.setText(Double.toString(width));




            if (!selected) {
                selected = true;
                showManipulator(selected);
              //  showButtonUi(selected,event);


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

            double offsetX = event.getSceneX() - sceneX;    //determine offset between new and old position
            double offsetY = event.getSceneY() - sceneY;
            double nTranslateX = translateX + offsetX;      //translate node to new position
            double nTranslateY = translateY + offsetY;


            ((Group) (event.getSource())).setTranslateX(nTranslateX);
            ((Group) (event.getSource())).setTranslateY(nTranslateY);

            hasBeenTranslated = true;               //if box has been moved -> hasBeenTranslated = true
        }
    };

    EventHandler<MouseEvent> rectMouseReleaseEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            endX = event.getSceneX();
            endY = event.getSceneY();

            // System.out.println("end: "+ endX + " " + endY);


            //Verschiebe die Punkte des BoxColliders
            if (hasBeenTranslated) {
                Vector2d translationVector = Vector2d.subtract(new Vector2d(endX, endY), new Vector2d(startX, startY));
                rect.translateBox(translationVector);       //after translation of Box -> hasBeenTranslated = false
                visualRect.translateBox(translationVector);

                //invertiere Vector um LayoutBounds zu korrigieren
                translationVector.invert();
                //verschiebe die LayoutBounds um translationVector.getX und translationVector.getY
                ((Group) (event.getSource())).setTranslateX(((Group) (event.getSource())).getTranslateX() + translationVector.getX());
                ((Group) (event.getSource())).setTranslateY(((Group) (event.getSource())).getTranslateY() + translationVector.getY());

                updateManipulator();        //Update Manipulator auf die neuen Punkte
                hasBeenTranslated = false;
            }



        }
    };

    public void showManipulator(Boolean b) {
        manipulators.setVisible(b);

    }

    public void showButtonUi(Boolean b,Event event){
        if(event.getSource() instanceof Group){
            Node nodeOut = ((Group)event.getSource()).getChildren().get(2);

            if (nodeOut instanceof GridPane){
                System.out.println("gridpane");
                ((GridPane) nodeOut).add(new Label("tes"),0,0);
                ((GridPane) nodeOut).setVisible(b);

            }
        }else{
            System.out.println("schade");
        }


    }

    private void updateManipulator() {
        manipulators.setLayoutX(rect.getMidpoint().getX() - 60);
        manipulators.setLayoutY(rect.getMidpoint().getY() - 10);
    }

    public BoxCollider getRect() {
        return rect;
    }

    public double getStartCoordX() {
        return startCoordX;
    }

    public double getEndCoordX() {
        return endCoordX;
    }


}
