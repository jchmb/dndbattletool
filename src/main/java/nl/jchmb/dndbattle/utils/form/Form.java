package nl.jchmb.dndbattle.utils.form;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import nl.jchmb.dndbattle.core.FormProvider;
import nl.jchmb.dndbattle.core.Settings;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.utils.MultiFactory;
import nl.jchmb.dndbattle.utils.Popups;

public class Form extends GridPane implements FormService {
	private int rowIndex = 0;
	private final List<Property<?>> properties = new ArrayList<>();
	private final Map<String, Node> labels = new HashMap<>();
	private final Map<String, Node> fields = new HashMap<>();
	
	public Form() {
		setPadding(new Insets(10, 10, 10, 10));
		ColumnConstraints cc1 = new ColumnConstraints();
		cc1.setMinWidth(150.0f);
		getColumnConstraints().add(cc1);
		ColumnConstraints cc2 = new ColumnConstraints();
		cc2.setHgrow(Priority.ALWAYS);
		getColumnConstraints().add(cc2);
	}
	
	protected final void setFieldVisibility(final String label, final boolean visibility) {
		for (int i = 0; i < 2; i++) {
			labels.get(label).setVisible(visibility);
			fields.get(label).setVisible(visibility);
		}
	}
	
	public void addField(Node node, String label) {
		Label fieldLabel = new Label(label);
		fieldLabel.setTextFill(Color.BLACK);
		add(fieldLabel, 0, rowIndex);
		add(node, 1, rowIndex);
		GridPane.setMargin(fieldLabel, new Insets(5));
		GridPane.setMargin(node, new Insets(5));
		labels.put(label, fieldLabel);
		fields.put(label, node);
		++rowIndex;
	}
	
	public void addVector2Field(
			final ObjectProperty<Vector2> property,
			final Spinner<Integer> spinnerX,
			final Spinner<Integer> spinnerY,
			final String labelX,
			final String labelY
	) {
		spinnerX.getValueFactory().setValue(property.get().getX());
		spinnerY.getValueFactory().setValue(property.get().getY());
		spinnerX.setEditable(true);
		spinnerY.setEditable(true);
		property.bind(
			new ObjectBinding<>() {
				{
					super.bind(
						spinnerX.valueProperty(),
						spinnerY.valueProperty()
					);
				}

				@Override
				public Vector2 computeValue() {
					return new Vector2(
						spinnerX.getValue(),
						spinnerY.getValue()
					);
				}
			}
		);
		properties.add(property);
		addField(spinnerX, labelX);
		addField(spinnerY, labelY);
	}
	
	public void addIntegerField(
		final IntegerProperty property,
		final Spinner<Integer> spinner,
		final String label
	) {
		final ObjectProperty<Integer> objectProperty = property.asObject();
		bindStrongly(objectProperty, spinner.getValueFactory().valueProperty());
		spinner.setEditable(true);
		addField(spinner, label);
	}
	
	public void addDoubleField(
		final DoubleProperty property,
		final Spinner<Double> spinner,
		final String label
	) {
		final ObjectProperty<Double> objectProperty = property.asObject();
		bindStrongly(objectProperty, spinner.getValueFactory().valueProperty());
		spinner.setEditable(true);
		addField(spinner, label);
	}
	
	public void addBooleanField(
		final BooleanProperty property,
		final String label
	) {
		CheckBox box = new CheckBox();
		box.setSelected(property.get());
		bindStrongly(property, box.selectedProperty());
		addField(box, label);
	}
	
	public void addStringField(
		final StringProperty property,
		final TextField field,
		final String label
	) {
		bindStrongly(property, field.textProperty());
		addField(field, label);
	}
	
	public void addColorField(
		final ObjectProperty<Color> property,
		final ColorPicker colorField,
		final String label
	) {
//		bindWeakly(property, colorField.valueProperty());
//		property.bind(colorField.valueProperty());
		bindStrongly(property, colorField.valueProperty());
		addField(colorField, label);
	}
	
