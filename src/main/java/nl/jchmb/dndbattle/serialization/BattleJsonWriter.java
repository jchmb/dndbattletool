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
import nl.jchmb.dndbattle.core.Entity;
import nl.jchmb.dndbattle.core.Gender;
import nl.jchmb.dndbattle.core.Status;
import nl.jchmb.dndbattle.core.Vector2;
import nl.jchmb.dndbattle.core.overlays.Overlay;

public class BattleJsonWriter {
	@SuppressWarnings("unchecked")
	public void write(Battle battle, File file) {
		JSONObject root = new JSONObject();
		
		JSONArray actorArray = new JSONArray();
		for (Actor actor : battle.getActors()) {
			serializeActor(actorArray, actor);
		}
		JSONArray entityArray = new JSONArray();
		for (Entity entity : battle.getEntities()) {
			serializeEntity(entityArray, entity);
		}
		JSONArray overlayArray = new JSONArray();
		for (Overlay overlay : battle.getOverlays()) {
			serializeOverlay(overlayArray, overlay);
		}
		JSONArray statusArray = new JSONArray();
		for (Status status : battle.getStatuses()) {
			serializeStatus(statusArray, status);
		}
		JSONArray genderArray = new JSONArray();
		for (Gender gender : battle.getGenders()) {
			serializeGender(genderArray, gender);
		}
		root.put("actors", actorArray); // TODO fix warning
		root.put("statuses", statusArray);
		root.put("overlays", overlayArray);
		root.put("entities", entityArray);
		root.put("genders", genderArray);
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
		if (battle.getBackgroundImageFile() != null) {
			o.put("background_image", battle.getBackgroundImageFile().getAbsolutePath());
		}
		o.put("border_color", serializeColor(battle.getBorderColor()));
		o.put("legend_entry_even_color", serializeColor(battle.getLegendEntryEvenColor()));
		o.put("legend_entry_odd_color", serializeColor(battle.getLegendEntryOddColor()));
		o.put("legend_entry_font_color", serializeColor(battle.getLegendEntryFontColor()));
		o.put("legend_entry_height", battle.getLegendEntryHeight());
		
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
	private void serializeEntity(JSONArray entityArray, Entity entity) {
		JSONObject o = new JSONObject();
		
		o.put("name", entity.getName());
		o.put("avatar", entity.getAvatar().getAbsolutePath());
		o.put("position", serializeVector2(entity.getPosition()));
		o.put("size", serializeVector2(entity.getSize()));
		
		entityArray.add(o);
	}
	
	@SuppressWarnings("unchecked")
	private void serializeOverlay(JSONArray overlayArray, Overlay overlay) {
		JSONObject o = new JSONObject();
		
		o.put("name", overlay.getName());
		o.put("position", serializeVector2(overlay.getPosition()));
		o.put("size", serializeVector2(overlay.getSize()));
		o.put("color", serializeColor(overlay.getColor()));
		o.put("opacity", overlay.getOpacity());
		
		overlayArray.add(o);
	}
	
	@SuppressWarnings("unchecked")
	private void serializeActor(JSONArray actorArray, Actor actor) {
		JSONObject o = new JSONObject();
		
		o.put("name", actor.getName());
		o.put("gender", actor.getGender().getId());
		o.put("initiative", actor.getInitiative());
		o.put("current_hp", actor.getCurrentHp());
		o.put("max_hp", actor.getMaxHp());
		o.put("avatar", actor.getAvatar().getAbsolutePath());
		o.put("x", actor.getPosition().getX());
		o.put("y", actor.getPosition().getY());
		o.put("size", serializeVector2(actor.getSize()));
		o.put("hide_hp", actor.isHiddenHp());
		o.put("hide_position", actor.isHiddenPosition());
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
	private void serializeGender(JSONArray genderArray, Gender gender) {
		JSONObject o = new JSONObject();
		
		o.put("name", gender.getSymbol());
		o.put("subject_pronoun", gender.getSubjectPronoun());
		o.put("object_pronoun", gender.getObjectPronoun());
		o.put("possessive_pronoun", gender.getPossessivePronoun());
		o.put("id", gender.getId());
		
		genderArray.add(o);
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
