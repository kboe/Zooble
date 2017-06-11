package GUI;


import Logic.Collision.Collider.BoxCollider;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Created by TheRop on 10/06/17.
 */
public class TransferRectData {


    public GridPane buttonUi = new GridPane();
    GridPane manipulator;
    Label angle = new Label("0");
    Label width = new Label("0");
    Button controlPlus = new Button("+");
    Button controlMinus = new Button("-");
    Button controlPlusRot = new Button("+");
    Button controlMinusRot = new Button("-");

    BoxCollider rect;
    int hitCounterP;
    int hitCounterM;


    public TransferRectData(){

        controlPlus.setOnAction(event -> {
            if (rect.getWidth() < 350){
                rect.scaleWidth(50);
                width.setText(Double.toString(rect.getWidth()));
                updateManipulator();
            }


        });

        controlMinus.setOnAction(event -> {
            if (rect.getWidth() > 200){
                rect.scaleWidth(-50);
                width.setText(Double.toString(rect.getWidth()));
                updateManipulator();
            }

        });

        controlPlusRot.setOnAction(event -> {
            if(rect.getAngle() < 67.5){
                rect.rotateBox(22.5);
                angle.setText(Double.toString(rect.getAngle()));
                updateManipulator();

            }
        });

        controlMinusRot.setOnAction(event -> {
            if(rect.getAngle() > -67.5){
                rect.rotateBox(-22.5);
                angle.setText(Double.toString(rect.getAngle()));
                updateManipulator();
            }
        });


        buttonUi.add(new Label("Selection: "),0,0);
        buttonUi.add(new Label("angle:"),0,1);
        buttonUi.add(angle,1,1);
        buttonUi.add(controlMinusRot,3,1);
        buttonUi.add(controlPlusRot,4,1);
        buttonUi.add(new Label("width:"),0,2);
        buttonUi.add(width,1,2);
        buttonUi.add(controlMinus,3,2);
        buttonUi.add(controlPlus,4,2);




    }

    private void updateManipulator() {
        manipulator.setLayoutX(rect.getMidpoint().getX() - 60);
        manipulator.setLayoutY(rect.getMidpoint().getY() - 10);
    }

}
