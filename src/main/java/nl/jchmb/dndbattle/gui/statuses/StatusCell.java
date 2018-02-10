package nl.jchmb.dndbattle.gui.statuses;

import java.io.File;
import java.util.function.Function;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.utils.CRUDCell;
import nl.jchmb.dndbattle.utils.PopOverForm;

public class StatusCell extends CRUDCell<Status> {
	private final boolean mainList;

	public StatusCell(
			ListProperty<Status> list,
			Function<Status, StringProperty> namePropertyFn,
			boolean mainList
	) {
		super(list, namePropertyFn);
		this.mainList = mainList;
	}

	@Override
	protected PopOverForm getEditor(Status item) {
		return new StatusEditor(item);
	}
	
	@Override
	protected int getGraphicSize() {
		return 24;
	}

	@Override
	protected ObjectBinding<ImageView> getGraphicBinding(Status item) {
		return new ObjectBinding<ImageView>() {
			{
				super.bind(
					item.symbolProperty(),
					item.backgroundColorProperty(),
					item.textColorProperty()
				);
			}
			
			@Override
			protected ImageView computeValue() {
				ImageView view = new ImageView();
				view.setImage(new StatusDrawer().getImage(item, getGraphicSize()));
				return view;
			}
			
		};
	}
	
	@Override
	protected void buildContextMenu(ContextMenu contextMenu, Status item) {
		if (mainList) {
			super.buildContextMenu(contextMenu, item);
		} else {
			contextMenu.getItems().addAll(
				getDeleteItem(item)
			);
		}
	}
	
}
