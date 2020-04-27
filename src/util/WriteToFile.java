package util;

import settings.Settings;
import settings.Spew;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteToFile {
    private static final String fileName = "subPoints.txt";
    private static final String errorFile = "ERRORS.sendtoMetal";
    public static void goBitch(int subs, Settings settings) {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                Spew.out(settings, false, "File created: " + myObj.getName());
            } else {
                Spew.out(settings, true, "File already exists.");
            }
            try {
                FileWriter myWriter = new FileWriter(fileName);
                // Custom text
                String textToWrite = String.format(settings.getCustomText(), Integer.toString(subs));
                // Nice mode
                if(settings.getNiceModeEnabled()) {
                    if(Integer.toString(subs).contains("69")) {
                        Spew.out(settings, false, "Looks like we have a nice number.");
                        textToWrite += " " + settings.getNiceModeText();
                    }
                }
                Spew.out(settings, true, "Final output:\n" + textToWrite);
                myWriter.write(textToWrite);
                myWriter.close();
                Spew.out(settings, false, "Successfully wrote sub points to file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Failed to create file.");
            e.printStackTrace();
        }
    }

    public static void dumpError(Exception ex, Settings settings) {
        try {
            File myObj = new File(errorFile);
            if (myObj.createNewFile()) {
                Spew.out(settings, false, "File created: " + myObj.getName());
            } else {
                Spew.out(settings, true, "File already exists.");
            }
            try {
                FileWriter myWriter = new FileWriter(errorFile, true);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                myWriter.write("\n=====\n\n" + formatter.format(date) +"\n");

                StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                myWriter.write(exceptionAsString);
                myWriter.close();
                Spew.out(settings, false, "Successfully wrote error log! Send to Metal!");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Failed to create file.");
            e.printStackTrace();
        }
    }
}
