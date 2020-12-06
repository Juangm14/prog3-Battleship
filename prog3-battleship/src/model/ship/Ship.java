/**
 * @author Juan García Martínez
 */

package model.ship;
import model.Orientation;
import model.Craft;

// TODO: Auto-generated Javadoc
/**
 * The Class Ship.
 */
public abstract class Ship extends Craft{

    /**
     * Instantiates a new ship.
     *
     * @param o the o
     * @param s the s
     * @param n the n
     */
    public Ship(Orientation o, char s, String n) {
    	super(o,s,n);
    }


}
