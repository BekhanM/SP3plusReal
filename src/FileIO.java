import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileIO implements IO {

    @Override
    public ArrayList<String> readUserData() {
        ArrayList<String> userData = new ArrayList<>();
        File file = new File("src/UserBase");

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                userData.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        return userData;
    }

    public void saveUserData(ArrayList<User> users) {
        try {
            File file = new File("src/UserBase");
            boolean fileExists = file.exists();

            FileWriter writer = new FileWriter(file, true); // true flag for append mode
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            if (!fileExists || file.length() == 0) {
                bufferedWriter.write("Username,Password" + "\n");
            }

            for (User c : users) {
                String textToSave = c.getUsername() + "," + c.getPassword();
                bufferedWriter.write(textToSave + "\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to the file");
        }
    }


    public ArrayList<String> readMovieData() {
        ArrayList<String> movieData = new ArrayList<>();

        File file = new File("src/100bedstefilm");

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                movieData.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        return movieData;
    }

    @Override
    public ArrayList<String> readSeriesData() {
        ArrayList<String> seriesData = new ArrayList<>();

        File file = new File("src/100bedsteserier");

        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                seriesData.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        return seriesData;
    }

    public ArrayList<String> readMediaData() {
        ArrayList<String> mediaData = new ArrayList<>();

        File file = new File("src/MediaContentContent");

        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                mediaData.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        return mediaData;
    }

    public void saveMyListData(String username, ArrayList<MediaContent> saveData) {
        ArrayList<String> savedData = new ArrayList<>();
        try {
            File f = new File("UserMyList/userMyList_" + username + ".txt");
            FileWriter writer = new FileWriter(f,true);
            if (!f.exists()) {
                f.createNewFile();
                for (MediaContent s : saveData) {
                    MediaContent textToSave = s;
                    writer.write(String.valueOf(textToSave + "\n"));
                    savedData.add(String.valueOf(textToSave));
                }
                System.out.println("has been added to new file");
                writer.close();
            } else if (f.exists()) {
                for (MediaContent s : saveData) {
                    MediaContent textToSave = s;
                    writer.write(String.valueOf(textToSave + "\n"));
                    savedData.add(String.valueOf(textToSave));
                    System.out.println("Has been added to existing file");
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to the file");
        }
    }
}