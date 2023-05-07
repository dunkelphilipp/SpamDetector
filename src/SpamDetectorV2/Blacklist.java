package SpamDetectorV2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//PD Blacklist Class for blacklist objects with .csv content
public class Blacklist {
	private static String file;
	
	//PD Constructor with file name
	protected Blacklist(String file) {
		Blacklist.file = file;
	}
	
	//PD Method to read the file and store the conent
	public ArrayList<String> readFile() {
		ArrayList<String> blacklist = new ArrayList<String>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			//read each line from the file 
			while ((line = reader.readLine()) != null) {
				//split the lines in words
				String[] keywords = line.split(";");
				//add words in ArrayList
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
