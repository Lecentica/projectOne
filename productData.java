public class productData {
    protected int id=-1;
    protected String director="N/A";
    protected String country="N/A";
    protected int releaseYear=-1;
    protected String rating="";
    protected double duration=-1.0;
    protected int numberOfSeasons=-1;
    protected String title="N/A";

    //Overloaded constructor accepting Movie and TV Show parameters
    public productData(int i, String t, String d, String c, int ry, String r, double l, String s)
    {
        id=i;
        director=d;
        country=c;
        releaseYear=ry;
        rating=r;
    }

    //Overloaded constructor accepting general parameters
    public productData(int i, String t, String n,int ry)
    {
        id=i;
        title=n;
        releaseYear=ry;
    }
    //Accessor methods for general Parameters

    public int getId()
    {
        return id;
    }


    public String getDirector()
    {
        return director;
    }

    public String getCountry()
    {
        return country;
    }

    public int getReleaseYear()
    {
        return releaseYear;
    }

    public String getRating()
    {
        return rating;
    }

    public double getDuration()
    {
        return duration;
    }

    public int getNumberOfSeasons()
    {
        return numberOfSeasons;
    }
}
