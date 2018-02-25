package nl.jchmb.dndbattle.gui.tiles;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Tile;
import nl.jchmb.dndbattle.gui.actors.ActorCell;

public class TileList extends GridPane {
	private int columnIndex = 0;
	private int numColumns = 3;
	
	public TileList(final ObjectProperty<Battle> battle) {
		
	}
}
