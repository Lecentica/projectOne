import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;


public class TVShows extends DigitalMediaData {
    int numberOfSeasons;
    public static final Set<String> TV_SHOW_RATINGS = Set.of("TV-Y", "TV-Y7", "TV-Y7-FV", "TV-G", "TV-PG", "TV-14", "TV-MA");
    public TVShows(int i, String n, String d, String c, int ry, String r, int l, String s) {
        super(i, n, d, c, ry, r, s);
        numberOfSeasons = l;
    }

    public int getNumberOfSeasons()
    {
        return numberOfSeasons;
    }

    public static List<List<String>> validateTVShowData(String id, String title, String releaseYear, String director,
            String country, String rating, String seasons, String description) {
        List<List<String>> logs = DigitalMediaData.validateDigitalMediaData(id, title, releaseYear, director, country,
                description);
        if (!ValidationUtils.hasUsableText(rating) || !TV_SHOW_RATINGS.contains(rating.toUpperCase(java.util.Locale.ROOT))) {
            if (ValidationUtils.hasUsableText(rating)) {
                logs.get(0).add("Invalid TV show rating - substituted \"Unknown\"");
            } else {
                ValidationUtils.addTextWarning(logs.get(0), "TV show rating", rating);
            }
        }
        
        int seasonCount = parseSeasons(seasons);
        if (seasonCount == 0) {
            logs.get(1).add("Invalid number of seasons");
        }
        return logs;
    }

    static int parseSeasons(String value) {
    if (value == null) return -1;
        Matcher m = Pattern.compile("^\\s*(\\d+)").matcher(value);
    return m.find() ? Integer.parseInt(m.group(1)) : -1;
}
}
