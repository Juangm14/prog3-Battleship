package model.aircraft;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

public  class Board3D extends Board{
	/**
	 * Construrctor del tablero3D
	 * @param size tamaño del tablero 3D
	 */
	public Board3D(int size) {	
		super(size);
	}

	@Override
	public boolean checkCoordinate(Coordinate c) throws IllegalArgumentException{
        
		if(!(c instanceof Coordinate3D)){
			throw new IllegalArgumentException();
		}else if((c.get(0) < this.getSize() &&  c.get(0) >= 0) && (c.get(1) >= 0 && c.get(1) < this.getSize())
				&& (c.get(2) < this.getSize() &&  c.get(2) >= 0)) {
		
			return true;
		}
		
		return false;
	}

	@Override
	public String show(boolean unveil) {

		String mapa = "";
		
		if(unveil) {
			for(int i = 0; i < this.getSize(); i++) {
				
				if(i != 0) {
					mapa += "\n";
				}
				
				for(int j = 0; j < this.getSize(); j++) {
					for(int k = 0; k < this.getSize(); k++) {

						Coordinate c = CoordinateFactory.createCoordinate(k, i, j);
						Craft ship = this.getCraft(c);
						
						if(ship != null) {
							if(isSeen(CoordinateFactory.createCoordinate(k, i, j))) {
								if(ship.isHit(CoordinateFactory.createCoordinate(k, i, j))) {
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
						
						if(k == this.getSize() - 1 && j != this.getSize() - 1) {
							mapa += '|';
						}
					}
				}
			}
		}else {
			for(int i = 0; i < this.getSize(); i++){
				if(i != 0) {
					mapa += "\n";
				}
				for(int j = 0; j < this.getSize(); j++) {
					for(int k = 0; k < this.getSize(); k++) {
						Coordinate c = CoordinateFactory.createCoordinate(k, i, j);
						Craft ship = this.getCraft(c);
						
						if(isSeen(CoordinateFactory.createCoordinate(k, i, j))) {
							if(ship != null) {
								if(ship.isShotDown()) {
									mapa += ship.getSymbol();
								}else if(ship.isHit(CoordinateFactory.createCoordinate(k, i, j))) {
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
						
						if(k == this.getSize() - 1 && j != this.getSize() - 1) {
							mapa += '|';
						}
					}
				}
			}
		}
		
		return mapa;
	}
	
}
