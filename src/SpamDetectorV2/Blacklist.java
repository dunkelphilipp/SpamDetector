package SpamDetectorV2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Blacklist {
	private static String file = "blacklist-spam-detector1.csv";
	
	//Constructor
	protected Blacklist(String file) {
		Blacklist.file = file;
	}
	
	//Method to store blacklist in an Arraylist
	public ArrayList<String> readFile() {
		ArrayList<String> blacklist = new ArrayList<String>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = reader.readLine()) != null) {
				String[] keywords = line.split(",");
				for (String s : keywords) {
					String trimmedStr = s.trim().toLowerCase().replace(";", "");
					if(!trimmedStr.isEmpty()) {
						blacklist.add(trimmedStr);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return blacklist;
	}
}
