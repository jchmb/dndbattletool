package nl.jchmb.dndbattle.core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gender {
	public static final Gender MALE = new Gender(
		"M",
		"he",
		"his",
		"him"
	);
	public static final Gender FEMALE = new Gender(
		"F",
		"she",
		"her",
		"her"
	);
	public static final Gender OTHER = new Gender();
	
	public Gender() {
		
	}
	
	public Gender(
		final String symbol,
		final String subjectPronoun,
		final String possessivePronoun,
		final String objectPronoun
	) {
		
	}
	
	private final StringProperty symbol = new SimpleStringProperty("O");
	private final StringProperty subjectPronoun = new SimpleStringProperty("they");
	private final StringProperty possessivePronoun = new SimpleStringProperty("their");
	private final StringProperty objectPronoun = new SimpleStringProperty("them");

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
	
}
