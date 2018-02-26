package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;

public interface Positionable {
	public ObjectProperty<Vector2> positionProperty();
	public Vector2 getPosition();
	public void setPosition(Vector2 position);
	public Node getImageRepresentation(final Battle battle);
}
