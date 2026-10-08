public class ProductData {
    protected int id;
    protected String country;
    protected int releaseYear;
    protected String title;

    public ProductData(int i, String n, int ry) {
        id = i;
        title = n;
        releaseYear = ry;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

}
