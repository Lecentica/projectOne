import java.util.Set;

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
}
