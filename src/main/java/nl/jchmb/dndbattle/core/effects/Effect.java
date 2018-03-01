package nl.jchmb.dndbattle.core.effects;

import nl.jchmb.dndbattle.core.Battle;

public interface Effect {
	public String getXml(final Battle battle, final boolean primary);
	public void perform(final Battle battle);
	public void undo(final Battle battle);
}
