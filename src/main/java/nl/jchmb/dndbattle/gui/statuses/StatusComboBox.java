package nl.jchmb.dndbattle.gui.statuses;

import javafx.beans.property.ListProperty;
import javafx.scene.control.ComboBox;
import nl.jchmb.dndbattle.core.Status;

public class StatusComboBox extends ComboBox<Status> {
	public StatusComboBox(final ListProperty<Status> statuses) {
		super();
		
		this.setCellFactory(lv -> {
			return new StatusCell(
				statuses,
				Status::nameProperty,
				false,
				false
			);
		});
		
		itemsProperty().bind(statuses);
		buttonCellProperty().set(
			new StatusCell(
				statuses,
				Status::nameProperty,
				false,
				false
			)
		);
	}
}
