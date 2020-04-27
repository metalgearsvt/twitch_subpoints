package settings;

public class Spew {
    public static void out(Settings settings, boolean debugOnly, String out) {
        if(!debugOnly) {
            System.out.println(out);
            return;
        }
        if(settings.getDebug()) {
            System.out.println(out);
        }
    }
}
