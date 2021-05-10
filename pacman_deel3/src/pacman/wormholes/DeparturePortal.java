package pacman.wormholes;
import java.util.HashSet;

import logicalcollections.LogicalSet;
import pacman_3.Square;

import java.util.Set;

/**
 * elk object van deze klasse stelt een DeparturePortal voor dat deel kan zijn van de portal-wormhole grafe
 * @invar elk departureportal heeft altijd 1 square
 * 	 | getSquare()!=null
 * @invar elk departureportal zijn wormholeset is niet null en gelinkt aan 0, 1 of meer wormholes die ook niet null, die wormholes hun departureportal is dan gelijk aan dit departureportal object
 * 	 |getWormholes() !=null
 * @invar | getWormholes().stream().allMatch(w-> w!=null && w.getDeparturePortal()==this )
 * invar| getWormholes()==null || getWormholes().stream().allMatch(w->w.getDeparturePortal()==this)
 */
public class DeparturePortal {

	/**
	 * @invar | square != null
	 */
	private Square square;	
	
	/**
	 * @invar | true 
	 * @invar | wormholes !=null
	 * @invar |wormholes.stream().allMatch(w->
	 * 			| w!=null && w.dep==this)
	 * @peerObjects
	 * @representationObject
	 * representationObjects
	 */
	Set<Wormhole> wormholes = new HashSet<Wormhole>();

	/**
	 * geeft het square terug van dit departureportal object
	 * @basic
	 */
	public Square getSquare() {
		return square; //kopie nemen? Wie is de client?
	}
	
	/**
	 * constructor die dit departureportal object initialiseert
	 * @throws IllegalArgumentException | square==null
	 * @post | getSquare()==square
	 * @mutates | this
	 * @post | getWormholes().isEmpty()
	 */
	public DeparturePortal(Square square){
		if (square == null&& square.isPassable()!=true)
			throw new IllegalArgumentException("square is null");
		this.square=square; //kopie? klant?)
	}
 	
	/**
	 * geeft de set van wormholes terug waar dit departure object op dit moment bij hoort
	 * @creates | result
	 * @basic
	 * peerObjects
	 * post | getWormholes()==old(getWormholes())
	 * post | getSquare()==old(getSquare())
	 * NEW postconditie nodig voor eventueel  geneste abstracties
	 * post| result.stream().allMatch(w->w!=null)
	 * post| result.stream().allMatch(w->w.getDeparturePortal().getSquare().equals(this.getSquare()))
	 */ 
	public Set<Wormhole> getWormholes() {
		return Set.copyOf(wormholes);
	}

}




/**
 * NEW VOOR GENESTE ABSRACTIE
 * invar| wormholes !=null
 * invar | wormholes.stream().allMatch(w->w!=null)
 * representationObject
 */
//private HashSet<Wormhole> wormholes= new HashSet<Wormhole>();

/**
 * 	NEW VOOR GENESTE ABSTRACTIE
 *peerObjects
 * 
 * post | result!=null
 * post| result.stream().allMatch(s->s!=null)
 * invar | getinternalwormholes().stream().allMatch(s->s.getDeparturePortal()==this)
 */
/*Set<Wormhole> getinternalwormholes(){
	return Set.copyOf(wormholes);
}*/

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
 * hrows IllegalArgumentException| wormhole==null
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