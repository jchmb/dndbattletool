package nl.jchmb.dndbattle.core.events;

import java.util.List;

import nl.jchmb.dndbattle.core.Battle;
import nl.jchmb.dndbattle.core.effects.Effect;

public interface BattleEvent {
	public String getHtml(final Battle battle);
	public List<Effect> getEffects();
}
