package spamdetector;

public class SpamDetectorController {
    private SpamDetectorModel model;
    private SpamDetectorView view;

    public SpamDetectorController(SpamDetectorModel model, SpamDetectorView view) {
        this.model = model;
        this.view = view;
    }

    public void setMessage(String message) {
        model.setMessage(message);
    }

    public void checkForSpam() {
        boolean isSpam = model.isSpam();
        view.showSpamStatus(isSpam);
    }
}

