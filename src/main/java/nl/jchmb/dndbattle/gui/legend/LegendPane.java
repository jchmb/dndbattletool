package nl.jchmb.dndbattle.gui.legend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;

public class LegendPane extends VBox {
	private ObjectProperty<Battle> battle;
	private List<? extends Actor> mirrorList;
	private final ChangeListener<Number> initiativeListener =
		(prop, oldValue, newValue) -> onInitiativeChanged(prop, oldValue, newValue);
	
	public LegendPane(final ObjectProperty<Battle> battle) {
		super();
		
		this.battle = battle;
		this.setPrefWidth(250.0f);
		battle.get().actorsProperty().addListener(this::onChanged);
	}
	
	private void onChanged(Change<? extends Actor> change) {
		rebuild(change.getList());
	}
	
	private void rebuild(List<? extends Actor> list) {
//		getChildren().stream()
//			.map(LegendEntry.class::cast)
//			.forEach(entry -> entry.getActor().);
		getChildren().clear();
		mirrorList = new ArrayList<>(list);
		mirrorList.sort(
			Comparator.<Actor>comparingInt(actor -> actor.getInitiative())
				.reversed()
		);
		int index = 0;
		for (Actor actor : mirrorList) {
			actor.initiativeProperty().removeListener(initiativeListener);
			actor.initiativeProperty().addListener(initiativeListener);
			getChildren().add(buildEntry(actor, index++));
		}
	}
	
	private Node buildEntry(Actor actor, int index) {
		return new LegendEntry(battle.get(), actor, index);
	}
	
	private void onInitiativeChanged(ObservableValue<? extends Number> property, Number oldValue, Number newValue) {
		rebuild(battle.get().getActors());
	}
}
