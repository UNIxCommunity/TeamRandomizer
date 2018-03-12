package TeamRandomizer;

public class Randomizer {

    private static String USER_NAME = "<...>";  
    private static String PASSWORD = "<...>"; 
    private static String RECIPIENT = "<...>";

    public static void main(String[] args) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; 
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";
        Mail mailService = new Mail();
        mailService.sendFromGMail(from, pass, to, subject, body);

    }

}

