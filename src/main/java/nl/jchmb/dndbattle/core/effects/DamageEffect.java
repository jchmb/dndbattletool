package nl.jchmb.dndbattle.core.effects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;

public class DamageEffect implements Effect {
	private final ObjectProperty<Actor> subject = new SimpleObjectProperty<>();
	private final StringProperty weapon = new SimpleStringProperty();
	private final ObjectProperty<Actor> target = new SimpleObjectProperty<>();
	private final IntegerProperty value = new SimpleIntegerProperty();
	private final BooleanProperty missed = new SimpleBooleanProperty(false);
	
	public DamageEffect() {
		
	}
	
	@Override
	public String getXml(Battle battle) {
		String attackMessage = String.format(
			"%s attacked %s with %s %s",
			getSubject()
				.asSubjectXml(),
			getTarget()
				.asObjectXml(),
			getSubject()
				.getGender()
				.getPossessivePronoun(),
			getWeapon()
		);
		if (isMissed()) {
			return String.format(
				"%s, but %s missed",
				attackMessage,
				getSubject()
					.getGender()
					.getSubjectPronoun()
			);
		} else {
			return String.format(
				"%s and dealt <damage>%s</damage>",
				attackMessage,
				getValue()
			);
		}
	}

	@Override
	public void perform(Battle battle) {
		if (!isMissed()) {
			getTarget().currentHpProperty()
				.subtract(getValue());
		}
	}

	@Override
	public void undo(Battle battle) {
		if (!isMissed()) {
			getTarget().currentHpProperty()
				.add(getValue());
		}
	}

	public final ObjectProperty<Actor> targetProperty() {
		return this.target;
	}
	

	public final Actor getTarget() {
		return this.targetProperty().get();
	}
	

	public final void setTarget(final Actor target) {
		this.targetProperty().set(target);
	}
	

	public final IntegerProperty valueProperty() {
		return this.value;
	}
	

	public final int getValue() {
		return this.valueProperty().get();
	}
	

	public final void setValue(final int value) {
		this.valueProperty().set(value);
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
	

	public final StringProperty weaponProperty() {
		return this.weapon;
	}
	

	public final String getWeapon() {
		return this.weaponProperty().get();
	}
	

	public final void setWeapon(final String weapon) {
		this.weaponProperty().set(weapon);
	}

	public final BooleanProperty missedProperty() {
		return this.missed;
	}
	

	public final boolean isMissed() {
		return this.missedProperty().get();
	}
	

	public final void setMissed(final boolean missed) {
		this.missedProperty().set(missed);
	}
	
	
	

}
