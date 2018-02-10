package nl.jchmb.dndbattle.core.events;

public class AttackMissedAction extends AttackAction {

	@Override
	protected String getResultText() {
		return "missed";
	}
	
}
