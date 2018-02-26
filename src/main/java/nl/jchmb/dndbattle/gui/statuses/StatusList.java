package nl.jchmb.dndbattle.gui.statuses;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;

public class StatusList extends ListView<Status> {
	public StatusList(final ListProperty<Status> statuses, boolean editable) {
		this.setWidth(200);
		this.setPrefHeight(96);
		this.setCellFactory(lv -> {
			return new StatusCell(
				statuses,
				Status::nameProperty,
				editable,
				true
			);
		});
		
		itemsProperty().bind(statuses);
	}

}
