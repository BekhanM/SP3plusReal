import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileIO fileio = new FileIO();
        StreamingService streamingService = new StreamingService();
        MediaContent media = new MediaContent("ss","","",0,"","");

        //streamingService.displaySeries();

       // System.out.println(fileio.readMediaData());
        streamingService.startMenu();
        //streamingService.searchByGenre();
        //streamingService.searchByGenre();
        //streamingService.searchByName();
        //streamingService.displaySeries();
        //streamingService.displayMediaContent();

        //System.out.println(fileio.readMediaData());
        //System.out.println(media.mediaContentSeparator());



    }
}
