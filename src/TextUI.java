import java.util.Scanner;

public class TextUI {
    private Scanner scan = new Scanner(System.in);

    public String getInput(String msg) {
        this.displayMessage(msg);
        return scan.nextLine();
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}
