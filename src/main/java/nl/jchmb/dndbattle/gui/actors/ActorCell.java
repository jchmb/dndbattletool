/**
 * 
 */
package nl.jchmb.dndbattle.gui.actors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.controlsfx.control.PopOver;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;

/**
 * @author jochem
 *
 */
public class ActorCell extends ListCell<Actor> {
	private final ActorList list;
	private final ObjectProperty<Battle> battle;
	
	public ActorCell(final ObjectProperty<Battle> battle, final ActorList list) {
		this.battle = battle;
		this.list = list;
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
			textProperty().bind(actor.nameProperty());
			graphicProperty().bind(new ObjectBinding<ImageView>(){
				{
					super.bind(actor.avatarProperty());
				}

				@Override
				protected ImageView computeValue() {
					ImageView view = new ImageView();
					try {
						File file = actor.avatarProperty().get();
						Image image;
						if (file != null) {
							image = new Image(
								new FileInputStream(file),
								48,
								48,
								false,
								true
							);
							view.setImage(image);
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					return view;
				}
				
			});
			addContextMenu(actor);
		}
	}
	
	private MenuItem getDuplicateItem(Actor actor) {
		MenuItem item = new MenuItem("Duplicate");
		item.setOnAction(event -> {
			Actor duplicateActor = actor.duplicate();
			duplicateActor.setRandomPosition(battle.get());
			battle.get().getActors().add(duplicateActor);
		});
		return item;
	}
	
	private void addContextMenu(Actor actor) {
		ContextMenu contextMenu = new ContextMenu();
		
		MenuItem editItem = new MenuItem("Edit");
		editItem.setOnAction(event -> {
			ActorEditor editor = new ActorEditor(actor, battle.get());
			editor.show(this);
		});
		
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction(event -> {
			battle.get().actorsProperty().remove(actor);
		});
		
		contextMenu.getItems().addAll(
			editItem,
			getDuplicateItem(actor),
			deleteItem
		);
		setContextMenu(contextMenu);
	}
}
