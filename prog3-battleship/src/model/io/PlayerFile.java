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

public class PlayerFile implements IPlayer{

	private String nombre;
	BufferedReader br;
	
	PlayerFile(String n, BufferedReader bf) throws NullPointerException{
		if(bf == null) {
			throw new NullPointerException();
		}else {
			nombre = n;
			br = bf;
		}
	}
	
	public String getName() {
		return nombre + " (PlayerFile)"; 
	}
	
	public void putCrafts(Board b) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		String line;
		Orientation o = null;
		
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
	        String nombre = "";
	        
	    	if(palabras.get(0).equals("put")) {
	    		
	    		switch(palabras.get(1)) {
			      case "Cruiser": 
			    	  es2D = true;
			    	  System.out.println("Entra");
			    	  nombre = palabras.get(1);
			    	  break;
			      case "DestroyeBooler":
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
			    	  System.out.println("Entra");
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
	    		}
	    		
	    		int c0 = 0;
	    		int c1 = 0;
	    		int c2 = 0;
	    		Coordinate c;
	    		
	    		if(es2D == true && palabras.size() == 5) {
	    			c0 = Integer.parseInt(palabras.get(3));
	    			c1 = Integer.parseInt(palabras.get(4));
	    			c = CoordinateFactory.createCoordinate(c0,c1);
	    			
	    		}else if(es2D == false && palabras.size() == 6){
	    			c0 = Integer.parseInt(palabras.get(3));
	    			c1 = Integer.parseInt(palabras.get(4));
	    			c2 = Integer.parseInt(palabras.get(5));
	    			c = CoordinateFactory.createCoordinate(c0,c1,c2);
	    		}
	    		
	    		Craft barco = CraftFactory.createCraft(nombre,o);
	    		
	    	}else if(palabras.get(0) == "endput" || palabras.get(0) == "exit"){
	    		break;
	    	}
	        
	        
			
	        
			 
			/*  
		      Craft c = CraftFactory.createCraft(tipo,o);
		      b.addCraft(c, position);*/
		}
		
		
	}
	
}
















