package spamdetector;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SpamDetectorView {
    private Stage stage;
    private Scene scene;
    private Label statusLabel;
    private TextArea messageTextArea;
    private Button checkButton;

    public SpamDetectorView(Stage stage) {
        this.stage = stage;
        this.statusLabel = new Label();
        this.messageTextArea = new TextArea();
        this.checkButton = new Button("Check for Spam");
        this.checkButton.setOnAction(event -> {
            SpamDetectorController controller = new SpamDetectorController(new SpamDetectorModel(), this);
            controller.setMessage(messageTextArea.getText());
            controller.checkForSpam();
        });

        VBox root = new VBox(10, new Label("Enter a message:"), messageTextArea, checkButton, statusLabel);
        root.setAlignment(Pos.CENTER);

        scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Spam Detector");
    }

    public void show() {
        stage.show();
    }

    public void showSpamStatus(boolean isSpam) {
        if (isSpam) {
            statusLabel.setText("This message is spam.");
        } else {
            statusLabel.setText("This message is not spam.");
        }
    }
}

