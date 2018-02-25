package nl.jchmb.dndbattle.gui.avatars;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ListView;
import nl.jchmb.dndbattle.core.Avatar;
import nl.jchmb.dndbattle.core.Battle;

public class AvatarList extends ListView<Avatar> {
	private final ObjectProperty<Battle> battle;
	
	public AvatarList(final ObjectProperty<Battle> battle) {
		super();
		
		this.battle = battle;
	}
}
