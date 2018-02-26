package nl.jchmb.dndbattle.gui.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.gui.options.GenderOptions;
import nl.jchmb.dndbattle.gui.options.GridOptions;
import nl.jchmb.dndbattle.gui.options.LegendOptions;
import nl.jchmb.dndbattle.gui.options.StatusOptions;
import nl.jchmb.dndbattle.utils.Popups;

public class OptionsMenu extends Menu {
	private final Battle battle;
	
	public OptionsMenu(final Battle battle, final BorderPane pane) {
		super("Options");
		this.battle = battle;
		getItems().addAll(
			getGridOptionsItem(pane),
			getLegendOptionsItem(pane),
			getStatusOptionsItem(pane),
			getGenderOptionsItem(pane)
		);
	}
	
	private final MenuItem getStatusOptionsItem(BorderPane pane) {
		MenuItem item = new MenuItem("Status options");
		item.setOnAction(event -> {
			Popups.show(
				new StatusOptions(battle),
				"Edit statuses"
			);
		});
		return item;
	}
	
	private final MenuItem getGridOptionsItem(final BorderPane pane) {
		MenuItem item = new MenuItem("Grid options");
		item.setOnAction(event -> {
			GridOptions gridOptions = new GridOptions(battle);
//			gridOptions.show(pane.getCenter());
			Popups.showForm(gridOptions, "Grid options");
		});
		return item;
	}
	
	private final MenuItem getLegendOptionsItem(final BorderPane pane) {
		MenuItem item = new MenuItem("Legend options");
		item.setOnAction(event -> {
			LegendOptions legendOptions = new LegendOptions(battle);
			Popups.showForm(legendOptions, "Legend options");
		});
		return item;
	}
	
	private final MenuItem getGenderOptionsItem(final BorderPane pane) {
		MenuItem item = new MenuItem("Gender options");
		item.setOnAction(event -> {
			GenderOptions genderOptions = new GenderOptions(battle);
			Popups.show(genderOptions, "Gender options");
		});
		return item;
	}
}
