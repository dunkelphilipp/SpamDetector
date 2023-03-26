package SpamDetectorV2;

import javafx.application.Application;
import javafx.stage.Stage;

public class SpamMVC extends Application{
	//PD declare MVC classes objects
	private SpamView view;
	private SpamModel model;
	private SpamController controller;
	
	//Override start method 
	@Override
	public void start(Stage stage) throws Exception {
		//instantiate MVC classes objects
		model = new SpamModel();
		view = new SpamView (stage, model);
		controller = new SpamController(view, model);
		
		//Call start method 
		view.start();
	}
	
	//Main method
	public static void main (String[] args) {
		launch(args);
	}

}
