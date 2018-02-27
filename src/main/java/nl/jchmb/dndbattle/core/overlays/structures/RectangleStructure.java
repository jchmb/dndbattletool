package nl.jchmb.dndbattle.core.overlays.structures;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import nl.jchmb.dndbattle.core.Sizable;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.utils.form.Form;

public class RectangleStructure implements OverlayStructure {
	private final ObjectProperty<Vector2> size = new SimpleObjectProperty<>(new Vector2(1, 1));
	
	@Override
	public final List<Vector2> getSquares() {
		final List<Vector2> squares = new ArrayList<>();
		for (int x = 0; x < getSize().getX(); x++) {
			for (int y = 0; y < getSize().getY(); y++) {
				squares.add(new Vector2(x, y));
			}
		}
		return squares;
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

	@Override
	public Form getForm() {
		final Form form = new Form();
		form.addVector2Field(
			size,
			Sizable.getSpinner(),
			Sizable.getSpinner(),
			"Width",
			"Height"
		);
		return form;
	}

	@Override
	public String toString() {
		return "Rectangle";
	}

	@Override
	public Property<?>[] getProperties() {
		return new Property<?>[] {
			size
		};
	}
	
}
