package model.io;

import java.util.ArrayList;
import java.util.Random;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;

public class PlayerRandom implements IPlayer {

	private String name;
	private Random rnd;
	
	
	PlayerRandom(String name, long semilla){
		this.name = name;
		rnd = new Random(semilla);
		
	}
	
	public String getName() {
		return this.name;
	}
	
	private int genRandomInt(int min, int max) { 
	    return rnd.nextInt(max-min) + min;
	}
	
	public Coordinate getRandomCoordinate(Board b, int offset) {
		
		int c0 = 0;
		int c1 = 0;
		int c2 = 0;
		
		Coordinate c = CoordinateFactory.createCoordinate(c0,c1);
		
		if(b.checkCoordinate(c)) {
			c0 = genRandomInt(0-offset, b.getSize());
			c1 = genRandomInt(0-offset, b.getSize());
			c2 = genRandomInt(0-offset, b.getSize());
			c = CoordinateFactory.createCoordinate(c0,c1,c2);
		}else {
			c0 = genRandomInt(0-offset, b.getSize());
			c1 = genRandomInt(0-offset, b.getSize());
			c =  CoordinateFactory.createCoordinate(c0,c1);
		}
		
		return c;
	}
	
	
	public void putCrafts(Board b) {
		
		Orientation o = null;
		int offset = Craft.BOUNDING_SQUARE_SIZE;
		ArrayList<String> nombres = new ArrayList<>();
		ArrayList<Craft> crafts = new ArrayList<>();
		
		nombres.add("Battleship");
		nombres.add("Carrier");
		nombres.add("Cruiser");
		nombres.add("Destroyer");
		nombres.add("Bomber");
		nombres.add("Fighter");
		nombres.add("Transport");
			
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
			
			b.addCraft(crafts.get(i), getRandomCoordinate());
		}
	}
}




















