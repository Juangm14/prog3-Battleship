package model;


/**
 * 
 * @author Juan Garcia Martinez
 *
 * 
 */

public class Coordinate {
	
	private int[] components;

	public Coordinate(int x, int y){
	
	    components = new int[2];
	    components[0] = x;
	    components[1] = y;
	}
	
	public Coordinate(Coordinate c){

		components = new int[2];
			
		for (int i = 0; i < components.length; i++)
			components[i] = c.components[i];
	}
	
	public int get(int component){
		
		if (component >= 0 && component < components.length) {
			return components[component];
		}
		else
			System.err.println("Error in Coordinate.get, component " + component + " is out of range");
		
		return -1;	
	}
	
	public boolean equals(Coordinate c){
		for (int i=0;i<components.length;i++) {
			if (components[i] != c.components[i]) {
				return false;
			}
		}
		return true;
	}
	
	public String toString(){
		String concatenation = "";
		concatenation += "(";
		
		for (int i=0;i<components.length;i++){
			concatenation += components[i];
			if (i<components.length-1) // no es la �ltima
				concatenation += ",";
		}
		
		concatenation += ")";
		return concatenation;
	}
	
	public Coordinate add(Coordinate c){
		Coordinate new_c = this ;
        
		   for (int i=0; i<components.length; i++)
		      new_c.set(i, new_c.get(i) + c.get(i));
		                
		   return new_c;	
	}
	
	public Coordinate subtract(Coordinate c){
		Coordinate new_c = this; 
	        
	   for (int i=0; i<components.length; i++)
		      new_c.set(i, new_c.get(i) - c.get(i));
		                
	   return new_c;
	}
	
	protected void set(int component,int value) {
		
		if (component >= 0 && component < components.length) {
		      components[component] = value;
		   }
		   else
		      System.err.println("Error in Coordinate.set, component " + component + " is out of range");
	}
	
	
};
