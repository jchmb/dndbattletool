package nl.jchmb.dndbattle.gui.options;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Gender;
import nl.jchmb.dndbattle.gui.gender.GenderList;

public class GenderOptions extends BorderPane {
	public GenderOptions(final Battle battle) {
		super();
		
		final GenderList list = new GenderList(battle, true, true);
		
		setLeft(list);
		setTop(getMenuBar(battle));
	}
	
	private MenuBar getMenuBar(final Battle battle) {
		MenuBar menuBar = new MenuBar();
		
		Menu menu = new Menu("Edit");
		MenuItem addItem = new MenuItem("Add gender");
		addItem.setOnAction(event -> {
			battle.gendersProperty().add(new Gender());
		});
		menu.getItems().addAll(
			addItem
		);
		
		menuBar.getMenus().addAll(menu);
		
		return menuBar;
	}
}
