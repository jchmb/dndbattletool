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
import javafx.stage.Stage;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Avatar;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Entity;
import nl.jchmb.dndbattle.core.Round;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.core.overlays.Overlay;
import nl.jchmb.dndbattle.gui.options.GridOptions;
import nl.jchmb.dndbattle.gui.options.LegendOptions;
import nl.jchmb.dndbattle.gui.options.StatusOptions;
import nl.jchmb.dndbattle.gui.statuses.StatusList;
import nl.jchmb.dndbattle.utils.Popups;
import nl.jchmb.dndbattle.utils.form.PopOverForm;

/**
 * @author jochem
 *
 */
public class EditMenu extends Menu {
	private final ObjectProperty<Battle> battle;
	
	public EditMenu(final ObjectProperty<Battle> battle, BorderPane pane, Stage window) {
		super("Edit");
		this.battle = battle;
		
		getItems().addAll(
			getAddActorItem(),
			getAddEntityItem(),
			getAddOverlayItem()
//			getAddRoundItem()
//			getAddTilesetItem()
		);
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
	
	private final MenuItem getAddEntityItem() {
		MenuItem item = new MenuItem("Add object");
		item.setOnAction(event -> {
			Entity entity = new Entity();
			entity.setName("New object");
			battle.get().entitiesProperty().add(entity);
		});
		item.setAccelerator(
			new KeyCodeCombination(
				KeyCode.J,
				KeyCombination.CONTROL_DOWN
			)
		);
		return item;
	}
	
	private final MenuItem getAddOverlayItem() {
		final MenuItem item = new MenuItem("Add overlay");
		item.setOnAction(event -> {
			Overlay overlay = new Overlay();
			overlay.setName("New overlay");
			battle.get().overlaysProperty().add(overlay);
		});
		item.setAccelerator(
			new KeyCodeCombination(
				KeyCode.V,
				KeyCombination.CONTROL_DOWN
			)
		);
		return item;
	}
	
	private final MenuItem getAddRoundItem() {
		MenuItem item = new MenuItem("Add round");
		item.setOnAction(event -> {
			Round round = new Round();
			
		});
		item.setAccelerator(
			new KeyCodeCombination(
				KeyCode.R,
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
