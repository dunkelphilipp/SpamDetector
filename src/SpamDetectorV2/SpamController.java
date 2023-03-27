package SpamDetectorV2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SpamController implements EventHandler<ActionEvent>{
	//declare view and model
	private final SpamView view;
	private final SpamModel model;
	
	//constructor
	protected SpamController (SpamView view, SpamModel model) {
		//reference view and model objects
		this.model = model;
		this.view = view;
		
		//Action events for Buttons
		view.addBtn.setOnAction(this::handle);
		view.deleteBtn.setOnAction(this::handle);
		view.deleteAllBtn.setOnAction(this::handle);
		view.checkBtn.setOnAction(this::handle);
		view.deleteSpamBtn.setOnAction(this::handle);
	}

	@Override
	public void handle(ActionEvent e) {
		Button b = (Button) e.getSource();
		
		if (b == view.addBtn) {
			model.add();
		} else if (b == view.deleteBtn) {
			
		} else if (b == view.deleteAllBtn) {
			
		} else if (b == view.checkBtn) {
			model.checkSpam();
		} else if (b == view.deleteSpamBtn) {
			
		}
	}
}
