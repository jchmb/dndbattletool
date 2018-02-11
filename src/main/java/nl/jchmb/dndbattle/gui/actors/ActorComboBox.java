package nl.jchmb.dndbattle.gui.actors;

import javafx.scene.control.ComboBox;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.gui.statuses.StatusCell;

public class ActorComboBox extends ComboBox<Actor> {
	public ActorComboBox(final Battle battle) {
		super();
		
		this.setCellFactory(lv -> {
			return new ActorCell(
				battle,
				false
			);
		});
		
		itemsProperty().bind(battle.actorsProperty());
		buttonCellProperty().set(new ActorCell(battle, false));
	}
}
