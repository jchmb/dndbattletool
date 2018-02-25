package nl.jchmb.dndbattle.gui.options;

import javafx.scene.layout.BorderPane;
import nl.jchmb.dndbattle.core.Battle;

public class TilesetOptions extends BorderPane {
	private final Battle battle;
	
	public TilesetOptions(final Battle battle) {
		super();
		
		this.battle = battle;
		
	}
}
