package nl.jchmb.dndbattle.gui.gender;

import javafx.scene.control.TextField;
import nl.jchmb.dndbattle.core.Gender;
import nl.jchmb.dndbattle.utils.form.Form;

public class GenderEditor extends Form {
	public GenderEditor(final Gender gender) {
		super();
		
		/* Symbol/name */
		addStringField(
			gender.symbolProperty(),
			new TextField(),
			"Name"
		);
		
		/* Subject pronoun */
		addStringField(
			gender.subjectPronounProperty(),
			new TextField(),
			"Subject pronoun"
		);
		
		/* Object pronoun */
		addStringField(
			gender.objectPronounProperty(),
			new TextField(),
			"Object pronoun"
		);
		
		/* Possessive pronoun */
		addStringField(
			gender.possessivePronounProperty(),
			new TextField(),
			"Possessive pronoun"
		);
	}
}
