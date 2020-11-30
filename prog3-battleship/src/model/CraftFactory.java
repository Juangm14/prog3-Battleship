package model;

import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;

public class CraftFactory{
	
	public static Craft createCraft(String tipo, Orientation o) {
		
		switch(tipo) {
		
			case "Carrier":
				return new Carrier(o);
			case "Cruiser":
				return new Cruiser(o);
			case "Destroyer":
				return new Destroyer(o);
			case  "Battleship":
				return new Battleship(o);
			case "Bomber":
				return new Bomber(o);
			case "Fighter":
				return new Fighter(o);
			case "Transport":
				return new Transport(o);
			default:
				return null;
		}
	}
}
