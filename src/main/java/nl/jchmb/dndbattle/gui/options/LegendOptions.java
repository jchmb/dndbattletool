package nl.jchmb.dndbattle.gui.options;

import javafx.scene.control.ColorPicker;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.utils.form.Form;

public class LegendOptions extends Form {
	public LegendOptions(final Battle battle) {
		super();
		
		/* Legend entry even color */
		addColorField(
			battle.legendEntryEvenColorProperty(),
			new ColorPicker(),
			"Entry background color (even)"
		);
		
		/* Legend entry odd color */
		addColorField(
			battle.legendEntryOddColorProperty(),
			new ColorPicker(),
			"Entry background color (odd)"
		);
		
		/* Legend entry font color */
		addColorField(
			battle.legendEntryFontColorProperty(),
			new ColorPicker(),
			"Entry font color"
		);
	}
}
