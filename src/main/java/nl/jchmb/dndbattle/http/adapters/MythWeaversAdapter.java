package nl.jchmb.dndbattle.http.adapters;

import nl.jchmb.dndbattle.http.CharacterSheet;

public class MythWeaversAdapter extends HttpAdapter {
	public MythWeaversAdapter() {
		super();
		
		mappings.put("TouchAC", "ACTouch");
		mappings.put("FFAC", "ACFlat");
	}
	
	public static void main(String[] args) {
		HttpAdapter adapter = new MythWeaversAdapter();
		CharacterSheet sheet = adapter.getCharacterSheet(
			"https://www.myth-weavers.com/sheet.html#id=1434493"
		);
		
		System.out.println(String.format(
			"AC: %s; Flat: %s; Touch: %s",
			sheet.getAc(),
			sheet.getFlatfooted(),
			sheet.getTouch()
		));
	}
}
