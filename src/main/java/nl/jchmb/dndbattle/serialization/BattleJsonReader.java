package nl.jchmb.dndbattle.serialization;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.paint.Color;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.core.Vector2;

public class BattleJsonReader {
	public void read(Battle battle, File file) {
		battle.reset();
		JSONParser parser = new JSONParser();
		try {
			JSONObject data = (JSONObject) parser.parse(new FileReader(file));
			JSONArray actorArray = (JSONArray) data.get("actors");
			JSONArray statusArray = (JSONArray) data.get("statuses");	
			for (Object o : statusArray) {
				readStatus(battle, (JSONObject) o);
			}
			for (Object o : actorArray) {
				readActor(battle, (JSONObject) o);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	private int toInt(JSONObject o, String key) {
		return ((Long) o.get(key)).intValue();
	}
	
	private Color readColor(JSONObject o) {
		return new Color(
			((Double) o.get("red")),
			((Double) o.get("green")),
			((Double) o.get("blue")),
			1.0d
		);
	}
	
	private void readStatus(Battle battle, JSONObject o) {
		Status status = new Status();
		
		status.setName((String) o.get("name"));
		status.setSymbol((String) o.get("symbol"));
		status.setBackgroundColor(readColor((JSONObject) o.get("background_color")));
		status.setTextColor(readColor((JSONObject) o.get("text_color")));
		
		battle.getStatuses().add(status);
	}

	private void readStatusesForActor(Battle battle, Actor actor, JSONArray array) {
		for (Object o : array) {
			Status status = battle.findStatusByName((String) o);
			if (status != null) {
				actor.getStatuses().add(status);
			}
		}
	}
	
	@SuppressWarnings("unused")
	private Vector2 readVector2(JSONObject o) {
		return new Vector2(
			toInt(o, "x"),
			toInt(o, "y")
		);
	}
	
	private void readActor(Battle battle, JSONObject o) {
		Actor actor = new Actor();
		
		actor.setName((String) o.get("name"));
		actor.setInitiative(toInt(o, "initiative"));
		actor.setCurrentHp(toInt(o, "current_hp"));
		actor.setMaxHp(toInt(o, "max_hp"));
		actor.setHiddenHp((Boolean) o.get("hide_hp"));
		actor.setAvatar(new File((String) o.get("avatar")));
		actor.setPosition(
			new Vector2(
				toInt(o, "x"),
				toInt(o, "y")
			)
		);
		
		if (o.containsKey("size")) {
			actor.setSize(readVector2((JSONObject) o.get("size")));
		}
		readStatusesForActor(battle, actor, (JSONArray) o.get("statuses"));
		
		battle.getActors().add(actor);
	}
}
