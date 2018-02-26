package nl.jchmb.dndbattle.core.effects;

import nl.jchmb.dndbattle.core.Battle;

public interface Effect {
	public String getXml(final Battle battle);
	public void perform(final Battle battle);
	public void undo(final Battle battle);
}
