package nl.jchmb.dndbattle.gui.legend;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.gui.statuses.StatusDrawer;
import nl.jchmb.dndbattle.utils.BindingUtils;
import nl.jchmb.dndbattle.utils.Images;

public class LegendEntry extends Pane {
	private final int HEALTH_BAR_WIDTH = 100, HEALTH_BAR_HEIGHT = 6;
	public final int ENTRY_WIDTH = 250; 
	private final Battle battle;
	private final Actor actor;
	private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
	private final ImageView imageView;
	private final Canvas healthView;
	private final Label nameView;
	private final Label hpView;
	private final Label positionView;
	private final HBox statusesView;
	private final WeakListChangeListener<Status> statusesChangeListener =
		new WeakListChangeListener<Status>(this::onStatusesChanged);
	private final int index;
	
	public LegendEntry(final Battle battle, final Actor actor, final int index) {
		super();
		
		this.battle = battle;
		this.actor = actor;
		this.index = index;
		this.minHeightProperty().bind(battle.cellSizeProperty());
		this.prefWidthProperty().set(ENTRY_WIDTH);
		final IntegerProperty cellSizeProperty = battle.cellSizeProperty();
		final ObjectProperty<File> urlProperty = actor.avatarProperty();
		this.image.bind(new ObjectBinding<Image>(){
			{
				super.bind(
					cellSizeProperty,
					urlProperty
				);
			}
			
			@Override
			protected Image computeValue() {
				int size = cellSizeProperty.get();
				return Images.load(urlProperty.get(), size, size).get();
			}
			
		});
		
		this.opacityProperty().bind(
			BindingUtils.binding(
				actor.currentHpProperty().asObject(),
				currentHp -> currentHp < 0 ? 0.3d : 1.0d
			)
		);
		
		/* Image */
		imageView = new ImageView();
		imageView.imageProperty().bind(image);
		
		/* Name */
		nameView = new Label();
		nameView.textProperty().bind(actor.nameProperty());
		nameView.translateXProperty().bind(Bindings.add(3.0f, battle.cellSizeProperty()));
		nameView.setTranslateY(5.0f);
		Font nameFont = Font.font("Verdana", FontWeight.BOLD, 15);
		nameView.setFont(nameFont);
		
		/* Healthbar */
		healthView = new Canvas(HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
		drawHealthBar();
		healthView.translateXProperty().bind(Bindings.add(3.0f, battle.cellSizeProperty()));
		healthView.setTranslateY(22.0f);
		actor.currentHpProperty().addListener(this::onHpChanged);
		actor.maxHpProperty().addListener(this::onHpChanged);
		
		/* Hp */
		hpView = new Label();
		hpView.setTextFill(Color.BLACK);
		hpView.textProperty().bind(new StringBinding() {
			{
				super.bind(actor.hiddenHpProperty(), actor.currentHpProperty(), actor.maxHpProperty());
			}
			
			@Override
			protected String computeValue() {
				if (!actor.hiddenHpProperty().get()) {
					return String.format(
						"%s / %s",
						actor.currentHpProperty().get(),
						actor.maxHpProperty().get()
					);
				} else {
					return "";
				}
			}
		});
		hpView.translateXProperty().bind(Bindings.add(3.0f, battle.cellSizeProperty()));
		hpView.setTranslateY(30.0f);
		
		/* Position */
		positionView = new Label();
		positionView.textProperty().bind(
			BindingUtils.binding(
				actor.positionProperty(),
				position -> position.add(new Vector2(1, 1)).toString()
			)
		);
		positionView.translateXProperty().bind(Bindings.add(10.0f + HEALTH_BAR_WIDTH, battle.cellSizeProperty()));
		positionView.setTranslateY(30.0f);
		positionView.setFont(new Font("Verdana", 12.0d));
		
		/* Statuses view */
		statusesView = new HBox();
//		actor.statusesProperty().addListener(statusesChangeListener);
		statusesView.translateXProperty().bind(
			Bindings.add(10.0f + HEALTH_BAR_WIDTH, battle.cellSizeProperty())
		);
		ListProperty<Node> statusesViewList = new SimpleListProperty<>();
		statusesViewList.bind(
			Bindings.createObjectBinding(
				() -> convertStatuses(actor.getStatuses()),
				actor.statusesProperty()
			)
		);
		
		Bindings.bindContent(
			statusesView.getChildren(),
			statusesViewList
		);
		statusesView.setTranslateY(2.0f);
		buildStatuses();
		
		getChildren().addAll(
			imageView,
			nameView,
			healthView,
			hpView,
			positionView,
			statusesView
		);
		
		this.setBackground(
			new Background(
				new BackgroundFill(
					index % 2 == 0 ? battle.getLegendEntryEvenColor() : battle.getLegendEntryOddColor(),
					null,
					null
				)
			)
		);
	}
	
	private ObservableList<Node> convertStatuses(List<Status> statuses) {
		return FXCollections.observableArrayList(
			statuses.stream()
				.map(this::convertStatusToNode)
				.collect(Collectors.toList()
			)
		);
	}
	
	private void onStatusesChanged(Change<? extends Status> change) {
		buildStatuses();
	}
	
	private void onHpChanged(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
		drawHealthBar();
	}
	
	public Actor getActor() {
		return actor;
	}
	
	private Node convertStatusToNode(Status status) {
		ImageView node = new ImageView();
		Image image = new StatusDrawer().getImage(status, 16); // TODO: 16 shouldn't be hardcoded
		node.setImage(image);
		return node;
	}
	
	private void buildStatuses() {
		statusesView.getChildren().clear();
		for (Status status : actor.getStatuses()) {
			statusesView.getChildren().add(convertStatusToNode(status));
		}
	}
	
	private void drawHealthBar() {
		GraphicsContext g = healthView.getGraphicsContext2D();
		g.setStroke(Color.BLACK);
		double fraction = actor.getHpFraction();
		int barX = (int) (fraction * HEALTH_BAR_WIDTH);
		if (barX > HEALTH_BAR_WIDTH) {
			barX = HEALTH_BAR_WIDTH;
		} else if (barX < 0) {
			barX = 0;
		}
		g.setFill(Color.WHITE);
		g.fillRect(0, 0, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
		
		g.setFill(getColorForFraction(fraction));
		g.fillRect(0, 0, barX, HEALTH_BAR_HEIGHT);
		g.strokeRect(0, 0, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
	}
	
	private Color getColorForFraction(double fraction) {
		if (fraction >= 0.7f) {
			return Color.GREEN;
		} else if (fraction >= 0.5f) {
			return Color.GREENYELLOW;
		} else if (fraction >= 0.35f) {
			return Color.YELLOW;
		} else if (fraction >= 0.2f) {
			return Color.ORANGE;
		} else {
			return Color.RED;
		}
	}
}
