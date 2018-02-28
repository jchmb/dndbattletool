package nl.jchmb.dndbattle.core.overlays.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
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
		final Stream<Vector2> squares;
		switch (getSize()) {
			case FIFTEEN:
				if (diagonal) {
					squares = Stream.of(
						new Vector2(0, 0),
						new Vector2(1, 0),
						new Vector2(2, 0),
						new Vector2(0, -1),
						new Vector2(0, -2),
						new Vector2(1, -1)
					);
				} else {
					squares = Stream.of(
						new Vector2(0, 0),
						new Vector2(1, -1),
						new Vector2(1, 0),
						new Vector2(1, 1),
						new Vector2(2, -1),
						new Vector2(2, 0),
						new Vector2(2, 1)
					);
				}
				break;
			default:
			case THIRTY:
				if (diagonal) {
					/* lol */
					squares = Stream.of(
						new Vector2(0, 0),
						new Vector2(1, 0),
						new Vector2(2, 0),
						new Vector2(3, 0),
						new Vector2(4, 0),
						new Vector2(5, 0),
						new Vector2(0, -1),
						new Vector2(0, -2),
						new Vector2(0, -3),
						new Vector2(0, -4),
						new Vector2(0, -5),
						new Vector2(1, -1),
						new Vector2(1, -2),
						new Vector2(1, -3),
						new Vector2(1, -4),
						new Vector2(2, -1),
						new Vector2(2, -2),
						new Vector2(2, -3),
						new Vector2(2, -4),
						new Vector2(3, -1),
						new Vector2(3, -2),
						new Vector2(3, -3),
						new Vector2(4, -1),
						new Vector2(4, -2)
					);
				} else {
					squares = Stream.of(
						new Vector2(0, 0),
						new Vector2(0, 1),
						new Vector2(1, -1),
						new Vector2(1, 0),
						new Vector2(1, 1),
						new Vector2(1, 2),
						new Vector2(2, -2),
						new Vector2(2, -1),
						new Vector2(2, 0),
						new Vector2(2, 1),
						new Vector2(2, 2),
						new Vector2(2, 3),
						new Vector2(3, -3),
						new Vector2(3, -2),
						new Vector2(3, -1),
						new Vector2(3, 0),
						new Vector2(3, 1),
						new Vector2(3, 2),
						new Vector2(3, 3),
						new Vector2(3, 4),
						new Vector2(4, -2),
						new Vector2(4, -1),
						new Vector2(4, 0),
						new Vector2(4, 1),
						new Vector2(4, 2),
						new Vector2(4, 3),
						new Vector2(5, 0),
						new Vector2(5, 1)
					);
				}
				break;
		}
		return squares.map(
				v -> v.rotate(getDirection())
			)
			.collect(Collectors.toList());
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
		
		/* Direction */
		form.addComboBoxField(
			direction,
			FXCollections.observableArrayList(
				Direction.values()
			),
			"Direction"
		);
		
		/* Size */
		form.addComboBoxField(
			size,
			FXCollections.observableArrayList(
				Size.values()
			),
			"Size"
		);
		
		return form;
	}
	
	@Override
	public String toString() {
		return "Cone";
	}


	@Override
	public Property<?>[] getProperties() {
		return new Property<?>[] {
			direction,
			size
		};
	}
	
	public static enum Size {	
		FIFTEEN("15'"),
		THIRTY("30'");
		
		private final String label;
		
		private Size(final String label) {
			this.label = label;
		}
		
		@Override
		public String toString() {
			return label;
		}
	}
}
