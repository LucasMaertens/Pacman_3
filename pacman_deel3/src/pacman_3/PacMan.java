package pacman;

/**
 * Each instance of this class represents the Pac-Man character in a Pac-Man maze.
 * 
 * @invar | 0 <= getNbLives()
 * @invar | getSquare() != null
 */
public class PacMan {
	
	/**
	 * @invar | 0 <= nbLives
	 * @invar | square != null 
	 */
	private int nbLives;
	private Square square;
	
	/**
	 * @basic
	 */
	public int getNbLives() {
		return nbLives;
	}
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }

	/**
	 * Initializes this object so that its initial position is the
	 * given square and its initial number of lives equals the given
	 * number of lives.
	 * 
	 * @throws | nbLives < 0
	 * @throws | square == null
	 * 
	 * @post | getNbLives() == nbLives
	 * @post | getSquare() == square
	 */
	public PacMan(int nbLives, Square square) {
		this.nbLives = nbLives;
		this.square = square;
	}
	
	/**
	 * Sets this object's position.
	 * 
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @mutates | this
	 * 
	 * @post | getSquare() == square
	 * @post | getNbLives() == old(getNbLives()) 
	 */
	public void setSquare(Square square) {
		this.square = square;
	}
	
	/**
	 * Decreases this object's number of lives.
	 * 
	 * @throws IllegalStateException | getNbLives() == 0
	 * 
	 * @mutates | this
	 * 
	 * @post | getNbLives() == old(getNbLives()) - 1
	 * @post | getSquare() == old(getSquare())
	 */
	public void die() {
		if (nbLives == 0)
			throw new IllegalStateException("no lives left");
		
		this.nbLives--;
	}

}
