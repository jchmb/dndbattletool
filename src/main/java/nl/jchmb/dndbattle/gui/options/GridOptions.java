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
		
		Spinner<Integer> legendColumnsField = new Spinner<Integer>(1, 3, 1);
		bind(legendColumnsField.getValueFactory().valueProperty(), battle.legendColumnsProperty().asObject());
		
		addField(sizeXField, "Grid width"); // TODO: e/i
		addField(sizeYField, "Grid height"); // TODO: e/i
		addField(cellSizeField, "Cell size"); // TODO: e/i
		addField(backgroundColorField, "Background color"); // TODO: e/i
		addField(gridColorField, "Grid color"); // TODO: e/i
		addField(legendColumnsField, "Legend columns"); // TODO: export/import
	}
}
