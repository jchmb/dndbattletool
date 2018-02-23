package nl.jchmb.dndbattle.utils;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popups {
	public static Stage create(final Parent content, final Stage root) {
		final Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(root);
		final Scene scene = new Scene(content);
		stage.setScene(scene);
		return stage;
	}
	
	public static void show(final Parent content, final Stage root) {
		create(content, root).show();
	}
}