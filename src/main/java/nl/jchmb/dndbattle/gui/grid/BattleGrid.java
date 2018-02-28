package nl.jchmb.dndbattle.gui.grid;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Entity;
import nl.jchmb.dndbattle.core.Positionable;
import nl.jchmb.dndbattle.core.Sizable;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.core.overlays.Overlay;
import nl.jchmb.dndbattle.core.overlays.structures.OverlayStructure;
import nl.jchmb.dndbattle.gui.actors.ActorCell;
import nl.jchmb.dndbattle.gui.actors.ActorEditor;
import nl.jchmb.dndbattle.gui.entities.EntityCell;
import nl.jchmb.dndbattle.gui.overlays.OverlayCell;
import nl.jchmb.dndbattle.utils.BindingUtils;
import nl.jchmb.dndbattle.utils.CRUDCell;
import nl.jchmb.dndbattle.utils.Images;
import nl.jchmb.dndbattle.utils.Popups;

public class BattleGrid extends Pane {
	private final Battle battle;
	private final ObjectProperty<Vector2> gridSize = new SimpleObjectProperty<Vector2>();
	private final Canvas backgroundLayer = new Canvas();
	private final Group actorLayer = new Group();
	private final Group entityLayer = new Group();
	private final Group overlayLayer = new Group();
	private Node selectedAvatar = null;
	private Vector2 originalMousePosition = new Vector2(0, 0);
	private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
	
	public BattleGrid(final Battle battle) {
		this.battle = battle;
		
		final ObjectProperty<Vector2> gridSize = battle.gridSizeProperty();
		final ObjectProperty<Integer> cellSize = battle.cellSizeProperty().asObject();
		this.gridSize.bind(new ObjectBinding<Vector2>(){
			{
				super.bind(gridSize, cellSize);
			}

			@Override
			protected Vector2 computeValue() {
				return gridSize.get().scale(cellSize.get());
			}
		});
		
		this.prefWidthProperty().bind(
			BindingUtils.binding(
				this.gridSize,
				gs -> gs.getX() + 250
			)
		);
		
		rebuildBackground();
		getChildren().addAll(
			backgroundLayer,
			entityLayer,
			actorLayer,
			overlayLayer
		);
		
		battle.actorsProperty().addListener(this::onActorsListChanged);
		battle.entitiesProperty().addListener(this::onEntityListChanged);
		battle.overlaysProperty().addListener(this::onOverlayListChanged);
		
		battle.backgroundColorProperty().addListener(this::onGridColorChanged);
		battle.borderColorProperty().addListener(this::onGridColorChanged);
		
		battle.backgroundImageFileProperty().addListener(
			(prop, oldValue, newValue) -> rebuildBackground()
		);
		
		this.gridSize.addListener(this::onGridSizeChanged);
	}
	
	private void onGridColorChanged(ObservableValue<? extends Color> property, Color oldValue, Color newValue) {
		rebuildBackground();
	}
	
	private void onGridSizeChanged(ObservableValue<? extends Vector2> property, Vector2 oldValue, Vector2 newValue) {
		rebuildBackground();
		rebuildEntities();
		rebuildActors();
		rebuildOverlays();
	}
	
	private void rebuildBackground() {
		final Vector2 gridSize = this.gridSize.get();
		final int cellSize = battle.getCellSize();
		backgroundLayer.setWidth(gridSize.getX());
		backgroundLayer.setHeight(gridSize.getY());
		GraphicsContext g = backgroundLayer.getGraphicsContext2D();
		g.clearRect(0, 0, gridSize.getX(), gridSize.getY());
		g.setFill(battle.getBackgroundColor());
		
		g.fillRect(0, 0, gridSize.getX(), gridSize.getY());
		
		if (battle.getBackgroundImageFile() == null) {
			image.set(null);
		} else {
			image.set(Images.load(battle.getBackgroundImageFile()).get());
			g.drawImage(image.get(), 0, 0);
		}
		
		g.setStroke(battle.getBorderColor());
		g.setFont(Font.font("Verdana", FontWeight.NORMAL, 8.0f));
		int i = 0;
		for (int x = 0; x < gridSize.getX() + cellSize; x += cellSize) {
			g.strokeLine(x, 0, x, gridSize.getY());
			if (x < gridSize.getX()) {
				g.strokeText(Integer.toString(++i), i * cellSize - cellSize / 2, 10);
			}
		}
		int j = 0;
		for (int y = 0; y < gridSize.getY() + cellSize; y += cellSize) {
			g.strokeLine(0, y, gridSize.getX(), y);
			if (y < gridSize.getY()) {
				g.strokeText(Integer.toString(++j), gridSize.getX() - 12, j * cellSize - cellSize / 2);
			}
		}
	}
	
	private void onActorsListChanged(Change<? extends Actor> change) {
		rebuildActors();
	}
	
	private void onEntityListChanged(final Change<? extends Entity> change) {
		rebuildEntities();
	}
	
	private void onOverlayListChanged(final Change<? extends Overlay> change) {
		rebuildOverlays();
	}
	
