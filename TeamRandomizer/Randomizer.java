package TeamRandomizer;
import javafx.util.Pair;
import java.util.ArrayList;

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
        //Mail mailService = new Mail();
        //mailService.sendFromGMail(from, pass, to, subject, body);
        //
        Parser parser = new Parser();
        ArrayList<Pair< String, ArrayList<String> > > data = parser.getNameAndMailList("input.txt");
     //   Pair <String, String> loginCredentials = parser.getLoginCredentials("loginCredentials.txt");
        showData(data);

    }

    private static void showData(ArrayList<Pair< String, ArrayList<String> > > data){
        for(int i=0; i< data.size(); i++){
        	Pair< String, ArrayList<String> > record = data.get(i);
            System.out.print(record.getKey()+ ": ");
            for(int j=0; j<record.getValue().size(); j++){
            	 System.out.print(record.getValue().get(j)+ "/");
            }
            System.out.println();
        }
    }

}

