package nl.jchmb.dndbattle.utils.form;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javafx.beans.WeakListener;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.WeakChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.utils.BindingUtils;

public class Form extends GridPane {
	private int rowIndex = 0;
	private final List<Property<?>> properties = new ArrayList<>();
	private final List<WeakListener> weakListeners;
	
	public Form() {
		weakListeners = new ArrayList<>();
		setPadding(new Insets(10, 10, 10, 10));
	}
	
	public void addField(Node node, String label) {
		Label fieldLabel = new Label(label);
		fieldLabel.setTextFill(Color.BLACK);
		add(fieldLabel, 0, rowIndex);
		add(node, 1, rowIndex);
		GridPane.setMargin(fieldLabel, new Insets(5));
		GridPane.setMargin(node, new Insets(5));
		++rowIndex;
	}
	
	protected <T> void bind(Property<T> p1, Property<T> p2) {
		p1.bindBidirectional(p2);
		properties.add(p1);
	}
	
	protected <T, U> void bindObject(Property<T> p1, Property<U> p2, Function<U, T> fn) {
		p1.bind(new ObjectBinding<T>() {
			{
				super.bind(p2);
			}
			
			@Override
			protected T computeValue() {
				return fn.apply(p2.getValue());
			}
			
		});
		properties.add(p1);
	}
	
	protected void unbindAll() {
		// TODO: unbind bidirectional?
		properties.forEach(Property::unbind);
	}
	
	protected void addVector2Field(
			final ObjectProperty<Vector2> property,
			final Spinner<Integer> spinnerX,
			final Spinner<Integer> spinnerY,
			final String labelX,
			final String labelY
	) {
		spinnerX.getValueFactory().setValue(property.get().getX());
		final WeakChangeListener<Integer> listenerX = new WeakChangeListener<Integer>(
			(prop, oldValue, newValue) -> property.set(new Vector2(newValue, property.get().getY()))
		);
		spinnerX.valueProperty().addListener(listenerX);
		
		spinnerY.getValueFactory().setValue(property.get().getY());
		final WeakChangeListener<Integer> listenerY = new WeakChangeListener<Integer>(
			(prop, oldValue, newValue) -> property.set(new Vector2(newValue, property.get().getY()))
		);
		spinnerY.valueProperty().addListener(listenerY);
		weakListeners.add(listenerX);
		weakListeners.add(listenerY);
		addField(spinnerX, labelX);
		addField(spinnerY, labelY);
	}
}
