package model.ship;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

public class Board2D extends Board {
	
    /**
	 * Constructor del tablero
	 * @param size tamaño del tablero size * size
	 */
	public Board2D(int size) {
		
		super(size);
	}
	/**
	 * Comprueba si una coordenada dentro del tablero
	 * @param c coordenada
	 * @return true si esta dentro del tablero y false en caso contrario.
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
	 * 
	 * @param unveil si es true devolvemos el tablero se muestra el tablero completo y false como lo veria el adversario
	 * @return cadena de caracteres representando el tablero
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