package nl.jchmb.dndbattle.gui.options;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.gui.statuses.StatusList;

public class StatusOptions extends BorderPane {
	private final Battle battle;
	public StatusOptions(final Battle battle, final Stage window) {
		super();
		
		this.battle = battle;
		setPrefSize(175, 400);
		final StatusList list = new StatusList(battle.statusesProperty(), window, true);
		final Pane emptyPane = new Pane();
//		emptyPane.setPrefSize(600, 400);
		
		setLeft(list);
//		setCenter(emptyPane);
		
		addMenu();
	}
	
	private void addMenu() {
		MenuBar menuBar = new MenuBar();
		Menu editMenu = new Menu("Edit");
		editMenu.getItems().addAll(
			getAddStatusItem()
		);
		menuBar.getMenus().addAll(
			editMenu
		);
		setTop(menuBar);
	}
	
	private MenuItem getAddStatusItem() {
		MenuItem item = new MenuItem("Add status");
		item.setOnAction(event -> {
			Status status = new Status();
			battle.statusesProperty().add(status);
		});
		return item;
	}
}
