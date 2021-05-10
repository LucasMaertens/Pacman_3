package pacman_3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;
import pacman_3.Direction;
import pacman_3.Ghost;
import pacman_3.PacMan;

import pacman_3.MazeMap;
import pacman_3.Square;
class mijnklasses {

	@Test
	void test() {
		boolean[] passable= {true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true};
		MazeMap mazemap = new MazeMap(4,4,passable);
		Random randi= new Random();
		PacMan pacman=new PacMan(5,Square.of(mazemap, 0, 0));
		Ghost[] ghosten= new Ghost[3];
		Square square1 = Square.of(mazemap, 3, 3);
		Square square2 = Square.of(mazemap, 1, 1);
		Ghost geest1=new Ghost(Square.of(mazemap, 0, 0),Direction.RIGHT);
		Ghost geest2=new Ghost(Square.of(mazemap, 2, 0),Direction.RIGHT);
		Ghost geest3=new Ghost(Square.of(mazemap, 0, 3),Direction.RIGHT);
		ghosten[0]=geest1;
		ghosten[1]=geest2;
		ghosten[2]=geest3;
		FoodItem[] eten = new FoodItem[0];
		
		 
		
		ArrivalPortal[] aaknomst=new ArrivalPortal[1];
		DeparturePortal[] departurePortals= new DeparturePortal[1];
		// VANAF AANMAKEN VAN EEN ARRIVALPORTAL OF DEPARTUREPORTAL LOOPT HET MIS
		//ArrivalPortal a= new ArrivalPortal(square1);
		assertEquals(new ArrivalPortal(square1).getSquare().equals(square1),true);
		//DeparturePortal d= new DeparturePortal(square2);
		//aaknomst[0]=a;
		//departurePortals[0]=d;
		//Maze mass= new Maze(randi,mazemap,pacman,ghosten,eten,aaknomst,departurePortals);
		
		//ADDWORMHOLE TEST : WERKT
		assertEquals(Arrays.asList(ghosten).contains(geest3),true);
		Ghost[] oudegeesten= ghosten.clone();
		Ghost[] nieuwelijst= new Ghost[oudegeesten.length+1];
		for(int i=0;i<oudegeesten.length;i++) { 
			nieuwelijst[i]=oudegeesten[i];}
		nieuwelijst[nieuwelijst.length-1]=new Ghost(Square.of(mazemap, 0, 0),Direction.DOWN);
		assertEquals(nieuwelijst.length,4);
		assertEquals(nieuwelijst[3].getSquare().equals(new Ghost(Square.of(mazemap, 0, 0),Direction.DOWN).getSquare()),true);
	}
		//  MOVEPACMAN TESTEN
		

}
