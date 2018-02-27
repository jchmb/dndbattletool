package nl.jchmb.dndbattle.gui.statuses;

import javafx.collections.FXCollections;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.utils.form.Form;

public class StatusEditor extends Form {
	private final Status status;
	
	public StatusEditor(final Status status) {
		super();
		
		this.status = status;
		buildFields();
	}
	
	private void buildFields() {
		/* Name */
		addStringField(
			status.nameProperty(),
			new TextField(),
			"Name"
		);
		
		/* Style mode */
//		final ComboBox<String> styleField = new ComboBox<>();
//		styleField.setItems(
//			FXCollections.observableArrayList(
//				"Text",
//				"Image"
//			)
//		);
//		styleField.setValue("Text");
//		
//		addField(
//			styleField,
//			"Graphics"
//		);
		
		/* Symbol */
		addStringField(
			status.symbolProperty(),
			new TextField(),
			"Symbol"
		);
		
		/* Background color */
		addColorField(
			status.backgroundColorProperty(),
			new ColorPicker(),
			"Background color"
		);
		
		/* Text color */
		addColorField(
			status.textColorProperty(),
			new ColorPicker(),
			"Text color"
		);
		
		/* Border color */
		addColorField(
			status.borderColorProperty(),
			new ColorPicker(),
			"Border color"
		);
		
		/* Text size */
		addIntegerField(
			status.textSizeProperty(),
			new Spinner<Integer>(4, 16, 1),
			"Text size"
		);
		
		/* Text offset */
		addVector2Field(
			status.textOffsetProperty(),
			new Spinner<Integer>(-16, 16, 1),
			new Spinner<Integer>(-16, 16, 1),
			"Text offset x",
			"Text offset y"
		);
		
		/* Image symbol */
//		addOptionalImageFileField(
//			status.imageFileProperty(),
//			"Image symbol"
//		);
//		
//		styleField.valueProperty().addListener(
//			(prop, oldValue, newValue) -> {
//				
//			}
//		);
	}
}
