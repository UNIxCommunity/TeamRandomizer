package TeamRandomizer;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Random;

public class Randomizer {

    public static void main(String[] args) {
    	String input = "input.txt";
    	String loginCredentials = "loginCredentials.txt";
    	int teamSize = 2;
    	work(input, loginCredentials, teamSize);
    }

    public static void work(String input, String loginData, int teamSize){
    	Parser parser = new Parser();
        ArrayList<Pair< String, ArrayList<String> > > data = parser.getNameAndMailList(input);
        Pair <String, String> loginCredentials = parser.getLoginCredentials(loginData);
        informTeamMembers(loginCredentials, generateTeams(data , teamSize));
    }


    private static boolean informTeamMembers(Pair <String, String> loginCredentials, ArrayList< ArrayList<Pair <String, ArrayList<String>>>> teamList){

    	Mail mailService = new Mail();
    	String from = loginCredentials.getKey();
    	String pass = loginCredentials.getValue();
    	String subject = "UNIx Community informs you about new Team Division";
    	for(int teamIndex = 0; teamIndex < teamList.size(); teamIndex++){

    		ArrayList<Pair<String, ArrayList<String>>> team = teamList.get(teamIndex);
    		ArrayList<String> recipients = new ArrayList<>();

    		StringBuilder body = new StringBuilder();


    		body.append("Hello, Fearless Hacker! You have been assigned to the following team: \n");

    		for(int memberIndex = 0; memberIndex < team.size(); memberIndex++){
    			body.append(team.get(memberIndex).getKey());
    			body.append(", ");
    			for(int memberMailIndex = 0; memberMailIndex < team.get(memberIndex).getValue().size(); memberMailIndex++){
    				recipients.add(team.get(memberIndex).getValue().get(memberMailIndex));
    				System.out.println("Notification was sent to "+team.get(memberIndex).getKey()+" on following mail: "+team.get(memberIndex).getValue().get(memberMailIndex));
    			}
    		}

    		body.delete(body.length()-2, body.length());
    		body.append(". \n");

    		String[] to = recipients.toArray(new String[recipients.size()]);

    		mailService.sendFromGMail(from, pass, to, subject, body.toString());
    	}

    	return true;
    }

    private static String showTeams(ArrayList< ArrayList<Pair <String, ArrayList<String>>>> teamList){
    	StringBuilder teams = new StringBuilder();
    	for(int i = 0; i < teamList.size(); i++){
    		ArrayList<Pair<String, ArrayList<String>>> team = teamList.get(i);
    		teams.append("Team #");
    		teams.append(Integer.toString(i+1));
    		teams.append(": ");
    		for(int ti = 0; ti < teamList.size(); ti++){
    			teams.append(team.get(ti).getKey());
    			teams.append(", ");
    		}
    		teams.delete(teams.length()-2, teams.length());
    		teams.append(". \n");
    	}
    	return teams.toString();
    }

    private static ArrayList< ArrayList<Pair <String, ArrayList<String>>>> generateTeams(ArrayList<Pair< String, ArrayList<String> > > namesList, int teamSize){
    	ArrayList< ArrayList<Pair <String, ArrayList<String>>>> teamList = new ArrayList< ArrayList<Pair <String, ArrayList<String>>>>();
    	ArrayList<ArrayList <Integer> > randomNumeration = Randomize(namesList.size(),teamSize);
    	for(int i=0; i< randomNumeration.size(); i++){
        	ArrayList <Integer>  team = randomNumeration.get(i);
        	ArrayList<Pair <String, ArrayList<String>>> teamData = new ArrayList<Pair <String, ArrayList<String>>>();
        	for(int j=0; j<team.size(); j++){
        		teamData.add(namesList.get((randomNumeration.get(i).get(j)-1)));
            }
        	teamList.add(teamData);
        }
    	return teamList;
    }

    private static ArrayList<ArrayList <Integer> > Randomize(int peopleAmount, int teamSize){

    	ArrayList<ArrayList <Integer> > solution= new ArrayList<ArrayList <Integer> >();
    	ArrayList<Integer> notSelectedToAnyTeam = new ArrayList<Integer>();

    	for(int i = 0; i<peopleAmount; i++){
    		notSelectedToAnyTeam.add(i+1);
    	}

    	int teamsAmount = 0;

    	if(teamSize != 0){
    		teamsAmount = (int) Math.ceil((peopleAmount*1.0)/teamSize);
    	}

    	for(int i = 0; i<teamsAmount; i++){
    		ArrayList<Integer> team =  new ArrayList<Integer>();
    		for(int j = 0; j<teamSize; j++){
        		if(notSelectedToAnyTeam.size()>0){
        			int randomIndex = new Random().nextInt(notSelectedToAnyTeam.size());
        			team.add(notSelectedToAnyTeam.get(randomIndex));
        			notSelectedToAnyTeam.remove(randomIndex);
        		}
        	}
    		solution.add(team);
    	}


    	return solution;
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

    private static void showRandom(ArrayList<ArrayList <Integer> > data){
        for(int i=0; i< data.size(); i++){
        	ArrayList <Integer>  record = data.get(i);
            for(int j=0; j<record.size(); j++){
            	 System.out.print(record.get(j)+ " ");
            }
            System.out.println();
        }
    }

}

