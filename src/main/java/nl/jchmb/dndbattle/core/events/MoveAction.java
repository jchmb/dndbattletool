package nl.jchmb.dndbattle.core.events;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import nl.jchmb.dndbattle.core.Vector2;

public class MoveAction extends BattleAction {
	private final ObjectProperty<Vector2> target = new SimpleObjectProperty<>(new Vector2(0, 0));
	
	@Override
	public String getText() {
		return String.format(
			"[b]%s[/b] at %s moved to %s.",
			getSubject().getName(),
			getSubject().getPosition().toString(),
			target.toString()
		);
	}

	public final ObjectProperty<Vector2> targetProperty() {
		return this.target;
	}
	

	public final Vector2 getTarget() {
		return this.targetProperty().get();
	}
	

	public final void setTarget(final Vector2 target) {
		this.targetProperty().set(target);
	}
	
	
}
