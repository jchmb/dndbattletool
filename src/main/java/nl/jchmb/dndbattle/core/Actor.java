/**
 * 
 */
package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.jchmb.dndbattle.core.genders.Gender;
import nl.jchmb.dndbattle.core.genders.Undetermined;

/**
 * @author jochem
 *
 */
public class Actor {
	private final StringProperty name = new SimpleStringProperty();
	private final IntegerProperty initiative = new SimpleIntegerProperty();
	private final ObjectProperty<File> avatar = new SimpleObjectProperty<>();
	private final StringProperty sheet = new SimpleStringProperty("");
	private final IntegerProperty currentHp = new SimpleIntegerProperty(1);
	private final IntegerProperty maxHp = new SimpleIntegerProperty(1);
	private final ObjectProperty<Vector2> position = new SimpleObjectProperty<Vector2>();
	private final ObjectProperty<Vector2> size = new SimpleObjectProperty<Vector2>(new Vector2(1, 1));
	private final BooleanProperty hiddenHp = new SimpleBooleanProperty(false);
	private final ListProperty<Status> statuses = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(new Undetermined());
	
	public Actor() {
		setAvatar(new File("res/unknown.jpg"));
	}
	
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
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
	
	public final ObjectProperty<File> avatarProperty() {
		return this.avatar;
	}
	
	public final File getAvatar() {
		return this.avatarProperty().get();
	}
	
	public final void setAvatar(final File avatarProperty) {
		this.avatarProperty().set(avatarProperty);
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
	
	public final float getHpFraction() {
		return ((float) getCurrentHp()) / ((float) getMaxHp());
	}
	
	public final void setMaxHp(final int maxHp) {
		this.maxHpProperty().set(maxHp);
	}

	public final ObjectProperty<Vector2> positionProperty() {
		return this.position;
	}
	

	public final Vector2 getPosition() {
		return this.positionProperty().get();
	}
	

	public final void setPosition(final Vector2 position) {
		this.positionProperty().set(position);
	}

	public final ObjectProperty<Vector2> sizeProperty() {
		return this.size;
	}
	

	public final Vector2 getSize() {
		return this.sizeProperty().get();
	}
	

	public final void setSize(final Vector2 size) {
		this.sizeProperty().set(size);
	}

	public final BooleanProperty hiddenHpProperty() {
		return this.hiddenHp;
	}
	

	public final boolean isHiddenHp() {
		return this.hiddenHpProperty().get();
	}
	

	public final void setHiddenHp(final boolean hiddenHp) {
		this.hiddenHpProperty().set(hiddenHp);
	}
	
	public final void setRandomPosition(Battle battle) {
		for (int x = 0; x < battle.getGridSize().getX(); x++) {
			for (int y = 0; y < battle.getGridSize().getY(); y++) {
				Vector2 v = new Vector2(x, y);
				boolean occupied = false;
				for (Actor actor : battle.actorsProperty().get()) {
					if (actor.getPosition().equals(v)) {
						occupied = true;
						break;
					}
				}
				
				if (!occupied) {
					setPosition(v);
					return;
				}
			}
		}
	}

	public final ListProperty<Status> statusesProperty() {
		return this.statuses;
	}
	

	public final ObservableList<Status> getStatuses() {
		return this.statusesProperty().get();
	}
	

	public final void setStatuses(final ObservableList<Status> statuses) {
		this.statusesProperty().set(statuses);
	}
	
	public Actor duplicate() {
		Actor duplicate = new Actor();
		
		duplicate.setName(getName());
		duplicate.setInitiative(getInitiative());
		duplicate.setCurrentHp(getCurrentHp());
		duplicate.setMaxHp(getMaxHp());
		duplicate.setHiddenHp(isHiddenHp());
		duplicate.setStatuses(FXCollections.observableArrayList(getStatuses()));
		duplicate.setSize(getSize());
		duplicate.setAvatar(getAvatar());
		
		return duplicate;
	}

	public final StringProperty sheetProperty() {
		return this.sheet;
	}
	

	public final String getSheet() {
		return this.sheetProperty().get();
	}
	

	public final void setSheet(final String sheet) {
		this.sheetProperty().set(sheet);
	}

	public final ObjectProperty<Gender> genderProperty() {
		return this.gender;
	}
	

	public final Gender getGender() {
		return this.genderProperty().get();
	}
	

	public final void setGender(final Gender gender) {
		this.genderProperty().set(gender);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
