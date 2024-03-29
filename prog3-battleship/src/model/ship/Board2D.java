package model.ship;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

// TODO: Auto-generated Javadoc
/**
 * The Class Board2D.
 */
public class Board2D extends Board {
	
	/**
	 * Instantiates a new board 2 D.
	 *
	 * @param size the size
	 */
	public Board2D(int size) {
		
		super(size);
	}
	
	/**
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	@Override
	public boolean checkCoordinate(Coordinate c) throws IllegalArgumentException{
		
		if(!(c instanceof Coordinate2D)){
			throw new IllegalArgumentException();
		}else if((c.get(0) < this.getSize() &&  c.get(0) >= 0) && (c.get(1) >= 0 && c.get(1) < this.getSize())) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Show.
	 *
	 * @param unveil the unveil
	 * @return the string
	 */
	@Override
	public String show(boolean unveil) {
		
		String mapa = "";
		
		if(unveil) {
			for(int i = 0; i < this.getSize(); i++){
				if(i != 0) {
					mapa += "\n";
				}
				for(int j = 0; j < this.getSize(); j++) {
					
					Coordinate c = CoordinateFactory.createCoordinate(j,i);
					Craft ship = this.getCraft(c);
					
					if(ship != null) {
						if(isSeen(CoordinateFactory.createCoordinate(j, i))) {
							if(ship.isHit(CoordinateFactory.createCoordinate(j, i))) {
								mapa += HIT_SYMBOL;
							}else {
								mapa += WATER_SYMBOL;
							}
						}else {
							mapa += ship.getSymbol();
						}
				
					}else {
						mapa += WATER_SYMBOL;
					}
				}
			}
		}else {
			for(int i = 0; i < this.getSize(); i++){
				if(i != 0) {
					mapa += "\n";
				}
				for(int j = 0; j < this.getSize(); j++) {
					
					Coordinate c = CoordinateFactory.createCoordinate(j,i);
					Craft ship = this.getCraft(c);
					
					if(isSeen(CoordinateFactory.createCoordinate(j, i))) {
						if(ship != null) {
							if(ship.isShotDown()) {
								mapa += ship.getSymbol();
							}else if(ship.isHit(CoordinateFactory.createCoordinate(j, i))) {
								mapa += HIT_SYMBOL;
							}else {
								mapa += WATER_SYMBOL;
							}
						}else {
							mapa += WATER_SYMBOL;
						}
				
					}else {
						mapa += NOTSEEN_SYMBOL;
					}
				}
			}
		}
		return mapa;
	}
}