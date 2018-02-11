package nl.jchmb.dndbattle.core.events.registry;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.jchmb.dndbattle.core.events.BattleEvent;

public class BattleEventRegistry {
	private final ListProperty<BattleEventRegistration> registrations = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);

	public final ListProperty<BattleEventRegistration> registrationsProperty() {
		return this.registrations;
	}
	

	public final ObservableList<BattleEventRegistration> getRegistrations() {
		return this.registrationsProperty().get();
	}
	

	public final void setRegistrations(final ObservableList<BattleEventRegistration> registrations) {
		this.registrationsProperty().set(registrations);
	}
	
	public BattleEventRegistry register(final String name, final Class<? extends BattleEvent> eventClass) {
		BattleEventRegistration registration = new BattleEventRegistration();
		registration.setName(name);
		registration.setEventClass(eventClass);
		registrations.add(registration);
		return this;
	}
}
