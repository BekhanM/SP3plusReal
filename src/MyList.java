import java.util.ArrayList;

public class MyList
{
    ArrayList<Media> myList = new ArrayList<>();

    public void addToMyList(Media media)
    {
        myList.add(media);
    }

    public void removeFromMyList(Media media)
    {
        myList.remove(media);
    }
}