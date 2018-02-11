package nl.jchmb.dndbattle.core.events.registry;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nl.jchmb.dndbattle.core.events.BattleEvent;

public class BattleEventRegistration {
	private final ObjectProperty<Class<? extends BattleEvent>> eventClass = new SimpleObjectProperty<>();
	private final StringProperty name = new SimpleStringProperty();
	public final ObjectProperty<Class<? extends BattleEvent>> eventClassProperty() {
		return this.eventClass;
	}
	
	public final Class<? extends BattleEvent> getEventClass() {
		return this.eventClassProperty().get();
	}
	
	public final void setEventClass(final Class<? extends BattleEvent> eventClass) {
		this.eventClassProperty().set(eventClass);
	}
	
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	
	
	
}
