package nl.jchmb.dndbattle.gui.gender;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Gender;

public class GenderList extends ListView<Gender> {
	public GenderList(final Battle battle, final boolean editable, final boolean deletable) {
		super();
		
		this.setWidth(200);
		this.setPrefHeight(96);
		this.setCellFactory(lv -> {
			return new GenderCell(
				battle.gendersProperty(),
				Gender::symbolProperty,
				editable,
				deletable
			);
		});
		
		itemsProperty().bind(battle.gendersProperty());
	}
}
