

import java.util.Scanner;

public class ManagerRunner {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Welcome to the Product Manager! \nPlease enter the name of the CSV file you would like to load (e.g. project1dataset.csv): ");
            String fileName = input.nextLine().trim();
            String result = manager.readFile(fileName);
            System.out.println(result);
            if (!result.contains("File not found")) {
                break;
            }
        }

        System.out.println("Total number of products: " + manager.getTotalProducts());
        System.out.println("Total number of movies: " + manager.getTotalMovies());
        System.out.println("Total number of TV shows: " + manager.getTotalTVShows());
        System.out.println("Total number of video games: " + manager.getTotalVideoGames());
        System.out.println("Total number of music albums: " + manager.getTotalMusicAlbums());

        ProductData oldestProduct = manager.getOldestProduct();
        System.out.println("Oldest product: " + oldestProduct.getTitle() + ", released in " + oldestProduct.getReleaseYear());

        MusicAlbums popularAlbum = (MusicAlbums) manager.getMostPopularMusicAlbum();
        System.out.println("Most popular Music Album: " + popularAlbum.getTitle() + ", with global sales of " + popularAlbum.getGlobalSales() + " copies");

        VideoGames popularGame = (VideoGames) manager.getPopularVideoGame();
        System.out.println("Most popular Video Game: " + popularGame.getTitle() + ", with copies sold of " + popularGame.getCopiesSold() + " million copies");

        System.out.println("Most common age rating among all film products, lists ties: " + manager.getCommonRatings());

        Movies shortestMovie = (Movies) manager.getShortestMovie();
        System.out.println("Shortest Movie: " + shortestMovie.getTitle() + ", with a duration of " + shortestMovie.getDuration() + " minutes");

        MusicAlbums shortestAlbum = (MusicAlbums) manager.getShortestAlbumDuration();
        System.out.println("Shortest Music Album: " + shortestAlbum.getTitle() + ", with a duration of " + shortestAlbum.getDuration() + " minutes");

        input.close();
    }
}
