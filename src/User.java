import java.util.ArrayList;
import java.util.Objects;

public class User
{
    WatchedList watchedList = new WatchedList();
    MyList myList = new MyList();

    private String username;
    private String password;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    ArrayList<Media> media = new ArrayList<>();

    @Override
    public String toString()
    {
        return "Username: " + username + " Password: " + password;
    }

    public void addWatchedMedia(MediaContent media)
    {
        watchedList.addToWatchedList(media);
    }

    public void addMyListMedia(MediaContent media)
    {
        myList.addToMyList(media);
    }

    public void removeWatchedMedia(MediaContent media)
    {
        watchedList.removeFromWatchedList(media);
    }

    public void removeMyListMedia(MediaContent media)
    {
        myList.removeFromMyList(media);
    }
}
