package spamdetector;

public class SpamDetectorModel {
    private String message;

    public SpamDetectorModel() {
        // standard Konstruktor
    }

    public boolean isSpam() {
    	//Business Logik um Spam nach Absender oder Inhalt zu erkennen
        return false;
    }
    
    //Schnittstelle zum abrufen von E-Mails einfügen 
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

