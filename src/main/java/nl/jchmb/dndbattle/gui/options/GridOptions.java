package nl.jchmb.dndbattle.gui.options;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.utils.BindingUtils;
import nl.jchmb.dndbattle.utils.form.PopOverForm;

public class GridOptions extends PopOverForm {
	private final Battle battle;
	
	public GridOptions(final Battle battle) {
		super();
		
		this.battle = battle;
		buildFields();
		finish();
	}
	
	private void buildFields() {
		Spinner<Integer> cellSizeField = new Spinner<Integer>(16, 96, 1);
		bind(cellSizeField.getValueFactory().valueProperty(), battle.cellSizeProperty().asObject());
		
		Spinner<Integer> sizeXField = new Spinner<Integer>(2, 20, 1);
		Spinner<Integer> sizeYField = new Spinner<Integer>(2, 20, 1);
		BindingUtils.bindSpinners(battle.gridSizeProperty(), sizeXField, sizeYField);
		
		ColorPicker backgroundColorField = new ColorPicker();
		bind(backgroundColorField.valueProperty(), battle.backgroundColorProperty());
		
		ColorPicker gridColorField = new ColorPicker();
		bind(gridColorField.valueProperty(), battle.borderColorProperty());
		
		addField(sizeXField, "Grid width");
		addField(sizeYField, "Grid height");
		addField(cellSizeField, "Cell size");
		addField(backgroundColorField, "Background color");
		addField(gridColorField, "Grid color");
	}
}
