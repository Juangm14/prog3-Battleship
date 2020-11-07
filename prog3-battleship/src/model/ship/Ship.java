/**
 * @author Juan García Martínez
 */

package model.ship;
import model.Orientation;
import model.Craft;

public abstract class Ship extends Craft{
	
	/**
	 * Constructor donde se le asignan los valores a los atributos
	 * @param o Orientacion del barco(ship)
	 * @param s Symbolo para representar el barco en el tablero
	 * @param n Nombre del barco
	 */
          
    public Ship(Orientation o, char s, String n) {
    	super(o,s,n);
    }


}
