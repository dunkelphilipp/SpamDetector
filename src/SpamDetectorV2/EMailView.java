//package SpamDetectorV2;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextFlow;
//import javafx.stage.Stage;
//
//public class EMailView {
//	private Stage stage;
//	private SpamModel model;
//	private int dataIndex;
//	
//	Label lblMailView = new Label("Email View");
//	Label lblViewMailHeading = new Label("View Email");
//	
//	public EMailView(Stage stage, SpamModel model, int dataIndex) {
//		this.stage = stage;
//		this.model = model;
//		this.dataIndex = dataIndex;
//		
//		BorderPane root = new BorderPane();
//		root.setCenter(createRightPane());
//
//		// Standard stuff for Scene and Stage
//		Scene scene = new Scene(root);
//		scene.getStylesheets().add(
//				getClass().getResource("/SpamDedector.css").toExternalForm());
//		stage.setTitle("Email");
//		stage.setScene(scene);
//		stage.show();
//	}
//	
//	public Stage getStage() {
//		return stage;
//	}
//	
//	public void start() {
//		stage.show();
//	}
//	
//	private Pane createRightPane() {
//		VBox pane = new VBox();
//		pane.getStyleClass().add("allViews");
//		lblMailView.getStyleClass().add("heading");
//		
//		pane.getChildren().add(lblMailView);
//		pane.getChildren().add(createRightGrid());
//		//TODO
//		
//		return pane;
//	}
//	
//	public ScrollPane createRightGrid() {
//		VBox pane = new VBox();
//		pane.getStyleClass().add("mailViewPadding");
//		//HBox senderPane = new HBox();
//		
//		//Get date from model via passed index and display the data
//		Text mailViewSender = new Text("Sender: \t\t");
//		Text mailViewSenderText = new Text(model.mails.get(dataIndex).getSender() + "\n");
//		mailViewSender.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//		Text mailViewReceiver = new Text("Receiver: \t");
//		Text mailViewReceiverText = new Text(model.mails.get(dataIndex).getRecipient() + "\n");
//		mailViewReceiver.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//		Text mailViewSubject = new Text("Subject: \t\t");
//		Text mailViewSubjectText = new Text(model.mails.get(dataIndex).getSubject() + "\n\n");
//		mailViewSubject.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//
////		Text mailViewContent = new Text("Content: \n");
////		Text mailViewContentText = new Text(model.mails.get(dataIndex).getContent() + "\n");
////		mailViewContent.setFont(Font.font("Arial", FontWeight.BOLD, 12));
////		
//		TextFlow mailViewAll = new TextFlow(
//				mailViewSender, mailViewSenderText,
//				mailViewReceiver,mailViewReceiverText,
//				mailViewSubject, mailViewSubjectText,
//				mailViewContent, mailViewContentText);
//		
//		mailViewAll.setMaxWidth(500);
//		mailViewAll.setLineSpacing(5);
//		
//		pane.getChildren().add(mailViewAll);
//		
//		//make content scrollable
//		ScrollPane sPane = new ScrollPane();
//		sPane.setContent(pane);
//	    sPane.setFitToWidth(true);
//	    sPane.setFitToHeight(true);
//		
//		return sPane;
//	}
//}
