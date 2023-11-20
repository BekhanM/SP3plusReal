public class Series extends Media {

    private int season;
    private int episode;

    public Series(String title, String genre, double rating, int releaseDate, int season, int episode) {
        super(title, genre, rating, releaseDate);
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }

    @Override
    public String toString() {
        return "Series{" +
                "season=" + season +
                ", episode=" + episode +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
