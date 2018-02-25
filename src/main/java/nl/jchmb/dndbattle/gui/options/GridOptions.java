package nl.jchmb.dndbattle.gui.options;

import java.io.File;
import java.util.Collection;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.utils.BindingUtils;
import nl.jchmb.dndbattle.utils.form.Form;
import nl.jchmb.dndbattle.utils.form.PopOverForm;

public class GridOptions extends Form {
	private final Battle battle;
	
	public GridOptions(final Battle battle) {
		super();
		
		this.battle = battle;
//		setPrefWidth(800.0f);
		buildFields();
	}
	
	private void buildFields() {
		Spinner<Integer> cellSizeField = new Spinner<Integer>(16, 96, 1);
		addIntegerField(
			battle.cellSizeProperty(),
			cellSizeField,
			"Cell size"
		);
		
		addVector2Field(
			battle.gridSizeProperty(),
			new Spinner<Integer>(2, 20, 1),
			new Spinner<Integer>(2, 20, 1),
			"Width",
			"Height"
		);
		
		addColorField(
			battle.backgroundColorProperty(),
			new ColorPicker(),
			"Background color"
		);
		
		addColorField(
			battle.borderColorProperty(),
			new ColorPicker(),
			"Grid color"
		);
		
		addOptionalImageFileField(
			battle.backgroundImageFileProperty(),
			"Background image"
		);
	}
}
