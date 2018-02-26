package nl.jchmb.dndbattle.gui.overlays;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import nl.jchmb.dndbattle.core.Sizable;
import nl.jchmb.dndbattle.core.overlays.Overlay;
import nl.jchmb.dndbattle.utils.form.Form;

public class OverlayEditor extends Form {
	public OverlayEditor(final Overlay overlay) {
		super();
		
		/* Name */
		addStringField(
			overlay.nameProperty(),
			new TextField(),
			"Name"
		);
		
		/* Color */
		addColorField(
			overlay.colorProperty(),
			new ColorPicker(),
			"Color"
		);
		
		/* Opacity */
		addDoubleField(
			overlay.opacityProperty(),
			new Spinner<Double>(0.0d, 1.0d, 0.5d, 0.05d),
			"Opacity"
		);
		
		/* Size */
		addVector2Field(
			overlay.sizeProperty(),
			Sizable.getSpinner(),
			Sizable.getSpinner(),
			"Width",
			"Height"
		);
	}
}
