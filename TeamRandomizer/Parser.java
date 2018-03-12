package TeamRandomizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.util.Pair;
import java.util.*;
import java.io.*;

public class Parser {

	Pair< String, String> getLoginCredentials(String fileName){
		Pair <String, String> credentials;
		String login = "";
		String password ="";
		 try{
			 Scanner fileIn = new Scanner(new File(fileName));
			 if(fileIn.hasNextLine()){
				 String line = fileIn.nextLine();
	            	if(fileIn.hasNextLine()){
	            		login = line;
		            	password = fileIn.nextLine();
		            }
			 	}
	    }catch(IOException ex){
	    	System.out.println("Error reading file "+ fileName);
	    }
		credentials = new Pair(login,password);
		return credentials;
	}

	ArrayList<Pair< String, ArrayList<String> > > getNameAndMailList(String fileName){
		ArrayList<Pair< String, ArrayList<String> > > data = new ArrayList<Pair <String, ArrayList<String> > >();
        String line = null;
        try{
            Scanner fileIn = new Scanner(new File(fileName));
            while(fileIn.hasNextLine())
            {
            	line = fileIn.nextLine();

            	String delim = "[,]";
                String[] NameMail = (line).split(delim,2);

                String name = "";
                ArrayList<String> mails = new ArrayList<String>();

                if(NameMail.length > 0){
                	name = NameMail[0];
                }

                if(NameMail.length > 1){
                	Pattern p = Pattern.compile("([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})");
                    Matcher m = p.matcher(line);
                    while(m.find()) {
                        mails.add(m.group(1));
                    }
                }
                data.add(new Pair(name, mails));
            }
        }catch(IOException ex){
            System.out.println("Error reading file"+ fileName);
        }
        return data;
    }
}
