package Persistent;

import GUI.ZooRect;
import Logic.Collision.Collider.BallCollider;
import Logic.Collision.Collider.BoxCollider;
import Persistent.Level.CustomLevel;
import Persistent.Level.DefaultLevel;
import Persistent.Level.Level;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Rocki on 28.05.2017.
 */


/*
* CSV CONVENTION:   |   OBJECTTYPE  |   ATTRIBUTE  |   VALUE  |     END    |
*
*   OBJECTTYPE can be: ELEPHANTBALL, OWLBALL, BOXCOLLIDER, GOALBOX, ...
*
*   ATTRIBUTE can be something like: POSITION, VELOCITY, MASS, etc. depends on the Object/Seperator
*
*   VALUE: specifies the objects value of the variable -> POSITION and VELOCITY have to be Vectors -> POSITION,2,4,VELOCITY,3,1 would be-> something like:
*   this.position = new Vector2d(2,4);
*   this.velocity = new Vector2d(3,1);
*
*   END tells the Scanner, that a new Row will be read
*
*   Every row is going to be a new Object/Game asset in the Game, but an Object can have multiple Attributes
* */

public final class CSVManager {

    public static void loadTest(File csvFile) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(csvFile.getAbsolutePath()));
            String line = "";

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    if (token.contains("PROPERTY")) {
                        System.out.println("\nproperty is dat");
                    } else if (token.contains("POSITION")) {
                        System.out.println("\nposition is dat");
                    }
                    System.out.println(token);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTest() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("test2.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("id");        //1st. Col
        sb.append(',');
        sb.append("Name");      //2nd. Col
        sb.append('\n');        //next row

        sb.append("1");
        sb.append(',');
        sb.append("Prashant Ghimire");
        sb.append('\n');

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
    }

    public static DefaultLevel loadDefaultLevel(int id) throws FileNotFoundException {
        File defaultLevelFile = new File(Constants.DIRECTORY + "/src/Persistent/Resources/Levels_CSV/DefaultLevels/DefaultLevel_" + id + ".csv");
        DefaultLevel defaultLevel = new DefaultLevel();
        Scanner scanner = new Scanner(defaultLevelFile);
        String separator = ",";
        String token;
        scanner.useDelimiter(separator);

        while (scanner.hasNext()){
            token = scanner.next();
            if (token.equals("ELEPHANTBALL")){

            } else if (token.equals("OWLBALL")){

            } else if (token.equals("CATBALL")){

            } else if (token.equals("BLOWFISHBALL")){

            } else if (token.equals("TURTLEBALL")){

            } else if (token.equals("BOOSTBOX")){
                //create a ZooRectTrigger thingy with a BoostBox as "rect" variable

            } else if (token.equals("GOALBOX")){
                //create a ZooRectTrigger thingy with a GoalBox as "rect" variable

            } else if (token.equals("BOXCOLLIDER")){
                //create a ZooRectCollider thingy with a BoxCollider as "rect" and "visualRect" variable

            } else {
                System.out.println("no Game Asset");
            }
        }
        scanner.close();

        //TODO read csv line by line and store every object with its Attributes into the Lists


        return defaultLevel;
    }

    public static CustomLevel loadCustomLevel(int id) throws FileNotFoundException {

        File customLevelFile = new File(Constants.DIRECTORY + "/src/Persistent/Resources/Levels_CSV/CustomLevels/CustomLevel_" + id + ".csv");
        CustomLevel customLevel = new CustomLevel();

        //TODO read csv line by line and store every object with its Attributes into the Lists


        return customLevel;
    }

    public static void saveLevel() {
        //TODO save the Level as a csv File somewhere on the Computer or in the Project Files
    }

    private void validateStorageOfOtherCollidersList(Object objectToStore){
        if (!(objectToStore instanceof ZooRect) || !(objectToStore instanceof BoxCollider)){
            throw new IllegalArgumentException("The Object which should have been stored was no ZooRect or BoxCollider");
        }
    }

    private void validateStorageOfBallCollidersList(Object objectToStore){
        if (!(objectToStore instanceof BallCollider)){
            throw new IllegalArgumentException("The Object which should have been stored was no BallCollider");
        }
    }

}
