package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

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

public class PlayerFile implements IPlayer{

	private String nombre;
	private BufferedReader br;
	
	PlayerFile(String n, BufferedReader buff) throws NullPointerException{
		if(buff == null) {
			throw new NullPointerException();
		}else {
			nombre = n;
			br = buff;
		}
	}
	
	public String getName() {
		return nombre + " (PlayerFile)"; 
	}
	
	public void putCrafts(Board b) throws BattleshipIOException{
		
		if(b == null) {
			throw new NullPointerException();
		}else{
			String line="";
			Orientation o = null;
			
			try {
				while(br.readLine() != null){
					line= br.readLine();
					String[] result = line.split(" ");
					ArrayList<String> palabras = new ArrayList<>();
					 
				    for(String palabra:result) {	
				    	if(!palabra.isEmpty()) {
				    		palabra = palabra.replace("\n", "");
				    		palabras.add(palabra);
				    	}
				    }
				    
				    Boolean es2D = false;
				    String nombre = null;
				    
					if(palabras.get(0).equals("put")) {
						
						switch(palabras.get(1)) {
					      case "Cruiser": 
					    	  es2D = true;
					    	  nombre = palabras.get(1);
					    	  break;
					      case "Destroyer":
					    	  es2D = true;
					    	  nombre = palabras.get(1);
					    	  break;
					       case "Carrier":
					    	   es2D = true;
					    	   nombre = palabras.get(1);
					    	  break;
					      case "Battleship":
					    	  es2D = true;
					    	  nombre = palabras.get(1);
					    	  break;
					      case "Bomber":
					    	  nombre = palabras.get(1);
					    	  break;
					      case "Aircraft":
					    	  nombre = palabras.get(1);
					    	  break;
					      case "Fighter":
					    	  nombre = palabras.get(1);
					    	  break;
					      case "Transport":
					    	  nombre = palabras.get(1);
					    	  break;
					      default:
					    	  break;
						}
						
						switch(palabras.get(2)) {
					      case "NORTH": 
					    	  o = Orientation.NORTH;
					    	  break;
					      case "SOUTH":
					    	  o = Orientation.SOUTH;
					    	  break;
					       case "EAST":
					    	  o = Orientation.EAST;
					    	  break;
					      case "WEST":
					    	  o = Orientation.WEST;
					    	  break;
					      default:
					    	  throw new BattleshipIOException("Error: La orientacion no es la esperada.");
					    		  
						}
						
						int c0 = 0;
						int c1 = 0;
						int c2 = 0;
						Coordinate c = null;
						Boolean isNumeric = true;
						
						if(palabras.size() < 4 || palabras.size() > 6) {
							throw new BattleshipIOException("La cantidad de parametros es incorrecta");
						}else if(es2D == true && b instanceof Board2D && palabras.size() == 5) {
							try {
				    			c0 = Integer.parseInt(palabras.get(3));
				    			c1 = Integer.parseInt(palabras.get(4));
							}catch(NumberFormatException e) {
								isNumeric = false;
							}
							
							if(isNumeric == true) {
								c = CoordinateFactory.createCoordinate(c0,c1);
							}else {
								throw new BattleshipIOException("La cooodenada no es un numero");
							}
							
							
						}else if(es2D == false && b instanceof Board3D && palabras.size() == 6){
							try {
				    			c0 = Integer.parseInt(palabras.get(3));
				    			c1 = Integer.parseInt(palabras.get(4));
				    			c2 = Integer.parseInt(palabras.get(5));
							}catch(NumberFormatException e) {
								isNumeric = false;
							}
							
							if(isNumeric == true) {
								c = CoordinateFactory.createCoordinate(c0,c1,c2);
							}else {
								throw new BattleshipIOException("La cooodenada no es un numero");
							}
							
						}
						
						Craft barco = CraftFactory.createCraft(nombre,o);
						

						b.addCraft(barco, c);

						
					}else if(palabras.get(0).equals("endput") || palabras.get(0).equals("exit")){
						break;
					}else {
						throw new BattleshipIOException("Comando diferente a put, endput o exit");
					}
				}
			} catch (IllegalArgumentException | InvalidCoordinateException | OccupiedCoordinateException
					| NextToAnotherCraftException | IOException | BattleshipIOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public Coordinate nextShoot(Board b) throws BattleshipIOException{
		
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
				    
				    if(palabras.get(0).equals("shoot")) {
						
				    	int c0 = 0;
						int c1 = 0;
						int c2 = 0;
						Coordinate c = null;
						Boolean isNumeric = true;
						
				    	if(palabras.size() < 3 || palabras.size() < 4) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}	
}
















