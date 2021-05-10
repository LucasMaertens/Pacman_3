package pacman_3;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze,
 * located at a particular position and moving in a particular direction.
 * 
 * @invar | getSquare() != null
 * @invar | getDirection() != null
 */
public class Ghost {
	
	/**
	 * @invar | originalSquare != null
	 * @invar | square != null
	 * @invar | direction != null
	 */
	private final Square originalSquare;
	private Square square;
	private Direction direction;
	private GhostState state = new RegularGhostState();
	
	public boolean isVulnerable() { return state.isVulnerable(); }

	public Square getOriginalSquare() { return originalSquare; }
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }

	/**
	 * @basic
	 */
	public Direction getDirection() { return direction; }
	
	/**
	 * Initializes this object so that its initial position is the
	 * given position and its initial direction is the given
	 * direction.
	 * 
	 * @throws IllegalArgumentException | square == null
	 * @throws IllegalArgumentException | direction == null
	 * 
	 * @post | getSquare() == square
	 * @post | getDirection() == direction
	 */
	public Ghost(Square square, Direction direction) {
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		if (direction == null)
			throw new IllegalArgumentException("`direction` is null");
		
		this.square = this.originalSquare = square;
		this.direction = direction;
	}
	
	/**
	 * Sets this ghost's position.
	 * 
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @mutates | this
	 * 
	 * @post | getSquare() == square
	 * @post | getDirection() == old(getDirection())
	 */
	public void setSquare(Square square) {
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		
		this.square = square;
	}
	
	/**
	 * Sets this ghost's direction.
	 * 
	 * @throws IllegalArgumentException | direction == null
	 * 
	 * @mutates | this
	 * 
	 * @post | getDirection() == direction
	 * @post | getSquare() == old(getSquare())
	 */
	public void setDirection(Direction direction) {
		if (direction == null)
			throw new IllegalArgumentException("`direction` is null");

		this.direction = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal documentation required.
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(direction) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal documentation required.
	public void move(Random random) {
		state = state.move(this, random);
	}
	
	public void reallyMove(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}

	public void pacManAtePowerPellet() {
		direction = direction.getOpposite();
		state = new VulnerableGhostState();
	}

	public void hitBy(PacMan pacMan) {
		state = state.hitBy(this, pacMan);
	}
}
