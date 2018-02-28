package nl.jchmb.dndbattle.core.overlays.structures;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import nl.jchmb.dndbattle.core.Direction;
import nl.jchmb.dndbattle.core.Sizable;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.utils.form.Form;

public class LineStructure implements OverlayStructure {
	private final IntegerProperty length = new SimpleIntegerProperty(4);
	private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>(Direction.EAST);
	
	@Override
	public Form getForm() {
		final Form form = new Form();
		
		/* Length */
		form.addIntegerField(
			length,
			Sizable.getSpinner(),
			"Length"
		);
		
		/* Direction */
		form.addComboBoxField(
			direction,
			FXCollections.observableArrayList(Direction.values()),
			"Direction"
		);
		
		return form;
	}

	@Override
	public Property<?>[] getProperties() {
		return new Property<?>[] {
			length,
			direction
		};
	}

	@Override
	public List<Vector2> getSquares() {
		return IntStream.range(0, getLength())
			.mapToObj(
				i -> getDirection().getValue().scale(i)
			)
			.collect(Collectors.toList());
	}

	public final IntegerProperty lengthProperty() {
		return this.length;
	}
	

	public final int getLength() {
		return this.lengthProperty().get();
	}
	

	public final void setLength(final int length) {
		this.lengthProperty().set(length);
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
	
	

}
