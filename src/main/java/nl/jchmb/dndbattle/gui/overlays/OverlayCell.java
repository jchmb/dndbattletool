package nl.jchmb.dndbattle.gui.overlays;

import java.util.function.Function;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Entity;
import nl.jchmb.dndbattle.core.overlays.Overlay;
import nl.jchmb.dndbattle.gui.entities.EntityCell;
import nl.jchmb.dndbattle.utils.CRUDCell;
import nl.jchmb.dndbattle.utils.form.Form;

public class OverlayCell extends CRUDCell<Overlay> {

	public OverlayCell(ListProperty<Overlay> list, Function<Overlay, StringProperty> namePropertyFn) {
		super(list, namePropertyFn);
	}

	@Override
	protected ObjectBinding<ImageView> getGraphicBinding(Overlay item) {
		return new ObjectBinding<>() {
			
			@Override
			protected ImageView computeValue() {
				return null;
			}
			
		};
	}

	@Override
	protected Form getEditor(Overlay overlay) {
		return new OverlayEditor(overlay);
	}
	
	public static ContextMenu createContextMenu(final Overlay overlay, final Battle battle) {
		return new OverlayCell(battle.overlaysProperty(), Overlay::nameProperty)
			.produceContextMenu(overlay);
	}
	
}
