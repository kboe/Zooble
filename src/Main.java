import Logic.Collision.RectangleCollider;
import Logic.Collision.SAT;
import Logic.Collision.TriangleCollider;
import Logic.Util.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

       // ROBIN CODE BEGINNING

        //Parent root = FXMLLoader.load(getClass().getResource("Controller/sample.fxml"));
        primaryStage.setTitle("Hello World");

        Group root = new Group();
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        Vector2d[] points = new Vector2d[4];

        points[0] = new Vector2d(5,5);
        points[1] = new Vector2d(105,5);
        points[2] = new Vector2d(105,105);
        points[3] = new Vector2d(5,105);


        RectangleCollider rect = new RectangleCollider(50,50,200,200);
        root.getChildren().add(canvas);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0,0, 500, 500);
                rect.draw(gc);
                //gc.fillOval(0,0,100,100);
            }
        }.start();
        primaryStage.show();

        //ROBIN CODE ENDING
    }


    public static void main(String[] args) {
        launch(args);
    }
}
