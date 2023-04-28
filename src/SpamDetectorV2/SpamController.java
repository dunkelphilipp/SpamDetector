package SpamDetectorV2;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SpamController implements EventHandler<ActionEvent>{

	private final SpamView view;
	private final SpamModel model;
	
	private EMail mail;
	
	//constructor
	protected SpamController (SpamView view, SpamModel model) {
				

		this.model = model;
		this.view = view;
		
		view.deleteBtn.setDisable(true);
		view.deleteAllBtn.setDisable(true);
		view.checkBtn.setDisable(true);
		view.deleteSpamBtn.setDisable(true);
		view.openEmail.setDisable(true);
		

		view.deleteBtn.setStyle("-fx-border-color: transparent;");
		view.deleteAllBtn.setStyle("-fx-border-color: transparent;");
		view.checkBtn.setStyle("-fx-border-color: transparent;");
		view.deleteSpamBtn.setStyle("-fx-border-color: transparent;");
		view.openEmail.setStyle("-fx-border-color: transparent;");
		

		view.addBtn.setOnAction(this::handle);
		view.deleteBtn.setOnAction(this::handle);
		view.deleteAllBtn.setOnAction(this::handle);
		view.checkBtn.setOnAction(this::handle);
		view.deleteSpamBtn.setOnAction(this::handle);
		view.openEmail.setOnAction(this::handle);
	}

	@Override
	public void handle(ActionEvent e) {
		Button b = (Button) e.getSource();
		
		if (b == view.addBtn) {
			
			model.chooseEmlFile();
			model.add();
			view.table.setItems(model.getMailList());

			if (!model.mails.isEmpty()) {
				view.deleteBtn.setDisable(false);
				view.deleteAllBtn.setDisable(false);
				view.checkBtn.setDisable(false);
				view.openEmail.setDisable(false);
				view.deleteSpamBtn.setDisable(false);
			}
			
			
			
		} else if (b == view.deleteBtn) {
			
			EMail selectedItem = view.table.getSelectionModel().getSelectedItem();
				
			if (selectedItem != null) {
		        model.mails.remove(selectedItem);
		        
		        if (model.mails.isEmpty()) {
					view.deleteBtn.setDisable(true);
					view.deleteAllBtn.setDisable(true);
					view.checkBtn.setDisable(true);
					view.openEmail.setDisable(true);
					view.deleteSpamBtn.setDisable(true);
				}
		    }
			
		} 
		else if (b == view.deleteAllBtn) {
			model.mails.clear();
			view.deleteBtn.setDisable(true);
			view.deleteAllBtn.setDisable(true);
			view.checkBtn.setDisable(true);
			view.openEmail.setDisable(true);
			view.deleteSpamBtn.setDisable(true);
		} 
		else if (b == view.checkBtn) {
			
			model.checkSpam();
			
			
		} 
		else if (b == view.deleteSpamBtn) {
			
		}
		else if(b == view.openEmail) {
		
		    view.openEmail.setOnAction(event ->{
		    	
		    	EMail selected = view.table.getSelectionModel().getSelectedItem();
		    	
		    	
		    	
		    });
		}

	}
}

