package nl.jchmb.dndbattle.gui.statuses;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ListView;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;

public class StatusList extends ListView<Status> {
	public StatusList(final ListProperty<Status> statuses, boolean mainList) {
		this.setWidth(200);
		this.setPrefHeight(96);
		this.setCellFactory(lv -> {
			return new StatusCell(
				statuses,
				Status::nameProperty,
				mainList
			);
		});
		
		itemsProperty().bind(statuses);
	}

}
