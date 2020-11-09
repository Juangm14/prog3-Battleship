package model.ship;

import model.Orientation;

public class Cruiser extends Ship{
	
	private static char s = 'Ø';
	private static String n = "Cruiser";
	/**
	 * Creacion del barco Cruiser
	 * @param o orientacion del barco
	 */
	public Cruiser(Orientation o) {
		super(o, s, n);
		
		shape = new int[][] {
			  { 0, 0, 0, 0, 0,
			    0, 0, 1, 0, 0,	
			    0, 0, 1, 0, 0,	
			    0, 0, 1, 0, 0,
			    0, 0, 0, 0, 0},
			  { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 1, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
			  { 0, 0, 0, 0, 0,
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0},
			  { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 1, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}};
	}

}