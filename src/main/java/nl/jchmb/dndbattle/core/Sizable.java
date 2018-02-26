package nl.jchmb.dndbattle.core;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Spinner;

public interface Sizable {
	public ObjectProperty<Vector2> sizeProperty();
	public Vector2 getSize();
	public void setSize(Vector2 size);
	
	public static Spinner<Integer> getSpinner() {
		return new Spinner<>(Constants.MIN_OBJECT_SIZE, Constants.MAX_OBJECT_SIZE, 1);
	}
}
