package nl.jchmb.dndbattle.http.adapters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RPGCrossingAdapter extends HttpAdapter {
	public RPGCrossingAdapter(String username, String password) {
		super();
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
}
