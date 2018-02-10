package nl.jchmb.dndbattle.gui.menu;

import org.controlsfx.control.PopOver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nl.jchmb.dndbattle.utils.Images;

public class UploadMenu extends Menu {
	public UploadMenu(BorderPane root) {
		super("Upload");
		
		getItems().addAll(
			getImgurItem(root)
		);
	}
	
	private MenuItem getImgurItem(BorderPane root) {
		MenuItem item = new MenuItem("Upload to imgur");
		
		item.setOnAction(event -> {
			String jsonString = Images.upload(root, "541e4bd70eadea1");
			JSONParser parser = new JSONParser();
			try {
				JSONObject data = (JSONObject) parser.parse(jsonString);
				String link = (String) ((JSONObject) data.get("data")).get("link");
				PopOver popOver = new PopOver();
				popOver.setDetached(true);
				
				
				HBox container = new HBox();
				
				TextField field = new TextField();
				field.setEditable(false);
				field.setText(link);
				field.setPrefWidth(250.0f);
				
				Button button = new Button("Copy to clipboard");
				button.setOnAction(event2 -> {
					Clipboard clipboard = Clipboard.getSystemClipboard();
					ClipboardContent content = new ClipboardContent();
					content.putString(link);
					clipboard.setContent(content);
				});
				
				container.getChildren().addAll(
					field,
					button
				);
				
				popOver.setContentNode(container);
				popOver.setPrefWidth(400.0f);
				popOver.setWidth(400.0f);
				popOver.setTitle("URL");
				popOver.show(root.getLeft());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		item.setAccelerator(
			new KeyCodeCombination(
				KeyCode.U,
				KeyCombination.CONTROL_DOWN,
				KeyCombination.SHIFT_DOWN
			)
		);
		
		return item;
	}
}
