package nl.jchmb.dndbattle.utils.binding;

import java.beans.Expression;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ListBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Spinner;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.utils.IndexedObject;

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
	
	public static <T> StringBinding bindWithDefault(final ObservableValue<T> obj, final String defaultValue) {
		return new StringBinding() {
			{
				super.bind(obj);
			}
			
			@Override
			protected String computeValue() {
				return obj.getValue() == null ?
					obj.toString() :
					defaultValue;
			}
			
		};
	}
	
	public static <T> WeakChangeListener<T> weakChangeSetter(Property<T> property) {
		return new WeakChangeListener<>(
			(prop, oldValue, newValue) -> property.setValue(newValue)
		);
	}
	
	public static WeakChangeListener<String> weakChangeSetter(StringProperty property) {
		return new WeakChangeListener<>(
			(prop, oldValue, newValue) -> property.set(newValue)
		);
	}
	
	public static <T> ListProperty<Node> bindNodes(
			final ListProperty<T> property,
			final List<Node> nodes,
			final Function<T, Node> mapper,
			final Property<?>... extraProperties
	) {
		return bindNodes(
			property,
			nodes,
			mapper,
			x -> true,
			(x, y) -> 0,
			extraProperties
		);
	}
	
	public static <T> ListProperty<Node> bindNodes(
			final ListProperty<T> property,
			final List<Node> nodes,
			final Function<T, Node> mapper,
			final Predicate<T> include,
			final Comparator<T> comparator,
			final Property<?>... extraProperties
	) {
		final ListProperty<Node> binding = new SimpleListProperty<>(
			FXCollections.observableArrayList()
		);
		binding.bind(
			new ListBinding<>() {
				{
					super.bind(property);
					super.bind(extraProperties);
				}
				
				@Override
				protected ObservableList<Node> computeValue() {
					return FXCollections.observableArrayList(
						property.stream()
							.filter(include)
							.sorted(comparator)
							.map(mapper)
							.collect(Collectors.toList())
					);
				}
				
			}
		);
		Bindings.bindContent(
			nodes,
			binding
		);
		return binding;
	}
	
	public static <T> ListProperty<Node> bindIndexedNodes(
			final ListProperty<T> property,
			final List<Node> nodes,
			final Function<IndexedObject<T>, Node> mapper,
			final Predicate<T> include,
			final Comparator<T> comparator,
			final Property<?>... extraProperties
	) {
		final ListProperty<Node> binding = new SimpleListProperty<>(
			FXCollections.observableArrayList()
		);
		final ListBinding<Node> listBinding =
			new ListBinding<>() {
				{
					super.bind(property);
					super.bind(extraProperties);
				}
				
				@Override
				protected ObservableList<Node> computeValue() {
					return FXCollections.observableArrayList(
						IndexedObject.map(
							property.stream()
								.filter(include)
								.sorted(comparator)
						)
						.map(mapper)
						.collect(Collectors.toList())
					);
				}
			};
		binding.bind(listBinding);
//		property.addListener(
//			(Change<? extends T> change) -> {
//				boolean rebind = false;
//				while (change.next()) {
//					if (change.wasUpdated()) {
//						rebind = true;
//					}
//				}
//				if (rebind) {
//					binding.unbind();
//					binding.bind(listBinding);
//				}
//			}
//		);
		Bindings.bindContent(
			nodes,
			binding
		);
		return binding;
	}
}
