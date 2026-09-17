public class VideoGames extends productData {
	String platform;
	String genre;
	String publisher;
	int copiesSold;

	public VideoGames(int i, String t, String n, String p, int ry, String g, String pu, int cs) {
		super(i, t, n, ry);
		platform = p;
		genre = g;
		publisher = pu;
		copiesSold = cs;
	}
}
