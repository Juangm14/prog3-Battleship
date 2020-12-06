package model.io;

import java.util.ArrayList;
import java.util.Random;

import model.Board;
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
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;

public class PlayerRandom implements IPlayer {

	private String name;
	private Random rnd;
	
	
	PlayerRandom(String name, long semilla){
		this.name = name;
		rnd = new Random(semilla);
		
	}
	
	public String getName() {
		return this.name + " (PlayerRandom)";
	}
	
	private int genRandomInt(int min, int max) { 
	    return rnd.nextInt(max-min) + min;
	}
	
	public Coordinate getRandomCoordinate(Board b, int offset) {
	
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
		
		if(b instanceof Board3D) {
			ArrayList<String> nomAircrafts = new ArrayList<>();
			nomAircrafts.add("Bomber");
			nomAircrafts.add("Fighter");
			nomAircrafts.add("Transport");
			
			for(int i = 0; i < nomAircrafts.size(); i++) {
				
				contador = 0;
				repite = false;
				o = Orientation.values()[genRandomInt(0,Orientation.values().length)];
						
				do{
					Craft cr = CraftFactory.createCraft(nomAircrafts.get(i), o);
					Coordinate c = this.getRandomCoordinate(b, offset);
					try {
						b.addCraft(cr, c);
						repite=false;
					} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException e) {
						repite=true;
					}
					contador++;
				}while(contador <= 100 && repite);
			}
			
		}else if(b instanceof Board2D) {
			ArrayList<String> nomCrafts = new ArrayList<>();
			nomCrafts.add("Battleship");
			nomCrafts.add("Carrier");
			nomCrafts.add("Cruiser");
			nomCrafts.add("Destroyer");
			
			for(int i = 0; i < nomCrafts.size(); i++) {
				
				contador = 0;
				repite = false;
				o = Orientation.values()[genRandomInt(0,Orientation.values().length)];
				
				do{
					Craft cr = CraftFactory.createCraft(nomCrafts.get(i), o);
					Coordinate c = this.getRandomCoordinate(b, offset);
					try {
						b.addCraft(cr, c);
						repite=false;
					} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException e) {
						repite=true;
					}
					contador++;
				}while(contador <= 100 && repite);
			}
		}

	}
	
	public Coordinate nextShoot(Board b) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		
		Coordinate c = getRandomCoordinate(b,0);

		b.hit(c);

		return c;
	}
}