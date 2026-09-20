public class VideoGames extends productData {
	String platform;
	String genre;
	String publisher;
	double copiesSold;

	public VideoGames(int i, String n, String p, int ry, String g, String pu, double cs) {
		super(i, n, ry);
		platform = p;
		genre = g;
		publisher = pu;
		copiesSold = cs;
	}

	public String getPlatform()
	{
		return platform;
	}

	public String getGenre()
	{
		return genre;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public double getCopiesSold()
	{
		return copiesSold;
	}
}
