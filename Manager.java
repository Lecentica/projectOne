import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Manager {
    
    private ArrayList<ProductData> products;

    public Manager() {
        products = new ArrayList<>();
    }

    private boolean appendValidationMessages(List<String> warnings, List<String> errors, int lineNumber, List<List<String>> validation) {
        for (String warning : validation.get(0)) {
            warnings.add("Line " + lineNumber + ": " + warning);
        }
        for (String error : validation.get(1)) {
            errors.add("Line " + lineNumber + ": " + error);
        }
        return validation.get(1).isEmpty();
    }

    private String normalizedRating(String value) {
        String rating = ValidationUtils.substituteUnknown(value);
        return rating.equals("Unknown") ? rating : rating.toUpperCase();
    }

    public String readFile(String fileName) {
        List<String> warnings = new ArrayList<>();
        List<String> errors = new ArrayList<>();
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
                    errors.add("Line " + lineNumber + ": Invalid number of fields " + data.length);
                    linesSkipped++;
                    continue;
                }
                String type = data[1];
                if (type.equals("Movie")) {
                    if (data.length != 9) {
                        errors.add("Line " + lineNumber + ": Invalid number of fields " + data.length);
                        linesSkipped++;
                        continue;
                    }
                        boolean isValid = appendValidationMessages(warnings, errors, lineNumber,
                            Movies.validateMovieData(data[0], data[2], data[5], data[3], data[4], data[6], data[7], data[8]));
                    if (isValid)
                        products.add(new Movies(Integer.parseInt(data[0]), ValidationUtils.substituteUnknown(data[2]),
                            ValidationUtils.substituteUnknown(data[3]), ValidationUtils.substituteUnknown(data[4]),
                            Integer.parseInt(data[5]), normalizedRating(data[6]), Integer.parseInt(data[7]),
                            ValidationUtils.substituteUnknown(data[8])));
                    else
                        linesSkipped++;
                } else if (type.equals("TV Show")) {
                    if (data.length != 9) {
                        errors.add("Line " + lineNumber + ": Invalid number of fields " + data.length);
                        linesSkipped++;
                        continue;
                    }
                        boolean isValid = appendValidationMessages(warnings, errors, lineNumber,
                            TVShows.validateTVShowData(data[0], data[2], data[5], data[3], data[4], data[6], data[7], data[8]));
                    int seasons = TVShows.parseSeasons(data[7]);
                    if (isValid)
                        products.add(new TVShows(Integer.parseInt(data[0]), ValidationUtils.substituteUnknown(data[2]),
                            ValidationUtils.substituteUnknown(data[3]), ValidationUtils.substituteUnknown(data[4]),
                            Integer.parseInt(data[5]), normalizedRating(data[6]), seasons,
                            ValidationUtils.substituteUnknown(data[8])));
                    else
                        linesSkipped++;
                } else if (type.equals("Music Album")) {
                    if (data.length != 9) {
                        errors.add("Line " + lineNumber + ": Invalid number of fields " + data.length);
                        linesSkipped++;
                        continue;
                    }
                        boolean isValid = appendValidationMessages(warnings, errors, lineNumber,
                            MusicAlbums.validateMusicAlbumData(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8]));
                    if (isValid)
                        products.add(new MusicAlbums(Integer.parseInt(data[0]), Integer.parseInt(data[2]),
                            ValidationUtils.substituteUnknown(data[3]), ValidationUtils.substituteUnknown(data[4]),
                            Integer.parseInt(data[5]), Integer.parseInt(data[6]), Double.parseDouble(data[7]),
                            ValidationUtils.substituteUnknown(data[8])));
                    else
                        linesSkipped++;
                } else if (type.equals("Video Game")) {
                    if (data.length != 8) {
                        errors.add("Line " + lineNumber + ": Invalid number of fields " + data.length);
                        linesSkipped++;
                        continue;
                    }
                        boolean isValid = appendValidationMessages(warnings, errors, lineNumber,
                            VideoGames.validateVideoGameData(data[0], data[2], data[3], data[4], data[5], data[6], data[7]));
                    if (isValid)
                        products.add(new VideoGames(Integer.parseInt(data[0]), ValidationUtils.substituteUnknown(data[2]),
                            ValidationUtils.substituteUnknown(data[3]), Integer.parseInt(data[4]),
                            ValidationUtils.substituteUnknown(data[5]), ValidationUtils.substituteUnknown(data[6]),
                            Double.parseDouble(data[7])));
                    else
                        linesSkipped++;
                }
                else {
                    errors.add("Line " + lineNumber + ": Invalid product type");
                    linesSkipped++;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            return "\tFile not found: " + fileName + "\n";
        }
        StringBuilder temp = new StringBuilder();
        temp.append("Loaded ").append(products.size()).append(" records.\n\n");
        if (!warnings.isEmpty()) {
            temp.append("Warnings (").append(warnings.size()).append("):\n");
            for (String warning : warnings) {
                temp.append("  ").append(warning).append("\n");
            }
            temp.append("\n");
        }
        if (!errors.isEmpty()) {
            temp.append("Errors (").append(errors.size()).append("):\n");
            for (String error : errors) {
                temp.append("  ").append(error).append("\n");
            }
            temp.append("\n");
        }
        temp.append("Skipped ").append(linesSkipped).append(" malformed rows.\n");
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
        for (ProductData product : products) {
            if (productType.isInstance(product)) {
                count++;
            }
        }
        return count;
    }

    public ProductData getOldestProduct() {
        int oldest = Integer.MAX_VALUE;
        int temp = 0;
        int index = 0;
        for (ProductData product : products) {
            if (product.getReleaseYear() < oldest) {
                oldest = product.getReleaseYear();
                index = temp;
            }
            temp++;
        }
        return products.get(index);
    }

    public ProductData getMostPopularMusicAlbum() {
        int temp = Integer.MIN_VALUE;
        int count = 0;
        int index = 0;
        for (ProductData product : products) {
            if (product instanceof MusicAlbums)
                if (((MusicAlbums) product).getGlobalSales() > temp) {
                    temp = ((MusicAlbums) product).getGlobalSales();
                    index = count;
                }
            count++;
        }
        if(products.get(index) instanceof MusicAlbums)
            return products.get(index);
        return null;
    }

    public ProductData getPopularVideoGame() {
        double temp = Double.NEGATIVE_INFINITY;
        int count = 0;
        int index = 0;
        for (ProductData product : products) {
            if (product instanceof VideoGames)
                if (((VideoGames) product).getCopiesSold() > temp) {
                    temp = ((VideoGames) product).getCopiesSold();
                    index = count;
                }
            count++;
        }
        if(products.get(index) instanceof VideoGames)
            return products.get(index);
        return null;
    }

    public List<String> getCommonRatings() {
        Map<String, Integer> counts = new HashMap<>();

        for (ProductData product : products) {
            if (product instanceof DigitalMediaData media) {
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

    public ProductData getShortestMovie() {
        double temp = Double.MAX_VALUE;
        int count = 0;
        int index = 0;
        for (ProductData product : products) {
            if (product instanceof Movies)
                if (((Movies) product).getDuration() < temp) {
                    temp = ((Movies) product).getDuration();
                    index = count;
                }
            count++;
        }
        if(products.get(index) instanceof Movies)
            return products.get(index);
        return null;
    }

    public ProductData getShortestAlbumDuration() {
        double temp = Double.MAX_VALUE;
        int count = 0;
        int index = 0;
        for (ProductData product : products) {
            if (product instanceof MusicAlbums)
                if (((MusicAlbums) product).getDuration() < temp) {
                    temp = ((MusicAlbums) product).getDuration();
                    index = count;
                }
            count++;
        }
        if(products.get(index) instanceof MusicAlbums)
            return products.get(index);
        return null;
    }
}
