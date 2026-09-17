import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Manager {
    private ArrayList<productData> products;

    public Manager() {
        products = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File("project1dataset.csv"));
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                String type = data[1];
                if (type.equals("Movie")) {
                    products.add(new Movies(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), data[6], Integer.parseInt(data[7]), data[8]));
                } else if (type.equals("TV Show")) {
                    products.add(new TVShows(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), data[6], Integer.parseInt(data[7]), data[8]));
                } else if (type.equals("Music Album")) {
                    products.add(new MusicAlbums(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[5]), data[2], data[3], Integer.parseInt(data[6]), Integer.parseInt(data[7]), Integer.parseInt(data[8]), data[9]));
                } else if (type.equals("Video Game")) {
                    products.add(new VideoGames(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[5]), data[6], data[7], Integer.parseInt(data[8])));
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: project1dataset.csv file not found.");
        }
    }

    public int getTotalProducts() {
        return products.size();
    }

    public int getTotalMovies() {
        int count = 0;
        for (productData product : products) {
            if (product instanceof Movies) {
                count++;
            }
        }
        return count;
    }

    public int getTotalTVShows() {
        int count = 0;
        for (productData product : products) {
            if (product instanceof TVShows) {
                count++;
            }
        }
        return count;
    }

    public int getTotalVideoGames() {
        int count = 0;
        for (productData product : products) {
            if (product instanceof VideoGames) {
                count++;
            }
        }
        return count;
    }

    public int getTotalMusicAlbums() {
        int count = 0;
        for (productData product : products) {
            if (product instanceof MusicAlbums) {
                count++;
            }
        }
        return count;
    }
}
