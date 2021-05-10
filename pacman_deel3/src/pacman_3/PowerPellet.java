package pacman_3;

public class PowerPellet extends FoodItem {
	
	/**
	 * @post | result == 2
	 */
	@Override
	public int getSize() {
		return 2;
	}
	
	public PowerPellet(Square square) {
		super(square);
	}
	
	@Override
	public void eatenByPacMan(Maze maze) {
		maze.pacManAtePowerPellet();
	}
}
