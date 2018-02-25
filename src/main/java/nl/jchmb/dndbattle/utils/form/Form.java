package nl.jchmb.dndbattle.utils.form;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javafx.beans.WeakListener;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.beans.value.WeakChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.utils.BindingUtils;

public class Form extends GridPane {
	private int rowIndex = 0;
	private final List<Property<?>> properties = new ArrayList<>();
	private final List<WeakListener> weakListeners = new ArrayList<>();
	
	public Form() {
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
		spinnerY.getValueFactory().setValue(property.get().getY());
		property.bind(
			new ObjectBinding<>() {
				{
					super.bind(
						spinnerX.valueProperty(),
						spinnerY.valueProperty()
					);
				}

				@Override
				protected Vector2 computeValue() {
					return new Vector2(
						spinnerX.getValue(),
						spinnerY.getValue()
					);
				}
			}
		);
		addField(spinnerX, labelX);
		addField(spinnerY, labelY);
	}
	
	protected void addIntegerField(
		final IntegerProperty property,
		final Spinner<Integer> spinner,
		final String label
	) {
		bindStrongly(property.asObject(), spinner.getValueFactory().valueProperty());
		addField(spinner, label);
	}
	
	protected void addStringField(
		final StringProperty property,
		final TextField field,
		final String label
	) {
		bindStrongly(property, field.textProperty());
		addField(field, label);
	}
	
	protected void addColorField(
		final ObjectProperty<Color> property,
		final ColorPicker colorField,
		final String label
	) {
//		bindWeakly(property, colorField.valueProperty());
//		property.bind(colorField.valueProperty());
		bindStrongly(property, colorField.valueProperty());
		addField(colorField, label);
	}
	
	protected void addOptionalFileField(
		final ObjectProperty<File> property,
		final Supplier<FileChooser> chooserSupplier,
		final String missingText,
		final String label
	) {
		final Button button = new Button();
		button.setOnAction(event -> {
			FileChooser chooser = chooserSupplier.get();
			File file = chooser.showOpenDialog(null);
			if (file != null) {
				property.set(file);
				button.setText(property.get().getAbsolutePath());
			}
		});
		if (property.get() == null) {
			button.setText(missingText);
		} else {
			button.setText(property.get().getAbsolutePath());
		}
		final Button deleteButton = new Button("[X]");
		deleteButton.setOnAction(event -> {
			button.setText(missingText);
			property.set(null);
		});
		final HBox containerField = new HBox();
		containerField.getChildren().addAll(
			button,
			deleteButton
		);
		addField(containerField, label);
	}
	
	protected void addOptionalImageFileField(
		final ObjectProperty<File> property,
		final String label
	) {
		addOptionalFileField(
			property,
			() -> {
				final FileChooser chooser = new FileChooser();
				chooser.setTitle("Choose an image file");
				chooser.getExtensionFilters().add(
					new ExtensionFilter("Image file", "*.jpg", "*.png")
				);
				return chooser;
			},
			"(no image)",
			label
		);
	}
	
	protected final <T> void bindStrongly(final Property<T> modelProperty, final Property<T> fieldProperty) {
		fieldProperty.setValue(modelProperty.getValue());
		modelProperty.bind(fieldProperty);
	}
	
	protected final <T> void bindWeakly(final Property<T> modelProperty, final Property<T> fieldProperty) {
		final WeakChangeListener<T> listener = BindingUtils.weakChangeSetter(modelProperty);
		fieldProperty.setValue(modelProperty.getValue());
		fieldProperty.addListener(listener);
		weakListeners.add(listener);
	}
}
