package nl.jchmb.dndbattle.gui.grid;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.utils.PopOverForm;

public class BattleGridOptions extends PopOverForm {
	private Battle battle;
	
	public BattleGridOptions(final Battle battle) {
		super();
		
		this.battle = battle;
		this.setDetached(false);
		this.setTitle("Battle grid options");
		this.setHeaderAlwaysVisible(true);
		
		buildFields();
		finish();
	}
	
	private void buildFields() {
		ColorPicker backgroundColor = new ColorPicker();
		backgroundColor.setValue(battle.getBackgroundColor());
		
		
		ColorPicker lineColor = new ColorPicker();
		lineColor.setValue(battle.getBorderColor());
		
		
		
	}
}
