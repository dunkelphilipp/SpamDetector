package SpamDetectorV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.text.Document;

import org.jsoup.Jsoup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SpamModel {
    EMail mail; 
    ObservableList<EMail> mails = FXCollections.observableArrayList();
    private SpamView view;
    private SpamModel model;
	private String textFileName;
	private File selectedFile;

	//EK Methode to open eml File from System
	public void chooseEmlFile() {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open EML File");
	    fileChooser.getExtensionFilters().addAll(
	        new ExtensionFilter("EML Files", "*.eml"),
	        new ExtensionFilter("All Files", ".")
	    );
	    selectedFile = fileChooser.showOpenDialog(null);
	}

	//EK Getter 
	public File getSelectedFile() {
	    return selectedFile;
	}

	//EK Method to add file into table
	public void add() {
		 try {
		        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
		        String line;
		        StringBuilder text = new StringBuilder();

		        while ((line = reader.readLine()) != null) {
		            //Escape special characters in the line
		            line = line.replaceAll("&", " ")
		                    .replaceAll("<", " ")
		                    .replaceAll(">", " ");

		            //Append the line to the text
		            text.append(line).append("\n");

		            //Check if line contains "From:", "Subject:", or "To:"
		            if (line.contains("From:") || line.contains("Subject:") || line.contains("To:")) {
		                System.out.println(line);
		            }
		        }

		        reader.close();
		        
		        String textString = text.toString();
		        
		        //Write the text to a file
		        File textFile = new File(selectedFile.getName() + ".txt");
		        FileWriter writer = new FileWriter(textFile);
		        writer.write(textString);
		        writer.close();

		        this.textFileName= textFile.getAbsolutePath();
		        System.out.println("Die Text-Datei wurde erstellt: " + textFile.getName());
	            try {
	            	
	            	
	                EMail email = EMail.fromFile(textFileName);
	                mails.add(email);

	           } catch (Exception e) {
	                e.printStackTrace();
	            }

		    } catch (IOException e) {
		        e.printStackTrace();

		    }
	}

	//EK eml to HTML method
    public String emlToHtml() {
    	return null;
    }

    //PD Check if Spam and set a spamscore
    public void checkSpam(){
        Blacklist blacklist = new Blacklist("blacklist-spam-detector1.csv");
        ArrayList<String> keywords = blacklist.readFile(); //blacklist keywords
        ArrayList<String> matchingWords = new ArrayList<String>(); //Matching words EMail - Blacklist
        //Zur Kontrolle 
        for(String s : keywords) {
        	System.out.print(s + " ");
        } System.out.println();
        
        for (EMail m : mails) {
        	int spamScore = 0;
            ArrayList<String> content = m.getContent(); //EMail subject, sender, body 
            //zur Kontrolle 
            for(String s : content) {
            	System.out.print(s + " ");
            	System.out.println();
            }
            for (String keyword : keywords) {
            	for (String s : content) {
                    if (s.equals(keyword)) {
                        spamScore++;
                        matchingWords.add(s);
                    }
            	}
            }
            m.setSpamScore(spamScore);
            m.setSpam(spamScore > 0);//Spamscore > 1 | spam = true 
            //Zur Kontrolle 
            System.out.println(m.getSpamScore());
            System.out.println(m.isSpam());
            for (String s : matchingWords) {
            	System.out.print(s + " ");
            }
        }
    }
        
    //EK getMailList method
    public ObservableList<EMail> getMailList(){
    	return this.mails;
    }
}