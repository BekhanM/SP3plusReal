import java.util.ArrayList;

public class StreamingService {
    private final FileIO io = new FileIO();
    private final ArrayList<String> userData = io.readUserData();
    private final DataValidator dataValidator = new DataValidator();
    private final TextUI ui = new TextUI();
    private final DataBaseIO db = new DataBaseIO();
    private String userInputUsername;
    private String userInputPassword;

    public void startMenu() {
        ui.displayMessage("Hej og velkommen til landets streamingstjeneste, bro!");
        String i = ui.getInput("""
                Vælg en funktion, bro:
                1) Logge ind.
                2) Lave en ny bruger.""");
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
        String i = ui.getInput("""
                Du har følgende valgmuligheder:
                1) Vis listen over alle film
                2) Vis listen over alle serier
                3) Søge efter en bestemt film
                4) Søge alle film i en kategori
                5) Se din liste over sete film
                6) Se din liste over gemte film
                7) Slet dit brugerdata fra systemet :(
                8) Log ud""");
        switch (i) {
            case "1":
                displayMovies();
                searchByName();
                break;
            case "2":
                displaySeries();
                searchByName();
                break;
            case "3":
                searchByName();
                break;
            case "4":
                db.searchGenreDatabase();
                searchByName();
                break;
            case "5":
                displayWatchedList();
                break;
            case "6":
                displayMyList();
                break;
            case "7":
                removeUser(userInputUsername, userInputPassword);
                break;
            case "8":
                logout();
                break;
            default:
                ui.displayMessage("Hvad fanden laver du?");
                mainMenu();
                break;
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
        String i = ui.getInput("""
                Er du sikker du vil fjerne din konto, bro?
                1) Hvis du gerne vil slette din konto:
                2) Hvis du ikke vil slette din konto:""");

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

    public void displayMovies() {
        db.showMovieDatabase();
    }

    public void displaySeries() {
        db.showSeriesDatabase();
    }

    public void displayWatchedList() {
        db.displayWatchedList();
    }

    public void addToWatchedList() {
        //db.addToWatchedList();
    }

    public void displayMyList() {

    }

    public void mediaOptions() {
        String userInput = ui.getInput("""
                Hvad vil du så gør nu, bro?:
                1) Se filmen
                2) Tilføje til min liste, så jeg kan se den bagefter
                3) Tilbage til menuen""");

        if (userInput.equals("1")) {
            ui.displayMessage("*Afspiller*");
            addToWatchedList();
        }
        if (userInput.equals("2")) {
            displayMyList();
        }
        if (userInput.equals("3")) {
            mainMenu();
        }
    }

    public void mediaNotFoundOptions() {
        String input = ui.getInput("""
                Ubeslutsom type.
                1) Hvis du har du brug for en hånd, kan du se hvad vi har
                2) Søg igen
                3) Tilbage til menuen""");
        if (input.equalsIgnoreCase("1")) {
            displayMovies();
            displaySeries();
            searchByName();
        } else if (input.equalsIgnoreCase("2")) {
            searchByName();
        } else if (input.equalsIgnoreCase("3")) {
            mainMenu();
        }
    }
}