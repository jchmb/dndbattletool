package nl.jchmb.dndbattle.gui.legend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
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
import nl.jchmb.dndbattle.utils.IndexedObject;
import nl.jchmb.dndbattle.utils.binding.BindingUtils;
import nl.jchmb.dndbattle.utils.binding.ListBinder;

public class LegendPane extends GridPane {
	private final Battle battle;
	private List<? extends Actor> mirrorList;
	private final ChangeListener<Number> initiativeListener =
		(prop, oldValue, newValue) -> onInitiativeChanged(prop, oldValue, newValue);
	private final ChangeListener<Boolean> activeListener =
		(prop, oldValue, newValue) -> onActiveChanged(prop, oldValue, newValue);
	private final ListBinder<Actor, IndexedObject<Actor>, Node> entryBinder;
		
	@SuppressWarnings("unchecked")
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
		
		entryBinder = ListBinder.indexedNodeListBinder(
			battle.actorsProperty(),
			getChildren(),
			x -> {
				final Node entry = buildEntry(x.getObject(), x.getIndex());
				GridPane.setConstraints(
					entry,
					x.getIndex() / battle.getGridSize().getY(),
					x.getIndex() % battle.getGridSize().getY()
				);
				return entry;
			},
			Actor::isActive,
			Comparator.comparingInt(Actor::getInitiative)
				.reversed(),
			battle.legendColumnsProperty(),
			battle.legendEntryEvenColorProperty(),
			battle.legendEntryOddColorProperty(),
			battle.legendEntryFontColorProperty(),
			battle.legendEntryHeightProperty()
		);
		entryBinder.addPropertyGetter(Actor::initiativeProperty);
		entryBinder.addPropertyGetter(Actor::activeProperty);
		entryBinder.bind();
		
		// TODO: initiative + active listener integrated in new style
		
//		battle.actorsProperty().addListener(this::onChanged);
		
//		addChangeListeners(
//			battle.legendColumnsProperty(),
//			battle.legendEntryEvenColorProperty(),
//			battle.legendEntryOddColorProperty()
//		);
		
//		rebuild(battle.actorsProperty());
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
			if (!actor.isActive()) {
				continue;
			}
			actor.initiativeProperty().removeListener(initiativeListener);
			actor.activeProperty().removeListener(activeListener);
			actor.initiativeProperty().addListener(initiativeListener);
			actor.activeProperty().addListener(activeListener);
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

	private void onActiveChanged(ObservableValue<? extends Boolean> property, Boolean oldValue, Boolean newValue) {
		rebuild(battle.getActors());
	}
	
	private void onInitiativeChanged(ObservableValue<? extends Number> property, Number oldValue, Number newValue) {
		rebuild(battle.getActors());
	}
}