	public void addFileFieldWithDefault(
		final ObjectProperty<File> property,
		final Supplier<FileChooser> chooserSupplier,
		final File defaultFile,
		final Consumer<File> fileSelectedConsumer,
		final String defaultText,
		final String resetButtonText,
		final String label
	) {
		final Button button = new Button();
		button.setOnAction(event -> {
			FileChooser chooser = chooserSupplier.get();
			File file = chooser.showOpenDialog(null);
			if (file != null) {
				property.set(file);
				button.setText(property.get().getName());
				fileSelectedConsumer.accept(file);
			}
		});
		if (property.get() == null) {
			button.setText(defaultFile == null ? defaultText : defaultFile.getName());
		} else {
			button.setText(property.get().getName());
		}
		final Button deleteButton = new Button(resetButtonText);
		deleteButton.setOnAction(event -> {
			button.setText(defaultFile == null ? defaultText : defaultFile.getName());
			property.set(defaultFile);
		});
		final HBox containerField = new HBox();
		containerField.getChildren().addAll(
			button,
			deleteButton
		);
		addField(containerField, label);
	}
	
	public void addOptionalFileField(
			final ObjectProperty<File> property,
			final Supplier<FileChooser> chooserSupplier,
			final Consumer<File> fileSelectedConsumer,
			final String missingText,
			final String label
	) {
		addFileFieldWithDefault(
			property,
			chooserSupplier,
			null,
			fileSelectedConsumer,
			missingText,
			"[X]",
			label
		);
	}
	
	private final FileChooser getImageChooser() {
		final FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(Settings.INSTANCE.getImageDirectory());
		chooser.setTitle("Choose an image file");
		chooser.getExtensionFilters().add(
			new ExtensionFilter("Image file", "*.jpg", "*.png")
		);
		return chooser;
	}
	
	private void setImageDirectory(final File file) {
		Settings.INSTANCE.setImageDirectory(file.getParentFile());
	}
	
	public void addOptionalImageFileField(
		final ObjectProperty<File> property,
		final String label
	) {
		addOptionalFileField(
			property,
			this::getImageChooser,
			this::setImageDirectory,
			"(no image)",
			label
		);
	}
	
	public void addImageFileFieldWithDefault(
			final ObjectProperty<File> property,
			final File defaultFile,
			final String defaultText,
			final String resetText,
			final String label
	) {
		addFileFieldWithDefault(
			property,
			this::getImageChooser,
			defaultFile,
			this::setImageDirectory,
			defaultText,
			resetText,
			label
		);
	}
	
	public <T> void addComboBoxField(
			final ObjectProperty<T> property,
			final ListProperty<T> sourceProperty,
			final String label
	) {
		final ComboBox<T> comboBox = new ComboBox<>();
		comboBox.itemsProperty().bind(sourceProperty);
		bindStrongly(property, comboBox.valueProperty());
		addField(comboBox, label);
	}
	
	public <T> void addComboBoxField(
			final ObjectProperty<T> property,
			final ObservableList<T> options,
			final String label
	) {
		final ComboBox<T> comboBox = new ComboBox<>();
		comboBox.setItems(options);
		bindStrongly(property, comboBox.valueProperty());
		addField(comboBox, label);
	}
	
	public <T extends FormProvider> void addSubclassableObjectField(
			final ObjectProperty<T> property,
			final MultiFactory<T> factory,
			final String label
	) {
		final ComboBox<T> comboBox = new ComboBox<>();
		comboBox.setItems(factory.produceObjects(property.get()));
		comboBox.setValue(property.get());
		final Button button = new Button("Edit");
		button.setOnAction(event -> {
			final Form subForm = property.get().getForm();
			Popups.showForm(
				subForm,
				"Edit " + property.get().toString().toLowerCase()
			);
		});
		final HBox containerField = new HBox();
		containerField.getChildren().addAll(
			comboBox,
			button
		);
		bindStrongly(property, comboBox.valueProperty());
		addField(containerField, label);
	}
	
	public final <T> void bindStrongly(final Property<T> modelProperty, final Property<T> fieldProperty) {
		fieldProperty.setValue(modelProperty.getValue());
		modelProperty.bind(fieldProperty);
		properties.add(modelProperty);
	}
	
	public void close() {
		properties.forEach(Property::unbind);
		properties.clear();
	}
}
