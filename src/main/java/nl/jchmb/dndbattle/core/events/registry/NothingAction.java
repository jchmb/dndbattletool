package nl.jchmb.dndbattle.core.events.registry;

import nl.jchmb.dndbattle.core.events.BattleAction;

public class NothingAction extends BattleAction {
	
	@Override
	public String getText() {
		return String.format(
			"[b]%s[/b] does nothing.",
			getSubject().getName()
		);
	}
	
}
