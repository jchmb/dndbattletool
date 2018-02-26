package nl.jchmb.dndbattle.gui;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.gui.grid.BattleGrid;

public class MainPane extends TabPane {
	private final Battle battle;
	private final BattleGrid grid;
	
	public MainPane(final Battle battle) {
		this.grid = new BattleGrid(battle);
		this.battle = battle;
		
		this.setPrefSize(800, 800);
		
		Tab gridTab = new Tab("Grid", grid);
		gridTab.setClosable(false);
		
		Tab legendTab = new Tab("Legend", null);
		legendTab.setClosable(false);
		
		getTabs().addAll(
			gridTab,
			legendTab
		);
	}
}
