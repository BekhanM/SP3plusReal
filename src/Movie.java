public class Movie extends Media
{
    public Movie(String title, String genre, double rating, int releaseDate)
    {
        super(title, genre, rating, releaseDate);
    }

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
    public String toString()
    {
        return super.toString();
    }
}
