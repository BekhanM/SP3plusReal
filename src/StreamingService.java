import java.util.ArrayList;
import java.util.List;

public class StreamingService {

    Movie movie = new Movie("", "", "", 0);
    Series series = new Series("", "", "", 0, "", "");
    MediaContent mediaContent = new MediaContent("","","",0,"","");

    private List<Movie> movies = movie.movieSeparator();
    private List<Series> serie = series.seriesSeparator();
    private List<MediaContent> mediaContents = mediaContent.mediaContentSeparator();
    private List<String>genreList = new ArrayList<>();
    private FileIO io = new FileIO();
    private ArrayList<String> userData = io.readUserData();
    private ArrayList<String> movieData = io.readMovieData();
    private ArrayList<String> seriesData = io.readSeriesData();
    private ArrayList<String> mediaContentData = io.readMediaData();
    private DataValidator dataValidator = new DataValidator();
    private ArrayList<User> users = new ArrayList<>();
    private TextUI ui = new TextUI();
    private List<Content> content;
    private List<User> myList = new ArrayList<>();

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
                "\n2) Søge efter en bestemt film" +
                "\n3) Søge alle film i en kategori" +
                "\n4) Se din liste over sete film" +
                "\n5) Se din liste over gemte film" +
                "\n6) logout");
        if (i.equals("1")) {
            displayMovies();
        }
        if (i.equals("2")) {
            searchByName();
        }
        if (i.equals("3")) {
            displayGenre();
            searchByGenre();
        }
        if (i.equals("4")) {
            displayWatchedList();
        }
        if (i.equals("5")) {
            displayMyList();
        }
        if (i.equals("6")) {
            logout();
        }
    }

    public void login() {
        String userInputUsername = ui.getInput("Brugernavn: ");
        if (dataValidator.checkLoginUsername(userData, userInputUsername)) {
            loginPassword(userInputUsername);
        } else {
            login();
        }
    }

    public void loginPassword(String user) {
        String userInputPassword = ui.getInput("Kodeord: ");
        if (dataValidator.checkLoginPassword(userData, userInputPassword)) {
            ui.displayMessage("Du er nu logget ind som: " + user);
            mainMenu();
        } else {
            loginPassword(user);
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
        if (dataValidator.checkRegisterUsername(userData, userInputUsername)) {
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
        userData.add(user.toString());
        users.add(user);
        io.saveUserData(users);
    }

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

    public void displayGenre(){
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
        for(int i = 0 ; i < genreList.size();i++){
            output = output+genreList.get(i)+"\n";
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

    public void displaySeries() {
        //-----------Printer listen af SERIES i en pen format------------
        List<Series> serie = series.seriesSeparator();
        for (Series s : serie) {
            System.out.println(s);
        }
    }

    /*
    public void displayMediaContent() {
        //-----------Printer listen af movies og series i en pen format------------
        List<MediaContent> mediaContents = mediaContent.mediaContentSeparator();

        for (MediaContent mc : mediaContents) {
            System.out.println(mc);
        }
        if(mediaContents.isEmpty()){
            System.out.println("Nixen bixen");

        }
    }
*/
    public void displayMediaContent() {
        // Use the existing mediaContents list
        for (MediaContent mc : mediaContents) {
            System.out.println(mc);
        }

        if (mediaContents.isEmpty()) {
            System.out.println("Nixen bixen");
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
                ui.displayMessage(m.toString());
                found = true;
            }
        }

        if (!found) {
            searchChoices();

        }
    }

    public void searchByReleaseDate() { //NICETOHAVE

    }

    public void searchByRating() { // NICE TO HAVE
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
            searchChoices();
            displayGenre();
        }
    }


    public void displayWatchedList() {

    }

    public void displayMyList() {

    }
    public void mediaOptions(){
        ui.displayMessage("Media found" + "\n" + "Pick a function:\n1) Play selected media\n2) Add media to My List\n3) Go back");
        String choice = ui.getInput("");
        if (choice.equals("1")) {
            ui.displayMessage("*Playing media*");
        }
        if (choice.equals("2")) {
            MyList myList1 = new MyList();
            myList1.addToMyList(null);

        }
        if (choice.equals("3")) {
            mainMenu();
        }
    }
    public void searchChoices(){
        ui.displayMessage("Media not found"+"\n"+"Do you want to ");

        String input = ui.getInput("1: Display our catalog"+"\n"+"2: Search again"+"\n"+"3: Go back to main menu");
        if(input.equalsIgnoreCase("1")){
            displayMovies();
        }else if(input.equalsIgnoreCase("2")){
            searchByName();
        }else if(input.equalsIgnoreCase("3")){
            mainMenu();
        }
    }
}


