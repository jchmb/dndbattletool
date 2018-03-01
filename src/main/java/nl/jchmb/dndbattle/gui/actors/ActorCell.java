/**
 * 
 */
package nl.jchmb.dndbattle.gui.actors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.controlsfx.control.PopOver;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.Window;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.utils.Images;
import nl.jchmb.dndbattle.utils.Popups;

/**
 * @author jochem
 *
 */
public class ActorCell extends ListCell<Actor> {
	private final Battle battle;
	private final boolean hasMenu;
	
	public ActorCell(final Battle battle, final boolean hasMenu) {
		this.battle = battle;
		this.hasMenu = hasMenu;
	}
	
	/* (non-Javadoc)
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	protected void updateItem(Actor actor, boolean empty) {
		super.updateItem(actor, empty);
		
		textProperty().unbind();
		graphicProperty().unbind();
		this.setContextMenu(null);
		if (empty) {
			textProperty().set("");
			graphicProperty().set(null);
			setContextMenu(null);
		} else {
			textProperty().bind(
				hasMenu ?
					actor.nameProperty() :
					Bindings.concat(
						actor.nameProperty(),
						"    ",
						actor.positionProperty()
					)
			);
			graphicProperty().bind(new ObjectBinding<ImageView>(){
				{
					super.bind(actor.avatarProperty());
				}

				@Override
				protected ImageView computeValue() {
					final ImageView view = new ImageView();
					final Path file = actor.avatarProperty().get();
					final Image image;
					if (file != null) {
						image = Images.load(file).get();
						view.setImage(image);
					}
					return view;
				}
				
			});
			if (hasMenu) {
				addContextMenu(actor);
			}
		}
	}
	
	private static MenuItem getDuplicateItem(final Actor actor, final Battle battle) {
		MenuItem item = new MenuItem("Duplicate");
		item.setOnAction(event -> {
			Actor duplicateActor = actor.duplicate();
			duplicateActor.setRandomPosition(battle);
			battle.getActors().add(duplicateActor);
		});
		return item;
	}
	
	private void addContextMenu(final Actor actor) {
		setContextMenu(createContextMenu(actor, battle));
	}
	
	public static ContextMenu createContextMenu(final Actor actor, final Battle battle) {
		ContextMenu contextMenu = new ContextMenu();
		
		MenuItem editItem = new MenuItem("Edit");
		editItem.setOnAction(event -> {
			ActorEditor editor = new ActorEditor(actor, battle);
			Popups.showForm(
				editor,
				"Edit actor"
			);
		});
		
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction(event -> {
			battle.actorsProperty().remove(actor);
		});
		
		contextMenu.getItems().addAll(
			editItem,
			getDuplicateItem(actor, battle),
			deleteItem
		);
		return contextMenu;
	}
}
