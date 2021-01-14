import java.util.HashMap;

import model.Coordinate;
import model.Craft;
import model.Orientation;
import model.ship.Coordinate2D;
import model.ship.Cruiser;


public class prueba {
       public static void main(String[] args) {
           HashMap<Coordinate, Craft> board = new HashMap<Coordinate, Craft>();

           
           Craft galeon = new Cruiser(Orientation.SOUTH);
           Craft fragata = new Cruiser(Orientation.WEST);
           board.put(new Coordinate2D(0,1), galeon);
           board.put(new Coordinate2D(0,3), galeon);
           board.put(new Coordinate2D(0,2), galeon);
           board.put(new Coordinate2D(5,4), fragata);

           System.out.println("Before removal");
           for( Coordinate s : board.keySet() ) {
               System.out.println( s );
           }

           System.out.println("\n\nAfter removal");

           
           board.remove(new Coordinate2D(0,1));
           board.remove(new Coordinate2D(0,2));
           board.remove(new Coordinate2D(0,3));
           
           for(Coordinate s : board.keySet() ) {
               System.out.println( s );
           }
       }
}