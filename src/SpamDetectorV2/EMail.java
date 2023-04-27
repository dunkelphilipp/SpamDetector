package SpamDetectorV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.*;

import javafx.collections.ObservableList;

public class EMail {
    // E-Mail object attributes
    private final int ID;
    private static int nextID = 1;
    private String sender;
    private String recipient;
    private String subject;
	
    private String body;
    private int spamScore;
    

    // Constructor for E-Mail objects
    protected EMail(String sender, String recipient, String subject) {
        this.ID = nextID++;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;


    }

    // Getter and Setter E-Mail attributes
    public int getID() {
        return ID;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }
    
    //HH Getter und Setter
    public int getSpamScore() {
		return spamScore;
	}
    
    public String getBody() {
    	return body;
    }

    //HH store Mail content in Arraylist
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		content.addAll(Arrays.asList(sender.toLowerCase().split("[^a-zA-Z0-9]")));
		content.addAll(Arrays.asList(subject.toLowerCase().split("[^a-zA-Z0-9]")));
		content.addAll(Arrays.asList(body.toLowerCase().split("[^a-zA-Z0-9]")));
		return content;
	}

    // Method to read EMail attributes from HTML File
    public static EMail fromFile(String fileName) {
        StringBuilder htmlEmail = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                htmlEmail.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // parse HTML elements as String in d
        Document d = Jsoup.parse(htmlEmail.toString());

        Element fromElement = d.select("p:contains(From: )").first();
        String sender = "";
        if (fromElement != null) {
            String withFromKey = fromElement.text();
            sender = withFromKey.substring(withFromKey.indexOf(" ") +1);
        }

        Element toElement = d.select("p:contains(To: )").first();
        String recipient = "";
        if (toElement != null) {
            String withToKey = toElement.text();
            recipient = withToKey.substring(withToKey.indexOf(" ") + 1);
        }

        Element subjectElement = d.select("p:contains(Subject: )").first();
        String subject = "";
        if (subjectElement != null) {
            String withSubjectKey = subjectElement.text();
            subject = withSubjectKey.substring(withSubjectKey.indexOf(" ") + 1);
        }

        System.out.println(sender);
        System.out.println(recipient);
        System.out.println(subject);

        return new EMail(sender, recipient, subject);

}

}