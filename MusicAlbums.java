import java.util.List;

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

    public static List<List<String>> validateMusicAlbumData(String id, String releaseYear, String artist, String title,
            String globalSales, String tracks, String duration, String genre) {
        List<List<String>> logs = ValidationUtils.validateProductData(id, title, releaseYear, "product");
        List<String> errors = logs.get(1);
        ValidationUtils.addTextWarning(logs.get(0), "music album artist", artist);
        if (!ValidationUtils.isValidIntegerValue(tracks)) {
            errors.add("Invalid music album tracklist");
        }
        if (!ValidationUtils.isValidIntegerValue(globalSales)) {
            errors.add("Invalid music album global sales");
        }
        if (duration == null || !duration.matches("\\d+(\\.\\d+)?")) {
            errors.add("Invalid music album duration");
        }
        ValidationUtils.addTextWarning(logs.get(0), "music album genre", genre);
        return logs;
    }
}
