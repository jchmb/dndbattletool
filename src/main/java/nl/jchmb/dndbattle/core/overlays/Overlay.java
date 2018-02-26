package nl.jchmb.dndbattle.core.overlays;

import java.io.File;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Positionable;
import nl.jchmb.dndbattle.core.Sizable;
import nl.jchmb.dndbattle.core.Vector2;

public class Overlay implements Positionable, Sizable {
	private final StringProperty name = new SimpleStringProperty("New overlay");
	private final ObjectProperty<Vector2> position = new SimpleObjectProperty<>(new Vector2(0, 0));
	private final ObjectProperty<Vector2> size = new SimpleObjectProperty<>(new Vector2(1, 1));
	private final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.RED);
	private final DoubleProperty opacity = new SimpleDoubleProperty(0.5f);
	
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
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

	@Override
	public final Node getImageRepresentation(final Battle battle) {
		final Color color = Color.color(
			getColor().getRed(),
			getColor().getGreen(),
			getColor().getBlue(),
			getOpacity()
		);
		final Rectangle rectangle = new Rectangle(
			battle.getCellSize() * getSize().getX(),
			battle.getCellSize() * getSize().getY(),
			color
		);
		return rectangle;
	}

	public final ObjectProperty<Color> colorProperty() {
		return this.color;
	}
	

	public final Color getColor() {
		return this.colorProperty().get();
	}
	

	public final void setColor(final Color color) {
		this.colorProperty().set(color);
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

	public final DoubleProperty opacityProperty() {
		return this.opacity;
	}
	

	public final double getOpacity() {
		return this.opacityProperty().get();
	}
	

	public final void setOpacity(final double opacity) {
		this.opacityProperty().set(opacity);
	}
	
	
	
	
	
}
