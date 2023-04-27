package SpamDetectorV2;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SpamController implements EventHandler<ActionEvent>{
	//declare view and model
	private final SpamView view;
	private final SpamModel model;
	
	EMail mail;
	
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
		view.openEmail.setOnAction(this::handle);
	}

	@Override
	public void handle(ActionEvent e) {
		Button b = (Button) e.getSource();
		
		if (b == view.addBtn) {
			
			model.chooseEmlFile();
			model.add();
			view.table.setItems(model.getMailList());
			
			
			
		} else if (b == view.deleteBtn) {
			
			view.deleteBtn.setOnAction(event -> {
				
				EMail selectedItem = view.table.getSelectionModel().getSelectedItem();
				
				if (selectedItem != null) {
		                model.mails.remove(selectedItem);
		                
		            }
		        });	
		} 
		else if (b == view.deleteAllBtn) {
			model.mails.clear();
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
