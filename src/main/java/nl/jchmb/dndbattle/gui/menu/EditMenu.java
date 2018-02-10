/**
 * 
 */
package nl.jchmb.dndbattle.gui.menu;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Avatar;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.gui.options.GridOptions;
import nl.jchmb.dndbattle.utils.PopOverForm;

/**
 * @author jochem
 *
 */
public class EditMenu extends Menu {
	private final ObjectProperty<Battle> battle;
	
	public EditMenu(final ObjectProperty<Battle> battle, BorderPane pane) {
		super("Edit");
		this.battle = battle;
		
		getItems().addAll(
			getGridOptionsItem(pane),
			getAddActorItem(),
			getAddStatusItem(),
			getAddTilesetItem()
		);
	}
	
	private final MenuItem getGridOptionsItem(BorderPane pane) {
		MenuItem item = new MenuItem("Grid options");
		item.setOnAction(event -> {
			GridOptions gridOptions = new GridOptions(battle.get());
			gridOptions.show(pane.getCenter());
		});
		return item;
	}
	
	private final MenuItem getAddActorItem() {
		MenuItem item = new MenuItem("Add actor");
		item.setOnAction(event -> {
			Actor actor = new Actor();
			actor.setName("New actor");
			actor.setRandomPosition(battle.get());
			battle.get().getActors().add(actor);
		});
		item.setAccelerator(
			new KeyCodeCombination(
				KeyCode.A,
				KeyCombination.CONTROL_DOWN
			)
		);
		return item;
	}
	
	private final MenuItem getAddStatusItem() {
		MenuItem item = new MenuItem("Add status");
		item.setOnAction(event -> {
			Status status = new Status();
			status.setName("New status");
			battle.get().statusesProperty().add(status);
		});
		item.setAccelerator(
			new KeyCodeCombination(
				KeyCode.T,
				KeyCombination.CONTROL_DOWN
			)
		);
		return item;
	}
	
	private final MenuItem getAddTilesetItem() {
		MenuItem item = new MenuItem("Add tileset");
		item.setOnAction(event -> {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Select tileset");
			chooser.getExtensionFilters().add(
				new ExtensionFilter("Image files", "*.png")
			);
			File file = chooser.showOpenDialog(null);
			
		});
		item.setAccelerator(
			new KeyCodeCombination(
				KeyCode.L,
				KeyCombination.CONTROL_DOWN
			)
		);
		return item;
	}
}
