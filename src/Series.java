import java.util.ArrayList;
import java.util.List;

public class Series extends Media {
    private List<Series>seriesList = new ArrayList<>(); // skal bruges til separationsmetoden
    private String season;
    private String episode;

    public Series(String title,String releaseDate, String genre, double rating, String season, String episode) {
        super(title,releaseDate,genre,rating);
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getSeason() {
        return season;
    }

    public String getEpisode() {
        return episode;
    }

    @Override
    public String toString()
    {
        return super.toString() + " Season: " + getSeason() + "Episode: " + getEpisode();
    }

    //Problemer med release date. Det skal være en String, eller skal constructor indeholde releaseDateStart og End
    public List<Series> seriesSeparator() {
        List<String> data = io.readSeriesData();
        for (String s : data) {
            String[] row = s.split(";");
            String title = row[0].trim();
            String releaseDate = row[1].trim();
            String genre = row[2].trim();
            double rating = Double.parseDouble(row[3].replace(",",".").trim()); // bruger replace so det kan skrives som double
            String season = row[4].trim();
            String episode = row[5].trim();
            registerSeries(title, releaseDate, genre, rating,season,episode);
        }
        return seriesList;
    }
    private void registerSeries(String title,String releaseDate, String genre, double rating, String season, String episode) {
        Series series=new Series(title,releaseDate,genre,rating,season,episode);
        seriesList.add(series);

    }

}
