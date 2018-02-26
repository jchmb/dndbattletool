package nl.jchmb.dndbattle.core;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.jchmb.dndbattle.core.effects.Effect;

public class Round {
	private final IntegerProperty number = new SimpleIntegerProperty(1);
	private final ListProperty<Effect> effects = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	public final IntegerProperty numberProperty() {
		return this.number;
	}
	
	public final int getNumber() {
		return this.numberProperty().get();
	}
	
	public final void setNumber(final int number) {
		this.numberProperty().set(number);
	}
	
	public final ListProperty<Effect> effectsProperty() {
		return this.effects;
	}
	
	public final ObservableList<Effect> getEffects() {
		return this.effectsProperty().get();
	}
	
	public final void setEffects(final ObservableList<Effect> effects) {
		this.effectsProperty().set(effects);
	}
	
	@Override
	public String toString() {
		return String.format(
			"Round %s",
			getNumber()
		);
	}
	
}
