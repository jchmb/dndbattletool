package nl.jchmb.dndbattle.gui.overlays;

import javafx.scene.control.ListView;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.overlays.Overlay;

public class OverlayList extends ListView<Overlay> {
	public OverlayList(final Battle battle) {
		super();
		
		this.setCellFactory(
			lv -> new OverlayCell(battle.overlaysProperty(), Overlay::nameProperty)
		);
		
		this.itemsProperty().bind(battle.overlaysProperty());
	}
}
