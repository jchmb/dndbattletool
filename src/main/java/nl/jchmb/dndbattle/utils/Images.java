package nl.jchmb.dndbattle.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Optional;

import javax.imageio.ImageIO;

//import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class Images {
	public static Optional<Image> load(File file) {
		try {
			return Optional.of(
				new Image(
					new FileInputStream(file)
				)
			);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	public static Optional<Image> load(File file, double w, double h) {
		try {
			return Optional.of(
				new Image(
					new FileInputStream(file),
					w,
					h,
					false,
					true
				)
			);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	public static void save(File file, BorderPane root) {
		try {
			ImageIO.write(snapshot(root), "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static BufferedImage snapshot(BorderPane root) {
		Region grid = (Region) root.getCenter();
		Region legend = (Region) root.getRight();
		int w, h;
		w = (int) (grid.getWidth() + legend.getWidth());
		h = (int) grid.getHeight();
		SnapshotParameters params = new SnapshotParameters();
		params.setViewport(
			new Rectangle2D(
				grid.getLocalToSceneTransform().getTx(),
				grid.getLocalToSceneTransform().getTy(),
				grid.getWidth() + legend.getWidth(),
				grid.getHeight()
			)
		);
		WritableImage image = root.snapshot(params, null);
		return null;
		//return SwingFXUtils.fromFXImage(image, null);
	}
	
	public static String upload(BorderPane root, String clientID) {
		try {
				URL url;
			    url = new URL("https://api.imgur.com/3/image");
			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			    
			    //read image
			    BufferedImage image = snapshot(root);
			    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			    ImageIO.write(image, "png", byteArray);
			    byte[] byteImage = byteArray.toByteArray();
			    String dataImage = Base64.getEncoder().encodeToString(byteImage);
			    String data = URLEncoder.encode("image", "UTF-8") + "="
			    + URLEncoder.encode(dataImage, "UTF-8");

			    conn.setDoOutput(true);
			    conn.setDoInput(true);
			    conn.setRequestMethod("POST");
			    conn.setRequestProperty("Authorization", "Client-ID " + clientID);
			    conn.setRequestMethod("POST");
			    conn.setRequestProperty("Content-Type",
			            "application/x-www-form-urlencoded");

			    conn.connect();
			    StringBuilder stb = new StringBuilder();
			    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			    wr.write(data);
			    wr.flush();

			    // Get the response
			    BufferedReader rd = new BufferedReader(
			            new InputStreamReader(conn.getInputStream()));
			    String line;
			    while ((line = rd.readLine()) != null) {
			        stb.append(line).append("\n");
			    }
			    wr.close();
			    rd.close();

			    return stb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
