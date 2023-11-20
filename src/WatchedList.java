import java.util.ArrayList;

public class WatchedList {
    ArrayList<Media> watchedList = new ArrayList<>();

    public void addToWatchedList(Media media) {
        watchedList.add(media);
    }

    public void removeFromWatchedList(Media media) {
        watchedList.remove(media);
    }
}
