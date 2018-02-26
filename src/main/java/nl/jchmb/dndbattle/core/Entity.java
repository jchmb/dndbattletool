package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entity implements Positionable {
	private final StringProperty name = new SimpleStringProperty();
	private final ObjectProperty<Vector2> size = new SimpleObjectProperty<>(new Vector2(1, 1));
	private final ObjectProperty<File> avatar = new SimpleObjectProperty<>(new File("res/unknown.jpg"));
	private final ObjectProperty<Vector2> position = new SimpleObjectProperty<>(new Vector2(1, 1));
	
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
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

	public final ObjectProperty<Vector2> positionProperty() {
		return this.position;
	}
	

	public final Vector2 getPosition() {
		return this.positionProperty().get();
	}
	

	public final void setPosition(final Vector2 position) {
		this.positionProperty().set(position);
	}

	public final ObjectProperty<File> avatarProperty() {
		return this.avatar;
	}
	

	public final File getAvatar() {
		return this.avatarProperty().get();
	}
	

	public final void setAvatar(final File avatar) {
		this.avatarProperty().set(avatar);
	}
	
	
	
}
