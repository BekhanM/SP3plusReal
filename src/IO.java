import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface IO {

    public ArrayList<String> readUserData();

    public void saveUserData(ArrayList<User> users);

    public ArrayList<String> readMovieData();

    public ArrayList<String> readSeriesData();

}
