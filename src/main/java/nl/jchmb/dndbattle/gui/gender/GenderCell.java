package nl.jchmb.dndbattle.gui.gender;

import java.util.function.Function;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import nl.jchmb.dndbattle.core.Gender;
import nl.jchmb.dndbattle.utils.CRUDCell;
import nl.jchmb.dndbattle.utils.form.Form;

public class GenderCell extends CRUDCell<Gender> {
	private final boolean editable;
	private final boolean deletable;

	public GenderCell(ListProperty<Gender> list, Function<Gender, StringProperty> namePropertyFn, boolean editable, boolean deletable) {
		super(list, namePropertyFn);
		this.editable = editable;
		this.deletable = deletable;
	}

	@Override
	protected ObjectBinding<ImageView> getGraphicBinding(Gender gender) {
		return new ObjectBinding<>() {

			@Override
			protected ImageView computeValue() {
				return null;
			}
			
		};
	}

	@Override
	protected Form getEditor(Gender gender) {
		return new GenderEditor(gender);
	}
	
	@Override
	protected void buildContextMenu(ContextMenu contextMenu, Gender gender) {
		if (!gender.equals(Gender.OTHER)) {
			super.buildContextMenu(contextMenu, gender);
		}
	}

}
