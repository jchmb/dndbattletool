package nl.jchmb.dndbattle.gui.statuses;

import javafx.beans.property.ListProperty;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import nl.jchmb.dndbattle.core.Status;

public class StatusComboBox extends ComboBox<Status> {
	public StatusComboBox(final ListProperty<Status> statuses, final Stage window) {
		super();
		
		this.setCellFactory(lv -> {
			return new StatusCell(
				statuses,
				Status::nameProperty,
				window,
				false,
				false
			);
		});
		
		itemsProperty().bind(statuses);
		buttonCellProperty().set(
			new StatusCell(
				statuses,
				Status::nameProperty,
				window,
				false,
				false
			)
		);
	}
}
