package nl.jchmb.dndbattle.core.overlays.structures;

import java.util.List;

import nl.jchmb.dndbattle.core.FormProvider;
import nl.jchmb.dndbattle.core.Vector2;

public interface OverlayStructure extends FormProvider {
	public List<Vector2> getSquares();
}
