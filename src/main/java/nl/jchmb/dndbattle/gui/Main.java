/**
 * 
 */
package nl.jchmb.dndbattle.gui;

import java.io.File;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.stage.Stage;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Settings;
import nl.jchmb.dndbattle.gui.actors.ActorList;
import nl.jchmb.dndbattle.gui.grid.BattleGrid;
import nl.jchmb.dndbattle.gui.legend.LegendPane;
import nl.jchmb.dndbattle.gui.menu.EditMenu;
import nl.jchmb.dndbattle.gui.menu.FileMenu;
import nl.jchmb.dndbattle.gui.menu.OptionsMenu;
import nl.jchmb.dndbattle.gui.menu.UploadMenu;
import nl.jchmb.dndbattle.serialization.SettingsReader;
import nl.jchmb.dndbattle.serialization.SettingsWriter;
import nl.jchmb.dndbattle.utils.BindingUtils;

/**
 * @author jochem
 *
 */
public class Main extends Application {
	private static final String VERSION = "0.6";
	
	private static final File SETTINGS_FILE = new File("settings.conf");
	private static final SettingsReader SETTINGS_READER = new SettingsReader();
	private static final SettingsWriter SETTINGS_WRITER = new SettingsWriter();
	
	private final ObjectProperty<Settings> settings = new SimpleObjectProperty<>(Settings.INSTANCE);
	private final ObjectProperty<Battle> battle = new SimpleObjectProperty<>(new Battle());
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/* Settings initialization */
			initializeSettings();
			
			/* Initialize stuff */
			battle.get().reset();
			BorderPane root = new BorderPane();
			buildGui(root, primaryStage);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
				"file:///" + new File("res/style.css").getAbsolutePath().replace("\\", "/")
			);
			primaryStage.titleProperty().bind(
				BindingUtils.binding(
					battle.get().fileProperty(),
					file -> String.format(
						"Tabletop Battle Tool %s%s",
						VERSION,
						battle.get().getFile() == null ?
							"" :
							" (" + battle.get().getFile().getName() + ")"
					)
				)
			);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initializeSettings() {
		final Settings settings = this.settings.get();
		if (!SETTINGS_FILE.exists()) {
			SETTINGS_WRITER.write(settings, SETTINGS_FILE);
		} else {
			SETTINGS_READER.read(settings, SETTINGS_FILE);
		}
		Stream<? extends Property<?>> properties = Stream.of(
			settings.battleDirectoryProperty(),
			settings.imageDirectoryProperty(),
			settings.exportDirectoryProperty()
		);
		properties.forEach(
			property -> property.addListener(
				(prop, oldValue, newValue) -> SETTINGS_WRITER.write(settings, SETTINGS_FILE)
			)
		);
	}
	
	private MenuBar getMenuBar(BorderPane root, final Stage window) {
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(
			new FileMenu(battle, settings.get(), root),
			new EditMenu(battle, root, window),
			new OptionsMenu(battle.get(), root),
			new UploadMenu(root)
		);
		return menuBar;
	}
	
	private void buildGui(BorderPane root, final Stage window) {
		OverviewPane overviewPane = new OverviewPane(battle);
		root.setTop(getMenuBar(root, window));
		root.setLeft(overviewPane);
		root.setCenter(new BattleGrid(battle.get()));
		root.setRight(new LegendPane(battle.get()));
//		root.setBottom(new InfoPane(overviewPane.actorList.getSelectionModel().selectedItemProperty()));
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public final ObjectProperty<Battle> battleProperty() {
		return this.battle;
	}

	public final Battle getBattle() {
		return this.battleProperty().get();
	}
	

	public final void setBattle(final Battle battle) {
		this.battleProperty().set(battle);
	}
	
	
	
}
