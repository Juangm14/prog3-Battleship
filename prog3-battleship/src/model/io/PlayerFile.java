package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

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
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;

public class PlayerFile implements IPlayer{

	private String nombre;
	private BufferedReader br;
	private CellStatus lastShotStatus;
	
	PlayerFile(String n, BufferedReader buff) throws NullPointerException{
		if(buff == null) {
			throw new NullPointerException();
		}else {
			nombre = n;
			br = buff;
			lastShotStatus = null;
		}
	}
	
	public String getName() {
		return nombre + " (PlayerFile)"; 
	}
	
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException, IllegalArgumentException{
		try {
			if(b == null) {
				throw new NullPointerException();
			}else {
				String line = "";
				Orientation o;
				try {
					while((line = br.readLine()) != null) {
						
						String[] result = line.split("\\s+");
						ArrayList<String> palabras = new ArrayList<>();
						
					    for(String palabra:result) {	
					    	if(palabra.trim().length() > 0) {
					    		palabras.add(palabra);
					    	}
					    }
					    
					    if(palabras.get(0).equals("exit")) {
					    	break;
					    }
					    
					    if(palabras.size() < 5 && palabras.get(0).equals("put")) {
					    	throw new BattleshipIOException("Cantidad de parametros en el comando put NO es CORRECTA.");
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
							
						   if(palabras.size() < 5 ) {
						    		throw new BattleshipIOException("Cantidad de parametros en el comando put NO es CORRECTA.");
						   }else if(palabras.size() == 6){
							   	try {
					    			int c0 = Integer.parseInt(palabras.get(3));
						    		int c1 = Integer.parseInt(palabras.get(4));
						    		int c2 = Integer.parseInt(palabras.get(5));
						    		Craft barco = CraftFactory.createCraft(palabras.get(1), o);
						    		b.addCraft(barco, CoordinateFactory.createCoordinate(c0, c1, c2));
							   	}catch(NumberFormatException  e) {
							   		throw new BattleshipIOException("Error: comando no valido");
							   	}
						   }else if(palabras.size() == 5) {
							   
							   try {
					    			int c0 = Integer.parseInt(palabras.get(3));
						    		int c1 = Integer.parseInt(palabras.get(4));
						    		
						    		Craft barco = CraftFactory.createCraft(palabras.get(1), o);
						    		b.addCraft(barco, CoordinateFactory.createCoordinate(c0, c1));
							   }catch(NumberFormatException e) {
							   		throw new BattleshipIOException("Error: comando no valido");
							   }
						   }else {
							   throw new BattleshipIOException("Error: Comando no valido.");
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
		lastShotStatus = null;
		
		if(b == null) {
			throw new NullPointerException();
		}else {
			String line;
			
			try {
				while((line = br.readLine()) != null) {
					
					String[] result = line.split("\\s+");
					ArrayList<String> palabras = new ArrayList<>();
					
				    for(String palabra:result) {	
				    	if(palabra.trim().length() > 0) {
			    			palabras.add(palabra);
				    	}
				    }
				    
				    if(palabras.get(0).equals("exit")) {
				    	return null;
				    }
				    
				    if(b instanceof Board2D) {
						if(palabras.get(0).equals("shoot") && palabras.size() == 3){
							try {
								int x = Integer.parseInt(palabras.get(1));
								int y = Integer.parseInt(palabras.get(2));
								Coordinate c = CoordinateFactory.createCoordinate(x,y);
								lastShotStatus = b.hit(c);
								return c;
							}catch(NumberFormatException e) {
								throw new BattleshipIOException("Las coordenadas no son enteros");
							}
						}else if(palabras.get(0).equals("exit")) {
							
						}else {
							throw new BattleshipIOException("El comando no es admisible");
						}
					}else if(b instanceof Board3D){
						if(!palabras.get(0).equals("shoot") && !palabras.get(0).equals("exit")) {
							throw new BattleshipIOException("El comando no es admisible");
						}else {
							try {
								int x = Integer.parseInt(palabras.get(1));
								int y = Integer.parseInt(palabras.get(2));
								int z = Integer.parseInt(palabras.get(3));
								Coordinate c = CoordinateFactory.createCoordinate(x,y,z);
								lastShotStatus = b.hit(c);
								return c;
							}catch(NumberFormatException e) {
								throw new BattleshipIOException("Las coordenadas no son enteros");
							}
						}
					}
				}
			} catch (IOException e) {
				throw new BattleshipIOException("Leemos linea del archivo.");
			}
			
			return null;
		}
	}

	@Override
	public CellStatus getLastShotStatus() {
		return lastShotStatus;
	}
}
















