import java.util.ArrayList;
import java.util.Scanner;

public class DataValidator {

    // Tjekker om en String (password) indeholder minimum et stort bogstav
    public boolean checkUpperCase(String str) {
        char c;
        boolean upperCaseFlag = false;
        boolean lowerCaseFlag = false;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                upperCaseFlag = true;
            } else if (Character.isLowerCase(c)) {
                lowerCaseFlag = true;
            }
            if (upperCaseFlag && lowerCaseFlag)
                return true;
        }
        System.out.println("Password must contain at least one uppercase letter");
        return false;
    }

    // tjekker længden er minimum på 8 characters i en String (password)
    public boolean checkLength(String str) {

        if (str.length() < 129 && str.length() > 7) {
            return true;
        } else {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }
    }

    // tjekker om en String (password) indeholder et tal
    public boolean checkNumeric(String str) {
        char c;
        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }

        if (!numberFlag) {
            System.out.println("Password must contain at least one number");
        }
        return false;
    }

    public boolean validatePassword(String password) {
        boolean i = checkNumeric(password);
        boolean j = checkLength(password);
        boolean k = checkUpperCase(password);
        if (i && j && k) {
            return true;
        } else {
            return false;
        }
    }
}

