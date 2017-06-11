package Persistent;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Rocki on 28.05.2017.
 */


/*
* CSV CONVENTION:   |   OBJECTTYPE  |   ATTRIBUTE  |   VALUE  |
*   OBJECTTYPE can be: ELEPHANTBALL, OWLBALL, BOXCOLLIDER, GOALBOX, ...
*   ATTRIBUTE can be something like: POSITION, VELOCITY, MASS, etc. depends on the Object/Seperator
*   VALUE: specifies the objects value of the variable -> POSITION and VELOCITY have to be Vectors -> POSITION,2,4,VELOCITY,3,1 would be-> something like:
*   this.position = new Vector2d(2,4);
*   this.velocity = new Vector2d(3,1);
*
*   Every row is going to be a new Object/Game asset in the Game, but an Object can have multiple Attributes
* */

public final class CSVManager {

    public static void loadTest(File csvFile){
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(csvFile.getAbsolutePath()));
            String line = "";

            while ((line = fileReader.readLine()) != null){
                String[] tokens = line.split(",");
                for (String token : tokens){
                    if (token.contains("PROPERTY")){
                        System.out.println("\nproperty is dat");
                    } else if (token.contains("POSITION")){
                        System.out.println("\nposition is dat");
                    }
                    System.out.println(token);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTest(){
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

    public static void loadLevel(File csvFile){
        //TODO load the Level from a csv File which is stored somewhere specific

    }

    public static void saveLevel(){
        //TODO save the Level as a csv File somewhere on the Computer or in the Project Files
    }

}
