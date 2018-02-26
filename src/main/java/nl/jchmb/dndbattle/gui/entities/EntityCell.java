package nl.jchmb.dndbattle.gui.entities;

import java.io.File;
import java.util.function.Function;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Entity;
import nl.jchmb.dndbattle.gui.actors.ActorEditor;
import nl.jchmb.dndbattle.utils.CRUDCell;
import nl.jchmb.dndbattle.utils.Images;
import nl.jchmb.dndbattle.utils.Popups;
import nl.jchmb.dndbattle.utils.form.Form;

public class EntityCell extends CRUDCell<Entity> {
	private final Battle battle;
	
	public EntityCell(final Battle battle, ListProperty<Entity> list, Function<Entity, StringProperty> namePropertyFn) {
		super(list, namePropertyFn);
		this.battle = battle;
	}

	@Override
	protected ObjectBinding<ImageView> getGraphicBinding(Entity entity) {
		return new ObjectBinding<>() {
			{
				super.bind(
					entity.avatarProperty()
				);
			}

			@Override
			protected ImageView computeValue() {
				ImageView view = new ImageView();
				Image image = Images.load(
					entity.getAvatar(),
					50,
					50
				).get();
				view.setImage(image);
				view.setUserData(entity);
				return view;
			}
			
			
		};
	}

	@Override
	protected Form getEditor(Entity entity) {
		return new EntityEditor(entity);
	}
	
	public static ContextMenu createContextMenu(final Entity entity, final Battle battle) {
		return new EntityCell(battle, battle.entitiesProperty(), Entity::nameProperty)
			.produceContextMenu(entity);
	}
	
}
