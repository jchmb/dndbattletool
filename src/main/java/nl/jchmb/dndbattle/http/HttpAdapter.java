package nl.jchmb.dndbattle.http;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;

public class HttpAdapter {
	private final JBrowserDriver driver;
	
	public HttpAdapter(String username, String password) {
		driver = new JBrowserDriver();
		login(username, password);
	}
	
	private void login(String username, String password) {
		driver.get("https://www.rpgcrossing.com");
		WebElement form = driver.findElementByTagName("form");
		WebElement usernameField = form.findElement(By.name("vb_login_username"));
		usernameField.sendKeys(username);
		WebElement passwordField = form.findElement(By.name("vb_login_password"));
		passwordField.sendKeys(password);
		form.submit();
	}
	
	private int getInteger(String name) {
		return Integer.parseInt(getString(name));
	}
	
	private String getString(String name) {
		return driver.findElement(By.name(name)).getAttribute("value");
	}
	
	public CharacterSheet getCharacterSheet(String url) {
		driver.get(url);
		CharacterSheet sheet = new CharacterSheet();
		
		sheet.setName(getString("Name"));
		sheet.setInitiative(getInteger("Init"));
		sheet.setMaxHp(getInteger("HP"));
		sheet.setCurrentHp(getInteger("HPWounds"));
		sheet.setAc(getInteger("AC"));
		sheet.setTouch(getInteger("TouchAC"));
		sheet.setFlatfooted(getInteger("FFAC"));
		
		return sheet;
	}
	
	public static void main(String[] args) {
		CharacterSheet sheet = adapter.getCharacterSheet(
			"https://www.rpgcrossing.com/profiler/view.php?id=74153"
		);
		
		System.out.println(String.format(
			"AC: %s; Flat: %s; Touch: %s",
			sheet.getAc(),
			sheet.getFlatfooted(),
			sheet.getTouch()
		));
	}
}
