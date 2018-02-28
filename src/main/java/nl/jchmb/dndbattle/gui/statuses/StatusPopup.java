package nl.jchmb.dndbattle.gui.statuses;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StatusPopup extends Stage {
	public StatusPopup(final Stage owner) {
		super();
		
		initModality(Modality.APPLICATION_MODAL);
		initOwner(owner);
		
		Label root = new Label("blabla");
		final Scene scene = new Scene(root, 300, 200);
		setScene(scene);
		show();
	}
}
