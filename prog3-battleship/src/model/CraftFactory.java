package model;

import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Craft objects.
 */
public class CraftFactory{
	
	/**
	 * Creates a new Craft object.
	 *
	 * @param tipo the tipo
	 * @param o the o
	 * @return the craft
	 */
	public static Craft createCraft(String tipo, Orientation o) {
		
		Class<? extends String> reflectCraft = tipo.getClass();

		String craftName = reflectCraft.getName();

		
		
		
		return null;
		

		/*switch(tipo) {
		
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
		}*/
	}
}
