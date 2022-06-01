package All;

public final class IntegrityStatement {
    public static String signature() {
        String names = "Peleg Ben Barak and Reut Arad"; // <- Fill in your names here!
        if (names.length() == 0) {
            throw new UnsupportedOperationException("You should sign here");
        }
        return names;
    }
}
