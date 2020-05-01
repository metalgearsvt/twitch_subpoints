package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private Properties p;

    private final String FILE = "settings.properties";

    private final String P_BROADCASTER = "broadcaster";
    private String broadcaster;

    private final String P_OAUTH = "oauth";
    private String oAuth;

    private final String P_CLIENT_ID = "clientId";
    private String clientId;

    private final String P_SUB_ENDPOINT = "endpoint";
    private String endpoint;

    private final String P_PARAM_BROADCASTER_ID = "paramBroadcasterId";
    private String paramBroadcasterId;

    private final String P_PARAM_CURSOR = "paramCursor";
    private String paramCursor;

    private final String DEBUG = "debug";
    private String debug;

    private final String TIME_DELAY = "timedelay";
    private String timedelay;

    private final String OFFSET = "subPointOffset";
    private String subPointOffset;

    private final String NICE_MODE_ENABLED = "niceMode";
    private String niceModeEnabled;

    private final String NICE_MODE_TEXT = "niceText";
    private String niceModeText;

    private final String CUSTOM_TEXT = "customText";
    private String customText;

    public Settings() throws IOException {
        p = new Properties();
        InputStream input = new FileInputStream(FILE);
        p.load(input);
        broadcaster = p.getProperty(P_BROADCASTER);
        if(null == broadcaster) {
            System.out.println("Settings property " + P_BROADCASTER + " is null, the program will not operate without this!");
            throw new IOException(P_BROADCASTER + " is not set in the settings file.");
        }

        oAuth = p.getProperty(P_OAUTH);
        if(null == oAuth) {
            System.out.println("Settings property " + P_OAUTH + " is null, the program will not operate without this!");
            throw new IOException(P_OAUTH + " is not set in the settings file.");
        }

        clientId = p.getProperty(P_CLIENT_ID);
        if(null == clientId) {
            System.out.println("Settings property " + P_CLIENT_ID + " is null, the program will not operate without this!");
            throw new IOException(P_CLIENT_ID + " is not set in the settings file.");
        }

        endpoint = p.getProperty(P_SUB_ENDPOINT);
        if(null == endpoint) {
            System.out.println("Settings property " + P_SUB_ENDPOINT + " is null.");
            endpoint = "https://api.twitch.tv/helix/subscriptions";
        }

        paramBroadcasterId = p.getProperty(P_PARAM_BROADCASTER_ID);
        if(null == paramBroadcasterId) {
            System.out.println("Settings property " + P_PARAM_BROADCASTER_ID + " is null.");
            paramBroadcasterId = "broadcaster_id";
        }

        paramCursor = p.getProperty(P_PARAM_CURSOR);
        if(null == paramCursor) {
            System.out.println("Settings property " + P_PARAM_CURSOR + " is null.");
            paramCursor = "after";
        }

        debug = p.getProperty(DEBUG);
        if(null == debug) {
            System.out.println("Settings property " + DEBUG + " is null.");
            debug = "0";
        }

        timedelay = p.getProperty(TIME_DELAY);
        if(null == timedelay) {
            System.out.println("Settings property " + TIME_DELAY + " is null.");
            timedelay = "10";
        }

        subPointOffset = p.getProperty(OFFSET);
        if(null == subPointOffset) {
            System.out.println("Settings property " + OFFSET + " is null.");
            subPointOffset = "0";
        }

        niceModeEnabled = p.getProperty(NICE_MODE_ENABLED);
        if(null == niceModeEnabled) {
            System.out.println("Settings property " + NICE_MODE_ENABLED + " is null.");
            niceModeEnabled = "0";
        }

        niceModeText = p.getProperty(NICE_MODE_TEXT);
        if(null == niceModeText) {
            System.out.println("Settings property " + NICE_MODE_TEXT + " is null.");
            niceModeText = "nice";
        }

        customText = p.getProperty(CUSTOM_TEXT);
        if(null == customText) {
            System.out.println("Settings property " + CUSTOM_TEXT + " is null.");
            customText = "%s";
        }

    }

    public String getBroadcaster() {
        return broadcaster;
    }

    public String getoAuth() {
        return oAuth;
    }

    public String getClientId() {return clientId; }

    public String getEndpoint() {
        return endpoint;
    }

    public String getParamBroadcasterId() {
        return paramBroadcasterId;
    }

    public String getParamCursor() {
        return paramCursor;
    }

    public boolean getDebug() {
        if(debug.equals("1")) {
            return true;
        }
        return false;
    }

    public int getTimeDelay() {
        try {
            int t = Integer.parseInt(timedelay);
            return t * 1000;
        } catch(Exception e) {
            return 10 * 1000;
        }
    }

    public int getOffset() {
        try {
            int t = Integer.parseInt(subPointOffset);
            return t;
        } catch(Exception e) {
            return 0;
        }
    }

    public boolean getNiceModeEnabled() {
        if(niceModeEnabled.equals("1")) {
            return true;
        }
        return false;
    }

    public String getNiceModeText() {return niceModeText;}

    public String getCustomText() {return customText;}
}
