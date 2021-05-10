package pacman.gui;
import pacman_3.Direction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import pacman_3.Direction;
import pacman_3.FoodItem;
import pacman_3.Ghost;
import pacman_3.Maze;
import pacman_3.MazeDescriptions;
import pacman_3.MazeMap;
import pacman_3.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class MazeView extends JPanel {
	
	private static final int squareSize = 30;
	private static final int lifeSize = squareSize;
	private static final int lifeMargin = 1;
	private static final int dotRadius = squareSize / 10;
	private static final int ghostMoveDelayMillis = 1000;
	private static final Color[] wormholeColors = {Color.cyan, Color.magenta, Color.orange}; // If you change this, also update the key listener!

	private static Image loadSquareImage(String filename) {
		URL url = MazeView.class.getResource(filename);
		Image image = Toolkit.getDefaultToolkit().getImage(url);
		return image.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH);
	}
	private static final Image pacManImage = loadSquareImage("PacMan.png");
	private static final Image ghostImage = loadSquareImage("ghost.png");
	private static final Image vulnerableGhostImage = loadSquareImage("Vulnerable-ghost.png");
	
	private Maze maze;
	private MazeMap map;
	private Timer ghostTimer;
	
	private void mazeChanged() {
		repaint();
		if (maze.getPacMan().getNbLives() == 0) {
			ghostTimer.stop();
			JOptionPane.showMessageDialog(this, "Game over :-(");
			initializeMaze();
		} else if (maze.isCompleted()) {
			ghostTimer.stop();
			JOptionPane.showMessageDialog(this, "Congratulations: you won!");
			initializeMaze();
		}
	}
	
	private void startMovingGhosts() {
		ghostTimer = new Timer(ghostMoveDelayMillis, actionEvent -> {
			maze.moveGhosts();
			mazeChanged();
		});
		ghostTimer.start();
	}
	
	private void initializeMaze() {
		maze = MazeDescriptions.createMazeFromDescription(new Random(), """
				#####################
				#A........#........D#
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
				  D  .  #GGG#  .  A  
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
				#D.................A#
				#####################
				""");
		DeparturePortal[] departurePortals = maze.getDeparturePortals();
		ArrivalPortal[] arrivalPortals = maze.getArrivalPortals();
		maze.addWormhole(new Wormhole(departurePortals[0], arrivalPortals[2]));
		maze.addWormhole(new Wormhole(departurePortals[0], arrivalPortals[0]));
		maze.addWormhole(new Wormhole(departurePortals[1], arrivalPortals[1]));
		map = maze.getMap();
		repaint();
		startMovingGhosts(); 
	}
	
	private void movePacMan(Direction direction) {
		maze.movePacMan(direction);
		mazeChanged();
	}
	
	private void moveWormholeArrivalPortal(Wormhole wormhole) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int column = e.getX() / squareSize;
				int row = e.getY() / squareSize;
				if (column < map.getWidth() && row < map.getHeight()) {
					Square square = Square.of(map, row, column);
					ArrivalPortal arrivalPortal = Arrays.stream(maze.getArrivalPortals()).filter(p -> p.getSquare().equals(square)).findAny().orElse(null);
					if (arrivalPortal != null && arrivalPortal != wormhole.getArrivalPortal()) {
						wormhole.setArrivalPortal(arrivalPortal);
						repaint();
					}
				}
				removeMouseListener(this);
			}
		});
	}
	
	private void moveWormhole(int index) {
		Wormhole wormhole =  maze.getWormholes()[index];
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int column = e.getX() / squareSize;
				int row = e.getY() / squareSize;
				if (column < map.getWidth() && row < map.getHeight()) {
					Square square = Square.of(map, row, column);
					DeparturePortal departurePortal = Arrays.stream(maze.getDeparturePortals()).filter(p -> p.getSquare().equals(square)).findAny().orElse(null);
					if (departurePortal != null) {
						if (departurePortal != wormhole.getDeparturePortal()) {
							wormhole.setDeparturePortal(departurePortal);
							repaint();
						}
						moveWormholeArrivalPortal(wormhole);
					}
				}
				removeMouseListener(this);
			}
		});
	}
	
	public MazeView() { 
		initializeMaze();
		setBackground(Color.black);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT -> movePacMan(Direction.RIGHT); 
				case KeyEvent.VK_DOWN -> movePacMan(Direction.DOWN);
				case KeyEvent.VK_LEFT -> movePacMan(Direction.LEFT);
				case KeyEvent.VK_UP -> movePacMan(Direction.UP);
				case KeyEvent.VK_C /* cyan */ -> moveWormhole(0);
				case KeyEvent.VK_M /* magenta */ -> moveWormhole(1);
				case KeyEvent.VK_O /* orange */ -> moveWormhole(2);
				}
			}
		});
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(squareSize * maze.getMap().getWidth(), squareSize * maze.getMap().getHeight() + lifeSize + 2 * lifeMargin);
	}
	
	@Override
	public boolean isFocusable() {
		return true;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Walls
		g.setColor(Color.blue);
		for (int row = 0; row < map.getHeight(); row++)
			for (int column = 0; column < map.getWidth(); column++)
				if (!map.isPassable(row, column))
					g.fillRect(squareSize * column, squareSize * row, squareSize, squareSize);
		
		// Departure portals
		g.setColor(Color.green);
		for (DeparturePortal portal : maze.getDeparturePortals()) {
			for (int i = 0; i < 3; i++) {
				g.drawOval(portal.getSquare().getColumnIndex() * squareSize + 3 * i, portal.getSquare().getRowIndex() * squareSize + 3 * i, squareSize - 6 * i, squareSize - 6 * i);
			}
		}
		
		// Arrival portals
		g.setColor(Color.red);
		for (ArrivalPortal portal : maze.getArrivalPortals()) {
			for (int i = 0; i < 3; i++) {
				g.drawOval(portal.getSquare().getColumnIndex() * squareSize + 3 * i, portal.getSquare().getRowIndex() * squareSize + 3 * i, squareSize - 6 * i, squareSize - 6 * i);
			}
		}
		
		// Food items
		g.setColor(Color.yellow);
		for (FoodItem foodItem : maze.getFoodItems()) {
			int foodItemRadius = foodItem.getSize() * dotRadius;
			g.fillOval(
					foodItem.getSquare().getColumnIndex() * squareSize + squareSize / 2 - foodItemRadius,
					foodItem.getSquare().getRowIndex() * squareSize + squareSize / 2 - foodItemRadius,
					2 * foodItemRadius,
					2 * foodItemRadius);
		}
		
		// Ghosts
		for (Ghost ghost : maze.getGhosts())
			g.drawImage(
					ghost.isVulnerable() ? vulnerableGhostImage : ghostImage,
					ghost.getSquare().getColumnIndex() * squareSize,
					ghost.getSquare().getRowIndex() * squareSize,
					this);
		
		// PacMan
		g.drawImage(
				pacManImage,
				maze.getPacMan().getSquare().getColumnIndex() * squareSize,
				maze.getPacMan().getSquare().getRowIndex() * squareSize,
				this);
		
		Graphics2D g2d = (Graphics2D)g;
		// Wormholes 
		Wormhole[] wormholes = maze.getWormholes();
		for (int i = 0; i < wormholes.length; i++) {
			Wormhole wormhole = wormholes[i];
			Square start = wormhole.getDeparturePortal().getSquare();
			Square end = wormhole.getArrivalPortal().getSquare();
			Vector startCenter = new Vector((start.getColumnIndex() + .5) * squareSize, (start.getRowIndex() + .5) * squareSize);
			Vector endCenter = new Vector((end.getColumnIndex() + .5) * squareSize, (end.getRowIndex() + .5) * squareSize);
			Vector offset = endCenter.minus(startCenter);
			double angle = -offset.getAngle();
			Path2D.Double path = new Path2D.Double();
			path.append(new Arc2D.Double(startCenter.x - squareSize / 2.0, startCenter.y - squareSize / 2.0, squareSize, squareSize, angle + 90, 180, Arc2D.OPEN), true);
			path.append(new Arc2D.Double(endCenter.x - squareSize / 2.0, endCenter.y - squareSize / 2.0, squareSize, squareSize, angle - 90, 180, Arc2D.OPEN), true);
			path.closePath();
			Color color = wormholeColors[i];
			g2d.setColor(color);
			g2d.draw(path);
			g2d.setColor(new Color(color.getRGB() - 0xC0000000, true));
			g2d.fill(path);
		}
		
		g.setColor(Color.yellow);
		// Lives
		for (int i = 0; i < maze.getPacMan().getNbLives(); i++)
			g.fillOval(i * (lifeSize + 2 * lifeMargin) + lifeMargin, map.getHeight() * squareSize + lifeMargin, lifeSize, lifeSize);
	}

}
