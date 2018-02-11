package nl.jchmb.dndbattle.core.events;

import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.utils.form.Form;

public interface BattleEvent {
	public String getText();
	public Form buildForm(Battle battle, Form form);
}
