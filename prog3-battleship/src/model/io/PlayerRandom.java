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
	
	
	public void putCrafts (Board b) throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		
		Orientation o = null;
		
		
		
	}
	
	public Coordinate nextShoot(Board b) throws InvalidCoordinateException, IllegalArgumentException, CoordinateAlreadyHitException  {
		
		Coordinate c = getRandomCoordinate(b,0);
		
		b.hit(c);

		return c;
	}
}




















