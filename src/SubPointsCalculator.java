import api.Subscribers;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import settings.Settings;
import settings.Spew;
import util.SubMath;
import util.WriteToFile;

import java.io.IOException;
import java.util.ArrayList;

public class SubPointsCalculator {
    public static void main(String args[]) throws IOException, InterruptedException {
        while(true) {
            try {
                Settings settings = new Settings();
                try {
                    Spew.out(settings, false, "Fetching subscriber list...");
                    ArrayList<JsonArray> results = Subscribers.callApi(settings);
                    int subpoints = SubMath.calculate(results, settings);
                    WriteToFile.goBitch(subpoints, settings);
                } catch (Exception e) {
                    Spew.out(settings, false, "Shit got fucked up, send error log to Metal!");
                    WriteToFile.dumpError(e, settings);
                }
                Spew.out(settings, false, "Sleeping for " + settings.getTimeDelay() + " ms.");
                Thread.sleep(settings.getTimeDelay());
            } catch(IOException ee) {
                System.out.println("ERROR READING SETTINGS FILE! SLEEPING 2 SECONDS AND TRYING AGAIN.");
                Thread.sleep(2000);
            }
        }
    }
}
