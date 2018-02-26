package nl.jchmb.dndbattle.serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import nl.jchmb.dndbattle.core.Settings;

public class SettingsWriter {
	public void write(Settings settings, File file) {
		JSONObject data = new JSONObject();
	
		writeFile(data, "battle_directory", settings.getBattleDirectory());
		writeFile(data, "image_directory", settings.getImageDirectory());
		writeFile(data, "export_directory", settings.getExportDirectory());
		
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.append(data.toJSONString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void writeFile(JSONObject data, String key, File file) {
		data.put(key, file.getAbsolutePath());
	}
}
