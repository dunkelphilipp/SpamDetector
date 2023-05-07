package SpamDetectorV2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class EMail {
    //EMail object attributes
    private final int ID;
    private static int nextID = 1;
    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private int spamScore = 0;
    private Boolean spam = null;

	//Constructor
    protected EMail(String sender, String recipient, String subject, String body) {
        this.ID = nextID++;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    //PD Getter and Setter E-Mail attributes
    public String getSender() {
        return sender;
    }
    public String getRecipient() {
        return recipient;
    }
    public String getSubject() {
        return subject;
    } 
    public Boolean isSpam() {
		return spam;
	}
	public void setSpam(Boolean spam) {
		this.spam = spam;
	}

	//HH Getter und Setter
    public int getSpamScore() {
		return spamScore;
	}  
    public void setSpamScore(int spamScore) {
		this.spamScore = spamScore;
	}
    public String getBody() {
    	return body;
    }
	public void setBody(String body) {
		this.body = body;
	}

	//HH Method to store Mail content in Arraylist
	public ArrayList<String> getContent() {
		ArrayList<String> content = new ArrayList<String>();
		if (sender != null) {
			content.addAll(Arrays.asList(sender.toLowerCase().split("[^a-zA-Z0-9]")));
		}
		if (subject != null) {
			content.addAll(Arrays.asList(subject.toLowerCase().split("[^a-zA-Z0-9]")));
			
		}
		if (body != null) {
			content.addAll(Arrays.asList(body.toLowerCase().split("[^a-zA-Z0-9]")));
		}
		return content;
	}

	//PD getConent test methode for checkspam method
//	public ArrayList<String> getContent() {
//		ArrayList<String> content = new ArrayList<String>();
//		content.add("spam");
//		content.add("kredit");
//		content.add("nudechats.com");
//		content.add("kredit@mail.com");
//		content.add("test");
//		content.add("Schule");
//		content.add("Arbeit");
//		return content;	
//	}
	
	//EK Method to read EMail content from file 
	public static EMail fromFile(String fileName) {
	    StringBuilder textEmail = new StringBuilder();

	    //Read file
	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            textEmail.append(line).append("\n"); // add newline character to preserve line breaks
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    //extract EMail content
	    String sender = "";
	    String recipient = "";
	    String subject = "";
	    String body = "";

	    //Add EMail content to the right String
	    String[] lines = textEmail.toString().split("\n");
	    for (String line : lines) {
	        if (line.startsWith("From:")) {
	            sender = line.substring(6).trim();
	        } else if (line.startsWith("To:")) {
	            recipient = line.substring(4).trim();
	        } else if (line.startsWith("Subject:")) {
	            subject = line.substring(9).trim();
	        } else {
	        	body += line + "\n"; // 
	        }
	    }
	    
	    //print to contoroll content 
	    System.out.println(sender);
	    System.out.println(subject);
	    System.out.println(body);

	    return new EMail(sender, recipient, subject, body);
	}
}