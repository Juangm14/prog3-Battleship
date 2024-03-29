package model.io;

import java.util.ArrayList;
import java.util.Random;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.ship.Board2D;

public class PlayerRandom implements IPlayer {
	
	private String name;
	private Random rnd;
	private CellStatus lastShotStatus;
	
	
	PlayerRandom(String name, long semilla){
		this.name = name;
		rnd = new Random(semilla);
		lastShotStatus = null;
	}
	
	public String getName() {
		return this.name + " (PlayerRandom)";
	}
	
	private int genRandomInt(int min, int max) { 
	    return rnd.nextInt(max-min) + min;
	}
	
	public Coordinate genRandomCoordinate(Board b, int offset) {
	
		if(b instanceof Board3D) {
			int c0 = genRandomInt(0-offset, b.getSize());
			int c1 = genRandomInt(0-offset, b.getSize());
			int c2 = genRandomInt(0-offset, b.getSize());
			Coordinate c = CoordinateFactory.createCoordinate(c0,c1,c2);
			return c;
		}else if(b instanceof Board2D){
			int c0 = genRandomInt(0-offset, b.getSize());
			int c1 = genRandomInt(0-offset, b.getSize());
			Coordinate c =  CoordinateFactory.createCoordinate(c0,c1);
			return c;
		}
		
		return null;
	}
	
	
	public void putCrafts (Board b) {
		
		Orientation o = null;
		int contador = 0;
		int offset = Craft.BOUNDING_SQUARE_SIZE;
		Boolean repite = false;
		
		if(b == null) {
			throw new NullPointerException();
		}
		
		if(b instanceof Board3D) {
			ArrayList<String> nomAircrafts = new ArrayList<>();
			nomAircrafts.add("ship.Battleship");
			nomAircrafts.add("ship.Carrier");
			nomAircrafts.add("ship.Cruiser");
			nomAircrafts.add("ship.Destroyer");
			nomAircrafts.add("aircraft.Bomber");
			nomAircrafts.add("aircraft.Fighter");
			nomAircrafts.add("aircraft.Transport");
			
			for(int i = 0; i < nomAircrafts.size(); i++) {
				contador = 0;
				repite = false;
				o = Orientation.values()[genRandomInt(0,Orientation.values().length)];
						
				Craft cr = CraftFactory.createCraft(nomAircrafts.get(i), o);
				
				do{
					
					Coordinate c = this.genRandomCoordinate(b, offset);
						
					try {
						b.addCraft(cr, c);
						repite = false;
					} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException e) {
						contador++;
						repite = true;
					}
				}while(contador != 100 && repite);
			}
			
		}else if(b instanceof Board2D) {
			ArrayList<String> nomCrafts = new ArrayList<>();
			nomCrafts.add("ship.Battleship");
			nomCrafts.add("ship.Carrier");
			nomCrafts.add("ship.Cruiser");
			nomCrafts.add("ship.Destroyer");
			
			for(int i = 0; i < nomCrafts.size(); i++) {
				
				contador = 0;
				repite = false;
				o = Orientation.values()[genRandomInt(0,Orientation.values().length)];
				
				do{
					Craft cr = CraftFactory.createCraft(nomCrafts.get(i), o);
					Coordinate c = this.genRandomCoordinate(b, offset);
					try {
						b.addCraft(cr, c);
						repite=false;
					} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException e) {
						repite=true;
					}
					contador++;
				}while(contador != 100 && repite);
			}
		}
	}
	
	public Coordinate nextShoot(Board b) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		
		Coordinate c = genRandomCoordinate(b,0);
		
		lastShotStatus = b.hit(c);
		
		return c;
	}

	@Override
	public CellStatus getLastShotStatus() {
		
		return lastShotStatus;
	}
}