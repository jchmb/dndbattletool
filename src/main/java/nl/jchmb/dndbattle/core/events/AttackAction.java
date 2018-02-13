package nl.jchmb.dndbattle.core.events;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nl.jchmb.dndbattle.core.Actor;

public abstract class AttackAction extends BattleAction {
	private final ObjectProperty<Actor> target = new SimpleObjectProperty<>();
	private final StringProperty weapon = new SimpleStringProperty();
	
	@Override
	public String getText() {
		return String.format(
			"[b]%s[/b] at %s attacked [b]%s[/b] with %s %s and %s.",
			getSubject().getPosition().toString(),
			getSubject().getName(),
			getTarget().getName(),
			getSubject().getGender().getPossessivePronoun(),
			getWeapon(),
			getResultText()
		);
	}
	
	protected abstract String getResultText();

	public final ObjectProperty<Actor> targetProperty() {
		return this.target;
	}
	

	public final Actor getTarget() {
		return this.targetProperty().get();
	}
	

	public final void setTarget(final Actor target) {
		this.targetProperty().set(target);
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
	
	
	
}
