package pacman_3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
	private Wormhole[] wormholes=new Wormhole[0];
	private ArrivalPortal[] arrivalPortals;
	private DeparturePortal[] departurePortals ;
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, ArrivalPortal[] arrivalPortals,DeparturePortal[] departurePortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan; 
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.arrivalPortals=arrivalPortals.clone();
		this.departurePortals=departurePortals.clone(); 
	}
	/**
	 *  
	 *  voegt een wormhole toe door een nieuwelijst te maken met +1 lengte
	 */
	public void addWormhole(Wormhole wormhole) {
		
		if(new HashSet<>(Arrays.asList(arrivalPortals)).contains((wormhole.getArrivalPortal()))==false) {
			throw new IllegalArgumentException("arrival port of wormhole is not in the list of arrivalports");
		}
		if(new HashSet<>(Arrays.asList(departurePortals)).contains((wormhole.getDeparturePortal()))==false) {
			throw new IllegalArgumentException("departure port of wormhole is not in the list of departureportals");
		}
		Wormhole[] oudelijst= wormholes.clone();
		Wormhole[] nieuwelijst= new Wormhole[oudelijst.length+1];
		for(int i=0;i<oudelijst.length;i++) { 
			nieuwelijst[i]=oudelijst[i];
			  
		}
		nieuwelijst[nieuwelijst.length-1]=wormhole;
		wormholes=nieuwelijst;
		
	}
	
	public ArrivalPortal[]  getArrivalPortals(){
	return Arrays.copyOf(arrivalPortals,arrivalPortals.length); // nog niet zeker of deze lengte de juist is mss -1?
	}
	public DeparturePortal[]  getDeparturePortals(){
		return Arrays.copyOf(departurePortals,departurePortals.length);
	}
	public Wormhole[] getWormholes() { 
		return  Arrays.copyOf(wormholes, wormholes.length);
		
	}
	public boolean isCompleted() {
		return foodItems.length == 0;
	}
	
	private void checkPacManDamage() { 
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	public void pacManAtePowerPellet() {
		for (Ghost ghost : ghosts)
			ghost.pacManAtePowerPellet();
	}
	
	private void removeFoodItemsAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}
	
	private void checkFoodItemCollision(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].eatenByPacMan(this);
				removeFoodItemsAtIndex(i);
				return;
			}
		}
	}
	// NOG NIET ZEKER OF DEZE JUIST WERKT
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		// zodat hij ook de de ghost raakt bij  departure gat.
		// dit moet omdat er gevraagd wordt om direct te verplaatsen naar het aankomstpunt van de womrhole dus de square
		// moet direct aangepast worden naar de arrival square en niet eerst nog naar de departure q
		for(int i=0;i<ghosts.length;i++) {
			if (ghosts[i].getSquare().equals(newSquare)){
				ghosts[i].hitBy(pacMan);
			}
		}
		if (newSquare.isPassable()) {
			
			for(int i=0;i<departurePortals.length;i++) {
				if (departurePortals[i].getSquare().equals(newSquare)&& departurePortals[i].getWormholes().size()>=1){
					
					Wormhole werkworm=(Wormhole) departurePortals[i].getWormholes().toArray()[random.nextInt(departurePortals[i].getWormholes().size())];
					
					newSquare=werkworm.getArrivalPortal().getSquare();
				}
			}
			
			pacMan.setSquare(newSquare);
			checkFoodItemCollision(newSquare);
			checkPacManDamage();
		}
	}
	
}
