package nl.jchmb.dndbattle.gui.statuses;

import javafx.scene.control.ColorPicker;
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
		
		/* Image symbol */
//		addOptionalImageFileField(
//			status.imageFileProperty(),
//			"Image symbol"
//		);
	}
}
