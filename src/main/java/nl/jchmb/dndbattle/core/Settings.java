package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Settings {
	public static final Settings INSTANCE = new Settings();
	
	private final ObjectProperty<File> battleDirectory = new SimpleObjectProperty<>(new File("."));
	private final ObjectProperty<File> imageDirectory = new SimpleObjectProperty<>(new File("."));
	private final ObjectProperty<File> exportDirectory = new SimpleObjectProperty<>(new File("."));
	
	private Settings() {
		
	}
	
	public final ObjectProperty<File> battleDirectoryProperty() {
		return this.battleDirectory;
	}
	
	public final File getBattleDirectory() {
		return this.battleDirectoryProperty().get();
	}
	
	public final void setBattleDirectory(final File battleDirectory) {
		this.battleDirectoryProperty().set(battleDirectory);
	}
	
	public final ObjectProperty<File> imageDirectoryProperty() {
		return this.imageDirectory;
	}
	
	public final File getImageDirectory() {
		return this.imageDirectoryProperty().get();
	}
	
	public final void setImageDirectory(final File imageDirectory) {
		this.imageDirectoryProperty().set(imageDirectory);
	}

	public final ObjectProperty<File> exportDirectoryProperty() {
		return this.exportDirectory;
	}
	

	public final File getExportDirectory() {
		return this.exportDirectoryProperty().get();
	}
	

	public final void setExportDirectory(final File exportDirectory) {
		this.exportDirectoryProperty().set(exportDirectory);
	}
	
	
}
