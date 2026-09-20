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

    public String getRating()
    {
        return rating;
    }
    
}
