package pacman.wormholes.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.wormholes.DeparturePortal;
import pacman_3.MazeMap;
import pacman_3.Square;

class testje {

	@Test
	void test() {
		boolean[] passable= {true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true};
		MazeMap mazemap = new MazeMap(4,4,passable );
		Square square1 = Square.of(mazemap, 3, 3);
		DeparturePortal vertrek= new DeparturePortal(square1); 
	}

} 
