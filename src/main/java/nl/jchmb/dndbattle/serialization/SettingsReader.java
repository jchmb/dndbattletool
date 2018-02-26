package nl.jchmb.dndbattle.serialization;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import nl.jchmb.dndbattle.core.Settings;

public class SettingsReader {
	public void read(Settings settings, File file) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject data = (JSONObject) parser.parse(new FileReader(file));
			
			readFile(data, "battle_directory")
				.ifPresent(settings::setBattleDirectory);
			readFile(data, "image_directory")
				.ifPresent(settings::setImageDirectory);
			readFile(data, "export_directory")
				.ifPresent(settings::setExportDirectory);
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	private Optional<File> readFile(JSONObject data, String key) {
		return data.containsKey(key) ?
			Optional.of(new File((String) data.get(key))) :
			Optional.empty();
	}
}
