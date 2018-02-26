package nl.jchmb.dndbattle.core.effects;

import java.util.stream.Collectors;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.jchmb.dndbattle.core.Battle;

public class ConjunctionEffect implements Effect {
	private final ListProperty<Effect> effects = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	
	@Override
	public String getXml(Battle battle) {
		if (effects.size() <= 2) {
			return effects.stream()
				.map(effect -> effect.getXml(battle))
				.collect(Collectors.joining("and"));
		} else {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < effects.size(); i++) {
				if (i > 0) {
					builder.append(", ");
				}
				if (i == effects.size() - 1) {
					builder.append(" and ");
				}
				builder.append(effects.get(i).getXml(battle));
			}
			return builder.toString();
		}
	}

	@Override
	public void perform(Battle battle) {
		effects.stream()
			.forEach(effect -> effect.perform(battle));
	}

	@Override
	public void undo(Battle battle) {
		/* Iteration needs to happen in reverse order to get the inverse of the entire conjunction. */
		for (int i = effects.size() - 1; i >= 0; i--) {
			effects.get(i).undo(battle);
		}
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
	
	
}
