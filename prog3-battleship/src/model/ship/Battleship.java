package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Battleship.
 */
public class Battleship extends Ship{

	private static char s = 'O';
	private static String n = "Battleship";

	/**
	 * Instantiates a new battleship.
	 *
	 * @param o the o
	 */
	public Battleship(Orientation o) {
		super(o, s, n);
		
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				0, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}};
	}

}
