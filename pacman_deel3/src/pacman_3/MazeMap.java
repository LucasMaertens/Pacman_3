package pacman;

import java.util.stream.IntStream;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @invar | 0 <= getWidth()
 * @invar | 0 <= getHeight()
 *  
 * @immutable
 */
public class MazeMap {

	/**
	 * @invar | 0 <= width
	 * @invar | 0 <= height
	 * @invar | passable != null
	 * @invar | passable.length == width * height
	 */
	private final int width;
	private final int height;
	
	/**
	 * Stores, for each position in this maze (in row-major order), whether the position is passable.
	 * 
	 * @representationObject
	 */
	private final boolean[] passable;
	
	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 * 
	 * @basic
	 */
	public int getWidth() { return width; }
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 * 
	 * @basic
	 */
	public int getHeight() { return height; }
	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 * 
	 * @throws IllegalArgumentException | rowIndex < 0
	 * @throws IllegalArgumentException | getHeight() <= rowIndex
	 * @throws IllegalArgumentException | columnIndex < 0
	 * @throws IllegalArgumentException | getWidth() <= columnIndex
	 * 
	 * @basic
	 */
	public boolean isPassable(int rowIndex, int columnIndex) {
		if (rowIndex < 0)
			throw new IllegalArgumentException("`rowIndex` is negative");
		if (height <= rowIndex)
			throw new IllegalArgumentException("`rowIndex` is not less than the height of the maze map");
		if (columnIndex < 0)
			throw new IllegalArgumentException("`columnIndex` is negative");
		if (width <= columnIndex)
			throw new IllegalArgumentException("`columnIndex` is not less than the width of the maze map");
		
		return passable[rowIndex * width + columnIndex];
	}
	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze). 
	 * 
	 * @throws IllegalArgumentException | width < 0
	 * @throws IllegalArgumentException | height < 0
	 * @throws IllegalArgumentException | passable == null
	 * @throws IllegalArgumentException | passable.length != width * height
	 * 
	 * @inspects | passable
	 * 
	 * @post | getWidth() == width
	 * @post | getHeight() == height
	 * @post | IntStream.range(0, height).allMatch(rowIndex ->
	 *       |     IntStream.range(0, width).allMatch(columnIndex ->
	 *       |         isPassable(rowIndex, columnIndex) == passable[rowIndex * width + columnIndex]))
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		if (width < 0)
			throw new IllegalArgumentException("`width` is negative");
		if (height < 0)
			throw new IllegalArgumentException("`height` is negative");
		if (passable == null)
			throw new IllegalArgumentException("`passable` is null");
		if (passable.length != width * height)
			throw new IllegalArgumentException("the length of `passable` is wrong");
		
		this.width = width;
		this.height = height;
		this.passable = passable.clone();
	}
	
}
