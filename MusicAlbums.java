public class MusicAlbums extends productData {
    String artist;
    int globalSales;
    int tracks;
    String genre;
    double duration;
    
    public MusicAlbums(int i, int ry, String a, String n, int gs, int tr, double d, String g) {
        super(i, n, ry);
        artist = a;
        globalSales = gs;
        tracks = tr;
        genre = g;
        duration = d;
    }

    public String getArtist()
    {
        return artist;
    }

    public int getGlobalSales()
    {
        return globalSales;
    }

    public int getNumTracks()
    {
        return tracks;
    }

    public String getGenre()
    {
        return genre;
    }

    public double getDuration()
    {
        return duration;
    }
}
