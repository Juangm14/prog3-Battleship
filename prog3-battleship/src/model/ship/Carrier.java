package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Carrier.
 */
public class Carrier extends Ship{

	private static char s = '®';
	private static String n = "Carrier";

	/**
	 * Instantiates a new carrier.
	 *
	 * @param o the o
	 */
	public Carrier(Orientation o) {
		super(o, s, n);
		
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
		        0, 0, 0, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,
				0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}};
	}

}
