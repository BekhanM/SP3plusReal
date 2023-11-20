import java.io.File;
import java.util.List;
import java.util.Scanner;

public interface IO {





    List<User> readUserData(String path);
    void saveUserData(List<User>users);
    List<String>readMovieData(String path, int length);
    List<String>readSeriesData(String path, int length);

}
