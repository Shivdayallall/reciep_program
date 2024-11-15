

public class Utility {
    public static int ParseIntOrDefault(String input, int defaultValue) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int ParseIntOrDefault(String input) {
        return Utility.ParseIntOrDefault(input, 0);
    }
}
