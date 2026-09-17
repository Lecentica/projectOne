public class TVShows extends digitalMediaData {
    int numberOfSeasons;

    public TVShows(int i, String t, String n, String d, String c, int ry, String r, int l, String s) {
        super(i, t, n, d, c, ry, r, s);
        numberOfSeasons = l;
    }
}
