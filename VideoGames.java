import java.util.List;

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

	public static List<List<String>> validateVideoGameData(String id, String title, String platform, String releaseYear,
			String genre, String publisher, String copiesSold) {
		List<List<String>> logs = ValidationUtils.validateProductData(id, title, releaseYear, "product");
		List<String> errors = logs.get(1);
		ValidationUtils.addTextWarning(logs.get(0), "video game platform", platform);
		ValidationUtils.addTextWarning(logs.get(0), "video game genre", genre);
		ValidationUtils.addTextWarning(logs.get(0), "video game publisher", publisher);
		if (copiesSold == null || !copiesSold.matches("\\d+(\\.\\d+)?")) {
			errors.add("Invalid video game copies sold");
		}
		return logs;
	}
}
