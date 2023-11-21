import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

/*
        //----------------Tænker om at constructor ikke er nødvendigt for Media og subklasserne--------
        //-----------Printer listen af movies i en pen format------------
        Movie movie = new Movie("dasd",132,"bingchillin",6.2);
        List<Movie> movies = movie.movieSeparator();
        for (Movie m : movies) {
            System.out.println(m);
        }*/
        //-----------Printer listen af series i en pen format------------
        Series series = new Series("dasd","132","bingchillin",6.2,"12","12");
        List<Series> serie = series.seriesSeparator();
        for (Series s : serie) {
            System.out.println(s);
        }
    }
}
