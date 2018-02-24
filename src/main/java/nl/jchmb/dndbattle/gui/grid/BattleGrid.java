package nl.jchmb.dndbattle.gui.grid;

import java.awt.event.MouseEvent;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.gui.actors.ActorEditor;
import nl.jchmb.dndbattle.utils.BindingUtils;
import nl.jchmb.dndbattle.utils.Images;

public class BattleGrid extends Pane {
	private final ObjectProperty<Battle> battle;
	private final ObjectProperty<Vector2> gridSize = new SimpleObjectProperty<Vector2>();
	private final Canvas backgroundLayer = new Canvas();
	private final Group actorLayer = new Group();
	private Node selectedAvatar = null;
	private Vector2 originalMousePosition = new Vector2(0, 0);
	
	
	public BattleGrid(final ObjectProperty<Battle> battle) {
		this.battle = battle;
		
		final ObjectProperty<Vector2> gridSize = battle.get().gridSizeProperty();
		final ObjectProperty<Integer> cellSize = battle.get().cellSizeProperty().asObject();
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
			actorLayer
		);
		
		battle.get().actorsProperty().addListener(this::onActorsListChanged);
		
		battle.get().backgroundColorProperty().addListener(this::onGridColorChanged);
		battle.get().borderColorProperty().addListener(this::onGridColorChanged);
		
		this.gridSize.addListener(this::onGridSizeChanged);
	}
	
	private void onGridColorChanged(ObservableValue<? extends Color> property, Color oldValue, Color newValue) {
		rebuildBackground();
	}
	
	private void onGridSizeChanged(ObservableValue<? extends Vector2> property, Vector2 oldValue, Vector2 newValue) {
		rebuildBackground();
		rebuildActors();
	}
	
	private void rebuildBackground() {
		final Vector2 gridSize = this.gridSize.get();
		final int cellSize = battle.get().getCellSize();
		backgroundLayer.setWidth(gridSize.getX());
		backgroundLayer.setHeight(gridSize.getY());
		GraphicsContext g = backgroundLayer.getGraphicsContext2D();
		g.clearRect(0, 0, gridSize.getX(), gridSize.getY());
		g.setFill(battle.get().getBackgroundColor());
		g.fillRect(0, 0, gridSize.getX(), gridSize.getY());
		g.setStroke(battle.get().getBorderColor());
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
	
	private void rebuildActors() {
		actorLayer.getChildren().clear();
		battle.get().actorsProperty().forEach(actor -> actorLayer.getChildren().add(buildActor(actor)));
		// TODO: clean old
	}
	
	private Node buildActor(Actor actor) {
		ImageView imageView = new ImageView();
		BorderPane view = new BorderPane(imageView);
		IntegerProperty cellSizeProperty = battle.get().cellSizeProperty();
		imageView.imageProperty().bind(
//			BindingUtils.binding(
//				actor.avatarProperty(),
//				cellSizeProperty.asObject(),
//				// TODO: correcting size for border so it fits on the grid... remove hardcoded part eventually
//				// Moved to somewhere else
//				(file, cellSize) -> Images.load(file, cellSize - 2, cellSize - 2).get()
//			)
			Bindings.createObjectBinding(
				() -> {
					int sizeX = cellSizeProperty.get() * actor.getSize().getX() - 2;
					int sizeY = cellSizeProperty.get() * actor.getSize().getY() - 2;
					return Images.load(
						actor.getAvatar(),
						sizeX,
						sizeY
					).get();
				},
				actor.avatarProperty(),
				actor.sizeProperty(),
				cellSizeProperty.asObject()
			)
		);
		view.getStyleClass().add("image-view-wrapper");
		view.setUserData(actor);
		view.translateXProperty().bind(
			BindingUtils.binding(
				actor.positionProperty(),
				battle.get().cellSizeProperty().asObject(),
				(position, cellSize) -> position.getX() * cellSize
			)
		);
		view.translateYProperty().bind(
			BindingUtils.binding(
				actor.positionProperty(),
				battle.get().cellSizeProperty().asObject(),
				(position, cellSize) -> position.getY() * cellSize
			)
		);
		view.setOnMousePressed(event -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				selectedAvatar = view;
				originalMousePosition = new Vector2((int) event.getScreenX(), (int) event.getScreenY());
			} else if (event.getButton().equals(MouseButton.SECONDARY)) {
				ActorEditor actorEditor = new ActorEditor(actor, battle.get());
				actorEditor.show(view);
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
				if (!actor.getPosition().equals(newPosition)) {
					actor.setPosition(newPosition);
				}
			}
		});
		view.setOnMouseReleased(event -> {
			selectedAvatar = null;
		});
		view.opacityProperty().bind(
			BindingUtils.binding(
				actor.currentHpProperty().asObject(),
				currentHp -> currentHp < 0 ? 0.3d : 1.0d
			)
		);
		return view;
	}
}
