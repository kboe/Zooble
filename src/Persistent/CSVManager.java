package Persistent;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Nikk1208 on 28.05.2017.
 */


/*
* CSV CONVENTION:   |SEPARATOR|   VARIABLE_NAME  |   VALUE  |
*   SEPARATOR can be: PROPERTY, POSITION,
*   VARIABLE_NAME is only relevant for human
*   VALUE: specifies the objects value of the variable
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
