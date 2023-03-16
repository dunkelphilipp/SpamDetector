package spamdetector;

import javafx.application.Application;
import javafx.stage.Stage;

public class SpamDetectorMVC extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SpamDetectorView view = new SpamDetectorView(primaryStage);
        view.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

