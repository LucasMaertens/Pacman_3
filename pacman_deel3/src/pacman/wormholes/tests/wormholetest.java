package pacman.wormholes.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;
import pacman_3.MazeMap;
import pacman_3.Square;


class wormholetest { 

	@Test
	void test() {
		//we maken een mazemap van 4x4 hokjes die allemaal passable zijn
		boolean[] passable= {true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true};
		MazeMap mazemap = new MazeMap(4,4,passable );
		Square square1 = Square.of(mazemap, 3, 3);
		DeparturePortal dep1 = new DeparturePortal(square1);
		assertEquals(dep1.getWormholes().equals(Set.of()),true);  
		assertEquals(square1.equals(dep1.getSquare()),true);
		ArrivalPortal arr1 = new ArrivalPortal(square1);
		assertEquals(Set.of().equals(arr1.getWormholes()),true);
		assertEquals(square1.equals(arr1.getSquare()),true); 
		Wormhole wormhole1 = new Wormhole(dep1, arr1);
		assertEquals(dep1.equals(wormhole1.getDeparturePortal()),true);
		assertEquals(arr1.equals(wormhole1.getArrivalPortal()),true);
		
		Square square2 = Square.of(mazemap, 2, 3);	
		DeparturePortal dep2 = new DeparturePortal(square2);
		ArrivalPortal arr2 = new ArrivalPortal(square2);
		wormhole1.setDeparturePortal(dep2);
		wormhole1.setArrivalPortal(arr2);
		assertEquals(dep2.equals(wormhole1.getDeparturePortal()),true);
		assertEquals(arr2.equals(wormhole1.getArrivalPortal()),true);	
		
		
		
	}

}
