package nl.jchmb.dndbattle.core.overlays.structures;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import nl.jchmb.dndbattle.core.Direction;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.utils.form.Form;

public class ConeStructure implements OverlayStructure {
	private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>(Direction.EAST);
	private final ObjectProperty<Size> size = new SimpleObjectProperty<>(Size.FIFTEEN);
	
	@Override
	public final List<Vector2> getSquares() {
		final boolean diagonal =
			getDirection().getValue().getX() != 0 &&
			getDirection().getValue().getY() != 0;
		final List<Vector2> squares = new ArrayList<>();
		switch (getSize()) {
			case FIFTEEN:
				if (diagonal) {
					
				} else {
					
				}
				break;
			case THIRTY:
				if (diagonal) {
					
				} else {
					
				}
				break;
		}
		return squares;
	}
	
	public static enum Size {
		FIFTEEN, THIRTY;
	}

	public final ObjectProperty<Direction> directionProperty() {
		return this.direction;
	}
	

	public final Direction getDirection() {
		return this.directionProperty().get();
	}
	

	public final void setDirection(final Direction direction) {
		this.directionProperty().set(direction);
	}
	

	public final ObjectProperty<Size> sizeProperty() {
		return this.size;
	}
	

	public final Size getSize() {
		return this.sizeProperty().get();
	}
	

	public final void setSize(final Size size) {
		this.sizeProperty().set(size);
	}


	@Override
	public Form getForm() {
		final Form form = new Form();
		return form;
	}
	
	@Override
	public String toString() {
		return "Cone";
	}


	@Override
	public Property<?>[] getProperties() {
		return new Property<?>[] {
			
		};
	}
}
