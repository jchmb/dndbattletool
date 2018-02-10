package nl.jchmb.dndbattle.core.genders;

public class Man implements Gender {

	@Override
	public String getSymbol() {
		return "M";
	}

	@Override
	public String getPersonalPronoun() {
		return "he";
	}

	@Override
	public String getPosessivePronoun() {
		return "his";
	}
	
}
