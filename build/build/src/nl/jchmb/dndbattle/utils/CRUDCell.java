package nl.jchmb.dndbattle.utils;

/**
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Function;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.jchmb.dndbattle.utils.form.PopOverForm;

/**
 * @author jochem
 *
 */
public abstract class CRUDCell<T> extends ListCell<T> {
	private final ListProperty<T> list;
	private final Function<T, StringProperty> namePropertyFn;
	
	public CRUDCell(
			final ListProperty<T> list,
			final Function<T, StringProperty> namePropertyFn
	) {
		this.list = list;
		this.namePropertyFn = namePropertyFn;
	}
	
	protected int getGraphicSize() {
		return 48;
	}
	
	protected abstract ObjectBinding<ImageView> getGraphicBinding(T item);
	
	/* (non-Javadoc)
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		
		textProperty().unbind();
		graphicProperty().unbind();
		this.setContextMenu(null);
		if (empty) {
			textProperty().set("");
			graphicProperty().set(null);
			setContextMenu(null);
		} else {
			textProperty().bind(namePropertyFn.apply(item));
			graphicProperty().bind(getGraphicBinding(item));
			addContextMenu(item);
		}
	}
	
	protected abstract PopOverForm getEditor(T item);
	
	protected MenuItem getEditItem(T item) {
		MenuItem editItem = new MenuItem("Edit");
		editItem.setOnAction(event -> {
			PopOverForm editor = getEditor(item);
			editor.show(this);
		});
		return editItem;
	}
	
	protected MenuItem getDeleteItem(T item) {
		MenuItem deleteItem = new MenuItem("Delete");
		deleteItem.setOnAction(event -> {
			list.remove(item);
		});
		return deleteItem;
	}
	
	protected void addContextMenu(T item) {
		ContextMenu contextMenu = new ContextMenu();
		
		buildContextMenu(contextMenu, item);
		
		setContextMenu(contextMenu);
	}
	
	protected void buildContextMenu(ContextMenu contextMenu, T item) {
		contextMenu.getItems().addAll(
			getEditItem(item),
			getDeleteItem(item)
		);
	}
}

