package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import settings.Settings;
import settings.Spew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Subscribers {

    public static ArrayList<JsonArray> callApi(Settings settings) throws IOException {
        return callApi(settings, "");
    }
    public static ArrayList<JsonArray> callApi(Settings settings, String pagination) throws IOException {
        boolean hasNextPage = true;
        ArrayList<JsonArray> resultSet = new ArrayList<JsonArray>();
        while(hasNextPage) {
            URL url = constructUrl(settings, pagination);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            con.setRequestProperty("Authorization", "Bearer " + settings.getoAuth());
            con.setRequestProperty("Client-ID", settings.getClientId());
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            Spew.out(settings, true, content.toString());
            JsonObject jsonObj = new JsonParser().parse(content.toString()).getAsJsonObject();

            JsonArray data = jsonObj.get("data").getAsJsonArray();
            String newPagination = jsonObj.get("pagination").getAsJsonObject().get("cursor").getAsString();
            if (data.size() == 0) {
                Spew.out(settings, false, "Reached end of results, no more to fetch.");
                hasNextPage = false;
            } else {
                Spew.out(settings, true, "Fetching next resultset with pagination ID: " + newPagination);
                Spew.out(settings, true, "Adding to results: " + data.toString());
                resultSet.add(data);
                pagination = newPagination;
            }
        }
        return resultSet;
    }

    public static URL constructUrl(Settings settings) throws MalformedURLException {
        return constructUrl(settings, "");
    }
    public static URL constructUrl(Settings settings, String pagination) throws MalformedURLException {
        String sUrl = settings.getEndpoint() + "?" +
                settings.getParamBroadcasterId() + "=" + settings.getBroadcaster();
        sUrl += "&" + "first" + "=" + "100";
        if(null != pagination && pagination != "") {
            sUrl += "&" + settings.getParamCursor() + "=" + pagination;
        }
        Spew.out(settings, true, "URL is: " + sUrl);
        return new URL(sUrl);
    }
}
