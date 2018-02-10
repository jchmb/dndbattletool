/**
 * 
 */
package nl.jchmb.dndbattle.gui.actors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.controlsfx.control.PopOver;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.Property;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.WeakListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.gui.statuses.StatusComboBox;
import nl.jchmb.dndbattle.gui.statuses.StatusList;
import nl.jchmb.dndbattle.utils.BindingUtils;
import nl.jchmb.dndbattle.utils.PopOverForm;

/**
 * @author jochem
 *
 */
public class ActorEditor extends PopOverForm {
	private final Actor actor;
	
	public ActorEditor(final Actor actor, final Battle battle) {
		super();
		
		this.actor = actor;
		this.setDetachable(false);
		this.setSkin(this.createDefaultSkin());
		
		buildFields(battle);
		finish();
	}
	
	protected void buildFields(final Battle battle) {
		/* Name */
		TextField nameField = new TextField();
		bind(nameField.textProperty(), actor.nameProperty());
		
		/* Initiative */
		Spinner<Integer> initiativeField = new Spinner<Integer>(-20, 100, 1);
		bind(initiativeField.valueFactoryProperty().get().valueProperty(), actor.initiativeProperty().asObject());
		initiativeField.setEditable(true);
		
		/* HP */
		Spinner<Integer> currentHpField = new Spinner<Integer>(-10, Integer.MAX_VALUE, 1);
		bind(currentHpField.valueFactoryProperty().get().valueProperty(), actor.currentHpProperty().asObject());
		currentHpField.setEditable(true);
		
		Spinner<Integer> maxHpField = new Spinner<Integer>(1, Integer.MAX_VALUE, 1);
		bind(maxHpField.valueFactoryProperty().get().valueProperty(), actor.maxHpProperty().asObject());
		maxHpField.setEditable(true);
		
		/* Hide HP */
		CheckBox hiddenField = new CheckBox();
		bind(hiddenField.selectedProperty(), actor.hiddenHpProperty());
		
		/* Avatar */
		Button avatarField = new Button("...choose image...");
		avatarField.setOnAction(event -> {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Select avatar");
			chooser.setInitialDirectory(new File("res"));
			chooser.getExtensionFilters().add(
				new ExtensionFilter("Image files", "*.png", "*.jpg", "*.jpeg")
			);
			File file = chooser.showOpenDialog(Window.getWindows().get(0));
			if (file != null) {
				actor.setAvatar(file);
			}
		});
		bindObject(
			avatarField.textProperty(),
			actor.avatarProperty(),
			avatar -> avatar.getAbsolutePath()
		);
		
		/* Size */
		Spinner<Integer> widthField = new Spinner<Integer>(1, 5, 1);
		widthField.getValueFactory().setValue(actor.getSize().getX());
		widthField.valueProperty().addListener(
			new WeakChangeListener<Integer>(
				(prop, oldValue, newValue) -> actor.setSize(new Vector2(newValue, actor.getSize().getY()))
			)
		);
		
		
		Spinner<Integer> heightField = new Spinner<Integer>(1, 5, 1);
		heightField.getValueFactory().setValue(actor.getSize().getY());
		heightField.valueProperty().addListener(
			new WeakChangeListener<Integer>(
				(prop, oldValue, newValue) -> actor.setSize(new Vector2(actor.getSize().getX(), newValue))
			)
		);
		
		/* Statuses */
		ListView<Status> statusesField = new StatusList(actor.statusesProperty(), false);
		HBox addStatusField = new HBox();
		ComboBox<Status> availableStatuses = new StatusComboBox(battle.statusesProperty());
		Button addStatusButton = new Button("Add");
		addStatusButton.setOnAction(event -> {
			Status selected = availableStatuses.getValue();
			if (selected != null && !actor.getStatuses().contains(selected)) {
				actor.getStatuses().add(selected);
			}
		});
		addStatusField.getChildren().addAll(
			availableStatuses,
			addStatusButton
		);
		
		VBox statusesContainer = new VBox();
		statusesContainer.getChildren().addAll(
			statusesField,
			addStatusField
		);
		
		/* Character sheet */
//		TextField sheetField = new TextField();
//		bind(sheetField.textProperty(), actor.sheetProperty());
		
		statusesField.itemsProperty().bind(actor.statusesProperty());
		
		addField(nameField, "Name");
		addField(initiativeField, "Initiative");
		addField(currentHpField, "Current HP");
		addField(maxHpField, "Max HP");
		addField(hiddenField, "Hide HP");
		addField(widthField, "Width");
		addField(heightField, "Height");
		addField(avatarField, "Avatar");
		addField(statusesContainer, "Statuses");
//		addField(sheetField, "Sheet URL");
		
		
	}
	
	
	
	
}
