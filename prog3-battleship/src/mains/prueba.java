package mains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IllegalCoordinateException;
import model.io.PlayerFile;
import model.io.PlayerRandom;
import model.ship.Board2D;

public class prueba {

	public static void main(String[] args) {
		
		Orientation o = null;
		int offset = Craft.BOUNDING_SQUARE_SIZE;
		ArrayList<String> nombres = new ArrayList<>();
		ArrayList<Craft> crafts = new ArrayList<>();
		
		if(b instanceof Board2D) {
			nombres.add("Battleship");
			nombres.add("Carrier");
			nombres.add("Cruiser");
			nombres.add("Destroyer");
		}else if(b instanceof Board3D) {
			nombres.add("Bomber");
			nombres.add("Fighter");
			nombres.add("Transport");
		}
			
		for(int i = 0; i < nombres.size(); i++) {
			
			int pos = genRandomInt(0,4);
			
			switch(pos) {
				case 0:
					o = Orientation.NORTH;
				case 1:
					o = Orientation.EAST;
				case 2:
					o = Orientation.SOUTH;
				case 3:
					o = Orientation.WEST;
			}
			
			crafts.add(CraftFactory.createCraft(nombres.get(i), o));
		}
		
		for(int i = 0; i < nombres.size(); i++) {
			
			Boolean notIn = false;
			int maxCoordAleatorias = 0;
			
			do {
				try {
					Coordinate c = getRandomCoordinate(b,offset);
					b.checkCoordinate(c);
					try {
						b.addCraft(crafts.get(i), c);
					} catch (OccupiedCoordinateException  | NextToAnotherCraftException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					notIn = false;
				}catch(InvalidCoordinateException | IllegalCoordinateException e) {
					notIn = true;
					maxCoordAleatorias++;
				}
			}while(notIn && maxCoordAleatorias == 100);
		}
	}
        
}