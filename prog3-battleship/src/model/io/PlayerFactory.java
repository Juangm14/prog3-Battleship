package model.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PlayerFactory implements IPlayer{
	
	private static Boolean isLong(String s){
		try {
			Long.parseLong(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static IPlayer createPlayer(String name, String tipo) throws FileNotFoundException{
		
		String a = "\\";
				
		if(tipo.contains(".") || tipo.contains("/") || tipo.contains(a.substring(0))) {
			FileReader reader = new FileReader(tipo);
			BufferedReader br = new BufferedReader(reader);
			return new PlayerFile(name, br);
		}else if(isLong(tipo)){
			return new PlayerRandom(name, Long.parseLong(tipo));
		}else {
			return null;
		}
	}
}
