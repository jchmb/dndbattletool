package nl.jchmb.dndbattle.gui;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.gui.actors.ActorList;
import nl.jchmb.dndbattle.gui.avatars.AvatarList;
import nl.jchmb.dndbattle.gui.statuses.StatusList;

public class OverviewPane extends TabPane {
	private final ObjectProperty<Battle> battle;
	public final ActorList actorList;
	
	public OverviewPane(final ObjectProperty<Battle> battle) {
		super();
		
		this.battle = battle;
		this.actorList = new ActorList(battle);
		
		Tab actorTab = new Tab("Actors");
		actorTab.setClosable(false);
		actorTab.setContent(actorList);
		
		getTabs().addAll(
			actorTab
		);
	}
}
