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
		
		/*BUENISIMOOOOO PARA PURCRAFTS CON EL TRIM();
		 String ruta = "test/files/testPutCraftsWithSpaces.in";
		FileReader f = null;
		try {
			f = new FileReader(ruta);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(f);
		String line;
		ArrayList<String> palabras = new ArrayList<>();
		
		try {
			while((line = br.readLine()) != null) {

				System.out.println(line);
				
				String result[] = line.split(" ");
				
			    for(String palabra:result) {	
			    	if(palabra.trim().length() > 0) {
			    		palabras.add(palabra);
			    	}
			    } 
			    
			    for(String palabra:palabras) {
			    	 System.out.println("Result: " + palabra);
			    }
			    

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    
	    
	}
}
		
		/* SHOOOTTTT
		 if(b == null) {
			throw new NullPointerException();
		}else {
			String line;
			
			try {
				if(br.readLine() != null) {
					
					line = br.readLine();
					String[] result = line.split(" ");
					ArrayList<String> palabras = new ArrayList<>();
					
				    for(String palabra:result) {	
				    	if(!palabra.isEmpty()) {
				    		palabra = palabra.replace("\n", "");
				    		palabras.add(palabra);
				    	}
				    }
				    
				    (palabras.get(0).equals("shoot")) {
						
				    	int c0 = 0;
						int c1 = 0;
						int c2 = 0;
						Coordinate c = null;
						Boolean isNumeric = true;
						
				    	if(palabras.size() < 4) {
				    		throw new BattleshipIOException("La cantidad de parametros es incorrecta.");
				    	}else if(palabras.size() == 3) {
				    		
				    		try {
				    			c0 = Integer.parseInt(palabras.get(1));
				    			c1 = Integer.parseInt(palabras.get(2));
				    		}catch(NumberFormatException e) {
				    			isNumeric = false;
				    		}
				    		
				    		if(isNumeric) {
				    			c = CoordinateFactory.createCoordinate(c0,c1);
				    		}else {
				    			throw new BattleshipIOException("Las coordenadas no son parametros numericos.");
				    		}
				    	}else if(palabras.size() == 4) {
				    		
				    		try {
				    			c0 = Integer.parseInt(palabras.get(2));
				    			c1 = Integer.parseInt(palabras.get(2));
				    			c2 = Integer.parseInt(palabras.get(2));
				    		}catch(NumberFormatException e){
				    			isNumeric = false;
				    		}
				    		
				    		if(isNumeric) {
				    			c = CoordinateFactory.createCoordinate(c0,c1,c2);
				    		}else {
				    			throw new BattleshipIOException("Las coordenadas no son parametros numericos.");
				    		}
				    	}
				    	
				    	b.hit(c);
				    	
				    	return c;
				    }else if(palabras.get(0).equals("exit")) {
				    	return null;
				    }else {
				    	throw new BattleshipIOException("Comando disferente a shoot, exit");
				    }
				}
			} catch (IllegalArgumentException | InvalidCoordinateException | CoordinateAlreadyHitException | IOException
					| BattleshipIOException e) {
				
			}
		}
		return null;*/
        
