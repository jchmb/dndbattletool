package nl.jchmb.dndbattle.gui.legend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.utils.BindingUtils;

public class LegendPane extends GridPane {
	private final Battle battle;
	private List<? extends Actor> mirrorList;
	private final ChangeListener<Number> initiativeListener =
		(prop, oldValue, newValue) -> onInitiativeChanged(prop, oldValue, newValue);
	
	public LegendPane(final Battle battle) {
		super();
		
		this.battle = battle;
		this.backgroundProperty().bind(
			BindingUtils.binding(
				battle.legendEntryEvenColorProperty(),
				color -> new Background(new BackgroundFill(color, null, null))
			)
		);
		setAlignment(Pos.TOP_RIGHT);
		battle.actorsProperty().addListener(this::onChanged);
		
		addChangeListeners(
			battle.legendColumnsProperty(),
			battle.legendEntryEvenColorProperty(),
			battle.legendEntryOddColorProperty()
		);
		
		rebuild(battle.actorsProperty());
	}
	
	private <T> void addChangeListener(Property<T> property) {
		property.addListener(event -> {
			rebuild(battle.actorsProperty());
		});
	}
	
	private void addChangeListeners(Property<?>... properties) {
		for (Property<?> property : properties) {
			addChangeListener(property);
		}
	}
	
	private void onChanged(Change<? extends Actor> change) {
		rebuild(change.getList());
	}
	
	private void rebuild(List<? extends Actor> list) {
		getChildren().clear();
		mirrorList = new ArrayList<>(list);
		mirrorList.sort(
			Comparator.<Actor>comparingInt(actor -> actor.getInitiative())
				.reversed()
		);
		int index = 0;
		int column = 0;
		for (Actor actor : mirrorList) {
			actor.initiativeProperty().removeListener(initiativeListener);
			actor.initiativeProperty().addListener(initiativeListener);
			add(buildEntry(actor, index), column, index);
			index++;
			if (index >= battle.getGridSize().getY()) {
				column++;
				index = 0;
			}
		}
	}
	
	private Node buildEntry(Actor actor, int index) {
		return new LegendEntry(battle, actor, index);
	}
	
	private void onInitiativeChanged(ObservableValue<? extends Number> property, Number oldValue, Number newValue) {
		rebuild(battle.getActors());
	}
}
