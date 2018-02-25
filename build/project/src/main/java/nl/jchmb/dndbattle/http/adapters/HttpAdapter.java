package nl.jchmb.dndbattle.http.adapters;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;

import nl.jchmb.dndbattle.http.CharacterSheet;

public class HttpAdapter {
	protected final JBrowserDriver driver;
	protected final Map<String, String> mappings;
	
	public HttpAdapter() {
		driver = new JBrowserDriver();
		mappings = new HashMap<>();
	}
	
	private int getInteger(String name) {
		return Integer.parseInt(getString(name));
	}
	
	private int getInteger(String name, int defaultValue) {
		String value = getString(name);
		return value == null ? defaultValue : Integer.parseInt(value);
	}
	
	private String getString(String name) {
		return driver.findElementByName(getKey(name))
			.getAttribute("value");
	}
	
	protected final String getKey(String key) {
		if (mappings.containsKey(key)) {
			return mappings.get(key);
		}
		return key;
	}
	
	public void updateCharacterSheet(CharacterSheet sheet, String url) {
		driver.get(url);
		
		sheet.setName(getString("Name"));
		sheet.setInitiative(getInteger("Init"));
		sheet.setMaxHp(getInteger("HP"));
		sheet.setCurrentHp(getInteger("HPWounds", sheet.getMaxHp()));
		sheet.setAc(getInteger("AC"));
		sheet.setTouch(getInteger("TouchAC"));
		sheet.setFlatfooted(getInteger("FFAC"));
	}
	
	public CharacterSheet getCharacterSheet(String url) {
		CharacterSheet sheet = new CharacterSheet();
		updateCharacterSheet(sheet, url);
		return sheet;
	}
}
