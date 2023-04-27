package SpamDetectorV2;

import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SpamModel {

    EMail mail; 
    ObservableList<EMail> mails = FXCollections.observableArrayList();
    SpamView view;
    SpamModel model;
	private String htmlFileName;

	private File selectedFile;

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


    public void add() {


           try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line;
                StringBuilder html = new StringBuilder();
                
     // Open the HTML document and add a <body> tag
                html.append("<!DOCTYPE html>\n<html>\n<body>\n");


                while ((line = reader.readLine()) != null) {
                    // Escape special characters in the line
                   line = line.replaceAll("&", " ")
                               .replaceAll("<", " ")
                               .replaceAll(">", " ");
                // Add the line as a paragraph
                    html.append("<p>").append(line).append("</p>\n");

                    // Check if line contains "From:", "Subject:", or "To:"
                    if (line.contains("From:") || line.contains("Subject:") || line.contains("To:")) {
                        System.out.println(line);
                   }
                }
                  // Close the HTML document and write it to a file
               html.append("</body>\n</html>");
                File htmlFile = new File(selectedFile.getName() + ".html");
                FileWriter writer = new FileWriter(htmlFile);
                writer.write(html.toString());
                writer.close();

                this.htmlFileName = htmlFile.getAbsolutePath();
                System.out.println("Die HTML-Datei wurde erstellt: " + htmlFile.getName());

                try {
                	
                	
                    EMail email = EMail.fromFile(htmlFileName);
                    mails.add(email);

               } catch (Exception e) {
                    e.printStackTrace();
                }

                
           } catch (IOException e) {
                e.printStackTrace();
            }
            
       }
    	
    	
    
   // private List<File> chooseEmlFiles() {

    public String emlToHtml() {
    	return null;
    }


    public void checkSpam() {

   
    }
        
    public ObservableList<EMail> getMailList(){
    	return this.mails;
    }
}