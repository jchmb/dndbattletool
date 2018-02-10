package nl.jchmb.dndbattle.core.genders;

public class Undetermined implements Gender {
	
	@Override
	public String getSymbol() {
		return "n/a";
	}

	@Override
	public String getPersonalPronoun() {
		return "they";
	}

	@Override
	public String getPosessivePronoun() {
		return "their";
	}
	
}
