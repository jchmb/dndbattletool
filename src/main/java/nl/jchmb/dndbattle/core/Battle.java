/**
 * 
 */
package nl.jchmb.dndbattle.core;

import java.io.File;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 * @author jochem
 *
 */
public class Battle {
	private final ObjectProperty<File> file = new SimpleObjectProperty<>();
	private final IntegerProperty cellSize = new SimpleIntegerProperty(50);
	private final ObjectProperty<Vector2> gridSize = new SimpleObjectProperty<>(new Vector2(17, 17));
	private final ListProperty<Actor> actors = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	private final ListProperty<Status> statuses = new SimpleListProperty<>(
		FXCollections.observableArrayList()	
	);
	private final ListProperty<Tile> tiles = new SimpleListProperty<>(
		FXCollections.observableArrayList()	
	);
	private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>(Color.WHITE);
	private final ObjectProperty<Color> borderColor = new SimpleObjectProperty<>(Color.GRAY);
	
	public Battle() {
		
	}
	
	public final IntegerProperty cellSizeProperty() {
		return this.cellSize;
	}
	
	public final int getCellSize() {
		return this.cellSizeProperty().get();
	}
	
	public final void setCellSize(final int cellSize) {
		this.cellSizeProperty().set(cellSize);
	}
	
	public final ObjectProperty<Vector2> gridSizeProperty() {
		return this.gridSize;
	}
	
	public final Vector2 getGridSize() {
		return this.gridSizeProperty().get();
	}
	
	public final void setGridSize(final Vector2 gridSize) {
		this.gridSizeProperty().set(gridSize);
	}
	
	public final ListProperty<Actor> actorsProperty() {
		return this.actors;
	}
	
	public final ObservableList<Actor> getActors() {
		return this.actorsProperty().get();
	}
	
	public final  void setActors(final ObservableList<Actor> actors) {
		this.actorsProperty().set(actors);
	}
	
	public void reset() {
		setGridSize(new Vector2(17, 17));
		setCellSize(50);
		getActors().clear();
		getStatuses().clear();
	}

	public final ListProperty<Status> statusesProperty() {
		return this.statuses;
	}
	

	public final ObservableList<Status> getStatuses() {
		return this.statusesProperty().get();
	}
	

	public final void setStatuses(final ObservableList<Status> statuses) {
		this.statusesProperty().set(statuses);
	}
	
	public final Status findStatusByName(String name) {
		for (Status status : getStatuses()) {
			if (status.getName().equals(name)) {
				return status;
			}
		}
		return null;
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

	public final ListProperty<Tile> tilesProperty() {
		return this.tiles;
	}
	

	public final ObservableList<Tile> getTiles() {
		return this.tilesProperty().get();
	}
	

	public final void setTiles(final ObservableList<Tile> tiles) {
		this.tilesProperty().set(tiles);
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
