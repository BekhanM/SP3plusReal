public class Main {
    public static void main(String[] args) {

        User user = new User("username","password");

        StreamingService streamingService = new StreamingService();

        System.out.println(streamingService.userData);

        //streamingService.startMenu();

        //streamingService.logout();

        streamingService.removeUser();

    }
}
