/**
 * 
 */
package nl.jchmb.dndbattle.gui.actors;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
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
			return new ActorCell(battle, this);
		});
		
		itemsProperty().bind(battle.get().actorsProperty());
	}

}
