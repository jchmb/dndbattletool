package nl.jchmb.dndbattle.gui.statuses;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.utils.PopOverForm;

public class StatusEditor extends PopOverForm {
	private final Status status;
	
	public StatusEditor(final Status status) {
		super();
		
		this.status = status;
		buildFields();
		finish();
	}
	
	private void buildFields() {
		/* Name */
		TextField nameField = new TextField();
		bind(nameField.textProperty(), status.nameProperty());
		
		/* Symbol */
		TextField symbolField = new TextField();
		bind(symbolField.textProperty(), status.symbolProperty());
		
		/* Background color */
		ColorPicker backgroundColorField = new ColorPicker();
		bind(backgroundColorField.valueProperty(), status.backgroundColorProperty());
		
		/* Text color */
		ColorPicker textColorField = new ColorPicker();
		bind(textColorField.valueProperty(), status.textColorProperty());
		
		addField(nameField, "Name");
		addField(symbolField, "Symbol");
		addField(backgroundColorField, "Background color");
		addField(textColorField, "Text color");
	}
}
