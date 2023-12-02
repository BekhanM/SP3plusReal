import java.util.ArrayList;

public class StreamingService {
    private String userInputUsername;
    private String userInputPassword;
    private FileIO io = new FileIO();
    private ArrayList<String> userData = io.readUserData();
    private DataValidator dataValidator = new DataValidator();
    private TextUI ui = new TextUI();
    private DataBaseIO db = new DataBaseIO();

    public void startMenu() {
        ui.displayMessage("Hej og velkommen til landets streamingstjeneste, bro!");
        String i = ui.getInput("Vælg en funktion, bro:" + "\n1) Logge ind." + "\n2) Lave en ny bruger.");
        if (i.equals("1")) {
            login();
        } else if (i.equals("2")) {
            addUser();
        } else {
            ui.displayMessage("Forkert valg. Vælg funktion 1 eller 2.");
            startMenu();
        }
    }

    public void mainMenu() {
        String i = ui.getInput("Du har følgende valgmuligheder:" +
                "\n1) Vis listen over alle film" +
                "\n2) Vis listen over alle serier" +
                "\n3) Søge efter en bestemt film" +
                "\n4) Søge alle film i en kategori" +
                "\n5) Se din liste over sete film" +
                "\n6) Se din liste over gemte film" +
                "\n7) Slet dit brugerdata fra systemet :(" +
                "\n8) Log ud");
        if (i.equals("1")) {
            db.showMovieDatabase();
            searchByName();
        }
        if (i.equals("2")) {
            db.showSeriesDatabase();
            searchByName();
        }
        if (i.equals("3")) {
            searchByName();
        }
        if (i.equals("4")) {
            db.searchGenreDatabase();
            searchByName();
        }
        if (i.equals("5")) {
            displayWatchedList();
        }
        if (i.equals("6")) {
            displayMyList();
        }
        if (i.equals("7")) {
            removeUser(userInputUsername, userInputPassword);
        }
        if (i.equals("8")) {
            logout();
        }
    }

    public void login() {
        userInputUsername = ui.getInput("Brugernavn: ");
        userInputPassword = ui.getInput("Password: ");

        User authenticatedUser = db.getAuthenticatedUser(userInputUsername, userInputPassword);

        if (authenticatedUser != null && authenticatedUser.getUsername().equals(userInputUsername) && authenticatedUser.getPassword().equals(userInputPassword)) {
            mainMenu();
        } else {
            login();
        }
    }

    public void logout() {
        String i = ui.getInput("Er du sikker du vil logge ud, bro?\nTast 1 hvis du gerne vil logge ud:\nTast 2 hvis du ikke vil logge ud:");
        if (i.equals("1")) {
            startMenu();
        } else if (i.equals("2")) {
            mainMenu();
        } else {
            ui.displayMessage("Forkert valg. Vælg funktion 1 eller 2.");
            logout();
        }
    }

    public void addUser() {
        String userInputUsername = ui.getInput("Indtast et nyt brugernavn: ");
        if (dataValidator.checkRegisterUsernamePassword(userData, userInputUsername)) {
            String userInputPassword = ui.getInput("Indsæt ønskede kodeord\nKodeord skal minimum være 8 karakterer, skal have et tal og et stort bogstav");
            dataValidator.validatePassword(userInputPassword);
            String userInputPassword2 = ui.getInput("Gentag kodeord");
            if (userInputPassword.equals(userInputPassword2)) {
                registerUser(userInputUsername, userInputPassword);
                ui.displayMessage("Du er nu registreret som bruger: " + userInputUsername);
                startMenu();
            }
        } else {
            addUser();
        }
    }

    public void registerUser(String username, String password) {
        db.saveUserData(username, password);
    }


    public void removeUser(String username, String password) {
        String i = ui.getInput("Er du sikker du vil fjerne din konto, bro?" +
                "\n1) Hvis du gerne vil slette din konto:" +
                "\n2) Hvis du ikke vil slette din konto:");

        if (i.equals("1")) {
            db.removeUserData(username, password);

            userInputUsername = null;
            userInputPassword = null;

            ui.displayMessage("Din konto er blevet fjernet. Tak for besøget!");

            startMenu();
        } else if (i.equals("2")) {
            mainMenu();
        } else {
            ui.displayMessage("Forkert valg. Vælg funktion 1 eller 2.");
            removeUser(username, password);
        }
    }


    public void searchByName() {
        // Call the search method
        int userChoice = db.searchMediaContentDatabase();

        // Process user choice
        switch (userChoice) {
            case 1:
                mediaOptions();
                break;
            case 2:
                mediaNotFoundOptions();
                break;
            default:
                ui.displayMessage("Er du fuld?");
                searchByName();
                break;
        }
    }

    public void displayWatchedList() {

    }

    public void displayMyList() {

    }

    public void mediaOptions() {
        String userInput = ui.getInput("Hvad vil du gør nu så, bro?:" + "\n1) Se filmen" + "\n2) Tilføje til min liste, så jeg kan se den bagefter" + "\n3) Tilbage til menuen");

        if (userInput.equals("1")) {
            ui.displayMessage("*Afspiller*");
        }
        if (userInput.equals("2")) {

        }
        if (userInput.equals("3")) {
            mainMenu();
        }
    }

    public void mediaNotFoundOptions() {
        String input = ui.getInput("Ubeslutsom type." + "\n1) Hvis du h" +
                "ar du brug for en hånd, kan du se hvad vi har" + "\n2) Søg igen" + "\n3) Tilbage til menuen");
        if (input.equalsIgnoreCase("1")) {
            db.showMovieDatabase();
            db.showSeriesDatabase();
            searchByName();
        } else if (input.equalsIgnoreCase("2")) {
            searchByName();
        } else if (input.equalsIgnoreCase("3")) {
            mainMenu();
        }
    }
}