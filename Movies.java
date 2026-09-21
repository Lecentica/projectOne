import java.util.Set;

public class Movies extends digitalMediaData {
    double duration;
    public static final Set<String> MOVIE_RATINGS = Set.of("G", "PG", "PG-13", "R", "NC-17");
    public Movies(int i, String n, String d, String c, int ry, String r, double l, String s) {
        super(i, n, d, c, ry, r, s);
        duration = l;
    }

    public double getDuration()
    {
        return duration;
    }

}
