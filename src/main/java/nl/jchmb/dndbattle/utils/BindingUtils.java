package nl.jchmb.dndbattle.utils;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.WeakChangeListener;
import javafx.scene.control.Spinner;
import nl.jchmb.dndbattle.core.Vector2;

public class BindingUtils {
	public static <T, U> ObjectBinding<U> binding(Property<T> property, Function<T, U> fn) {
		return new ObjectBinding<U>() {
			{
				super.bind(property);
			}
			
			@Override
			protected U computeValue() {
				return fn.apply(property.getValue());
			}
			
		};
	}
	
	public static <T, U, V> ObjectBinding<V> binding(Property<T> p1, Property<U> p2, BiFunction<T, U, V> fn) {
		return new ObjectBinding<V>() {
			{
				super.bind(p1, p2);
			}
			
			@Override
			protected V computeValue() {
				return fn.apply(p1.getValue(), p2.getValue());
			}
			
		};
	}
	
	public static void bindSpinners(ObjectProperty<Vector2> property, Spinner<Integer> spinnerX, Spinner<Integer> spinnerY) {
		spinnerX.getValueFactory().setValue(property.get().getX());
		spinnerX.valueProperty().addListener(
			new WeakChangeListener<Integer>(
				(prop, oldValue, newValue) -> property.set(new Vector2(newValue, property.get().getY()))
			)
		);
		
		spinnerY.getValueFactory().setValue(property.get().getY());
		spinnerY.valueProperty().addListener(
			new WeakChangeListener<Integer>(
				(prop, oldValue, newValue) -> property.set(new Vector2(property.get().getX(), newValue))
			)
		);
	}
}
