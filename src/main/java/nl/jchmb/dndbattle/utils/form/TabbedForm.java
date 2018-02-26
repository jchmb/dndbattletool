package nl.jchmb.dndbattle.utils.form;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabbedForm extends TabPane {
	private final Map<String, Form> forms = new HashMap<>();
	
	public TabbedForm() {
		super();
	}
	
	protected final Form addFormTab(String key, String label) {
		final Form form = new Form();
		final Tab tab = new Tab(label);
		tab.setClosable(false);
		tab.setContent(form);
		getTabs().add(tab);
		forms.put(key, form);
		return form;
	}
	
	protected final Form getFormTab(String key) {
		return forms.get(key);
	}
}
