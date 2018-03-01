package nl.jchmb.dndbattle.core.effects;

import nl.jchmb.dndbattle.core.Battle;

public class NullEffect implements Effect {

	@Override
	public String getXml(final Battle battle, final boolean primary) {
		return "nothing happened";
	}

	@Override
	public void perform(Battle battle) {
		
	}

	@Override
	public void undo(Battle battle) {
		
	}
	
}
