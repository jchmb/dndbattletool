package nl.jchmb.dndbattle.core.events;

import java.util.stream.Collectors;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

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
	
}
