package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Destroyer.
 */
public class Destroyer extends Ship{

	private static char s = 'Ω';
	private static String n = "Destroyer";
	
	/**
	 * Instantiates a new destroyer.
	 *
	 * @param o the o
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
	
	public int getValue() {
		return 3;
	}

}
