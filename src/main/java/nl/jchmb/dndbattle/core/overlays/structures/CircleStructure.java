package nl.jchmb.dndbattle.core.overlays.structures;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.utils.form.Form;

public class CircleStructure implements OverlayStructure {
	private final ObjectProperty<Size> size = new SimpleObjectProperty<>(Size.FIVE);
	
	@Override
	public Form getForm() {
		final Form form = new Form();
		
		/* Size */
		form.addComboBoxField(
			size,
			FXCollections.observableArrayList(Size.values()),
			"Size"
		);
		
		return form;
	}

	@Override
	public Property<?>[] getProperties() {
		return new Property<?>[] {
			size
		};
	}

	@Override
	public List<Vector2> getSquares() {
		final Stream<Vector2> squares;
		switch (getSize()) {
			case FIVE:
				squares = Stream.of(
					new Vector2(0, 0),
					new Vector2(0, 1),
					new Vector2(1, 0),
					new Vector2(1, 1)
				);
				break;
			case TEN:
				squares = Streams.concat(
					Vector2.line(
						new Vector2(-1, 0),
						Vector2.EX,
						4
					),
					Vector2.line(
						new Vector2(-1, 1),
						Vector2.EX,
						4
					),
					Vector2.line(
						new Vector2(0, -1),
						Vector2.EY,
						4
					),
					Vector2.line(
						new Vector2(1, -1),
						Vector2.EY,
						4
					)
				).distinct();
				break;
			case TWENTY:
			default:
				squares = Streams.concat(
					Vector2.line(
						new Vector2(-3, 0),
						Vector2.EX,
						8
					),
					Vector2.line(
						new Vector2(-3, 1),
						Vector2.EX,
						8
					),
					Vector2.line(
						new Vector2(0, -3),
						Vector2.EY,
						8
					),
					Vector2.line(
						new Vector2(1, -3),
						Vector2.EY,
						8
					),
					Vector2.line(
						new Vector2(-2, -2),
						Vector2.EX,
						6
					),
					Vector2.line(
						new Vector2(-2, -1),
						Vector2.EX,
						6
					),
					Vector2.line(
						new Vector2(-2, 2),
						Vector2.EX,
						6
					),
					Vector2.line(
						new Vector2(-2, 3),
						Vector2.EX,
						6
					)
				).distinct();
				break;
		}
		return squares.collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return "Circle";
	}
	
	public static enum Size {
		FIVE("5'"),
		TEN("10'"),
		TWENTY("20'");
		
		private final String label;
		
		private Size(final String label) {
			this.label = label;
		}
		
		@Override
		public String toString() {
			return label;
		}
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
	
}
