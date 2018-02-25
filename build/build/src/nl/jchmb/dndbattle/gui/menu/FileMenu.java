/**
 * 
 */
package nl.jchmb.dndbattle.gui.menu;

import java.io.File;

import org.controlsfx.control.PopOver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.serialization.BattleJsonReader;
import nl.jchmb.dndbattle.serialization.BattleJsonWriter;
import nl.jchmb.dndbattle.utils.Images;

/**
 * @author jochem
 *
 */
public class FileMenu extends Menu {
	private final ObjectProperty<Battle> battle;
	
	public FileMenu(final ObjectProperty<Battle> battle, BorderPane root) {
		super("File");
		this.battle = battle;
		
		getItems().addAll(
			getNewItem(),
			getOpenItem(),
			getSaveItem(),
			getSaveAsItem(),
			getExportItem(root),
			getExitItem()
		);
	}
	
	private MenuItem getNewItem() {
		MenuItem item = new MenuItem("New battle");
		item.setOnAction(event -> {
			battle.get().reset();
			battle.get().setFile(null);
		});
		item.setAccelerator(
				new KeyCodeCombination(
					KeyCode.N,
					KeyCombination.CONTROL_DOWN
				)
			);
		return item;
	}
	
	private MenuItem getOpenItem() {
		MenuItem item = new MenuItem("Open battle");
		item.setOnAction(event -> {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Open battle");
			File file = chooser.showOpenDialog(null);
			if (file != null) {
				BattleJsonReader reader = new BattleJsonReader();
				reader.read(battle.get(), file);
				battle.get().setFile(file);
			}
		});
		item.setAccelerator(
				new KeyCodeCombination(
					KeyCode.O,
					KeyCombination.CONTROL_DOWN
				)
			);
		return item;
	}
	
	private MenuItem getSaveItem() {
		MenuItem item = new MenuItem("Save battle");
		item.setOnAction(event -> {
			if (battle.get().getFile() == null) {
				saveAsAction();
			} else {
				BattleJsonWriter writer = new BattleJsonWriter();
				writer.write(battle.get(), battle.get().getFile());
			}
		});
		item.setAccelerator(
				new KeyCodeCombination(
					KeyCode.S,
					KeyCombination.CONTROL_DOWN
				)
			);
		return item;
	}
	
	private void saveAsAction() {
		BattleJsonWriter writer = new BattleJsonWriter();
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save battle as...");
		chooser.getExtensionFilters().add(
			new ExtensionFilter("Battle file", "*.battle")
		);
		File file = chooser.showSaveDialog(null);
		if (file != null) {
			writer.write(battle.get(), file);
			battle.get().setFile(file);
		}
	}
	
	private MenuItem getSaveAsItem() {
		MenuItem item = new MenuItem("Save battle as...");
		item.setOnAction(event -> {
			saveAsAction();
		});
		item.setAccelerator(
				new KeyCodeCombination(
					KeyCode.S,
					KeyCombination.CONTROL_DOWN,
					KeyCombination.SHIFT_DOWN
				)
			);
		return item;
	}
	
	private MenuItem getExportItem(BorderPane root) {
		MenuItem item = new MenuItem("Export battle as...");
		item.setOnAction(event -> {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Export battle");
			chooser.getExtensionFilters().add(
				new ExtensionFilter("Image file", "*.png", "*.jpg")
			);
			File file = chooser.showSaveDialog(null);
			if (file != null) {
				Images.save(file, root);
			}
		});
		item.setAccelerator(
				new KeyCodeCombination(
					KeyCode.E,
					KeyCombination.CONTROL_DOWN,
					KeyCombination.SHIFT_DOWN
				)
			);
		return item;
	}
	
	
	
	private MenuItem getExitItem() {
		MenuItem item = new MenuItem("Exit");
		item.setOnAction(event -> {
			System.exit(-1);
		});
		return item;
	}
}
