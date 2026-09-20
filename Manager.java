import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Manager {
    private ArrayList<productData> products;

    public Manager() {
        products = new ArrayList<>();
    }

    public boolean readFile(String fileName)
    {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) {
                    break;
                }
                String[] data = line.split(",");
                String type = data[1];
                if (type.equals("Movie")) {
                    products.add(new Movies(Integer.parseInt(data[0]), data[2], data[3], data[4], Integer.parseInt(data[5]), data[6], Integer.parseInt(data[7]), data[8]));
                } else if (type.equals("TV Show")) {
                    String[] temp = data[7].split(" ");
                    products.add(new TVShows(Integer.parseInt(data[0]), data[2], data[3], data[4], Integer.parseInt(data[5]), data[6], Integer.parseInt(temp[0]), data[8]));
                } else if (type.equals("Music Album")) {
                    products.add(new MusicAlbums(Integer.parseInt(data[0]), Integer.parseInt(data[2]), data[3], data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), Double.parseDouble(data[7]), data[8]));
                } else if (type.equals("Video Game")) {
                    products.add(new VideoGames(Integer.parseInt(data[0]), data[2], data[3], Integer.parseInt(data[4]), data[5], data[6], Double.parseDouble(data[7])));
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
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

    public productData getOldestProduct()
    {
        int oldest=Integer.MAX_VALUE;
        int temp=0;
        int index=0;
        for (productData product : products)
        {
            if(product.getReleaseYear()<oldest)
            {
                oldest=product.getReleaseYear();
                index=temp;
            }
            temp++;
        }
        return products.get(index);
    }    

    public productData getMostPopularMusicAlbum()
    {
        int temp=Integer.MIN_VALUE;
        int count=0;
        int index=0;
        for (productData product : products)
        {
            if (product instanceof MusicAlbums)
                if (((MusicAlbums) product).getGlobalSales()>temp)
                {
                    temp=((MusicAlbums) product).getGlobalSales();
                    index=count;
                }
            count++;
        }
        return products.get(index);
    }

    public productData getPopularVideoGame()
    {
        double temp=Double.MIN_VALUE;
        int count=0;
        int index=0;
        for (productData product : products)
        {
            if (product instanceof VideoGames)
                if (((VideoGames) product).getCopiesSold()>temp)
                {
                    temp=((VideoGames) product).getCopiesSold();
                    index=count;
                }
            count++;
        }
        return products.get(index);
    }

    public String getCommonRating()
    {
        HashMap <String, Integer> list = new HashMap<>();
        int MAX_VALUE=Integer.MIN_VALUE;
        String commonRating="";
        for (productData product : products)
        {
            if (product instanceof digitalMediaData)
            {
                String temp = ((digitalMediaData) product).getRating();
                if(list.get(temp)==null)
                    list.put(temp, 0);
                else
                {
                    list.put(temp, list.get(temp)+1);
                    if(MAX_VALUE<list.get(temp))
                    {
                        MAX_VALUE=list.get(temp);
                        commonRating=temp;
                    }
                }

            }
        
        }
        return commonRating;
    }

    public productData getShortestMovie()
    {
        double temp=Double.MAX_VALUE;
        int count=0;
        int index=0;
        for (productData product : products)
        {
            if (product instanceof Movies)
                if (((Movies) product).getDuration()<temp)
                {
                    temp=((Movies) product).getDuration();
                    index=count;
                }
            count++;
        }
        return products.get(index);
    }

    public productData getShortestAlbum()
    {
        double temp=Double.MAX_VALUE;
        int count=0;
        int index=0;
        for (productData product : products)
        {
            if (product instanceof MusicAlbums)
                if (((MusicAlbums) product).getNumTracks()<temp)
                {
                    temp=((MusicAlbums) product).getNumTracks();
                    index=count;
                }
            count++;
        }
        return products.get(index);
    }
}
