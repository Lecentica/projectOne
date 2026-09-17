public class digitalMediaData extends productData{
    protected String director;
    protected String country;
    protected String rating;
    protected String discription;

    public digitalMediaData(int i, String t, String n, String d, String c, int ry, String r, String s) {
        super(i, t, n, ry);
        director=d;
        country=c;
        rating=r;
        discription=s;

    }
    
}
