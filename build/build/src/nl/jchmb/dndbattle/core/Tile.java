package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class Tile {
	private final ObjectProperty<File> file = new SimpleObjectProperty<>();
	private final ObjectProperty<Vector2> offset = new SimpleObjectProperty<>();
	private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
	
	public Tile() {
//		image.bind(
//			BindingUtils.binding(
//				file,
//				f -> f.
//			)
//		);
	}
	
	public final ObjectProperty<File> fileProperty() {
		return this.file;
	}
	
	public final File getFile() {
		return this.fileProperty().get();
	}
	
	public final void setFile(final File file) {
		this.fileProperty().set(file);
	}
	
	public final ObjectProperty<Vector2> offsetProperty() {
		return this.offset;
	}
	
	public final Vector2 getOffset() {
		return this.offsetProperty().get();
	}
	
	public final void setOffset(final Vector2 offset) {
		this.offsetProperty().set(offset);
	}
	
	
}
