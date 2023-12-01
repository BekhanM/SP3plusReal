import java.sql.*;

public class DataBaseIO {
    static final String DB_URL = "jdbc:mysql://localhost/my_streaming";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Heisenberg2001!";
    String username1;
    String password1;
    User user = new User(username1, password1);
    TextUI ui = new TextUI();

    public User getAuthenticatedUser(String name, String puffPass) {
        Connection conn = null;
        PreparedStatement stmt = null;

        user = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT name, password FROM USER WHERE name = ? AND password = ? ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, puffPass);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(name, puffPass);
                ui.displayMessage("User/password correct!");
            } else {
                ui.displayMessage("User/password incorrect?");
            }
            stmt.close();
            conn.close();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void showMovieDatabase() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 2: Open a connection
            //ui.displayMessage("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
           // ui.displayMessage("Creating statement...");
            String sql = "SELECT name, genre, year, rating FROM my_streaming.movie;";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name


                String genre = rs.getString("Genre");
                String name = rs.getString("Name");
                int year = rs.getInt("Year");
                double rating = rs.getDouble("Rating");

                String formatString = "Name: %-45sGenre: %-35sRelease Date: %-12sRating:%-5.2f";

                String formattedOutput = String.format(formatString, name, genre, year, rating);

                ui.displayMessage(formattedOutput);

            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }


    public void showSeriesDatabase() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 2: Open a connection
            //ui.displayMessage("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            //ui.displayMessage("Creating statement...");
            String sql = "SELECT name, genre, year, rating, seasons_episodes FROM my_streaming.series;";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            //STEP 4: Extract data from result set
            while (rs.next()) {

                String genre = rs.getString("Genre");
                String name = rs.getString("Name");
                String year = rs.getString("Year");
                double rating = rs.getDouble("Rating");
                String seasons_episodes = rs.getString("Seasons_episodes");

                String formatString = "Name: %-28sGenre: %-30sRelease Date: %-12sRating: %-5.2fSeasons and episodes: %s";

                String formattedOutput = String.format(formatString, name, genre, year, rating, seasons_episodes);

                ui.displayMessage(formattedOutput);

            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void saveUserData(String newName, String newPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO USER (name, password) VALUES ( ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, newName);
            stmt.setString(2, newPassword);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ui.displayMessage("Brugeren er gemt");
            } else {
                ui.displayMessage("Mislykkes at gemme brugeren");
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayWatchedList() {

    }

    public void displayMyList() {

    }
}