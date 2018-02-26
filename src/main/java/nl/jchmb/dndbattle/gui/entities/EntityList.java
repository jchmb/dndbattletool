package nl.jchmb.dndbattle.gui.entities;

import javafx.scene.control.ListView;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Entity;

public class EntityList extends ListView<Entity> {
	public EntityList(final Battle battle) {
		super();
		
		this.setWidth(200);
		this.setCellFactory(lv -> {
			return new EntityCell(battle, battle.entitiesProperty(), Entity::nameProperty);
		});
		
		itemsProperty().bind(battle.entitiesProperty());
	}
}
