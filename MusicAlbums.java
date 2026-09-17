public class MusicAlbums extends productData {
    String artist;
    int globalSales;
    int tracks;
    double duration;
    String genre;

    public MusicAlbums(int i, String t, int ry, String a, String n, int gs, int tr, double d, String g) {
        super(i, t, n, ry);
        artist = a;
        globalSales = gs;
        tracks = tr;
        duration = d;
        genre = g;
    }
}
