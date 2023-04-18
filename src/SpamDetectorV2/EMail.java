package SpamDetectorV2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
//        this.body = body;
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

//    public String getBody() {
//    	return body;
//    }

    public void setSpamScore(int spamScore) {
        this.spamScore = spamScore;
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
        
        String withFromKey = d.select("p:contains(From)").first().text();
        String sender = withFromKey.substring(withFromKey.indexOf(" "), withFromKey.length());
        
        String withToKey = d.select("p:contains(To)").first().text();
        String recipient = withToKey.substring(withToKey.indexOf(" "), withToKey.length());
        
        String withSubjectKey =  d.select("p:contains(Subject)").first().text();
        String subject = withSubjectKey.substring(withSubjectKey.indexOf(" "), withSubjectKey.length());

//      String withBodyKey = d.select("p:contains(Body)").first().text();
//      String body = withBodyKey.substring(withBodyKey.indexOf(" "), withBodyKey.length());
        
        
        System.out.println(sender);
        System.out.println(recipient);
        System.out.println(subject);

        // save HTML elements from
		
        return new EMail(sender, recipient, subject);
    }


}