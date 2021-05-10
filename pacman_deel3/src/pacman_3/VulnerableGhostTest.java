package pacman_3;
import pacman_3.Direction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import pacman_3.Maze;
import pacman_3.MazeDescriptions;
import pacman_3.PowerPellet;
import pacman_3.Square;
class VulnerableGhostTest {
 
 	MazeMap mazeMap = new MazeMap(9, 3, new boolean[] {
		false, false, false, false, false, false, false, false, false,
		false, true,  true,  true,  true,  true,  true,  true,  false,
		false, false, false, false, false, false, false, false, false
 	});
	Ghost ghost = new Ghost(Square.of(mazeMap, 1, 1), Direction.LEFT);
	Random random = new Random();
	{
		ghost.pacManAtePowerPellet();
	}

	@Test
	void testMoveTenTimes() {
		for (int i = 1; i < 6; i++) {
			assert ghost.isVulnerable();
			assert ghost.getSquare().equals(Square.of(mazeMap, 1, i));
			assert ghost.getDirection() == Direction.RIGHT;
			
			ghost.move(random);
			assert ghost.isVulnerable();
			assert ghost.getSquare().equals(Square.of(mazeMap, 1, i));
			assert ghost.getDirection() == Direction.RIGHT;
			
			ghost.move(random);
		}
	}

	@Test
	void testMoveThirteenTimes() {
		for (int i = 1; i < 7; i++) {
			assert ghost.isVulnerable();
			assert ghost.getSquare().equals(Square.of(mazeMap, 1, i));
			assert ghost.getDirection() == Direction.RIGHT;
			
			ghost.move(random);
			assert ghost.isVulnerable();
			assert ghost.getSquare().equals(Square.of(mazeMap, 1, i));
			assert ghost.getDirection() == Direction.RIGHT;
			
			ghost.move(random);
		}
		assert !ghost.isVulnerable();
		assert ghost.getSquare().equals(Square.of(mazeMap, 1, 7));
		assert ghost.getDirection() == Direction.RIGHT;
		
		ghost.move(random);
		assert !ghost.isVulnerable();
		assert ghost.getSquare().equals(Square.of(mazeMap, 1, 6));
		assert ghost.getDirection() == Direction.LEFT;
	}
	
	@Test
	void testHitBy() {
		ghost.move(random);
		ghost.move(random);
		assert ghost.getSquare().equals(Square.of(mazeMap, 1, 2));
		assert ghost.isVulnerable();
		PacMan pacMan = new PacMan(3, Square.of(mazeMap, 1, 2));
		assert pacMan.getNbLives() == 3;
		ghost.hitBy(pacMan);
		assert pacMan.getNbLives() == 3;
		assert ghost.getSquare().equals(Square.of(mazeMap, 1, 1));
		assert !ghost.isVulnerable();
		pacMan.setSquare(Square.of(mazeMap, 1, 1));
		ghost.hitBy(pacMan);
		assert pacMan.getNbLives() == 2;
	}
}
