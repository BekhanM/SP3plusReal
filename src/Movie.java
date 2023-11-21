import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Movie extends Media
{
    private List<Movie>movieList = new ArrayList<>();

    public Movie(String title,String releaseDate, String genre, double rating) // Tar constructor fra Media
    {
        /*super(title, genre, rating, releaseDate);*/
        super(title,releaseDate,genre,rating);
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

    public String getReleaseDate()
    {
        //---------------bruge movieSeparator/List<Movie>movieList og bare tag fat i row 1 ???--------------
        return releaseDate;
    }

    @Override
    public String toString() // Bruger super toString fra Media
    {
        return super.toString();
    }


    public List<Movie> movieSeparator() {
        List<String> data = io.readMovieData();
        for (String s : data) {
            String[] row = s.split(";");
            String title = row[0].trim();
            String releaseDate = row[1].trim();
            String genre = row[2].trim();
            double rating = Double.parseDouble(row[3].replace(",",".").trim());// bruger replace so det kan skrives som double

            registerMovies(title, releaseDate, genre, rating);
        }
        return movieList;
    }

    private void registerMovies(String title, String releaseDate,String genre, double rating) {
        Movie movie=new Movie(title,releaseDate,genre,rating);
        movieList.add(movie);

    }

}
