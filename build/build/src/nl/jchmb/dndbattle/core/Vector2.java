/**
 * 
 */
package nl.jchmb.dndbattle.core;

/**
 * @author jochem
 *
 */
public class Vector2 {
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
}
