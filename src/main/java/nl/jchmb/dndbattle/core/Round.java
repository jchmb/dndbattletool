package nl.jchmb.dndbattle.core;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import nl.jchmb.dndbattle.core.events.BattleEvent;

public class Round {
	private final StringProperty name = new SimpleStringProperty();
	private final IntegerProperty number = new SimpleIntegerProperty();
	private final ListProperty<BattleEvent> events = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}


	public final IntegerProperty numberProperty() {
		return this.number;
	}
	


	public final int getNumber() {
		return this.numberProperty().get();
	}
	


	public final void setNumber(final int number) {
		this.numberProperty().set(number);
	}
	
	
}
