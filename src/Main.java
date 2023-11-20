import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
FileIO fileData = new FileIO();




        String password = "wsw";

        DataValidator dataValidator = new DataValidator();

        System.out.println(dataValidator.validatePassword(password));

    }
}
