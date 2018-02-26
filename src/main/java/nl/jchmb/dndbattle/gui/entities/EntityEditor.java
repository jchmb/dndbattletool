package nl.jchmb.dndbattle.gui.entities;

import java.io.File;

import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import nl.jchmb.dndbattle.core.Constants;
import nl.jchmb.dndbattle.core.Entity;
import nl.jchmb.dndbattle.utils.form.Form;

public class EntityEditor extends Form {
	public EntityEditor(final Entity entity) {
		super();
		
		/* Name */
		addStringField(
			entity.nameProperty(),
			new TextField(),
			"Name"
		);
		
		/* Image */
		addImageFileFieldWithDefault(
			entity.avatarProperty(),
			new File("res/unknown.jpg"),
			"(no image)",
			"Reset",
			"Avatar"
		);
		
		/* Size */
		addVector2Field(
			entity.sizeProperty(),
			new Spinner<Integer>(
				Constants.MIN_OBJECT_SIZE,
				Constants.MAX_OBJECT_SIZE,
				1
			),
			new Spinner<Integer>(
				Constants.MIN_OBJECT_SIZE,
				Constants.MAX_OBJECT_SIZE,
				1
			),
			"Width",
			"Height"
		);
	}
}
