import java.util.List;

public class digitalMediaData extends productData{
    protected String director;
    protected String country;
    protected String rating;
    protected String discription;

    public digitalMediaData(int i, String n, String d, String c, int ry, String r, String s) {
        super(i, n, ry);
        director=d;
        country=c;
        rating=r;
        discription=s;

    }

    public static List<List<String>> validateDigitalMediaData(String id, String title, String releaseYear,
            String director, String country, String description) {
        List<List<String>> logs = ValidationUtils.validateProductData(id, title, releaseYear, "digital media");
        List<String> warnings = logs.get(0);
        ValidationUtils.addTextWarning(warnings, "director", director);
        ValidationUtils.addTextWarning(warnings, "country", country);
        ValidationUtils.addTextWarning(warnings, "description", description);
        return logs;
    }

    public String getRating()
    {
        return rating;
    }
    
}
