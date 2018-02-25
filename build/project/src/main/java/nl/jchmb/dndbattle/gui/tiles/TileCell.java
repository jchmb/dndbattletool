package nl.jchmb.dndbattle.gui.tiles;

import java.util.function.Function;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import nl.jchmb.dndbattle.core.Tile;
import nl.jchmb.dndbattle.utils.CRUDCell;
import nl.jchmb.dndbattle.utils.form.PopOverForm;

public class TileCell extends CRUDCell<Tile> {

	public TileCell(ListProperty<Tile> list, Function<Tile, StringProperty> namePropertyFn) {
		super(list, namePropertyFn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ObjectBinding<ImageView> getGraphicBinding(Tile item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PopOverForm getEditor(Tile item) {
		// TODO Auto-generated method stub
		return null;
	}

}
