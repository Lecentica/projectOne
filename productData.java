public class productData {
    protected int id;
    protected String director;
    protected String country;
    protected int releaseYear;
    protected String title;
    public int getGlobalSales;

    //Overloaded constructor accepting Movie and TV Show parameters
    public productData(int i, String d, String c, int ry, String s)
    {
        id=i;
        director=d;
        country=c;
        releaseYear=ry;
        title=s;
    }

    //Overloaded constructor accepting general parameters
    public productData(int i, String n,int ry)
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

    public String getTitle()
    {
        return title;
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


}
