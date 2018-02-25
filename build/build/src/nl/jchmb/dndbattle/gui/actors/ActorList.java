/**
 * 
 */
package nl.jchmb.dndbattle.gui.actors;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ListView;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;

/**
 * @author jochem
 *
 */
public class ActorList extends ListView<Actor> {
	public ActorList(final ObjectProperty<Battle> battle) {
		this.setWidth(200);
		this.setCellFactory(lv -> {
			return new ActorCell(battle.get(), true);
		});
		
		itemsProperty().bind(battle.get().actorsProperty());
	}

}
