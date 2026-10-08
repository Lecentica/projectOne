import java.util.Set;
import java.util.List;

public class Movies extends DigitalMediaData {
    double duration;
    public static final Set<String> MOVIE_RATINGS = Set.of("G", "PG", "PG-13", "R", "NC-17", "TV-14", "TV-MA", "TV-PG");
    public Movies(int i, String n, String d, String c, int ry, String r, double l, String s) {
        super(i, n, d, c, ry, r, s);
        duration = l;
    }

    public double getDuration()
    {
        return duration;
    }

    public static List<List<String>> validateMovieData(String id, String title, String releaseYear, String director,
            String country, String rating, String duration, String description) {
        List<List<String>> logs = DigitalMediaData.validateDigitalMediaData(id, title, releaseYear, director, country,
                description);
        if (!ValidationUtils.hasUsableText(rating) || !MOVIE_RATINGS.contains(rating.toUpperCase(java.util.Locale.ROOT))) {
            ValidationUtils.addTextWarning(logs.get(0), "movie rating", rating);
            if (ValidationUtils.hasUsableText(rating)) {
                logs.get(0).add("Invalid movie rating - substituted \"Unknown\"");
            }
        }
        if (!ValidationUtils.isValidIntegerValue(duration)) {
            logs.get(1).add("Invalid movie duration");
        }
        return logs;
    }

}
