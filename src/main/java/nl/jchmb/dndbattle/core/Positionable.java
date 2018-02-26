package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.ObjectProperty;

public interface Positionable {
	public ObjectProperty<Vector2> positionProperty();
	public ObjectProperty<File> avatarProperty();
	public ObjectProperty<Vector2> sizeProperty();
	public File getAvatar();
	public Vector2 getPosition();
	public Vector2 getSize();
	public void setAvatar(File avatar);
	public void setPosition(Vector2 position);
	public void setSize(Vector2 size);
}
