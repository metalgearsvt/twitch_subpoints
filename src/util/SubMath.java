package util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import settings.Settings;
import settings.Spew;

import java.util.ArrayList;

public class SubMath {
    public static int calculate(ArrayList<JsonArray> results, Settings settings) {
        int subPoints = 0;
        subPoints += settings.getOffset();
        int entries = 0;
        int t1 = 0;
        int t2 = 0;
        int t3 = 0;
        Spew.out(settings, false, "Calculating sub points...");
        for(JsonArray array : results) {
            for(JsonElement sub : array) {
                JsonObject subObj = sub.getAsJsonObject();
                // Count as a sub if they're not the broadcaster.
                if(!subObj.get("user_id").getAsString().equals(settings.getBroadcaster())) {
                    entries++;
                    String tier = subObj.get("tier").getAsString();
                    if(tier.equals("1000")) {
                        subPoints += 1;
                        t1++;
                    } else if(tier.equals("2000")) {
                        subPoints += 2;
                        t2++;
                    } else if(tier.equals("3000")) {
                        subPoints += 6;
                        t3++;
                    } else {
                        Spew.out(settings, true, "Tier with value: " + tier);
                    }
                }
            }
        }
        Spew.out(settings, false, "Subscribers: " + entries);
        Spew.out(settings, true, "Tier 1: " + t1);
        Spew.out(settings, true, "Tier 2: " + t2);
        Spew.out(settings, true, "Tier 3: " + t3);
        Spew.out(settings, false, "Calculated sub points: " + subPoints);
        return subPoints;
    }
}
