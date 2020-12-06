package model;
import java.util.Arrays;
import java.util.Set;
import java.util.Objects;


// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate.
 */
public abstract class Coordinate {
	
	private int[] components;
	

	/**
	 * Instantiates a new coordinate.
	 *
	 * @param dimensiones the dimensiones
	 */
	protected Coordinate(int dimensiones){
		components = new int[dimensiones];
	}

	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	protected Coordinate(final Coordinate c) {
		
		components = new int [c.components.length];
		
		for(int i = 0; i < c.components.length;i++) {
			components[i] = c.components[i];
		}
	}

	/**
	 * Gets the.
	 *
	 * @param component the component
	 * @return the int
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public int get(int component) throws IllegalArgumentException{
		
		if (component >= 0 && component < components.length) {
		      return components[component];
		}else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Sets the.
	 *
	 * @param component the component
	 * @param value the value
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	protected void set(int component,int value) throws IllegalArgumentException{
	   
		if (component>=0 && component<components.length) {
	      components[component] = value;
	   }
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public abstract Set<Coordinate> adjacentCoordinates();
	
	/**
	 * Copy.
	 *
	 * @return the coordinate
	 */
	public abstract Coordinate copy();
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}
	
	/**
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
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
	 * Subtract.
	 *
	 * @param c the c
	 * @return the coordinate
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

