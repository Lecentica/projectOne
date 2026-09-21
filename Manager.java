import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.regex.Pattern;

public class Manager {
    final Pattern HAS_LETTER = Pattern.compile("[a-zA-Z]");
    private ArrayList<productData> products;

    private boolean isValidIntegerValue(String value) {
        if (value == null || !value.matches("\\d+")) {
            return false;
        }
        try {
            return Long.parseLong(value) <= Integer.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Manager() {
        products = new ArrayList<>();
    }

    private List<String> validateProductData(String id, String title, String releaseYear) {
        List<String> errors = new ArrayList<>();
        if (!isValidIntegerValue(id)) {
            errors.add("Invalid product ID");
        }
        if (title == null || title.isBlank()) {
            errors.add("Invalid product title");
        }
        if (!isValidIntegerValue(releaseYear)) {
            errors.add("Invalid product release year");
        }
        return errors;
    }

    private List<String> validateDigitalMediaData(String id, String title, String releaseYear, String director,
            String country, String description) {
        List<String> errors = new ArrayList<>();
        if (!isValidIntegerValue(id)) {
            errors.add("Invalid digital media ID");
        }
        if (title == null || title.isBlank()) {
            errors.add("Invalid digital media title");
        }
        if (!isValidIntegerValue(releaseYear)) {
            errors.add("Invalid digital media release year");
        }
        if (director == null || !HAS_LETTER.matcher(director).find()) {
            errors.add("Invalid digital media director");
        }
        if (country == null || !HAS_LETTER.matcher(country).find()) {
            errors.add("Invalid digital media country");
        }
        if (description == null || !HAS_LETTER.matcher(description).find()) {
            errors.add("Invalid digital media description");
        }
        return errors;
    }

    public String readFile(String fileName) {
        StringBuilder logs = new StringBuilder();
        products.clear();
        int linesSkipped = 0;
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int lineNumber = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                
                if(data.length < 2) {
                    logs.append("\tLine ").append(lineNumber).append(": Invalid number of fields ")
                            .append(data.length).append("\n");
                    linesSkipped++;
                    continue;
                }
                String type = data[1];
                if (type.equals("Movie")) {
                    boolean isValid = true;
                    if (data.length != 9) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid number of fields ")
                                .append(data.length).append("\n");
                        isValid = false;
                        linesSkipped++;
                        continue;
                    }
                    List<String> digitalMediaValidation = validateDigitalMediaData(data[0], data[2], data[5], data[3],
                            data[4], data[8]);
                    for (String error : digitalMediaValidation) {
                        logs.append("\tLine ").append(lineNumber).append(": ").append(error).append("\n");
                        isValid = false;
                    }
                    if (!Movies.MOVIE_RATINGS.contains(data[6].toUpperCase())) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid movie rating\n");
                        isValid = false;
                    }
                    if (!data[7].matches("\\d+")) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid movie duration\n");
                        isValid = false;
                    }
                    if (isValid)
                        products.add(new Movies(Integer.parseInt(data[0]), data[2], data[3], data[4], Integer.parseInt(data[5]), data[6], Integer.parseInt(data[7]), data[8]));
                    else
                        linesSkipped++;
                } else if (type.equals("TV Show")) {
                    boolean isValid = true;
                    if (data.length != 9) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid number of fields ")
                                .append(data.length).append("\n");
                        isValid = false;
                        linesSkipped++;
                        continue;
                    }
                    List<String> digitalMediaValidation = validateDigitalMediaData(data[0], data[2], data[5], data[3],
                            data[4], data[8]);
                    for (String error : digitalMediaValidation) {
                        logs.append("\tLine ").append(lineNumber).append(": ").append(error).append("\n");
                        isValid = false;
                    }
                    if (!TVShows.TV_SHOW_RATINGS.contains(data[6].toUpperCase())) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid TV show rating\n");
                        isValid = false;
                    }
                    int seasons = -1;
                    if (!data[7].matches("\\d+ [a-zA-Z]+")) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid number of seasons\n");
                        isValid = false;
                    }
                    else
                    {
                        String[] temp = data[7].split(" ");
                        seasons = Integer.parseInt(temp[0]);
                    }
                    if (isValid)
                        products.add(new TVShows(Integer.parseInt(data[0]), data[2], data[3], data[4],
                                Integer.parseInt(data[5]), data[6], seasons, data[8]));
                    else
                        linesSkipped++;
                } else if (type.equals("Music Album")) {
                    boolean isValid = true;
                    if (data.length != 9) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid number of fields ")
                                .append(data.length).append("\n");
                        isValid = false;
                        linesSkipped++;
                        continue;
                    }
                    List<String> productValidation = validateProductData(data[0], data[4], data[2]);
                    for (String error : productValidation) {
                        logs.append("\tLine ").append(lineNumber).append(": ").append(error).append("\n");
                        isValid = false;
                    }
                    if (!HAS_LETTER.matcher(data[3]).find()) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid music album artist\n");
                        isValid = false;
                    }
                    if (!data[6].matches("\\d+")) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid music album tracklist\n");
                        isValid = false;
                    }
                    if (!isValidIntegerValue(data[5])) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid music album global sales\n");
                        isValid = false;
                    }
                    if (!data[7].matches("\\d+(\\.\\d+)?")) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid music album duration\n");
                        isValid = false;
                    }
                    if (!HAS_LETTER.matcher(data[8]).find()) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid music album genre\n");
                        isValid = false;
                    }
                    if (isValid)
                        products.add(new MusicAlbums(Integer.parseInt(data[0]), Integer.parseInt(data[2]), data[3],
                                data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), Double.parseDouble(data[7]), data[8]));
                    else
                        linesSkipped++;
                } else if (type.equals("Video Game")) {
                    boolean isValid = true;
                    if (data.length != 8) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid number of fields ")
                                .append(data.length).append("\n");
                        isValid = false;
                        linesSkipped++;
                        continue;
                    }
                    List<String> productValidation = validateProductData(data[0], data[2], data[4]);
                    for (String error : productValidation) {
                        logs.append("\tLine ").append(lineNumber).append(": ").append(error).append("\n");
                        isValid = false;
                    }
                    if (!HAS_LETTER.matcher(data[3]).find()) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid video game platform\n");
                        isValid = false;
                    }
                    if (!HAS_LETTER.matcher(data[5]).find()) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid video game genre\n");
                        isValid = false;
                    }
                    if (!HAS_LETTER.matcher(data[6]).find()) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid video game publisher\n");
                        isValid = false;
                    }
                    if (!data[7].matches("\\d+(\\.\\d+)?")) {
                        logs.append("\tLine ").append(lineNumber).append(": Invalid video game copies sold\n");
                        isValid = false;
                    }
                    if (isValid)
                        products.add(new VideoGames(Integer.parseInt(data[0]), data[2], data[3],
                                Integer.parseInt(data[4]), data[5], data[6], Double.parseDouble(data[7])));
                    else
                        linesSkipped++;
                }
                else {
                    logs.append("\tLine ").append(lineNumber).append(": Invalid product type\n");
                    linesSkipped++;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            logs.append("\tFile not found: ").append(fileName).append("\n");
            return logs.toString();
        }
        StringBuilder temp = new StringBuilder();
        temp.append("Loaded ").append(products.size()).append(" records.\n\tSkipped ").append(linesSkipped).append(" malformed rows:\n");
        temp.append(logs);
        return temp.toString();
    }

    public int getTotalProducts() {
        return products.size();
    }

    public int getTotalMovies() {
        return countProducts(Movies.class);
    }

    public int getTotalTVShows() {
        return countProducts(TVShows.class);
    }

    public int getTotalVideoGames() {
        return countProducts(VideoGames.class);
    }

    public int getTotalMusicAlbums() {
        return countProducts(MusicAlbums.class);
    }

    private int countProducts(Class<?> productType) {
        int count = 0;
        for (productData product : products) {
            if (productType.isInstance(product)) {
                count++;
            }
        }
        return count;
    }

    public productData getOldestProduct() {
        int oldest = Integer.MAX_VALUE;
        int temp = 0;
        int index = 0;
        for (productData product : products) {
            if (product.getReleaseYear() < oldest) {
                oldest = product.getReleaseYear();
                index = temp;
            }
            temp++;
        }
        return products.get(index);
    }

    public productData getMostPopularMusicAlbum() {
        int temp = Integer.MIN_VALUE;
        int count = 0;
        int index = 0;
        for (productData product : products) {
            if (product instanceof MusicAlbums)
                if (((MusicAlbums) product).getGlobalSales() > temp) {
                    temp = ((MusicAlbums) product).getGlobalSales();
                    index = count;
                }
            count++;
        }
        return products.get(index);
    }

    public productData getPopularVideoGame() {
        double temp = Double.NEGATIVE_INFINITY;
        int count = 0;
        int index = 0;
        for (productData product : products) {
            if (product instanceof VideoGames)
                if (((VideoGames) product).getCopiesSold() > temp) {
                    temp = ((VideoGames) product).getCopiesSold();
                    index = count;
                }
            count++;
        }
        return products.get(index);
    }

    public List<String> getCommonRatings() {
        Map<String, Integer> counts = new HashMap<>();

        for (productData product : products) {
            if (product instanceof digitalMediaData media) {
                String rating = media.getRating();
                if (rating == null || rating.isBlank())
                    continue;
                counts.merge(rating, 1, Integer::sum);
            }
        }

        List<String> mostCommon = new ArrayList<>();
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommon.clear();
                mostCommon.add(entry.getKey());
            } else if (entry.getValue() == maxCount) {
                mostCommon.add(entry.getKey());
            }
        }

        Collections.sort(mostCommon);
        return mostCommon;
    }

    public productData getShortestMovie() {
        double temp = Double.MAX_VALUE;
        int count = 0;
        int index = 0;
        for (productData product : products) {
            if (product instanceof Movies)
                if (((Movies) product).getDuration() < temp) {
                    temp = ((Movies) product).getDuration();
                    index = count;
                }
            count++;
        }
        return products.get(index);
    }

    public productData getShortestAlbumDuration() {
        double temp = Double.MAX_VALUE;
        int count = 0;
        int index = 0;
        for (productData product : products) {
            if (product instanceof MusicAlbums)
                if (((MusicAlbums) product).getDuration() < temp) {
                    temp = ((MusicAlbums) product).getDuration();
                    index = count;
                }
            count++;
        }
        return products.get(index);
    }
}
