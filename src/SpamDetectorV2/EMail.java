package SpamDetectorV2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class EMail {
	//E-Mail object attributes
	private final int ID;
	private static int nextID = 1;
	private String sender;
	private String recipient;
	private String subject;
	private String body;
	private int spamScore;
	
	//Constructor for E-Mail objects
	protected EMail(String sender, String recipient, String subject, String body, int spamScore) {
		this.ID = nextID;
		this.nextID = nextID++;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
		this.spamScore = spamScore;
	}

	//Getter and Setter E-Mail attributes
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

	public String getBody() {
		return body;
	}
	
	//Jsoup parse
	//Method to read EMail attributes from HTML File 
//	public static EMail fromFile(String fileName) {
//		
//		StringBuilder htmlEmail = new StringBuilder();
//		
//		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				htmlEmail.append(line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		//parse HTML elements as String in d 
//		Document d = Jsoup.parse(htmlEmail.toString());
//		
//		//save HTML elements from d
//		String sender = d.select("div.sender, span[from]").first().text();
//		String recipient = d.select("div.recipient, span[to]").first().text();
//		String subject = d.select("div.subject, h1[subject]").first().text();
//		String body = d.select("div.body, p.body").first().html();
//		
//		return new EMail (sender, recipient, subject, body);
//		
//	}
}
