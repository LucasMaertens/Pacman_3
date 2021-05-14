package pacman;

/**
 * Each instance of this class represents a dot, located at a fixed position in a Pac-Man maze.
 * A dot serves as the food for Pac-Man.
 * 
 * @invar | getSquare() != null
 * 
 * @immutable
 */
public class Dot extends FoodItem {
	
	/**
	 * @post | result == 1
	 */
	@Override
	public int getSize() {
		return 1;
	}

	public Dot(Square square) {
		super(square);
	}

}
