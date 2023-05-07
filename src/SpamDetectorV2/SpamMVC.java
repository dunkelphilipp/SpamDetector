package SpamDetectorV2;

import javafx.application.Application;
import javafx.stage.Stage;

//PD Mainclass Spamdetector
public class SpamMVC extends Application{
	private SpamView view;
	private SpamModel model;
	private SpamController controller;
	
	//PD start method
	@Override
	public void start(Stage stage) throws Exception {
		model = new SpamModel();
		view = new SpamView (stage, model);
		controller = new SpamController(view, model);
		
		view.start();
	}
	
	//PD Main method
	public static void main (String[] args) {
		launch(args);
	}

}
