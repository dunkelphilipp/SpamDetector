package SpamDetectorV2;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
public class SpamController implements EventHandler<ActionEvent>{
	private final SpamView view;
	private final SpamModel model;
	private EMail mail;
	
	//EK / PD constructor
	protected SpamController (SpamView view, SpamModel model) {
		this.model = model;
		this.view = view;
		
		//EK Disable buttons if no EMail is added
		view.deleteBtn.setDisable(true);
		view.deleteAllBtn.setDisable(true);
		view.checkBtn.setDisable(true);
		view.deleteSpamBtn.setDisable(true);
		view.openEmail.setDisable(true);
		
		//EK set disabled Button Style
		view.deleteBtn.setStyle("-fx-border-color: transparent;");
		view.deleteAllBtn.setStyle("-fx-border-color: transparent;");
		view.checkBtn.setStyle("-fx-border-color: transparent;");
		view.deleteSpamBtn.setStyle("-fx-border-color: transparent;");
		view.openEmail.setStyle("-fx-border-color: transparent;");
		
		//EK set Buttons on handler 
		view.addBtn.setOnAction(this::handle);
		view.deleteBtn.setOnAction(this::handle);
		view.deleteAllBtn.setOnAction(this::handle);
		view.checkBtn.setOnAction(this::handle);
		view.deleteSpamBtn.setOnAction(this::handle);
		view.openEmail.setOnAction(this::handle);
	}

	//EK / PD handle method
	@Override
	public void handle(ActionEvent e) {
		//PD get Button source
		Button b = (Button) e.getSource();
		
		//Mehtods for each Button
		//EK Add a new EMail to table 
		if (b == view.addBtn) {
			model.chooseEmlFile();
			model.add();
			view.table.setItems(model.getMailList());

			//Update Buttons 
			if (!model.mails.isEmpty()) {
				view.deleteBtn.setDisable(false);
				view.deleteAllBtn.setDisable(false);
				view.checkBtn.setDisable(false);
				view.openEmail.setDisable(false);
				view.deleteSpamBtn.setDisable(false);
			}
			
		//EK delete selected EMail from table
		} else if (b == view.deleteBtn) {
			EMail selectedItem = view.table.getSelectionModel().getSelectedItem();
	
			if (selectedItem != null) {
		        model.mails.remove(selectedItem);
		        
		      //Update Buttons 
		        if (model.mails.isEmpty()) {
					view.deleteBtn.setDisable(true);
					view.deleteAllBtn.setDisable(true);
					view.checkBtn.setDisable(true);
					view.openEmail.setDisable(true);
					view.deleteSpamBtn.setDisable(true);
				}
		    }
			
		//EK delete all EMails from table
		} else if (b == view.deleteAllBtn) {
			model.mails.clear();
			
			//Update Buttons 
			view.deleteBtn.setDisable(true);
			view.deleteAllBtn.setDisable(true);
			view.checkBtn.setDisable(true);
			view.openEmail.setDisable(true);
			view.deleteSpamBtn.setDisable(true);
			
		//PD check if Spam
		} else if (b == view.checkBtn) {
			model.checkSpam();
			view.table.refresh();
			
		//EK delete the Spammails
		} else if (b == view.deleteSpamBtn) {
			ArrayList<EMail> spamMails = new ArrayList<>();
			for (EMail m : model.mails) {
			    if (m.isSpam()) {
			        spamMails.add(m);
			    }
			}
			model.mails.removeAll(spamMails);
			view.table.refresh();
			
		//EK Show complete EMail
		} else if(b == view.openEmail) {
		    view.openEmail.setOnAction(event ->{
		        EMail selectedRow = ((TableView<EMail>) view.table).getSelectionModel().getSelectedItem();
		        int index = ((TableView<EMail>) view.table).getItems().indexOf(selectedRow);
		        EMailView emailView = new EMailView(new Stage(), model, index);
		        emailView.start();
		    });
		}
	}
}

