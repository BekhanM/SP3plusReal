public class Movie extends Media
{
    public Movie(String title, String genre, double rating, int releaseDate) // Tar constructor fra Media
    {
        super(title, genre, rating, releaseDate);
    }

    // Laver getters igen
    public String getTitle()
    {
        return title;
    }
    public String getGenre()
    {
        return genre;
    }

    public double getRating()
    {
        return rating;
    }

    public int getReleaseDate()
    {
        return releaseDate;
    }

    @Override
    public String toString() // Bruger super toString fra Media
    {
        return super.toString();
    }
}
