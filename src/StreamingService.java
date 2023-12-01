import java.util.ArrayList;
import java.util.List;

public class StreamingService {
    Movie movie = new Movie("", "", "", 0);
    Series series = new Series("", "", "", 0, "", "");
    MediaContent mediaContent = new MediaContent("", "", "", 0, "", "");
    private String userInputUsername;
    private String userInputPassword;
    User user = new User(userInputUsername, userInputPassword);
    private List<Movie> movies = movie.movieSeparator();
    private List<Series> serie = series.seriesSeparator();
    private List<MediaContent> mediaContents = mediaContent.mediaContentSeparator();
    private List<String> genreList = new ArrayList<>();
    private FileIO io = new FileIO();
    private ArrayList<String> userData = io.readUserData();
    private ArrayList<String> movieData = io.readMovieData();
    private ArrayList<String> seriesData = io.readSeriesData();
    private ArrayList<String> mediaContentData = io.readMediaData();
    private DataValidator dataValidator = new DataValidator();
    private ArrayList<User> users = new ArrayList<>();
    private TextUI ui = new TextUI();
    private List<Content> content;
    private MyList myList = new MyList();
    private DataBaseIO db = new DataBaseIO();

    public void startMenu() {
        ui.displayMessage("Hej og velkommen til landets værste streamingtjeneste");
        String i = ui.getInput("Vælg en funktion:" +
                "\n1) Logge ind." +
                "\n2) Lave en ny bruger.");
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
                "\n7) logout");
        if (i.equals("1")) {
            db.showMovieDatabase();
        }
        if (i.equals("2")) {
            db.showSeriesDatabase();
        }
        if (i.equals("3")) {
            searchByName();
        }
        if (i.equals("4")) {
            displayGenre();
            searchByGenre();
        }
        if (i.equals("5")) {
            displayWatchedList();
        }
        if (i.equals("6")) {
            displayMyList();
        }
        if (i.equals("7")) {
            logout();
        }
    }

    public void login() {
        userInputUsername = ui.getInput("Brugernavn: ");
        userInputPassword = ui.getInput("Password: ");

        User authenticatedUser = db.getAuthenticatedUser(userInputUsername, userInputPassword);

        if (authenticatedUser != null && authenticatedUser.getUsername().equals(userInputUsername)
                && authenticatedUser.getPassword().equals(userInputPassword)) {
            // loginPassword(userInputUsername);
            mainMenu();
        } else {
            login();
        }
    }

    public void logout() {
        String i = ui.getInput("Er du sikker du vil logge ud, bro?\nTast 1 hvis du gerne vil logge ud:\nTast 2 hvis du ikke vil logge ud:");
        if (i.equals("1")) {
            io.saveMyListData(userInputUsername, myList.getMyList());
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
                ui.displayMessage("Du er nu registreret som bruger:");
                startMenu();
            }
        } else {
            addUser();
        }
    }

    public void registerUser(String username, String password) {
        User user = new User(username, password);
        db.saveUserData(username, password);
    }

    /*
    public void removeUser() {
        String i = ui.getInput("Er du sikker du vil fjerne din konto ud, bro?\nTast 1 hvis du gerne vil slette din konto:\nTast 2 hvis du ikke vil slette din konto:");
        if (i.equals("1")) {
            String uname = ui.getInput("Indtast brugernavn: ");
            String pword = ui.getInput("Indtast kodeord: ");
            terminateUser(uname, pword);
        } else if (i.equals("2")) {
            mainMenu();
        }
    }

    public void terminateUser(String username, String password) {
        User userToRemove = new User(username, password);
        for (User currentUser : users) {
            if (currentUser.equals(userToRemove)) {
            }
        }

        for (User user : users) {
            users.remove(user);
            userData.remove(user.toString());
        }
        io.saveUserData(users);
    }
     */

    public void displayGenre() {
        genreList.add("1: Drama");
        genreList.add("2: Crime");
        genreList.add("3: Biography");
        genreList.add("4: History");
        genreList.add("5: Romance");
        genreList.add("6: War");
        genreList.add("7: Adventure");
        genreList.add("8: Family");
        genreList.add("9: Mystery");
        genreList.add("10: Sport");
        genreList.add("11: Thriller");
        genreList.add("12: Music/Musical");
        genreList.add("13: Sci-Fi");
        genreList.add("14: Horror");
        genreList.add("15: Film-Noir");
        genreList.add("16: Fantasy");

        String output = "";
        for (int i = 0; i < genreList.size(); i++) {
            output = output + genreList.get(i) + "\n";
        }
        System.out.println(output);
    }

    public void displayMovies() {
        //-----------Printer listen af movies i en pen format------------
        List<Movie> movies = movie.movieSeparator();
        for (Movie m : movies) {
            System.out.println(m);
        }
    }

    public void searchByName() {
        String input = ui.getInput("Type to search titles");
        String[] inputTitles = input.split(", "); // Split the user input into an array of genres

        boolean found = false;

        for (MediaContent m : mediaContents) {
            String[] mediaContentTitles = m.getTitle().split(", "); // Split movie genres into an array
            boolean matchFound = false;

            // Check if any movie genre matches any of the input genres
            for (String title : inputTitles) {
                for (String mediaContentTitle : mediaContentTitles) {
                    if (mediaContentTitle.equalsIgnoreCase(title)) {
                        matchFound = true;
                    }
                }
                if (matchFound) {
                    break;
                }
            }

            if (matchFound) {
                ui.displayMessage("Media found: ");
                ui.displayMessage(m.toString());
                mediaOptions(m);
                found = true;
            }
        }

        if (!found) {
            mediaNotFoundOptions();

        }
    }

    public void searchByGenre() {
        displayGenre();
        String input = ui.getInput("Type to search in genre");
        String[] inputGenres = input.split(", "); // Split the user input into an array of genres

        boolean found = false;

        for (MediaContent m : mediaContents) {
            String[] mediaContentGenres = m.getGenre().split(", "); // Split movie genres into an array
            boolean matchFound = false;

            // Check if any movie genre matches any of the input genres
            for (String genre : inputGenres) {
                for (String mediaContentGenre : mediaContentGenres) {
                    if (mediaContentGenre.equalsIgnoreCase(genre)) {
                        matchFound = true;
                    }
                }
                if (matchFound) {
                    break;
                }
            }

            if (matchFound) {
                ui.displayMessage(m.toString());
                found = true;
            }
        }

        if (!found) {
            mediaNotFoundOptions();
            displayGenre();
        }
    }

    public void displayWatchedList() {

    }

    public void displayMyList() {

    }

    public void mediaOptions(Media m) {
        String userInput = ui.getInput("\nPick a function:" +
                "\n1) Play selected media" +
                "\n2) Add media to My List" +
                "\n3) Go back to main menu");

        if (userInput.equals("1")) {
            ui.displayMessage("*Playing media*");
        }
        if (userInput.equals("2")) {
            myList.addToMyList(mediaContents.get(mediaContents.indexOf(m))); // skal ikke hardcodes
            ui.displayMessage("Media successfully added: " + m);
            mediaOptions(m);
        }
        if (userInput.equals("3")) {
            mainMenu();
        }
    }

    public void mediaNotFoundOptions() {
        String input = ui.getInput("Select an option:" +
                "\n1) Display our catalog" +
                "\n2) Search again" +
                "\n)3 Go back to main menu");
        if (input.equalsIgnoreCase("1")) {
            db.showMovieDatabase();
            db.showSeriesDatabase();
        } else if (input.equalsIgnoreCase("2")) {
            searchByName();
        } else if (input.equalsIgnoreCase("3")) {
            mainMenu();
        }
    }
}