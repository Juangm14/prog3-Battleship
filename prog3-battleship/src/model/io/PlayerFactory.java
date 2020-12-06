package model.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import model.Board;
import model.Coordinate;
import model.exceptions.io.BattleshipIOException;

public class PlayerFactory {
	
	private static Boolean isLong(String s){
		try {
			Long.parseLong(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static IPlayer createPlayer(String name, String tipo) throws BattleshipIOException{
		
		String a = "\\";
				
		if(tipo.contains(".") || tipo.contains("/") || tipo.contains(a.substring(0))) {
			FileReader reader;
			try {
				reader = new FileReader(tipo);
			} catch (FileNotFoundException e) {
				throw new BattleshipIOException("Error: El archivo no se encuentra.");
			}
			BufferedReader br = new BufferedReader(reader);
			return new PlayerFile(name, br);
		}else if(isLong(tipo)){
			return new PlayerRandom(name, Long.parseLong(tipo));
		}
		
		return null;
	}

}
