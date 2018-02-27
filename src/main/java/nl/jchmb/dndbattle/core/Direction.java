package nl.jchmb.dndbattle.core;

public enum Direction {
	EAST(new Vector2(1, 0)),
	NORTH_EAST(new Vector2(1, -1)),
	NORTH(new Vector2(0, -1)),
	NORTH_WEST(new Vector2(-1, -1)),
	WEST(new Vector2(-1, 0)),
	SOUTH_WEST(new Vector2(-1, 1)),
	SOUTH(new Vector2(0, 1)),
	SOUTH_EAST(new Vector2(1, -1));
	
	public final Vector2 value;
	
	private Direction(final Vector2 value) {
		this.value = value;
	}
	
	public final Vector2 getValue() {
		return value;
	}
}
