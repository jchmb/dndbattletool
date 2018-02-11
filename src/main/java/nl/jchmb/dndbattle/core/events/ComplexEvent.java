package nl.jchmb.dndbattle.core.events;

import java.util.stream.Collectors;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComplexEvent implements BattleEvent {
	private final ListProperty<BattleEvent> events = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	
	@Override
	public String getText() {
		return events.stream()
			.map(BattleEvent::getText)
			.collect(Collectors.joining(" "));
	}

	public final ListProperty<BattleEvent> eventsProperty() {
		return this.events;
	}
	

	public final ObservableList<BattleEvent> getEvents() {
		return this.eventsProperty().get();
	}
	

	public final void setEvents(final ObservableList<BattleEvent> events) {
		this.eventsProperty().set(events);
	}
	
	
}
