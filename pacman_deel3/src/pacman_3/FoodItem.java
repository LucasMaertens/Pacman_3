package pacman_3;

public abstract class FoodItem {
	
	/**
	 * @post | 1 <= result
	 */
	public abstract int getSize();
	
	/**
	 * @invar | square != null
	 */
	private final Square square;
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare() == square
	 */
	public FoodItem(Square square) {
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		
		this.square = square;
	}

	public void eatenByPacMan(Maze maze) {}

}
