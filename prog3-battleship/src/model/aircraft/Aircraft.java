package model.aircraft;

import model.Craft;
import model.Orientation;

public abstract class Aircraft extends Craft{
	/**
	 * Constructor de la aeronave
	 * @param o orientacion de la aeronave
	 * @param s symbolo de la aeronave
	 * @param n nombre de la aeronave
	 */
	public Aircraft(Orientation o, char s, String n) {
		super(o, s, n);

	}

}
