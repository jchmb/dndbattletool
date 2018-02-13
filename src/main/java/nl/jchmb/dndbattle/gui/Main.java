/**
 * 
 */
package nl.jchmb.dndbattle.gui;

import javafx.application.Application;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
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
import nl.jchmb.dndbattle.core.events.AttackDamageAction;
import nl.jchmb.dndbattle.core.events.AttackMissedAction;
import nl.jchmb.dndbattle.core.events.ComplexEvent;
import nl.jchmb.dndbattle.core.events.MoveAction;
import nl.jchmb.dndbattle.core.events.registry.BattleEventRegistry;
import nl.jchmb.dndbattle.core.events.registry.NothingAction;
import nl.jchmb.dndbattle.gui.actors.ActorList;
import nl.jchmb.dndbattle.gui.grid.BattleGrid;
import nl.jchmb.dndbattle.gui.info.InfoPane;
import nl.jchmb.dndbattle.gui.legend.LegendPane;
import nl.jchmb.dndbattle.gui.menu.EditMenu;
import nl.jchmb.dndbattle.gui.menu.FileMenu;
import nl.jchmb.dndbattle.gui.menu.UploadMenu;
import nl.jchmb.dndbattle.utils.BindingUtils;

/**
 * @author jochem
 *
 */
public class Main extends Application {
	private static final String VERSION = "0.3";
	
	private final ObjectProperty<Battle> battle = new SimpleObjectProperty<>(new Battle());
	private final BattleEventRegistry eventRegistry = new BattleEventRegistry();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/* Initialize stuff */
			registerBattleEvents();
			BorderPane root = new BorderPane();
			buildGui(root);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
				getClass().getResource("style.css").toExternalForm()
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
	
	private MenuBar getMenuBar(BorderPane root) {
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(
			new FileMenu(battle, root),
			new EditMenu(battle, root),
			new UploadMenu(root)
		);
		return menuBar;
	}
	
	private void buildGui(BorderPane root) {
		OverviewPane overviewPane = new OverviewPane(battle);
		root.setTop(getMenuBar(root));
		root.setLeft(overviewPane);
		root.setCenter(new BattleGrid(battle));
		root.setRight(new LegendPane(battle));
//		root.setBottom(new InfoPane(overviewPane.actorList.getSelectionModel().selectedItemProperty()));
	}
	
	private void registerBattleEvents() {
		eventRegistry
			.register("move", MoveAction.class)
			.register("attack_missed", AttackMissedAction.class)
			.register("attack_damage", AttackDamageAction.class)
			.register("complex", ComplexEvent.class)
			.register("nothing", NothingAction.class)
		;
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
