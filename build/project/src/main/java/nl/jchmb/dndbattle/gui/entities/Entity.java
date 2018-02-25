package nl.jchmb.dndbattle.gui.entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nl.jchmb.dndbattle.core.Vector2;

public class Entity {
	private final StringProperty name = new SimpleStringProperty();
	private final ObjectProperty<Vector2> size = new SimpleObjectProperty<>(new Vector2(1, 1));
}
