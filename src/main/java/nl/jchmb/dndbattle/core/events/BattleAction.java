package nl.jchmb.dndbattle.core.events;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.utils.form.Form;

public abstract class BattleAction implements BattleEvent {
	private final ObjectProperty<Actor> subject = new SimpleObjectProperty<>();

	public final ObjectProperty<Actor> subjectProperty() {
		return this.subject;
	}
	

	public final Actor getSubject() {
		return this.subjectProperty().get();
	}
	

	public final void setSubject(final Actor subject) {
		this.subjectProperty().set(subject);
	}

	@Override
	public Form buildForm(Battle battle, Form form) {
		
	}
}
