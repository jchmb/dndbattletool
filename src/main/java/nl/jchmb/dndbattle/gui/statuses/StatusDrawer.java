package nl.jchmb.dndbattle.gui.statuses;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import nl.jchmb.dndbattle.core.Status;

public class StatusDrawer {
	public void draw(GraphicsContext g, int size, Status status) {
		final Font font = Font.font(
			"Verdana",
			FontWeight.BOLD,
			status.getTextSize()
		);
		g.clearRect(0, 0, size, size);
		g.setFill(status.getBackgroundColor());
		g.fillOval(0, 0, size, size);
		g.setStroke(status.getBorderColor());
		g.strokeOval(0, 0, size, size);
		g.setFont(font);
		g.setFill(status.getTextColor());
//		g.setTextAlign(TextAlignment.CENTER);
		g.fillText(
			status.getSymbol(),
			size / 4 + 2 + status.getTextOffset().getX(),
			(size / 4) * 3 - 2 + status.getTextOffset().getY()
		);
	}
	
	public Image getImage(Status status, int size) {
		Canvas canvas = new Canvas(size, size);
		GraphicsContext g = canvas.getGraphicsContext2D();
		draw(g, size, status);
		WritableImage image = canvas.snapshot(new SnapshotParameters(), null);
		return image;
	}
}
