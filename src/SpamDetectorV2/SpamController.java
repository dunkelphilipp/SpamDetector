package SpamDetectorV2;

public class SpamController {
	//declare view and model
	private final SpamView view;
	private final SpamModel model;
	
	//constructor
	protected SpamController (SpamView view, SpamModel model) {
		//reference view and model objects
		this.model = model;
		this.view = view;
	}
}
