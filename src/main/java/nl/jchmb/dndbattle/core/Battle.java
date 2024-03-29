/**
 * 
 */
package nl.jchmb.dndbattle.core;

import java.io.File;
import java.util.Comparator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import nl.jchmb.dndbattle.core.overlays.Overlay;

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
	private final ListProperty<Gender> genders = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	private final ListProperty<Entity> entities = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	private final ListProperty<Overlay> overlays = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	private final ListProperty<Round> rounds = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);
	private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>(Color.WHITE);
	private final ObjectProperty<Color> borderColor = new SimpleObjectProperty<>(Color.GRAY);
	private final IntegerProperty legendColumns = new SimpleIntegerProperty(1);
	private final ObjectProperty<Color> legendEntryEvenColor = new SimpleObjectProperty<>(Color.WHITE);
	private final ObjectProperty<Color> legendEntryOddColor = new SimpleObjectProperty<>(Color.LIGHTGRAY);
	private final ObjectProperty<Color> legendEntryFontColor = new SimpleObjectProperty<>(Color.BLACK);
	private final IntegerProperty legendEntryHeight = new SimpleIntegerProperty(50);
	
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
		setLegendEntryHeight(48);
		setLegendEntryEvenColor(Color.WHITE);
		setLegendEntryOddColor(new Color(0.95f, 0.95f, 0.95f, 1.0f));
		setLegendEntryFontColor(Color.BLACK);
		setBackgroundImageFile(null);
		resetGenders();
		getActors().clear();
		getStatuses().clear();
		getEntities().clear();
		getOverlays().clear();
		resetRounds();
	}
	
	private void resetGenders() {
		genders.clear();
		genders.addAll(
			Gender.MALE,
			Gender.FEMALE,
			Gender.OTHER
		);
	}
	
	private void resetRounds() {
		rounds.clear();
		Round firstRound = new Round();
		firstRound.setNumber(1);
		rounds.addAll(
			firstRound
		);
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

	public final IntegerProperty legendEntryHeightProperty() {
		return this.legendEntryHeight;
	}
	

	public final int getLegendEntryHeight() {
		return this.legendEntryHeightProperty().get();
	}
	

	public final void setLegendEntryHeight(final int legendEntryHeight) {
		this.legendEntryHeightProperty().set(legendEntryHeight);
	}

	public final ListProperty<Gender> gendersProperty() {
		return this.genders;
	}
	

	public final ObservableList<Gender> getGenders() {
		return this.gendersProperty().get();
	}
	

	public final void setGenders(final ObservableList<Gender> genders) {
		this.gendersProperty().set(genders);
	}
	
	public final Gender findGenderById(final int id) {
		return genders.stream()
			.filter(gender -> gender.getId() == id)
			.findFirst()
			.orElse(Gender.OTHER);
	}

	public final ListProperty<Entity> entitiesProperty() {
		return this.entities;
	}
	

	public final ObservableList<Entity> getEntities() {
		return this.entitiesProperty().get();
	}
	

	public final void setEntities(final ObservableList<Entity> entities) {
		this.entitiesProperty().set(entities);
	}

	public final ListProperty<Round> roundsProperty() {
		return this.rounds;
	}
	

	public final ObservableList<Round> getRounds() {
		return this.roundsProperty().get();
	}
	

	public final void setRounds(final ObservableList<Round> rounds) {
		this.roundsProperty().set(rounds);
	}
	
	public final Round nextRound() {
		Round nextRound = new Round();
		nextRound.setNumber(
			rounds.stream()
				.max(Comparator.comparingInt(Round::getNumber))
				.map(Round::getNumber)
				.orElse(1)
		);
		rounds.add(nextRound);
		return nextRound;
	}

	public final ListProperty<Overlay> overlaysProperty() {
		return this.overlays;
	}
	

	public final ObservableList<Overlay> getOverlays() {
		return this.overlaysProperty().get();
	}
	

	public final void setOverlays(final ObservableList<Overlay> overlays) {
		this.overlaysProperty().set(overlays);
	}
	
	
	
	
	
	
	
	
	
}
