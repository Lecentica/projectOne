import java.util.Set;
import java.util.List;

public class TVShows extends digitalMediaData {
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
        List<List<String>> logs = digitalMediaData.validateDigitalMediaData(id, title, releaseYear, director, country,
                description);
        if (!ValidationUtils.hasUsableText(rating) || !TV_SHOW_RATINGS.contains(rating.toUpperCase(java.util.Locale.ROOT))) {
            if (ValidationUtils.hasUsableText(rating)) {
                logs.get(0).add("Invalid TV show rating - substituted \"Unknown\"");
            } else {
                ValidationUtils.addTextWarning(logs.get(0), "TV show rating", rating);
            }
        }
        if (seasons == null || !seasons.matches("\\d+ [a-zA-Z]+")) {
            logs.get(1).add("Invalid number of seasons");
        } else {
            String seasonCount = seasons.split(" ")[0];
            if (!ValidationUtils.isValidIntegerValue(seasonCount)) {
                logs.get(1).add("Invalid number of seasons");
            }
        }
        return logs;
    }
}
