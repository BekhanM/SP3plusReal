import java.io.File;
import java.util.List;
import java.util.Scanner;

public interface IO {

    List<String> readUserData();

    void saveUserData(List<User> users);

    List<String> readMovieData();

    List<String> readSeriesData();

}
