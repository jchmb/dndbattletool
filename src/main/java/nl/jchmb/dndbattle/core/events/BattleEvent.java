package nl.jchmb.dndbattle.core.events;

import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.effects.Effect;

public interface BattleEvent {
	public String getXml(final Battle battle);
	public Effect getEffect();
}
