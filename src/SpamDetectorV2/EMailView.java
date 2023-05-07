package SpamDetectorV2;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage; 

//EK view to open EMails
public class EMailView {
	private final Stage stage;
	private final SpamModel model;
	private int index; 
	
	EMail email;
	
    protected BorderPane root;
    protected GridPane inhalt;


	//EK EMail view constructor
	protected EMailView(Stage stage, SpamModel model, int index){
		
		this.stage = stage;
		this.model = model; 
		this.index = index; 
		
		Label l1 = new Label("E-Mail:");
		
		root = new BorderPane();
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
 
        ScrollPane contentPane = new ScrollPane();
        Text contentText = new Text(b.getText());
        contentText.setWrappingWidth(400); // Set the preferred width for the content text
        contentPane.setContent(contentText);
        contentPane.setFitToWidth(true);
        contentPane.setPrefViewportHeight(200); // Set the preferred height for the content text

        VBox vbox = new VBox(inhalt, contentPane);
        root.setCenter(vbox);

        stage.setTitle("E-Mail");

        double contentWidth = inhalt.getBoundsInParent().getWidth();
        double contentHeight = inhalt.getBoundsInParent().getHeight();

        double windowWidth = contentWidth + 50;
        double windowHeight = contentHeight + 300; 

        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Spam.css").toExternalForm());
        stage.sizeToScene();
        stage.setScene(scene);

    }
    public void start() {
        stage.show();
    }
}