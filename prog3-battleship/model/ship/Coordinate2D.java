package model.ship;
import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

public class Coordinate2D extends Coordinate{

	/**
	 * Constructor de la coordenada 2D
	 * @param x primera posicion de coordenada
	 * @param y segunda posicion de coordenada
	 */
	public Coordinate2D(int x, int y){
		super(2);
		set(0,x);
		set(1,y);
	}
	/**
	 * Costructor de la coordenada 2D
	 * @param c coordenada que vamos a crear
	 */
	public Coordinate2D(Coordinate2D c) {
		super(c);
	}
	
	public Coordinate2D copy() {
		return new Coordinate2D(this);
	}
	
	public Set<Coordinate> adjacentCoordinates() {
		
		Set<Coordinate> adyacentes = new HashSet<Coordinate>();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				else {
					adyacentes.add(CoordinateFactory.createCoordinate(i + this.get(0), j + this.get(1)));
				}
			}
		}

		return adyacentes;
	}
	
	public final String toString(){
		   
		String concatenation = "";
		   

		concatenation += "(";
		concatenation += this.get(0);
		concatenation += ", ";
		concatenation += this.get(1);
		concatenation += ")";
			
		return concatenation;
	}



}
