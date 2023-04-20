package SpamDetectorV2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SpamView {
	//PD reference Stage and Model
	private final Stage stage;
	private final SpamModel model;
	
	//declare GUI Elements
	protected BorderPane root;	
	
	//top
	protected HBox topBar;
	protected Button addBtn;
	protected Button deleteBtn;
	protected Button deleteAllBtn;
	protected Button checkBtn;
	
	//center
	protected TableView table;
	protected TableColumn senderClm;
	protected TableColumn recipientClm;
	protected TableColumn subjectClm;
	protected TableColumn contentClm;
	protected TableColumn spamClm;
	
	//bottom
	protected HBox lowerBar;
	protected Label spamLbl;
	protected Label scoreLbl;
	protected Button deleteSpamBtn;
	
	//constructor SpamView
	protected SpamView(Stage stage, SpamModel model) {
		//reference view and model objects
		this.stage = stage;
		this.model = model;
		
		//instantiate GUI elements
		//top elements
		topBar = new HBox();
		topBar.setId("topBar");
		addBtn = new Button("Add E-Mail");
		deleteBtn = new Button("Delete");
		deleteAllBtn = new Button("Delete all");
		checkBtn = new Button("Check Spam");
		topBar.getChildren().addAll(addBtn, deleteBtn, deleteAllBtn, checkBtn);
		
		//center elements
		//https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
		table = new TableView();
		//table.setEditable(true); ??????
		senderClm = new TableColumn("Sender");
		senderClm.setId("senderClm");
		recipientClm = new TableColumn("Recipient");
		recipientClm.setId("recipientClm");
		subjectClm = new TableColumn("Subject");
		subjectClm.setId("subjectClm");
		contentClm = new TableColumn("E-Mail Content");
		contentClm.setId("contentClm");
		spamClm = new TableColumn("Spam Score");
		spamClm.setId("spamClm");
		table.getColumns().addAll(senderClm, recipientClm, subjectClm, contentClm, spamClm);
		
		//bottom elements
		lowerBar = new HBox();
		lowerBar.setId("lowerBar");
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		Region spacer2 = new Region();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		spamLbl = new Label("Add E-Mails");
		spamLbl.setId("spamLbl");
		scoreLbl = new Label();
		//" 1: low Spampotential 2: medium Spampotential 3: high Spampotential"
		scoreLbl.setId("scoreLbl");
		TextFlow scoreFlow = new TextFlow();
		Text text1 = new Text("1: low Spampotential ");
		text1.setId("text1");
		Text text2 = new Text("2: medium Spampotential ");
		text2.setId("text2");
		Text text3 = new Text("3: high Spampotential");
		text3.setId("text3");
		scoreFlow.getChildren().addAll(text1, text2, text3);
		scoreLbl.setGraphic(scoreFlow);
		deleteSpamBtn = new Button("Delete Spam");
		lowerBar.getChildren().addAll(spamLbl, spacer1, scoreLbl, spacer2, deleteSpamBtn);
		
		//root Borderpane
		root = new BorderPane();
		root.setId("root");
		root.setTop(topBar);
		root.setCenter(table);
		root.setBottom(lowerBar);
		
		//create scene
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Spam.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Spam Detector");
	}
	
	//start method
	public void start() {
		stage.show();
	}
}
