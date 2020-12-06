package model.aircraft;

import model.Orientation;

public class Transport extends Aircraft{

	private static char s = '⇋';
	private static String n = "Transport";
	/**
	 * Ceamos otro tipo de aeronave
	 * @param o orientacion 
	 */
	public Transport(Orientation o) {
		super(o, s, n);
		
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 0, 1, 0, 0, 0,
				0, 0, 1, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 1, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
				1, 0, 1, 0, 1,	
				0, 1, 1, 1, 0,	
				0, 0, 1, 0, 0,
				0, 0, 1, 0, 0},
		      { 0, 0, 0, 1, 0,
				0, 0, 1, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 0, 0, 1, 0}};
	}

	
}
