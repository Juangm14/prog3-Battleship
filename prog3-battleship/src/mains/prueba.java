package mains;

import java.util.ArrayList;

import model.Orientation;

public class prueba {

	public static void main(String[] args) {
	
		String cadena = "put           Cruiser NORTH -1 2\n";
		
        String[] palabras = cadena.split(" ");
		ArrayList<String> result = new ArrayList<>();
		
		
        for(String palabra:palabras) {	
        	if(!palabra.isEmpty()) {
        		palabra = palabra.replace("\n", "");
        		result.add(palabra);
        	}
        }
        
        System.out.println(result.size());
        Boolean es2D = false;
        String nombre;
        
        Orientation o;
        
    	if(result.get(0).equals("put")) {
    		
    		switch(result.get(1)) {
		      case "Cruiser": 
		    	  es2D = true;
		    	  System.out.println("Entra");
		    	  nombre = result.get(1);
		    	  break;
		      case "DestroyeBooler":
		    	  es2D = true;
		    	  nombre = result.get(1);
		    	  break;
		       case "Carrier":
		    	   es2D = true;
		    	   nombre = result.get(1);
		    	  break;
		      case "Battleship":
		    	  es2D = true;
		    	  nombre = result.get(1);
		    	  break;
		      case "Bomber":
		    	  nombre = result.get(1);
		    	  break;
		      case "Aircraft":
		    	  nombre = result.get(1);
		    	  break;
		      case "Fighter":
		    	  nombre = result.get(1);
		    	  break;
		      case "Transport":
		    	  nombre = result.get(1);
		    	  break;
		      default:
		    	  break;
    		}
    		
    		switch(result.get(2)) {
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
    		
    		if(es2D == true && result.size() == 5) {
    			c0 = Integer.parseInt(result.get(3));
    			c1 = Integer.parseInt(result.get(4));
    			
    		}else if(es2D == false && result.size() == 6){
    			c0 = Integer.parseInt(result.get(3));
    			c1 = Integer.parseInt(result.get(4));
    			c2 = Integer.parseInt(result.get(5));
    		}	
    	}
    }
       

   	

}