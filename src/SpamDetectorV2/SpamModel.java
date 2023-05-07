package SpamDetectorV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Document;

import org.jsoup.Jsoup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SpamModel {

    EMail mail; 
    ObservableList<EMail> mails = FXCollections.observableArrayList();
    SpamView view;
    SpamModel model;
	private String textFileName;
	String boundary1 = "0000000000000000000000000000000000000000000000000000000000000000000";

	private File selectedFile;

	//EK
	public void chooseEmlFile() {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open EML File");
	    fileChooser.getExtensionFilters().addAll(
	        new ExtensionFilter("EML Files", "*.eml"),
	        new ExtensionFilter("All Files", ".")
	    );
	    selectedFile = fileChooser.showOpenDialog(null);
	}

	public File getSelectedFile() {
	    return selectedFile;
	}
	//EK
	public void add() {
		 try {
		        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
		        String line;
		        StringBuilder text = new StringBuilder();
			 	boolean isHtmlContent = false;

			 while ((line = reader.readLine()) != null) {
				 // Escape special characters in the line
				/* line = line.replaceAll("&", " ")
						 .replaceAll("<", " ")
						 .replaceAll(">", " ");
					*/

				 Pattern boundaryPattern = Pattern.compile("boundary=\"(.+?)\"");
				 Matcher boundaryMatcher = boundaryPattern.matcher(line);
				 if (boundaryMatcher.find()) {
					String boundary = boundaryMatcher.group(1);
					 this.boundary1 = boundary;
				 }


				 // Check if line contains "From:", "Subject:", or "To:"
				 if (line.contains("From:") || line.contains("Subject:") || line.contains("To:")) {
					 System.out.println(line);
					 text.append(line); text.append("\n");
				 }
				 if (line.startsWith("Content-Type: text/html")) {
					 isHtmlContent = true;
				 } else if (line.startsWith("Content-Type:")) {
					 isHtmlContent = false;
				 } else if (isHtmlContent) {
				 	if ( !line.contains("Content-Transfer-Encoding: quoted-printable")) {
				 		if (!line.contains(this.boundary1)){
							text.append(line.replaceAll("=([A-Fa-f0-9]{2})", "%$1"));
							// text.append(line);
							text.append("\n");
						}
					 }
				 }
			 }
			 reader.close();
		        
		        String textString = text.toString();
		
		        // Write the text to a file
		        File textFile = new File(selectedFile.getName() + ".txt");
		        FileWriter writer = new FileWriter(textFile);
		        writer.write(textString);
		        writer.close();

		        this.textFileName= textFile.getAbsolutePath();
		        System.out.println("Die Text-Datei wurde erstellt: " + textFile.getName());
	            try {
	            	
	            	
	                EMail email = EMail.fromFile(textFileName);
	              //  email.removeHtmlTags();
	                mails.add(email);

	           } catch (Exception e) {
	                e.printStackTrace();
	            }

		    } catch (IOException e) {
		        e.printStackTrace();

		    }
	}

    //PD Check if Spam and set a spamscore
    public void checkSpam(){
        Blacklist blacklist = new Blacklist("blacklist-spam-detector1.csv");
        ArrayList<String> keywords = blacklist.readFile();
        ArrayList<String> matchingWords = new ArrayList<String>();
        //Zur Kontrolle 
        for(String s : keywords) {
        	System.out.print(s + " ");
        } System.out.println();
        
        for (EMail m : mails) {
        	int spamScore = 0;
            ArrayList<String> content = m.getContent();
            //Zur Kontrolle 
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
            m.setSpam(spamScore > 0);
            //Zur Kontrolle 
            System.out.println(m.getSpamScore());
            System.out.println(m.isSpam());
            for (String s : matchingWords) {
            	System.out.print(s + " ");
            }
        }
    }
        
    public ObservableList<EMail> getMailList(){
    	return this.mails;
    }
}