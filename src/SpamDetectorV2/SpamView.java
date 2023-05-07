package SpamDetectorV2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SpamView {
	private final Stage stage;
	private final SpamModel model;
	EMail mail;
	
	//PD eclare GUI Elements
	protected BorderPane root;	
	
	//top
	protected HBox topBar;
	protected Button addBtn;
	protected Button deleteBtn;
	protected Button deleteAllBtn;
	protected Button checkBtn;
	protected Button openEmail;
	
	//center
	protected TableView<EMail> table;
	protected TableColumn senderClm;
	protected TableColumn recipientClm;
	protected TableColumn subjectClm;
	protected TableColumn spamClm;
	
	//bottom
	protected HBox lowerBar;
	protected Label spamLbl;
	protected Button deleteSpamBtn;
	
	//PD constructor SpamView
	protected SpamView(Stage stage, SpamModel model) {
		this.stage = stage;
		this.model = model;
		
		//PD instantiate GUI elements
		//PD top elements
		topBar = new HBox();
		topBar.setId("topBar");
		addBtn = new Button("Add E-Mail");
		deleteBtn = new Button("Delete");
		deleteAllBtn = new Button("Delete all");
		checkBtn = new Button("Check Spam");
		openEmail = new Button("Open E-Mail");
		
		topBar.getChildren().addAll(addBtn, deleteBtn, deleteAllBtn, checkBtn, openEmail);
		
		//PD center elements
		table = new TableView<EMail>();
		
		TableColumn<EMail, String> senderClm= new TableColumn<>("Sender");
		senderClm.setId("senderClm");
		senderClm.setCellValueFactory(new PropertyValueFactory<EMail, String>("sender"));

		
		TableColumn<EMail, String> recipientClm= new TableColumn<>("Recipient");
		recipientClm.setId("recipientClm");
		recipientClm.setCellValueFactory(new PropertyValueFactory<EMail, String>("recipient"));
		
		TableColumn<EMail, String> subjectClm = new TableColumn<>("Subject");
		subjectClm.setId("subjectClm");
		subjectClm.setCellValueFactory(new PropertyValueFactory<EMail, String>("subject"));
		
		
		TableColumn<EMail, String> spamClm = new TableColumn<>("Spam");
		spamClm.setId("spamClm");
		spamClm.setCellValueFactory(new PropertyValueFactory<EMail, String>("spam"));

		//HH create tableview
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().addAll(senderClm, recipientClm, subjectClm, spamClm);
		table.setEditable(true);

		//PD bottom elements
		lowerBar = new HBox();
		lowerBar.setId("lowerBar");
		
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		
		Region spacer2 = new Region();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		
		spamLbl = new Label("Add E-Mails");
		spamLbl.setId("spamLbl");
		deleteSpamBtn = new Button("Delete Spam");
		lowerBar.getChildren().addAll(spamLbl, spacer1, deleteSpamBtn);
		
		//PD root Borderpane
		root = new BorderPane();
		root.setId("root");
		root.setTop(topBar);
		root.setCenter(table);
		root.setBottom(lowerBar);
		
		//PD create scene
		Scene scene = new Scene(root);
		stage.setMaximized(true);

		scene.getStylesheets().add(getClass().getResource("Spam.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Spam Detector");

	}

	//PD start method
	public void start() {
		stage.show();
	}

}
