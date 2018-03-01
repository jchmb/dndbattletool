package nl.jchmb.dndbattle.core.events;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.effects.Effect;
import nl.jchmb.dndbattle.core.effects.NullEffect;

public class BattleAction implements BattleEvent {
	private final ObjectProperty<Actor> subject = new SimpleObjectProperty<>();
	private final ObjectProperty<Effect> effect = new SimpleObjectProperty<>(new NullEffect());
	
	@Override
	public String getXml(final Battle battle) {
		return getEffect().getXml(battle, true);
	}

	public final ObjectProperty<Actor> subjectProperty() {
		return this.subject;
	}
	

	public final Actor getSubject() {
		return this.subjectProperty().get();
	}
	

	public final void setSubject(final Actor subject) {
		this.subjectProperty().set(subject);
	}
	

	public final ObjectProperty<Effect> effectProperty() {
		return this.effect;
	}
	

	public final Effect getEffect() {
		return this.effectProperty().get();
	}
	

	public final void setEffect(final Effect effect) {
		this.effectProperty().set(effect);
	}
	
	
}
