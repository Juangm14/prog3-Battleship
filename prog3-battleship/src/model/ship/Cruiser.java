package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Cruiser.
 */
public class Cruiser extends Ship{
	
	private static char s = 'Ø';
	private static String n = "Cruiser";

	/**
	 * Instantiates a new cruiser.
	 *
	 * @param o the o
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