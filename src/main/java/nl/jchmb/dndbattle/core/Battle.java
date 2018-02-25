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
	private final ObjectProperty<File> backgroundImageFile = new SimpleObjectProperty<>();
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
	private final IntegerProperty legendColumns = new SimpleIntegerProperty(1);
	private final ObjectProperty<Color> legendEntryEvenColor = new SimpleObjectProperty<>(Color.WHITE);
	private final ObjectProperty<Color> legendEntryOddColor = new SimpleObjectProperty<>(Color.LIGHTGRAY);
	private final ObjectProperty<Color> legendEntryFontColor = new SimpleObjectProperty<>(Color.BLACK);
	
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
		setBackgroundColor(Color.WHITE);
		setBorderColor(Color.GRAY);
		setLegendEntryEvenColor(Color.WHITE);
		setLegendEntryOddColor(new Color(0.95f, 0.95f, 0.95f, 1.0f));
		setBackgroundImageFile(null);
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

	public final IntegerProperty legendColumnsProperty() {
		return this.legendColumns;
	}
	

	public final int getLegendColumns() {
		return this.legendColumnsProperty().get();
	}
	

	public final void setLegendColumns(final int legendColumns) {
		this.legendColumnsProperty().set(legendColumns);
	}

	public final ObjectProperty<File> backgroundImageFileProperty() {
		return this.backgroundImageFile;
	}
	

	public final File getBackgroundImageFile() {
		return this.backgroundImageFileProperty().get();
	}
	

	public final void setBackgroundImageFile(final File backgroundImageFile) {
		this.backgroundImageFileProperty().set(backgroundImageFile);
	}

	public final ObjectProperty<Color> legendEntryEvenColorProperty() {
		return this.legendEntryEvenColor;
	}
	

	public final Color getLegendEntryEvenColor() {
		return this.legendEntryEvenColorProperty().get();
	}
	

	public final void setLegendEntryEvenColor(final Color legendEntryEvenColor) {
		this.legendEntryEvenColorProperty().set(legendEntryEvenColor);
	}
	

	public final ObjectProperty<Color> legendEntryOddColorProperty() {
		return this.legendEntryOddColor;
	}
	

	public final Color getLegendEntryOddColor() {
		return this.legendEntryOddColorProperty().get();
	}
	

	public final void setLegendEntryOddColor(final Color legendEntryOddColor) {
		this.legendEntryOddColorProperty().set(legendEntryOddColor);
	}

	public final ObjectProperty<Color> legendEntryFontColorProperty() {
		return this.legendEntryFontColor;
	}
	

	public final Color getLegendEntryFontColor() {
		return this.legendEntryFontColorProperty().get();
	}
	

	public final void setLegendEntryFontColor(final Color legendEntryFontColor) {
		this.legendEntryFontColorProperty().set(legendEntryFontColor);
	}
	
	
	
	
	
	
	
	
	
}
