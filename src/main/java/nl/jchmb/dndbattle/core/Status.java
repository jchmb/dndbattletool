package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Status {
	private final StringProperty name = new SimpleStringProperty("New status");
	private final StringProperty symbol = new SimpleStringProperty("S");
	private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<Color>(Color.WHITE);
	private final ObjectProperty<Color> textColor = new SimpleObjectProperty<Color>(Color.BLACK);
	private final ObjectProperty<File> imageFile = new SimpleObjectProperty<File>();
	private final ObjectProperty<Vector2> textOffset = new SimpleObjectProperty<>(Vector2.zero());
	private final IntegerProperty textSize = new SimpleIntegerProperty(12);
	private final ObjectProperty<Color> borderColor = new SimpleObjectProperty<>(Color.BLACK);
	
	public final StringProperty nameProperty() {
		return this.name;
	}
	
	public final String getName() {
		return this.nameProperty().get();
	}
	
	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final StringProperty symbolProperty() {
		return this.symbol;
	}
	

	public final String getSymbol() {
		return this.symbolProperty().get();
	}
	

	public final void setSymbol(final String symbol) {
		this.symbolProperty().set(symbol);
	}
	

	public final ObjectProperty<Color> backgroundColorProperty() {
		return this.backgroundColor;
	}
	

	public final Color getBackgroundColor() {
		return this.backgroundColorProperty().get();
	}
	

	public final void setBackgroundColor(final Color backgroundColor) {
		this.backgroundColorProperty().set(backgroundColor);
	}
	

	public final ObjectProperty<Color> textColorProperty() {
		return this.textColor;
	}
	

	public final Color getTextColor() {
		return this.textColorProperty().get();
	}
	

	public final void setTextColor(final Color textColor) {
		this.textColorProperty().set(textColor);
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public final ObjectProperty<File> imageFileProperty() {
		return this.imageFile;
	}
	

	public final File getImageFile() {
		return this.imageFileProperty().get();
	}
	

	public final void setImageFile(final File imageFile) {
		this.imageFileProperty().set(imageFile);
	}

	public final ObjectProperty<Vector2> textOffsetProperty() {
		return this.textOffset;
	}
	

	public final Vector2 getTextOffset() {
		return this.textOffsetProperty().get();
	}
	

	public final void setTextOffset(final Vector2 textOffset) {
		this.textOffsetProperty().set(textOffset);
	}

	public final IntegerProperty textSizeProperty() {
		return this.textSize;
	}
	

	public final int getTextSize() {
		return this.textSizeProperty().get();
	}
	

	public final void setTextSize(final int textSize) {
		this.textSizeProperty().set(textSize);
	}
	

	public final ObjectProperty<Color> borderColorProperty() {
		return this.borderColor;
	}
	

	public final Color getBorderColor() {
		return this.borderColorProperty().get();
	}
	

	public final void setBorderColor(final Color borderColor) {
		this.borderColorProperty().set(borderColor);
	}
	
	
	
}
