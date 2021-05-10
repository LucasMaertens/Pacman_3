package pacman.wormholes;
import java.util.HashSet;
import pacman_3.Square;

import java.util.Set;

/**
 * elk object van deze klasse stelt een ArrivalPortal voor dat deel kan zijn van de portal-wormhole grafe
 * @invar elk arrivalportal heeft altijd 1 square
 * 	 | getSquare()!=null
 * @invar elk arrivalportal zijn wormholeset is niet null en gelinkt aan 0, 1 of meer wormholes die zelf weer niet null zijn, die wormholes hun arrivalportal is dan gelijk aan dit arrivalportal object
 * 	|getWormholes() !=null && getWormholes().stream().allMatch(w->
 * 			 |w!=null && w.getArrivalPortal()==this)
 * 
 */
public class ArrivalPortal {

	/**  
	 * @invar | square != null
	 */
	private  Square square;
	 
	/**
	 * 
	 *@invar | wormholes !=null && wormholes.stream().allMatch(w->
 	 * 	| w!=null && w.arr==this )
	 * @peerObjects
	 * @representationObject
	 * representationObjects // BESTAAT DIT? je kan niet en representatiobjects en peerobjets zijn
	 */
	HashSet<Wormhole> wormholes = new HashSet<Wormhole>();
	
	/**
	 * geeft het square terug van dit ArrivalPortal object
	 * @basic
	 */
	public Square getSquare() {
		return square; //square is immutable, dus volgens mij geen kopie nemen
		
	}
	
	/**
	 * constructor die dit arrivalportal object initialiseert
	 * @throws IllegalArgumentException | square==null
	 * @post | getSquare().equals(square)
	 * @mutates | this
	 * post | getWormholes().isEmpty()
	 */
	public ArrivalPortal(Square square){
		if (square == null && square.isPassable()!=true) // MOET DEZE ISPASSABLE WEL? EN INDIEN JA MOET DAN NIET FALSE ZIJN?
			throw new IllegalArgumentException("square is null");
		this.square=square;
	}
	 
	/**
	 * geeft de set van wormholes terug waar dit arrival object op dit moment bij hoort
	 * creates | result
	 * basic
	 * peerObjects
	 * post | getWormholes()==old(getWormholes())
	 * post | getSquare().equals(old(getSquare()))
	 * post | result.stream().allMatch(s->s!=null)
	 */
	public Set<Wormhole> getWormholes() {
		return Set.copyOf(wormholes);
	}
	
}







/**
// * NEW VOOR GENESTE ABSRACTIE
 //* @invar| wormholes !=null
 //* @invar | wormholes.stream().allMatch(w->w!=null)
 //* @representationObject
 //*/
//private HashSet<Wormhole> wormholes= new HashSet<Wormhole>();

/**
 * 	NEW VOOR GENESTE ABSTRACTIE
 *peerObjects
 * 
 * post | result!=null
 * post| result.stream().allMatch(s->s!=null)
 * invar | getinternalwormholes().stream().allMatch(s->s.getArrivalPortal()==this)
 */
/*Set<Wormhole> getinternalwormholes(){
	return Set.copyOf(wormholes);*/



/**
 * 	NEW
 * throws IllegalArgumentException| wormhole==null
 * throws IllegalArgumentException| getinternalwormholes().contains(wormhole)==false
 * mutates| this
 * mutates_properties | getinternalwormholes()
 * post| getinternalwormholes().equals(LogicalSet.minus(old(getinternalwormholes()),wormhole))
 * param wormhole
 */
/*void removewormhole(Wormhole wormhole) {
	if(wormhole==null) {
		throw new IllegalArgumentException("can't be null");}
	if(getWormholes().contains(wormhole)==false) {
		throw new IllegalArgumentException("not in list");}

	wormholes.remove(wormhole);
	
	}*/
/**
 * NEW
 * param wormhole
 * throws IllegalArgumentException| wormhole==null
 *throws IllegalArgumentException| getinternalwormholes().contains(wormhole)
 * mutates| this
 * mutates_properties | getinternalwormholes()
 */
/*void addwormhole(Wormhole wormhole) {
	if(wormhole==null) {
		throw new IllegalArgumentException("can't be null");}
	if(getWormholes().contains(wormhole)==true) {
		throw new IllegalArgumentException("ALREADY in list");}

	wormholes.add(wormhole);
}*/
