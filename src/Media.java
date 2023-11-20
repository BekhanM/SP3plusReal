public abstract class Media
{
    protected String title;
    protected String genre;
    protected double rating;
    protected int releaseDate;

    public Media(String title,String genre,double rating, int releaseDate)
    {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public String getTitle()
    {
        return title;
    }
    public String getGenre()
    {
        return genre;
    }
}
