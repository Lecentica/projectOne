public class TVShows extends digitalMediaData {
    int numberOfSeasons;

    public TVShows(int i, String n, String d, String c, int ry, String r, int l, String s) {
        super(i, n, d, c, ry, r, s);
        numberOfSeasons = l;
    }
}
