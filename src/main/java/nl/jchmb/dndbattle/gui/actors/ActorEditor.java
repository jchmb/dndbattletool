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
import javafx.stage.Stage;
import javafx.stage.Window;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.gui.statuses.StatusComboBox;
import nl.jchmb.dndbattle.gui.statuses.StatusList;
import nl.jchmb.dndbattle.utils.binding.BindingUtils;
import nl.jchmb.dndbattle.utils.form.Form;
import nl.jchmb.dndbattle.utils.form.PopOverForm;
import nl.jchmb.dndbattle.utils.form.TabbedForm;

/**
 * @author jochem
 *
 */
public class ActorEditor extends TabbedForm {
	private final Actor actor;
	
	public ActorEditor(final Actor actor, final Battle battle) {
		super();
		
		this.actor = actor;
		
		addFormTab("general", "General");
		addFormTab("avatar", "Avatar");
		addFormTab("stats", "Stats");
		addFormTab("statuses", "Statuses");
		addFormTab("options", "Options");
		
		buildFields(battle);
	}
	
	protected void buildFields(final Battle battle) {
		/* Name */
		getFormTab("general").addStringField(
			actor.nameProperty(),
			new TextField(),
			"Name"
		);
		
		/* Gender */
		getFormTab("general").addComboBoxField(
			actor.genderProperty(),
			battle.gendersProperty(),
			"Gender"
		);
		
		/* Initiative */
		getFormTab("stats").addIntegerField(
			actor.initiativeProperty(),
			new Spinner<Integer>(-20, Integer.MAX_VALUE, 1),
			"Initiative"
		);
		
		/* HP */
		getFormTab("stats").addIntegerField(
			actor.currentHpProperty(),
			new Spinner<Integer>(-10, Integer.MAX_VALUE, 1),
			"Current HP"
		);
		
		/* Max HP */
		getFormTab("stats").addIntegerField(
			actor.maxHpProperty(),
			new Spinner<Integer>(1, Integer.MAX_VALUE, 1),
			"Max HP"
		);
		
		/* Active */
		getFormTab("options").addBooleanField(
			actor.activeProperty(),
			"Active"
		);
		
		/* Hide HP */
		getFormTab("options").addBooleanField(
			actor.hiddenHpProperty(),
			"Hide HP"
		);
		
		/* Hide position */
		getFormTab("options").addBooleanField(
			actor.hiddenPositionProperty(),
			"Hide position"
		);
		
		/* Proper */
		getFormTab("options").addBooleanField(
			actor.properProperty(),
			"Proper name"
		);
		
		/* Avatar */
		getFormTab("avatar").addImageFileFieldWithDefault(
			actor.avatarProperty(),
			new File("res/unknown.jpg"),
			"(no image)",
			"[Reset]",
			"Avatar"
		);
		
		/* Size */
		getFormTab("avatar").addVector2Field(
			actor.sizeProperty(),
			new Spinner<Integer>(1, 5, 1),
			new Spinner<Integer>(1, 5, 1),
			"Width",
			"Height"
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
		
//		ActorComboBox test = new ActorComboBox(battle);
		
		/* Character sheet */
//		TextField sheetField = new TextField();
//		bind(sheetField.textProperty(), actor.sheetProperty());
		
		statusesField.itemsProperty().bind(actor.statusesProperty());
		getFormTab("statuses").addField(statusesContainer, "Statuses");
//		addField(test, "Test");
		
		
	}
	
	
	
	
}
