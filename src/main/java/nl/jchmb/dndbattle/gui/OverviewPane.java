package nl.jchmb.dndbattle.gui;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.gui.actors.ActorList;
import nl.jchmb.dndbattle.gui.entities.EntityList;

public class OverviewPane extends TabPane {
	private final ObjectProperty<Battle> battle;
	public final ActorList actorList;
	public final EntityList entityList;
	
	public OverviewPane(final ObjectProperty<Battle> battle) {
		super();
		
		this.battle = battle;
		this.actorList = new ActorList(battle);
		this.entityList = new EntityList(battle.get());
		
		Tab actorTab = new Tab("Actors");
		actorTab.setClosable(false);
		actorTab.setContent(actorList);
		
		Tab entityTab = new Tab("Objects");
		entityTab.setClosable(false);
		entityTab.setContent(entityList);
		
		getTabs().addAll(
			actorTab,
			entityTab
		);
	}
}
