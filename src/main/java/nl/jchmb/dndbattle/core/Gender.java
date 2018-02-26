package nl.jchmb.dndbattle.core;

import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gender {
	public static final Gender MALE = new Gender(
		1,
		"Male",
		"he",
		"his",
		"him"
	);
	public static final Gender FEMALE = new Gender(
		2,
		"Female",
		"she",
		"her",
		"her"
	);
	public static final Gender OTHER = new Gender(
		0,
		"Other",
		"they",
		"them",
		"their"
	);
	
	public Gender() {
		setId(Math.abs(new Random().nextInt() + 3));
	}
	
	public Gender(
		final int id,
		final String symbol,
		final String subjectPronoun,
		final String possessivePronoun,
		final String objectPronoun
	) {
		setId(id);
		setSymbol(symbol);
		setSubjectPronoun(subjectPronoun);
		setPossessivePronoun(possessivePronoun);
		setObjectPronoun(objectPronoun);
	}
	
	private final StringProperty symbol = new SimpleStringProperty("Undetermined");
	private final StringProperty subjectPronoun = new SimpleStringProperty("they");
	private final StringProperty possessivePronoun = new SimpleStringProperty("their");
	private final StringProperty objectPronoun = new SimpleStringProperty("them");
	private final IntegerProperty id = new SimpleIntegerProperty();

	public final StringProperty symbolProperty() {
		return this.symbol;
	}
	

	public final String getSymbol() {
		return this.symbolProperty().get();
	}
	

	public final void setSymbol(final String symbol) {
		this.symbolProperty().set(symbol);
	}
	

	public final StringProperty subjectPronounProperty() {
		return this.subjectPronoun;
	}
	

	public final String getSubjectPronoun() {
		return this.subjectPronounProperty().get();
	}
	

	public final void setSubjectPronoun(final String subjectPronoun) {
		this.subjectPronounProperty().set(subjectPronoun);
	}
	

	public final StringProperty possessivePronounProperty() {
		return this.possessivePronoun;
	}
	

	public final String getPossessivePronoun() {
		return this.possessivePronounProperty().get();
	}
	

	public final void setPossessivePronoun(final String possessivePronoun) {
		this.possessivePronounProperty().set(possessivePronoun);
	}
	

	public final StringProperty objectPronounProperty() {
		return this.objectPronoun;
	}
	

	public final String getObjectPronoun() {
		return this.objectPronounProperty().get();
	}
	

	public final void setObjectPronoun(final String objectPronoun) {
		this.objectPronounProperty().set(objectPronoun);
	}
	
	@Override
	public String toString() {
		return getSymbol();
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	
}
