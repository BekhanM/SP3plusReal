public abstract class Media
{
    protected String title; // Måske ikke protected kan lige se på det senere
    protected String genre;
    protected double rating;
    protected int releaseDate;

    public Media(String title,String genre,double rating, int releaseDate) // Super constructor til Movie og Series
    {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    // Laver getters til klassen
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
    public String toString() // Super toString som skal bruges i Movie og Series
    {
        return "Title: " + title + " Genre: " + genre + " Rating: " + rating + " Release Date: " + releaseDate;
    }
}
