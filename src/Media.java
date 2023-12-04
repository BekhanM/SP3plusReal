public abstract class Media
{
    protected String title; // Måske ikke protected kan lige se på det senere
    protected String genre;
    protected double rating;
    protected String releaseDate;
    FileIO io = new FileIO();

    public Media(String title,String releaseDate, String genre, double rating) // Super constructor til Movie og Series
    {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() // Super toString som skal bruges i Movie og Series
    {
        return "Title: " + title + " Genre: " + genre + " Rating: " + rating + " Release Date: " + releaseDate;
    }
}


