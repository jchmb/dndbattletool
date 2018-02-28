/**
 * 
 */
package nl.jchmb.dndbattle.core;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author jochem
 *
 */
public class Vector2 {
	public static final Vector2 EX = new Vector2(1, 0);
	public static final Vector2 EY = new Vector2(0, 1);
	
	private final int x, y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	public Vector2 add(Vector2 v) {
		return new Vector2(x + v.x, y + v.y);
	}
	
	public Vector2 subtract(Vector2 v) {
		return new Vector2(x - v.x, y - v.y);
	}
	
	public Vector2 scale(int scaling) {
		return new Vector2(scaling * x, scaling * y);
	}
	
	public Vector2 rotate(boolean clockwise) {
		return new Vector2(
			clockwise ? y : -y,
			clockwise ? -x : x
		);
	}
	
	public Vector2 rotate(int numRotations) {
		Vector2 v = this;
		for (int i = 0; i < Math.abs(numRotations); i++) {
			v = v.rotate(numRotations > 0);
		}
		return v;
	}
	
	/**
	 * Rotate this Vector2, given the east as its initial direction, or the north-east if the 
	 * direction is diagonal.
	 * @param direction
	 * @return
	 */
	public Vector2 rotate(final Direction direction) {
		switch (direction) {
			case EAST:
			case NORTH_EAST:
				return this;
			case NORTH:
			case NORTH_WEST:
				return rotate(1);
			case WEST:
			case SOUTH_WEST:
				return rotate(2);
			case SOUTH:
			case SOUTH_EAST:
			default:
				return rotate(3);
		}
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Vector2)) {
			return false;
		}
		Vector2 that = (Vector2) other;
		return this.x == that.x && this.y == that.y;
	}
	
	@Override
	public int hashCode() {
		return x * 5 + y * 7;
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s)", x, y);
	}
	
	public String getXml() {
		return String.format(
			"<vector x=\"%s\" y=\"%s\" />",
			x,
			y
		);
	}
	
	public static final Vector2 zero() {
		return new Vector2(0, 0);
	}
	
	public static final Stream<Vector2> line(
			final Vector2 offset,
			final Vector2 velocity,
			final int repetitions
	) {
		return IntStream.range(0, repetitions)
			.mapToObj(
				i -> offset.add(
					velocity.scale(i)
				)
			);
	}
}
