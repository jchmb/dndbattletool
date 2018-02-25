package nl.jchmb.dndbattle.serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.paint.Color;
import nl.jchmb.dndbattle.core.Actor;
import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.core.Vector2;

public class BattleJsonWriter {
	@SuppressWarnings("unchecked")
	public void write(Battle battle, File file) {
		JSONObject root = new JSONObject();
		
		JSONArray actorArray = new JSONArray();
		for (Actor actor : battle.getActors()) {
			serializeActor(actorArray, actor);
		}
		JSONArray statusArray = new JSONArray();
		for (Status status : battle.getStatuses()) {
			serializeStatus(statusArray, status);
		}
		root.put("actors", actorArray); // TODO fix warning
		root.put("statuses", statusArray);
		root.put("options", serializeOptionsObject(battle));

		FileWriter stream;
		try {
			StringWriter writer = new StringWriter();
			root.writeJSONString(writer);
			String jsonString = writer.toString();
			stream = new FileWriter(file);
			stream.write(jsonString);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject serializeOptionsObject(final Battle battle) {
		JSONObject o = new JSONObject();
		
		o.put("grid_size", serializeVector2(battle.getGridSize()));
		o.put("cell_size", battle.getCellSize());
		o.put("background_color", serializeColor(battle.getBackgroundColor()));
		o.put("background_image", battle.getBackgroundImageFile().getAbsolutePath());
		o.put("border_color", serializeColor(battle.getBorderColor()));
		o.put("legend_entry_even_color", serializeColor(battle.getLegendEntryEvenColor()));
		o.put("legend_entry_odd_color", serializeColor(battle.getLegendEntryOddColor()));
		
		return o;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject serializeVector2(Vector2 v) {
		JSONObject o = new JSONObject();
		o.put("x", v.getX());
		o.put("y", v.getY());
		return o;
	}
	
	@SuppressWarnings("unchecked")
	private void serializeActor(JSONArray actorArray, Actor actor) {
		JSONObject o = new JSONObject();
		
		o.put("name", actor.getName());
		o.put("initiative", actor.getInitiative());
		o.put("current_hp", actor.getCurrentHp());
		o.put("max_hp", actor.getMaxHp());
		o.put("avatar", actor.getAvatar().getAbsolutePath());
		o.put("x", actor.getPosition().getX());
		o.put("y", actor.getPosition().getY());
		o.put("size", serializeVector2(actor.getSize()));
		o.put("hide_hp", actor.isHiddenHp());
		o.put("statuses", statusesToJsonArray(actor));
		
		actorArray.add(o);
	}
	
	@SuppressWarnings("unchecked")
	private void serializeStatus(JSONArray statusArray, Status status) {
		JSONObject o = new JSONObject();
		
		o.put("name", status.getName());
		o.put("symbol", status.getSymbol());
		o.put("background_color", serializeColor(status.getBackgroundColor()));
		o.put("text_color", serializeColor(status.getTextColor()));
		
		statusArray.add(o);
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject serializeColor(Color color) {
		JSONObject o = new JSONObject();
		
		o.put("red", color.getRed());
		o.put("green", color.getGreen());
		o.put("blue", color.getBlue());
		
		return o;
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray statusesToJsonArray(Actor actor) {
		JSONArray array = new JSONArray();
		
		for (Status status : actor.getStatuses()) {
			array.add(status.getName());
		}
		
		return array;
	}
}
