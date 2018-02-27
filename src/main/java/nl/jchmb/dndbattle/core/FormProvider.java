package nl.jchmb.dndbattle.core;

import javafx.beans.property.Property;
import nl.jchmb.dndbattle.utils.form.Form;

public interface FormProvider {
	public Form getForm();
	public Property<?>[] getProperties();
}
