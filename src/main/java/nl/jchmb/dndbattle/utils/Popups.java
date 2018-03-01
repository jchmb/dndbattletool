package nl.jchmb.dndbattle.utils;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import nl.jchmb.dndbattle.utils.form.Form;
import nl.jchmb.dndbattle.utils.form.FormService;
import nl.jchmb.dndbattle.utils.form.TabbedForm;

public class Popups {
	public static Stage create(final Parent content, final Stage root, final String title) {
		final Stage stage = new Stage();
		stage.setTitle(title);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(root);
		final Scene scene = new Scene(content);
		stage.setScene(scene);
		return stage;
	}
	
	public static void show(final Parent content, final Stage root, final String title) {
		create(content, root, title).show();
	}
	
	public static void show(final Parent content, final String title) {
		show(content, (Stage) Window.getWindows().get(0), title);
	}
	
	public static void showForm(final Form form, final String title) {
		Stage stage = create(form, (Stage) Window.getWindows().get(0), title);
		stage.setOnCloseRequest(event -> {
			form.close();
		});
		stage.show();
	}
	
	public static void showForm(final TabbedForm form, final String title) {
		Stage stage = create(form, (Stage) Window.getWindows().get(0), title);
		stage.setOnCloseRequest(event -> {
			form.close();
		});
		stage.show();
	}
}
