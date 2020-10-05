package model;


/**
 * 
 *@author Juan Garcia Martinez
 *@ClaseCoordinate
 *La clase Coordinate trata de crear unas Coordenadas las cuales puedan ser sumadas, restadas,
 *ver si son iguales, consultar su valor e incluso editarlas.
 *
 * 
 */

public class Coordinate {
	
	private int[] components;
	
	/**
	 * Constructor que da los valores iniciales(Se los das en el main) a una Coordenada
	 * 
	 * @param x
	 * Primer valor de la coordenada
	 * @param y
	 * Segundo valor de la coordenada
	 */
	public Coordinate(int x, int y){

	   components = new int[2];
	   //Aqui el valor de x se lo damos a la primera posicion de la coordenada e y a la segunda.
	   components[0]=x;
	   components[1]=y;
	}
	/**
	 * Constructor copia
	 * 
	 * @param c
	 * Coordenada de la que se va a hacer una copia
	 */
	public Coordinate(final Coordinate c) {
		
		components = new int [2];
		
		for (int i=0;i<components.length;i++)
			components[i] = c.components[i];
	}
	/**
	 * Coge un valor Coordenada de la posicion que tu elijas.
	 * 
	 * @param component
	 * Posicion del elemento (x o y) que se quiere coger(entre 0 y 1) de una determinada Coordenada.
	 * @return
	 * Devuelve el valor x o y de la coordenada, en caso de estar fuera de rango salta un error 
	 * y devueleve -1
	 * 
	 */
	public final int get(int component){
	   if (component >= 0 && component < components.length) {
	      return components[component];
	   }
	   else
	      System.err.println( "Error in Coordinate.get, component " + component + " is out of range" );;

	   return -1;
	}
	/**
	 * Sustituye una de las componentes de Coordenadas con el valor que le pases
	 * 
	 * @param component
	 * Posicion del elemento (x o y) que se quiere modificar y en caso de no entrar dentro del rango
	 * de las coordenadas saltara un error.
	 * @param value
	 * Sustuira al elemento anteriormente selecciondo
	 * 
	 */
	protected void set(int component,int value)
	{
	   if (component>=0 && component<components.length) {
	      components[component] = value;
	   }
	   else
	      System.err.println( "Error in Coordinate.set, component " + component + " is out of range" );
	}
	
	public Set<Coordinate> adjacentCoordinates() {
		
		
	}
	
	public Coordinate Copy() {
		
		Coordinate copia = new Coordinate(this);
		
		return copia;
	}
	
	/**
	 *Busca alguna Coordenada que sea igual a la que le has pasado y devuelve un Boolean
	 *sobre el resultado de su busqueda
	 * 
	 * @param c
	 * Coordenada la cual se comparara para ver si existe alguna con el mismo valor
	 * @return
	 * Retornara true si la encuentra y false si la Coordenada pasada es null o no la ha encontrado.
	 *
	 */
	public final boolean equals(final Coordinate c){
		
		if(c == null) {
			return false;
		}
		   for (int i = 0; i < components.length; i++) {
		      if (components[i] != c.components[i]){
		    	  return false;
		      }
		   }
		   
		   return true;
	}
	
	/**
	 *Trasformamos una coordenada en un String
	 */
	public final String toString(){
		   String concatenation = "";
		   
		   concatenation += "(";
		   
		   for (int i=0;i<components.length;i++){
		      concatenation += components[i];
		      if (i<components.length-1) // no es la última
		         concatenation += ", ";
		   }
		   concatenation += ")";
		   return concatenation;
	}
	
	public int hashCode() {
			
	}
	
	/**
	 * 
	 * Suma 2 coordenadas que indiques
	 * 
	 * @param c
	 * Coordenada que se quiere sumar con la coordenada que llama al metodo.
	 * @return
	 * Devuelve la suma de ambas coordenadas
	 */
	
	public final Coordinate add(final Coordinate c){
		   Coordinate new_c = new Coordinate(this);
		        
		   for (int i=0; i<components.length; i++)
			   new_c.set(i, new_c.get(i) + c.get(i));
		   	
		   return new_c;
	}
	
	/**
	 * 
	 * Resta dos coordenadas que indiques
	 * 
	 * @param c
	 * Coordenada que se quiere restar con la coordenada que llama al metodo
	 * @return
	 * Devuleve la resta de ambas coordenadas
	 */
	public final Coordinate subtract(final Coordinate c){
		   Coordinate new_c = new Coordinate(this); 
		        
		   for (int i=0; i<components.length; i++)
		      new_c.set(i, new_c.get(i) - c.get(i));
		                
		   return new_c;
		}
};

