package SpamDetectorV2;

import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpamModel {

    EMail mail; 
    ObservableList<EMail> mails = FXCollections.observableArrayList();
    SpamView view;
    SpamModel model;
	private String htmlFileName;

    public void add() {
        JFileChooser chooser = new JFileChooser();

        int rueckgabeWert = chooser.showOpenDialog(null);

        if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            System.out.println("Die zu öffnende Datei ist: " + selectedFile.getName());

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
    }

    public void checkSpam() {

    }
        
    public ObservableList<EMail> getMailList(){
    	return this.mails;
    }
}