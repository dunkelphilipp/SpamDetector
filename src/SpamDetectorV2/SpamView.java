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
	protected TableColumn firstNameClm;
	protected TableColumn lastNameClm;
	protected TableColumn eMailClm;
	protected TableColumn subjectClm;
	protected TableColumn spamClm;
	
	//bottom
	protected HBox lowerBar;
	protected Label spamLbl;
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
		firstNameClm = new TableColumn("First Name");
		firstNameClm.setId("firstNameClm");
		lastNameClm = new TableColumn("Last Name");
		lastNameClm.setId("lastNameClm");
		eMailClm = new TableColumn("E-Mail Adress");
		eMailClm.setId("eMailClm");
		subjectClm = new TableColumn("Subject");
		subjectClm.setId("subjectClm");
		spamClm = new TableColumn("Spam");
		spamClm.setId("spamClm");
		table.getColumns().addAll(firstNameClm, lastNameClm, eMailClm, subjectClm, spamClm);
		
		//bottom elements
		lowerBar = new HBox();
		lowerBar.setId("lowerBar");
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		spamLbl = new Label("Add E-Mails");
		spamLbl.setId("spamLbl");
		deleteSpamBtn = new Button("Delete Spam");
		lowerBar.getChildren().addAll(spamLbl, spacer, deleteSpamBtn);
		
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
