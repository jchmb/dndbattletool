package nl.jchmb.dndbattle.core.genders;

public class Woman implements Gender {

	@Override
	public String getSymbol() {
		return "F";
	}

	@Override
	public String getPersonalPronoun() {
		return "she";
	}

	@Override
	public String getPosessivePronoun() {
		return "her";
	}

}
