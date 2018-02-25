package nl.jchmb.dndbattle.gui.statuses;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import nl.jchmb.dndbattle.core.Status;

public class StatusDrawer {
	public void draw(GraphicsContext g, int size, Status status) {
		g.clearRect(0, 0, size, size);
		g.setFill(status.getBackgroundColor());
		g.fillOval(0, 0, size, size);
		g.setStroke(Color.BLACK);
		g.strokeOval(0, 0, size, size);
		g.setFill(status.getTextColor());
		g.fillText(status.getSymbol(), size / 4, (size / 4) * 3);
	}
	
	public Image getImage(Status status, int size) {
		Canvas canvas = new Canvas(size, size);
		GraphicsContext g = canvas.getGraphicsContext2D();
		draw(g, size, status);
		WritableImage image = canvas.snapshot(new SnapshotParameters(), null);
		return image;
	}
}
