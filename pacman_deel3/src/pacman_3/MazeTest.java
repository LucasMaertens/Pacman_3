package pacman_3;
import pacman_3.Direction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import pacman_3.Maze;
import pacman_3.MazeDescriptions;
import pacman_3.PowerPellet;
import pacman_3.Square;

class MazeTest {
	@Test
	void test() {
	Maze maze = MazeDescriptions.createMazeFromDescription(new Random(), """
			#####################
			#.........#.........#
			#.###.###.#.###.###.#
			#p###.###.#.###.###p#
			#.###.###.#.###.###.#
			#...................#
			#.###.#.#####.#.###.#
			#.###.#.#####.#.###.#
			#.....#...#...#.....#
			#####.### # ###.#####
			    #.#   G   #.#    
			    #.# #   # #.#    
			#####.# #   # #.#####
			     .  #GGG#  .     
			#####.# ##### #.#####
			    #.#       #.#    
			    #.# ##### #.#    
			#####.# ##### #.#####
			#.........#.........#
			#.###.###.#.###.###.#
			#p..#.....P.....#..p#
			###.#.#.#####.#.#.###
			###.#.#.#####.#.#.###
			#.....#...#...#.....#
			#.#######.#.#######.#
			#...................#
			#####################
			""");
	PowerPellet[] powerPellets =
		Arrays.stream(maze.getFoodItems())
			.flatMap(i -> i instanceof PowerPellet ? Stream.of((PowerPellet)i) : Stream.of())
			.sorted(Comparator.<PowerPellet>comparingInt(i -> i.getSquare().getRowIndex())
					          .thenComparingInt(i -> i.getSquare().getColumnIndex()))
			.toArray(n -> new PowerPellet[n]);

	
		assertEquals( powerPellets.length == 4,true);
		assert powerPellets[0].getSquare().equals(Square.of(maze.getMap(), 3, 1));
		assert powerPellets[1].getSquare().equals(Square.of(maze.getMap(), 3, 19));
		assert powerPellets[2].getSquare().equals(Square.of(maze.getMap(), 20, 1));
		assert powerPellets[3].getSquare().equals(Square.of(maze.getMap(), 20, 19));
	

		assert powerPellets[0].getSize() == 2;
	}
	
}
