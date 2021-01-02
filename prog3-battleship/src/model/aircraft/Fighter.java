package model.aircraft;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Fighter.
 */
public class Fighter extends Aircraft{

	private static char s = '⇄';
	private static String n = "Fighter";
	
	/**
	 * Instantiates a new fighter.
	 *
	 * @param o the o
	 */
	public Fighter(Orientation o) {
		super(o, s, n);
	

	shape = new int[][] {
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
	    	0, 1, 1, 1, 0,	
	    	0, 0, 1, 0, 0,
	    	0, 0, 1, 0, 0},
	      { 0, 0, 0, 0, 0,
	    	0, 0, 1, 0, 0,	
			1, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0},
	      { 0, 0, 1, 0, 0,
			0, 0, 1, 0, 0,	
			0, 1, 1, 1, 0,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0},
	      { 0, 0, 0, 0, 0,
			0, 0, 1, 0, 0,	
			0, 1, 1, 1, 1,	
			0, 0, 1, 0, 0,
			0, 0, 0, 0, 0}}; 
		
	}
	
	public int getValue() {
		return 10;
	}
}
