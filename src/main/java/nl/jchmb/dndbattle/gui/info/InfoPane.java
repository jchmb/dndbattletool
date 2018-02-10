package nl.jchmb.dndbattle.gui.info;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.scene.layout.Pane;
import jodd.jerry.Jerry;
import nl.jchmb.dndbattle.core.Actor;

public class InfoPane extends Pane {
	private final ObjectProperty<Actor> actor = new SimpleObjectProperty<>();
	
	public InfoPane(final ObservableValue<Actor> property) {
		super();
		
		actor.bind(property);
		actor.addListener(
			(prop, oldValue, newValue) -> rebuild()
		);
		rebuild();
	}
	
	private void rebuild() {
		getChildren().clear();
		if (actor.get() == null || actor.get().getSheet().isEmpty()) {
			return;
		}
		try {
			URL u = new URL(actor.get().getSheet());
		    String html = new String(u.openStream().readAllBytes(), StandardCharsets.UTF_8);
		    Jerry doc = Jerry.jerry(html);
		    buildFromJerry(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void buildFromJerry(Jerry doc) {
		String s = doc.$("td[name=AC]").first().attr("value");
		System.out.println(s);
	}
}
