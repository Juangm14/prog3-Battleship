package mains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.PlayerFactory;
import model.io.PlayerFile;
import model.io.PlayerRandom;
import model.ship.Board2D;

public class prueba {

	public static void main(String[] args) {
		
		
		String line = "put    Carrier    SOUTH		 3 			0";
		String[] result = line.split("\\s+");
		ArrayList<String> palabras = new ArrayList<>();
		
	    for(String palabra:result) {	
	    	if(palabra.trim().length() > 0) {
    			palabras.add(palabra);
	    	}
	    	System.out.println(palabra);
	    }
	    
    	if(palabras.get(2).equals("NORTH")) {
    		System.out.println("Hola");
    	}
	}
	
	
	if(nextToShoot == 1) {
		try {
			jugador1.nextShoot(tablero2);
			nextToShoot = 2;
			shootCounter++;
		}catch (InvalidCoordinateException | BattleshipIOException e) {
			throw new RuntimeException();
		}catch(CoordinateAlreadyHitException e) {
			System.out.println("Action by "+ jugador2.getName() + e.getMessage());
            shootCounter++;
            nextToShoot = 2;
    		return true;
		}
		return true;
	}else if(nextToShoot == 2) {
		
		try {
			nextToShoot = 1;
			jugador2.nextShoot(tablero1);
			nextToShoot = 1;
			shootCounter++;
		} catch (InvalidCoordinateException | BattleshipIOException e) {
			throw new RuntimeException();
		}catch(CoordinateAlreadyHitException e) {
			System.out.println("Action by "+ jugador2.getName() + e.getMessage());
            shootCounter++;
			nextToShoot = 1;
			return true;
		}
		return true;
	}
		
	return false;
}
        
