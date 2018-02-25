package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tileset {
	private final StringProperty name = new SimpleStringProperty();
	private final ObjectProperty<File> file = new SimpleObjectProperty<>();
	
	
}
