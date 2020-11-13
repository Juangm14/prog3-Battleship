package model;

import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;

public class CraftFactory{
	
	public Craft createCraft(String tipo, Orientation o) {
		
		switch(tipo) {
		
			case "Carrier":
				return new Carrier(o);
			case "Cruiser":
				return new Cruiser(o);
			case "Destroyer":
				return new Destroyer(o);
			case  "Battleship":
				return new Battleship(o);
			default:
					return null;
		}
	}
}
