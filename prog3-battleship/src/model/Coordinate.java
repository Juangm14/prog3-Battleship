package model;
import java.util.Arrays;
import java.util.Set;
import java.util.Objects;


/**
 * 
 *@author Juan Garcia Martinez
 *@ClaseCoordinate
 *La clase Coordinate trata de crear unas Coordenadas las cuales puedan ser sumadas, restadas,
 *ver si son iguales, consultar su valor e incluso editarlas.
 *
 * 
 */

public abstract class Coordinate {
	
	/**
	 * @components
	 *  componentes de la coordenada (x e y)
	 */
	private int[] components;
	
	/**
	 * Constructor que da los valores iniciales(Se los das en el main) a una Coordenada
	 * 
	 * @param x
	 * Primer valor de la coordenada
	 * @param y
	 * Segundo valor de la coordenada
	 */
	protected Coordinate(int dimensiones){
		components = new int[dimensiones];
	}
	/**
	 * Constructor copia
	 * 
	 * @param c
	 * Coordenada de la que se va a hacer una copia
	 */
	protected Coordinate(final Coordinate c) {
		
		components = new int [c.components.length];
		
		for(int i = 0; i < c.components.length;i++) {
			components[i] = c.components[i];
		}
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
	public int get(int component) throws IllegalArgumentException{
		
		if (component >= 0 && component <= components.length) {
		      return components[component];
		}
		
		throw new IllegalArgumentException();
		
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
	protected void set(int component,int value) throws IllegalArgumentException{
	   
		if (component>=0 && component<components.length) {
	      components[component] = value;
	   }
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public abstract Set<Coordinate> adjacentCoordinates();
	
	/**
	 * 
	 * @return copia del constructor.
	 */
	public abstract Coordinate copy();
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		return Arrays.equals(components, other.components);
	}	

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
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
	
	public final Coordinate add(final Coordinate c) {
		   
		
		Coordinate new_c = this.copy();
		   
		Objects.requireNonNull(c);

		for (int i=0; i < components.length && i < c.components.length; i++) {
				   
			new_c.set(i, new_c.get(i) + c.get(i));
		}		   
		   
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
		   Coordinate new_c = this.copy(); 
		        
		   Objects.requireNonNull(c);
		   
		   if(c != null) {
			   for (int i=0; i < components.length && i < c.components.length; i++) {
				   
				   new_c.set(i, new_c.get(i) - c.get(i));
			   }
		   }

		   return new_c;
		}
};

