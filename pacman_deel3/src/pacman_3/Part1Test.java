package pacman_3;
import pacman_3.Direction;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import pacman_3.Direction;
import pacman_3.Dot;
import pacman_3.MazeMap;
import pacman_3.PacMan;
import pacman_3.Square;

class Part1Test {

 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
		true, false, true, true,
		true, true, false, true,
		false, true, true, true
 	});
	Square square02 = Square.of(mazeMap, 0, 2);
	Dot dot = new Dot(Square.of(mazeMap, 1, 3));
	PacMan pacMan = new PacMan(3, Square.of(mazeMap, 1, 0));

	@Test
	void testPart1() {
		assertEquals(4, mazeMap.getWidth());
		assertEquals(3, mazeMap.getHeight());
		assertTrue(mazeMap.isPassable(0, 0));
		assertFalse(mazeMap.isPassable(0, 1));
		assertTrue(mazeMap.isPassable(0, 2));
		assertTrue(mazeMap.isPassable(1, 0));
		assertTrue(mazeMap.isPassable(1, 1));
		assertFalse(mazeMap.isPassable(1, 2));

		assertEquals(mazeMap, square02.getMazeMap());
		assertEquals(0, square02.getRowIndex());
		assertEquals(2, square02.getColumnIndex());
		assertTrue(Square.of(mazeMap, 0, 1).equals(square02.getNeighbor(Direction.LEFT)));
		assertTrue(Square.of(mazeMap, 0, 3).equals(square02.getNeighbor(Direction.RIGHT)));
		assertTrue(Square.of(mazeMap, 1, 2).equals(square02.getNeighbor(Direction.DOWN)));
		assertTrue(Square.of(mazeMap, 2, 2).equals(square02.getNeighbor(Direction.UP)));
		assertTrue(Square.of(mazeMap, 1, 0).equals(Square.of(mazeMap, 1, 3).getNeighbor(Direction.RIGHT)));
		assertTrue(Square.of(mazeMap, 0, 2).equals(Square.of(mazeMap, 2, 2).getNeighbor(Direction.DOWN)));
		assertTrue(Square.of(mazeMap, 0, 3).equals(Square.of(mazeMap, 0, 0).getNeighbor(Direction.LEFT)));
		assertTrue(square02.canMove(Direction.RIGHT));
		assertFalse(square02.canMove(Direction.LEFT));
		assertTrue(square02.canMove(Direction.UP));
		assertFalse(square02.canMove(Direction.DOWN));
		assertEquals(new HashSet<Direction>(Arrays.asList(new Direction[] {Direction.UP})), new HashSet<Direction>(Arrays.asList(square02.getPassableDirectionsExcept(Direction.RIGHT))));
		
		assertTrue(Square.of(mazeMap, 1, 3).equals(dot.getSquare()));
		
		assertEquals(3, pacMan.getNbLives());
		assertTrue(Square.of(mazeMap, 1, 0).equals(pacMan.getSquare()));
		pacMan.setSquare(Square.of(mazeMap, 2, 3));
		assertTrue(Square.of(mazeMap, 2, 3).equals(pacMan.getSquare()));
		pacMan.die();
		assertEquals(2, pacMan.getNbLives());
	}

}
