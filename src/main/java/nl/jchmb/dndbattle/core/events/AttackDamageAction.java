package nl.jchmb.dndbattle.core.events;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AttackDamageAction extends AttackAction {
	private final IntegerProperty damage = new SimpleIntegerProperty();
	
	@Override
	protected String getResultText() {
		return String.format(
			"[color=red]%s damage[/color]",
			getDamage()
		);
	}

	public final IntegerProperty damageProperty() {
		return this.damage;
	}
	

	public final int getDamage() {
		return this.damageProperty().get();
	}
	

	public final void setDamage(final int damage) {
		this.damageProperty().set(damage);
	}
	
	
}
