package nl.jchmb.dndbattle.core.effects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Vector2;

public class MoveEffect implements Effect {
	private final ObjectProperty<Actor> subject = new SimpleObjectProperty<>();
	private final ObjectProperty<Vector2> translation = new SimpleObjectProperty<>(new Vector2(0, 0));
	
	@Override
	public String getXml(final Battle battle, final boolean primary) {
		return String.format(
			"%s moved from %s to %s",
			getSubject()
				.asSubjectXml(primary),
			getSubject()
				.getPosition()
				.getXml(),
			getSubject()
				.getPosition()
				.add(getTranslation())
				.getXml()
		);
	}

	@Override
	public void perform(Battle battle) {
		getSubject().setPosition(
			getSubject().getPosition()
				.add(getTranslation())
		);
	}

	@Override
	public void undo(Battle battle) {
		getSubject().setPosition(
			getSubject().getPosition()
				.subtract(getTranslation())
		);
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
	

	public final ObjectProperty<Vector2> translationProperty() {
		return this.translation;
	}
	

	public final Vector2 getTranslation() {
		return this.translationProperty().get();
	}
	

	public final void setTranslation(final Vector2 translation) {
		this.translationProperty().set(translation);
	}
	
	
}
