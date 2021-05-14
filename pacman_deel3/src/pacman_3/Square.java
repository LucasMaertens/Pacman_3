package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @invar | getMazeMap() != null
 * @invar | 0 <= getRowIndex()
 * @invar | getRowIndex() < getMazeMap().getHeight()
 * @invar | 0 <= getColumnIndex()
 * @invar | getColumnIndex() < getMazeMap().getWidth()
 * 
 * @immutable
 */
public class Square {
	
	/**
	 * @invar | mazeMap != null
	 * @invar | 0 <= rowIndex
	 * @invar | rowIndex < mazeMap.getHeight()
	 * @invar | 0 <= columnIndex
	 * @invar | columnIndex < mazeMap.getWidth()
	 */
	private final MazeMap mazeMap;
	private final int rowIndex;
	private final int columnIndex;
	
	/**
	 * @basic
	 */
	public MazeMap getMazeMap() { return mazeMap; }
	
	/**
	 * @basic
	 */
	public int getRowIndex() { return rowIndex; }
	
	/**
	 * @basic
	 */
	public int getColumnIndex() { return columnIndex; }
	
	/**
	 * @post | result == getMazeMap().isPassable(getRowIndex(), getColumnIndex())
	 */
	public boolean isPassable() { return mazeMap.isPassable(rowIndex, columnIndex); }
	
	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (mazeMap == null)
			throw new IllegalArgumentException("`mazeMap` must not be null");
		if (rowIndex < 0)
			throw new IllegalArgumentException("`rowIndex` must not be negative");
		if (mazeMap.getHeight() <= rowIndex)
			throw new IllegalArgumentException("`rowIndex` must be less than the height of the maze map");
		if (columnIndex < 0)
			throw new IllegalArgumentException("`columnIndex` must not be negative");
		if (mazeMap.getWidth() <= columnIndex)
			throw new IllegalArgumentException("`columnIndex` must be less than the width of the maze map");
		
		this.mazeMap = mazeMap;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	/**
	 * Initializes this object so that it represents the position in the given maze map
	 * specified by the given row index and column index.
	 * 
	 * @throws IllegalArgumentException | mazeMap == null
	 * @throws IllegalArgumentException | rowIndex < 0
	 * @throws IllegalArgumentException | mazeMap.getHeight() <= rowIndex
	 * @throws IllegalArgumentException | columnIndex < 0
	 * @throws IllegalArgumentException | mazeMap.getWidth() <= columnIndex
	 * 
	 * @post | result != null
	 * @post | result.getMazeMap() == mazeMap
	 * @post | result.getRowIndex() == rowIndex
	 * @post | result.getColumnIndex() == columnIndex
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		return new Square(mazeMap, rowIndex, columnIndex);
	}
	
	// No formal documentation required.
	public Square getNeighbor(Direction direction) {
		int rowIndex = this.rowIndex;
		int columnIndex = this.columnIndex;
		switch (direction) {
		case RIGHT -> columnIndex = (columnIndex + 1) % mazeMap.getWidth();
		case LEFT -> columnIndex = Math.floorMod(columnIndex - 1, mazeMap.getWidth());
		case DOWN -> rowIndex = (rowIndex + 1) % mazeMap.getHeight();
		case UP -> rowIndex = Math.floorMod(rowIndex - 1, mazeMap.getHeight());
		}
		return Square.of(mazeMap, rowIndex, columnIndex);
	}
	
	// No formal documentation required.
	public boolean canMove(Direction direction) {
		return getNeighbor(direction).isPassable();
	}
	
	// No formal documentation required.
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		Direction[] result = new Direction[4];
		int nbDirections = 0;
		for (Direction direction : Direction.values())
			if (direction != excludedDirection && canMove(direction))
				result[nbDirections++] = direction;
		return Arrays.copyOf(result, nbDirections);
	}

	/**
	 * Returns whether this object represents the same position in the same maze map as the given object.
	 * 
	 * @throws IllegalArgumentException | other == null
	 * 
	 * @post | result == (
	 *       |     getMazeMap() == other.getMazeMap() &&
	 *       |     getRowIndex() == other.getRowIndex() &&
	 *       |     getColumnIndex() == other.getColumnIndex()
	 *       | ) 
	 */
	public boolean equals(Square other) {
		if (other == null)
			throw new IllegalArgumentException("`other` must not be null");
		
		return mazeMap == other.mazeMap && rowIndex == other.rowIndex && columnIndex == other.columnIndex;
	}
	
}