	private void rebuildOverlays() {
		overlayLayer.getChildren().clear();
		battle.overlaysProperty().forEach(overlay -> overlayLayer.getChildren().add(buildOverlay(overlay)));
	}
	
	private void rebuildActors() {
		actorLayer.getChildren().clear();
		battle.actorsProperty().forEach(actor -> actorLayer.getChildren().add(buildActor(actor)));
		// TODO: clean old
	}
	
	private void rebuildEntities() {
		entityLayer.getChildren().clear();
		battle.entitiesProperty().forEach(entity -> entityLayer.getChildren().add(buildEntity(entity)));
	}
	
	private Node buildEntity(final Entity entity) {
		final BorderPane view = buildPositionable(
			entity,
			(p, b) -> EntityCell.createContextMenu((Entity) p, b),
			entity.avatarProperty()
		);
		return view;
	}
	
	private Node buildActor(final Actor actor) {
		final BorderPane view = buildPositionable(
			actor,
			(p, b) -> ActorCell.createContextMenu((Actor) p, b),
			actor.avatarProperty()
		);
		view.opacityProperty().bind(
			BindingUtils.binding(
				actor.currentHpProperty().asObject(),
				currentHp -> currentHp < 0 ? 0.3d : 1.0d
			)
		);
		return view;
	}
	
	private Node buildOverlay(final Overlay overlay) {
		final Property<?>[] extraProperties = overlay.getStructure().getProperties();
		final Property<?>[] properties = new Property<?>[3 + extraProperties.length];
		properties[0] = overlay.colorProperty();
		properties[1] = overlay.opacityProperty();
		properties[2] = overlay.structureProperty();
		for (int i = 3; i < properties.length; i++) {
			properties[i] = extraProperties[i - 3];
		}
		final BorderPane view = buildPositionable(
			overlay,
			(p, b) -> OverlayCell.createContextMenu((Overlay) p, b),
			properties
		);
		final WeakChangeListener<OverlayStructure> listener = new WeakChangeListener<>(
			(prop, oldValue, newValue) -> {
				view.centerProperty().unbind();
				view.centerProperty().bind(
					new ObjectBinding<>() {
						{
							super.bind(
								battle.cellSizeProperty(),
								overlay.colorProperty(),
								overlay.opacityProperty(),
								overlay.structureProperty()
							);
							super.bind(newValue.getProperties());
						}

						@Override
						protected Node computeValue() {
							return overlay.getImageRepresentation(battle);
						}
						
					}
				);
			}
		);
		overlay.structureProperty().addListener(listener);
		view.setUserData(listener);
		return view;
	}
	
	private BorderPane buildPositionable(
			final Positionable positionable,
			final BiFunction<Positionable, Battle, ContextMenu> contextMenuFactory,
			final Property<?>... imageProperties
	) {
		BorderPane view = new BorderPane();
		IntegerProperty cellSizeProperty = battle.cellSizeProperty();
		if (positionable instanceof Sizable) {
			view.centerProperty().bind(
				new ObjectBinding<>() {
					{
						super.bind(
							battle.cellSizeProperty(),
							((Sizable) positionable).sizeProperty()
						);
						super.bind(
							imageProperties	
						);
					}
					@Override
					protected Node computeValue() {
						return positionable.getImageRepresentation(battle);
					}
					
				}
			);
		} else {
			view.centerProperty().bind(
					new ObjectBinding<>() {
						{
							super.bind(
								battle.cellSizeProperty()
							);
							super.bind(
								imageProperties
							);
						}
						@Override
						protected Node computeValue() {
							return positionable.getImageRepresentation(battle);
						}
						
					}
				);
		}
		view.setUserData(positionable);
		view.translateXProperty().bind(
			BindingUtils.binding(
				positionable.positionProperty(),
				battle.cellSizeProperty().asObject(),
				(position, cellSize) -> position.getX() * cellSize
			)
		);
		view.translateYProperty().bind(
			BindingUtils.binding(
				positionable.positionProperty(),
				battle.cellSizeProperty().asObject(),
				(position, cellSize) -> position.getY() * cellSize
			)
		);
		view.setOnMousePressed(event -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				selectedAvatar = view;
				originalMousePosition = new Vector2((int) event.getScreenX(), (int) event.getScreenY());
			} else if (event.getButton().equals(MouseButton.SECONDARY)) {
				final ContextMenu contextMenu = contextMenuFactory.apply(positionable, battle);
				contextMenu.show(view, Side.RIGHT, 0, 0);
			}
		});
		view.setOnMouseDragged(event -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				int rootX = (int) this.getLocalToSceneTransform().getTx();
				int rootY = (int) this.getLocalToSceneTransform().getTy();
				Vector2 newPosition = new Vector2(
					(int) ((event.getSceneX() - rootX) / cellSizeProperty.get()),
					(int) ((event.getSceneY() - rootY) / cellSizeProperty.get())
				);
				if (!positionable.getPosition().equals(newPosition)) {
					positionable.setPosition(newPosition);
				}
			}
		});
		view.setOnMouseReleased(event -> {
			selectedAvatar = null;
		});
		return view;
	}
}
