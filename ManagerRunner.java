import java.util.Scanner;

public class ManagerRunner {
    public static void main(String[] args) {
        boolean loop = true;
        Manager manager = new Manager();
        Scanner input = new Scanner(System.in);
        while(loop)
        {
            System.out.print("Welcome to the Product Manager! \nPlease enter the name of the CSV file you would like to load (e.g. project1dataset.csv): ");
            String fileName = input.nextLine().trim();
            if(manager.readFile(fileName))
            {
                System.out.println("File loaded successfully!");
                loop = false;   
            }
            else
            {
                System.out.println("File not found. Please try again.");
            }
        
        }   
        boolean mainLoop = true;
        boolean pageSelection = true;
        while(mainLoop)
        {
            if(pageSelection)
                System.out.println("Please select an option, select a non option to switch pages: \n 1. Display total number of products \n 2. Display total number of movies \n 3. Display total number of TV shows \n 4. Display total number of music albums \n 5. Display total number of video games \n 6. Exit                    p1/2");
            else
                System.out.println("Please select an option, select a non option to switch pages: \n 1. Display oldest product (by release_year) \n 2. Display most popular Music Album \n 3. Display most popular Video Game \n 4. Display most common age rating among all film products \n 5. Display shortest Movie \n 6. Display shortest Music Album \n 7. Exit                   p2/2");
            System.out.print("Please enter your selection: ");
            String temp = input.nextLine().trim();
            if(pageSelection)
            {
                switch(temp)
                {
                    case "1":
                        System.out.println("Total number of products: " + manager.getTotalProducts());
                        break;
                    case "2":
                        System.out.println("Total number of movies: " + manager.getTotalMovies());
                        break;
                    case "3":
                        System.out.println("Total number of TV shows: " + manager.getTotalTVShows());
                        break;
                    case "4":
                        System.out.println("Total number of music albums: " + manager.getTotalMusicAlbums());
                        break;
                    case "5":
                        System.out.println("Total number of video games: " + manager.getTotalVideoGames());
                        break;
                    case "6":
                        mainLoop = false;
                        break;
                    default:
                        pageSelection = false;
                }
            }
            else
            {
                switch(temp)
                {
                    case "1":
                        System.out.println("Oldest product: " + manager.getOldestProduct().getTitle() + ", released in " + manager.getOldestProduct().getReleaseYear());
                        break;
                    case "2":
                        System.out.println("Most popular Music Album: " + manager.getMostPopularMusicAlbum().getTitle() + ", with global sales of " + ((MusicAlbums) manager.getMostPopularMusicAlbum()).getGlobalSales() + " copies");
                        break;
                    case "3":
                        System.out.println("Most popular Video Game: " + manager.getPopularVideoGame().getTitle() + ", with copies sold of " + ((VideoGames) manager.getPopularVideoGame()).getCopiesSold() + " million copies");
                        break;
                    case "4":
                        System.out.println("Most common age rating among all film products: " + manager.getCommonRating());
                        break;
                    case "5":
                        System.out.println("Shortest Movie: " + manager.getShortestMovie().getTitle() + ", with a duration of " + ((Movies) manager.getShortestMovie()).getDuration() + " minutes");
                        break;
                    case "6":
                        System.out.println("Shortest Music Album: " + ((MusicAlbums) manager.getShortestAlbum()).getTitle() + ", with a duration of " + ((MusicAlbums) manager.getShortestAlbum()).getDuration() + " minutes");
                        break;
                    case "7":
                        mainLoop = false;
                        break;
                    default:
                        pageSelection = true;
                }
            }
            System.out.println();
        }
        input.close();
    }
}
