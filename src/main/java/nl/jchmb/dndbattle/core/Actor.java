package nl.jchmb.dndbattle.core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.jchmb.dndbattle.utils.Images;

/**
 * @author jochem
 *
 */
public class Actor implements Positionable, Sizable {
	private final StringProperty name = new SimpleStringProperty();
	private final IntegerProperty initiative = new SimpleIntegerProperty();
	private final ObjectProperty<Path> avatar = new SimpleObjectProperty<>();
	private final StringProperty sheet = new SimpleStringProperty("");
	private final IntegerProperty currentHp = new SimpleIntegerProperty(1);
	private final IntegerProperty maxHp = new SimpleIntegerProperty(1);
	private final ObjectProperty<Vector2> position = new SimpleObjectProperty<Vector2>();
	private final ObjectProperty<Vector2> size = new SimpleObjectProperty<Vector2>(new Vector2(1, 1));
	private final BooleanProperty hiddenHp = new SimpleBooleanProperty(false);
	private final BooleanProperty hiddenPosition = new SimpleBooleanProperty(false);
	private final ListProperty<Status> statuses = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.OTHER);
	private final BooleanProperty proper = new SimpleBooleanProperty(true);
	private final BooleanProperty active = new SimpleBooleanProperty(true);
	
	public Actor() {
		setAvatar(Paths.get("res/unknown.jpg"));
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
	
	public final ObjectProperty<Path> avatarProperty() {
		return this.avatar;
	}
	
	public final Path getAvatar() {
		return this.avatarProperty().get();
	}
	
	public final void setAvatar(final Path avatarProperty) {
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
		duplicate.setGender(getGender());
		duplicate.setInitiative(getInitiative());
		duplicate.setCurrentHp(getCurrentHp());
		duplicate.setMaxHp(getMaxHp());
		duplicate.setHiddenHp(isHiddenHp());
		duplicate.setHiddenPosition(isHiddenPosition());
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
	
	public final String asSubjectString(final boolean primary) {
		return primary ?
			getName() :
			getGender().getSubjectPronoun();
	}
	
	public final String asObjectString(final boolean primary) {
		return primary ?
			getName() :
			getGender().getObjectPronoun();
	}
	
	public final String asPossessiveString(final boolean primary) {
		return primary ?
			(getName() + "'s") :
			getGender().getPossessivePronoun();
	}
	
	private final String getNameXml() {
		return String.format(
			"%s<actor>%s</actor>",
			!isProper() ? "the " : "",
			getName()
		);
	}
	
	public final String asSubjectXml(final boolean primary) {
		return primary ?
			getNameXml() :
			getGender().getSubjectPronoun();
	}
	
	public final String asObjectXml(final boolean primary) {
		return primary ?
			getNameXml() :
			getGender().getObjectPronoun();
	}
	
	public final String asPossessiveXml(final boolean primary) {
		return primary ?
			(getNameXml() + "'s") :
			getGender().getPossessivePronoun();
	}
	
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public Node getImageRepresentation(final Battle battle) {
		final ImageView imageView = new ImageView();
		final Image image = Images.load(
			getAvatar(),
			battle.getCellSize() * getSize().getX(),
			battle.getCellSize() * getSize().getY()
		).get();
		imageView.setImage(image);
		return imageView;
	}

	public final BooleanProperty hiddenPositionProperty() {
		return this.hiddenPosition;
	}
	

	public final boolean isHiddenPosition() {
		return this.hiddenPositionProperty().get();
	}
	

	public final void setHiddenPosition(final boolean hiddenPosition) {
		this.hiddenPositionProperty().set(hiddenPosition);
	}

	public final BooleanProperty properProperty() {
		return this.proper;
	}
	

	public final boolean isProper() {
		return this.properProperty().get();
	}
	

	public final void setProper(final boolean proper) {
		this.properProperty().set(proper);
	}

	public final BooleanProperty activeProperty() {
		return this.active;
	}
	

	public final boolean isActive() {
		return this.activeProperty().get();
	}
	

	public final void setActive(final boolean active) {
		this.activeProperty().set(active);
	}
	
	
	
}
