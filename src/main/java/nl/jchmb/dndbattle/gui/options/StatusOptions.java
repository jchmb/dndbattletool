package nl.jchmb.dndbattle.gui.options;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.gui.statuses.StatusList;

public class StatusOptions extends BorderPane {
	public StatusOptions(final Battle battle) {
		super();
		
		final StatusList list = new StatusList(battle.statusesProperty(), true);
		final Pane emptyPane = new Pane();
		emptyPane.setPrefSize(300, 600);
		
		setLeft(list);
		setCenter(emptyPane);
	}
}
