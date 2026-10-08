import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

final class ValidationUtils {
    private static final Pattern HAS_LETTER = Pattern.compile("[a-zA-Z]");

    private ValidationUtils() {
    }

    static List<List<String>> validateProductData(String id, String title, String releaseYear, String type) {
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

    static boolean isValidIntegerValue(String value) {
        if (value == null || !value.matches("\\d+")) {
            return false;
        }
        try {
            return Long.parseLong(value) <= Integer.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean hasUsableText(String value) {
        return value != null && !value.isBlank() && HAS_LETTER.matcher(value).find();
    }

    static String substituteUnknown(String value) {
        return hasUsableText(value) ? value : "Unknown";
    }

    static void addTextWarning(List<String> warnings, String label, String value) {
        if (!hasUsableText(value)) {
            String issue = value == null || value.isBlank() ? "Missing " : "Invalid ";
            warnings.add(issue + label + " - substituted \"Unknown\"");
        }
    }
}