import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class productData {
    protected int id;
    protected String director;
    protected String country;
    protected int releaseYear;
    protected String title;
    public int getGlobalSales;
    protected static final Pattern HAS_LETTER = Pattern.compile("[a-zA-Z]");

    public productData(int i, String d, String c, int ry, String s) {
        id = i;
        director = d;
        country = c;
        releaseYear = ry;
        title = s;
    }

    public productData(int i, String n, int ry) {
        id = i;
        title = n;
        releaseYear = ry;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    protected static boolean isValidIntegerValue(String value) {
        if (value == null || !value.matches("\\d+")) {
            return false;
        }
        try {
            return Long.parseLong(value) <= Integer.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected static boolean hasUsableText(String value) {
        return value != null && !value.isBlank() && HAS_LETTER.matcher(value).find();
    }

    public static String substituteUnknown(String value) {
        return hasUsableText(value) ? value : "Unknown";
    }

    protected static void addTextWarning(List<String> warnings, String label, String value) {
        if (!hasUsableText(value)) {
            String issue = value == null || value.isBlank() ? "Missing " : "Invalid ";
            warnings.add(issue + label + " - substituted \"Unknown\"");
        }
    }

    public static List<List<String>> validateProductData(String id, String title, String releaseYear) {
        return validateProductData(id, title, releaseYear, "product");
    }

    protected static List<List<String>> validateProductData(String id, String title, String releaseYear, String type) {
        List<List<String>> logs = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        if (!isValidIntegerValue(id)) {
            errors.add("Invalid " + type + " ID");
        }
        addTextWarning(warnings, "title", title);
        if (!isValidIntegerValue(releaseYear)) {
            errors.add("Invalid " + type + " release year");
        }
        logs.add(warnings);
        logs.add(errors);
        return logs;
    }
}
