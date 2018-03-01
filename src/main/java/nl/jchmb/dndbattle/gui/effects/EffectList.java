package nl.jchmb.dndbattle.gui.effects;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Round;
import nl.jchmb.dndbattle.core.effects.Effect;
import nl.jchmb.dndbattle.utils.binding.BindingUtils;

public class EffectList extends BorderPane {
	public EffectList(final Battle battle) {
		super();
		
		final ComboBox<Round> selectedRound = new ComboBox<>();
		selectedRound.itemsProperty().bind(battle.roundsProperty());
		
		final ListView<Effect> effectList = new ListView<>();
		effectList.itemsProperty().bind(
			BindingUtils.binding(
				selectedRound.valueProperty(),
				round -> round == null ?
					FXCollections.emptyObservableList() :
					round.effectsProperty()
			)
		);
		
		setTop(selectedRound);
		setCenter(effectList);
	}
}
