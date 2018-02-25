package nl.jchmb.dndbattle.http;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CharacterSheet {
	private final StringProperty name = new SimpleStringProperty();
	private final IntegerProperty currentHp = new SimpleIntegerProperty();
	private final IntegerProperty maxHp = new SimpleIntegerProperty();
	private final IntegerProperty initiative = new SimpleIntegerProperty();
	private final IntegerProperty ac = new SimpleIntegerProperty();
	private final IntegerProperty flatfooted = new SimpleIntegerProperty();
	private final IntegerProperty touch = new SimpleIntegerProperty();
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	
	public final IntegerProperty currentHpProperty() {
		return this.currentHp;
	}
	
	public final int getCurrentHp() {
		return this.currentHpProperty().get();
	}
	
	public final void setCurrentHp(final int currentHp) {
		this.currentHpProperty().set(currentHp);
	}
	
	public final IntegerProperty maxHpProperty() {
		return this.maxHp;
	}
	
	public final int getMaxHp() {
		return this.maxHpProperty().get();
	}
	
	public final void setMaxHp(final int maxHp) {
		this.maxHpProperty().set(maxHp);
	}
	
	public final IntegerProperty initiativeProperty() {
		return this.initiative;
	}
	
	public final int getInitiative() {
		return this.initiativeProperty().get();
	}
	
	public final void setInitiative(final int initiative) {
		this.initiativeProperty().set(initiative);
	}
	
	public final IntegerProperty acProperty() {
		return this.ac;
	}
	
	public final int getAc() {
		return this.acProperty().get();
	}
	
	public final void setAc(final int ac) {
		this.acProperty().set(ac);
	}
	
	public final IntegerProperty flatfootedProperty() {
		return this.flatfooted;
	}
	
	public final int getFlatfooted() {
		return this.flatfootedProperty().get();
	}
	
	public final void setFlatfooted(final int flatfooted) {
		this.flatfootedProperty().set(flatfooted);
	}
	
	public final IntegerProperty touchProperty() {
		return this.touch;
	}
	
	public final int getTouch() {
		return this.touchProperty().get();
	}
	
	public final void setTouch(final int touch) {
		this.touchProperty().set(touch);
	}
	
	
	
}
