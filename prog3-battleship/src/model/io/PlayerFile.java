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
	
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException{
		try {
			if(b == null) {
				throw new NullPointerException();
			}else {
				String line = "";
				Orientation o;
				try {
					while(br.readLine() != null) {
	
						line= br.readLine();
						
						String[] result = line.split(" ");
						ArrayList<String> palabras = new ArrayList<>();
						 
					    for(String palabra:result) {	
					    	if(!palabra.isEmpty()) {
					    		palabra = palabra.replace("\n", "");
					    		palabras.add(palabra);
					    	}
					    }
					    
					    if(palabras.get(0).equals("put")) {
					    	
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
							
						    if(b instanceof Board3D) {
						    	if(palabras.size() < 6 || palabras.size() > 6) {
						    		throw new BattleshipIOException("Cantidad de parametros en el comando put NO es CORRECTA.");
						    	}else {
						    		try {
						    			int c0 = Integer.parseInt(palabras.get(3));
							    		int c1 = Integer.parseInt(palabras.get(4));
							    		int c2 = Integer.parseInt(palabras.get(5));
							    		Craft barco = CraftFactory.createCraft(palabras.get(1), o);
							    		b.addCraft(barco, CoordinateFactory.createCoordinate(c0, c1, c2));
						    		}catch(NumberFormatException e) {
						    			throw new BattleshipIOException("ERROR: Un parametro de la coordenada no es un numero.");
						    		}
						    	}
						    }else if(b instanceof Board2D) {
						    	if(palabras.size() < 5 || palabras.size() > 5) {
						    		throw new BattleshipIOException("Cantidad de parametros en el comando put NO es CORRECTA.");
						    	}else {
						    		try {
						    			int c0 = Integer.parseInt(palabras.get(3));
							    		int c1 = Integer.parseInt(palabras.get(4));
							    		Craft barco = CraftFactory.createCraft(palabras.get(1), o);
							    		b.addCraft(barco, CoordinateFactory.createCoordinate(c0, c1));
						    		}catch(NumberFormatException e) {
						    			throw new BattleshipIOException("ERROR: Un parametro de la coordenada no es un numero.");
						    		}
						    	}
						    }
					    }else if(palabras.get(0).equals("exit") || palabras.get(0).equals("endput")){
					    	break;
					    }else {
					    	throw new BattleshipIOException("ERROR: Comando diferente a 'exit', 'endput' o 'put.'");
					    }
					}
				}catch(NullPointerException e) {
					e.printStackTrace();
				}
			}
		}catch (IOException e) {
			throw new BattleshipIOException("Se ha leido una linea de br.");
		}
	}

	
	
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException,CoordinateAlreadyHitException{
		
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
















