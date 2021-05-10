package pacman.wormholes;

/**
 * elk object van deze klasse stelt een wormhole voor, die deel uitmaakt van de wormhole-portal grafe
 * @invar elk wormhole is altijd geassocieerd met 1 departurePortal en 1 arrivalportal
 * 	 |  getArrivalPortal()!=null && getArrivalPortal().getWormholes().contains(this) 
 * invar elke wormhole zijn ArrivalPortal heeft in zijn wormhole set dit wormhole object staan
 * 	
 * * @invar elke wormhole zijn DeparturelPortal heeft in zijn wormhole set dit wormhole object staan
 * 	| getDeparturePortal().getWormholes().contains(this) && getDeparturePortal()!=null
 */
public class Wormhole { 
	
	/**
	 * 
	 * @invar | dep!=null
	 * @invar | dep.wormholes.contains(this) 
	 * @peerObject
	 */
	DeparturePortal dep;
	
	/**
	 * 
	 * @invar | arr!=null
	 * @invar| arr.wormholes.contains(this) 
	 * 
	 * @peerObject
	 */
	ArrivalPortal arr;
	
	/**
	 * geeft het departurePortal van dit wormhole terug
	 * @basic
	 * @peerObject
	 * 
	 * @post|result!=null
	 * @creates| result
	 */
	public DeparturePortal getDeparturePortal() {
		return dep;
	}
	
	/**
	 * geeft het arrivalPortal van dit wormhole terug
	 * @basic
	 * @peerObject
	 * @post | result!=null
	 * @creates| result
	 */
	public ArrivalPortal getArrivalPortal() {
		return this.arr;
	}
	 
	/**
	 * initialiseert dit wormhole object met een meegegeven arrival en departure object
	 * @throws IllegalArgumentException | dep == null
	 * @throws IllegalArgumentException | arr == null
	 * @post | getDeparturePortal()==dep
	 * @post | getArrivalPortal()==arr
	 * @mutates | this, dep, arr
	 * @mutates_properties | this.getDeparturePortal(), this.getArrivalPortal(), arr.getWormholes(), dep.getWormholes()  // denk niet dat de eerste twee nodig zijn want je kan geen get opvragen van dit object
	 * als het nog niet is aangemaakt dus dan kan de uitkomst ook niet veranderen
	 */
	public Wormhole(DeparturePortal dep, ArrivalPortal arr) {
		if (dep == null)
			throw new IllegalArgumentException("departureportal is null");
		if (arr == null)
			throw new IllegalArgumentException("arrivalportal is null");
		this.dep=dep;
		this.arr=arr;
		arr.wormholes.add(this); 
		dep.wormholes.add(this);
		//  new
		//arr.addwormhole(this);
		//dep.addwormhole(this);
	}
	
	/**
	 * zet het departurePortal van dit wormhole object gelijk aan de meegegeven departure portal
	 * @throws IllegalArgumentException | dep == null
	 * @post | old(getDeparturePortal()).equals(dep)?
	 * 		 | getDeparturePortal()==old(getDeparturePortal())
	 * 		 |: getDeparturePortal()==dep
	 * @post | getArrivalPortal() == old(getArrivalPortal())
	 * @post | dep.getWormholes().contains(this)
	 * @mutates | 
	 * 			| 
	 * 			| this, dep
	 * 			|
	 * @mutates_properties |  this.getDeparturePortal(), dep.getWormholes()
	 * 					  
	 * 					   
	 * 					   
	 */
	public void setDeparturePortal(DeparturePortal dep) {
		if (dep == null)
			throw new IllegalArgumentException("departureportal is null");
		
		if (this.dep.equals(dep)==false) {
			// dep moet this.dep zijn
		    this.dep.wormholes.remove(this);
			//dep.removewormhole(this);;
			this.dep=dep;
			dep.wormholes.add(this);
			//dep.addwormhole(this);
			}
	}
	
	
	/**
	 * zet het arrivalPortal van dit wormhole object gelijk aan de meegegeven departure portal
	 * @throws IllegalArgumentException | arr == null
     * @post | old(getArrivalPortal()).equals(arr)?
	 * 		 | getArrivalPortal()==old(getArrivalPortal())
	 * 		 |: getArrivalPortal()==arr
	 * @post | getDeparturePortal() == old(getDeparturePortal())
	 * @post | arr.getWormholes().contains(this)
	 * @mutates | 
	 * 			| this, arr
	 * @mutates_properties | 
	 * 					   | getArrivalPortal(), arr.getWormholes()
	 */
	public void setArrivalPortal(ArrivalPortal arr) {
		if (arr == null)
			throw new IllegalArgumentException("arrivalportal is null");
		if (this.arr.equals(arr)==false) {		
			this.arr.wormholes.remove(this);
			// lijntjes indien met geneste abstracties wordt gewerkt
			//arr.removewormhole(this);
			this.arr=arr;
			arr.wormholes.add(this);}
			//arr.addwormhole(this);
	}
	
}






// 	ALLES WAT NODIG IS VOOR DE UITBREIDING NAAR GENESTE ABSTRACTIES
/*

/**
 * NEW
 * invar | dep!=null
 */
//private DeparturePortal dep;
/**
 * 	NEW
 * peerObject
 * post | result!=null
 * creates | result
 * invar | getinternaldepartureportal().getinternalwormholes().contains(this) 
 */
/*DeparturePortal getinternaldepartureportal() {
	return dep;
}*/

/**
 * NEW
 * invar | arr!=null
 */
//private ArrivalPortal arr;
/**NEW
 * post |result !=null
 * creates | result
 * peerObject
 * invar | getinternalarrivalportal().getinternalwormholes().contains(this)
 */
/*ArrivalPortal getinternalarrivalportal() {
	return arr;
}*/
