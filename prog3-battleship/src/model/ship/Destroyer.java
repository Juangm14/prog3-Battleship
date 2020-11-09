package model.ship;

import model.Orientation;

public class Destroyer extends Ship{

	private static char s = 'Ω';
	private static String n = "Destroyer";
	
	/**
	 * Creacion del barco Destroyer
	 * @param o orientacion del barco
	 */
	public Destroyer(Orientation o) {
		super(o, s, n);
	
	shape = new int[][] {
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0,	
	    	0, 1, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 0, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
	   		0, 0, 0, 0, 0,	
	   		0, 1, 1, 0, 0,	
	    	0, 0, 0, 0, 0,
	    	0, 0, 0, 0, 0}};
	}

}
