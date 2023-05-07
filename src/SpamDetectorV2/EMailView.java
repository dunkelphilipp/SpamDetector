package SpamDetectorV2;

import javafx.scene.Scene;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage; 

//EK view to open EMails
public class EMailView {
	private final Stage stage;
	private final SpamModel model;
	private int index; 
	
	EMail mail;

	//declare GUI Elements
	protected BorderPane root;	
	
	protected GridPane inhalt;

	//EK EMail view constructor
	protected EMailView(Stage stage, SpamModel model, int index){
		
		this.stage = stage;
		this.model = model; 
		this.index = index; 
		
		Label l1 = new Label("E-Mail:");
		
		root = new BorderPane();
		root.setId("MailRoot");
		root.setTop(l1);
		
		inhalt = new GridPane();
		
		Label fromV = new Label("Sender: ");
		Label toV = new Label("Recipient: ");
		Label subjectV = new Label("Subject: ");
		Label contentV = new Label("Content: ");
		
		inhalt.add(fromV, 0, 0);
		inhalt.add(toV, 0, 1);
		inhalt.add(subjectV, 0, 2);
		inhalt.add(contentV, 0, 3);

		Text s = new Text(model.mails.get(index).getSender());
		Text r = new Text(model.mails.get(index).getRecipient());
		Text sub = new Text(model.mails.get(index).getSubject());
		Text b = new Text(model.mails.get(index).getBody());
		
		inhalt.add(s, 1, 0);
		inhalt.add(r, 1, 1);
		inhalt.add(sub, 1, 2);
	    inhalt.add(b, 1, 4);
		
		root.setCenter(inhalt);

		//create scene
		
		stage.setTitle("E-Mail");
		
	
		double contentWidth = inhalt.getBoundsInParent().getWidth();
		double contentHeight = inhalt.getBoundsInParent().getHeight();

		double windowWidth = (contentWidth+50);
		double windowHeight = (contentHeight+100);

	
		stage.setWidth(windowWidth);
		stage.setHeight(windowHeight);

		Scene scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("Spam.css").toExternalForm());
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();


	}

	//start method
	public void start() {
		stage.show();
	}
}


