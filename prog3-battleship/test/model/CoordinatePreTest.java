package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/* Para realizar los test se sugiere usar mÃ©todos de la librerÃ­a de junit como:
 * assertEquals, assertNotEquals, assertSame; assertNotSame, assertTrue; assertFalse
 */
public class CoordinatePreTest {
	
    List vcoordinates = new ArrayList<int[]>();
    int []vcoor= {0,0,-70,-2,20}; //Para crear Coordinates
    final int DIM = vcoor.length;
    List<Coordinate> lcoor;
    
	@Before
	public void setUp() throws Exception {
		lcoor = new ArrayList<Coordinate>();
		//Se crean las Coordinates (0,0),(0,-70), (-70,-2),(-2,20);
		for (int i=0; i<DIM-1; i++) {
			lcoor.add(new Coordinate(vcoor[i],vcoor[i+1]));
		}
		
	}

	/* Se comprueba que cuando dos Coordinate son iguales, el resultado del hash ha 
	 * de ser el mismo. 
	 * Si los Coordinate son distintos el hashCode puede ser igual o no.
	 */
	@Test
	public void testHashCode() {
		Coordinate c1 = lcoor.get(2);
		Coordinate c2 = (c1);
		
		assertEquals (c1,c2);
		assertEquals (c1.hashCode(), c2.hashCode());
	}

	/* Se comprueba que el Constructor funciona bien. Para ello se analiza que las 
	 * las componentes  '0' y '1' de cada Coordinate creada en el setUp() son las 
	 * correctas.
	 */
	@Test
	public void testCoordinateConstructor() {
		int j=0;
		for (int i=0; i<DIM-1; i++) {			
			assertEquals("x",vcoor[i],lcoor.get(j).get(0));
			assertEquals("y",vcoor[i+1],lcoor.get(j).get(1));
			j++;
		}
	}

	/* Comprobar que el constructor de copia crea una nueva Coordinate con
	 * los mismos valores que las componentes respectivas del Coordinate copiado.
	 * Y eso se hace para cada Coordinate creada en setUp();
	 */
	@Test
	public void testCoordinateConstructorCopy() {
		
		//Test ME
		Coordinate a = new Coordinate(14, 13);
		Coordinate b = a;
		
		assertEquals(a,b);
		assertEquals(a.hashCode(), b.hashCode());

	}

	/* Se comprueba que el mÃ©todo get(int) para cada componente de una Coordinate 
	 * funciona correctamente.
	 * Modificar los valores de las componentes de la Coordinate anterior con el 
	 * mÃ©todo set(int, int) y comprobar con get(int) que los valores de sus 
	 * componentes han cambiado a dichos valores.
	 */
	@Test
	public void testGetSet() {
		Coordinate c = lcoor.get(2);
		assertEquals("x==-70", -70, c.get(0));
		assertEquals("x==-2", -2, c.get(1));
		
		//Implementado por ME
		c.set(0, -5);
		c.set(1, 10);
		
		assertEquals(-5, c.get(0));
		assertEquals(10, c.get(1));
		
	}

	/* Se suman las Coordinate creadas en el setUp() y comprueba, conforme se van 
	 * sumando, que los valores de sus componentes van tomando los valores correctos 
	 * y que el Coordinate que devuelve no es el mismo que el Coordinate que invoca 
	 * al mÃ©todo.
	 */
	@Test  //{0,0,-70,-2,20}
	public void testAdd() {
		Coordinate caux1 = lcoor.get(0);
		Coordinate caux2;
		
		int sumx = caux1.get(0);
		int sumy = caux1.get(1);
		for (int i=0; i<DIM-2; i++) {	
		   caux2 = caux1;
		   //Duplicamos el objeto para luego comparar si han cambiado cuando hacemos el add o no.
		   Coordinate c = new Coordinate(caux1);
		   caux1 = caux1.add(lcoor.get(i+1));	  
		   sumx += (vcoor[i+1]);
		   sumy += (vcoor[i+2]);
		   /*Usa aquÃ­ los mÃ©todos de junit adecuados para comprobar:
		    * - que sumx y sumy son iguales a los componentes '0' y '1' 
		    *   respectivamente de caux1.
		    * - que el Coordinate que devuelve 'add' no es el mismo que
	        *   el Coordinate que invocÃ³ al mÃ©todo.
		    */
		   
		   //Sumx y sumy iguales(ME):
		   assertEquals(sumx, caux1.get(0));
		   assertEquals(sumy, caux1.get(1));
		   
		   //Coordinate de add no sea igual al Coordinate que invoca el metodo(ME)
		   assertNotEquals(c, caux1);

		}
	}

	/* Resta las Coordinate creadas en el setUp() y comprueba, conforme se van restando, 
	 * que los valores de sus componentes van tomando los valores correctos y que el 
	 * Coordinate que devuelve no es el mismo que el Coordinate que invoca 
	 * al mÃ©todo.
	 */
	@Test
	public void testSubtract() {
		Coordinate caux1 = lcoor.get(0);
		Coordinate caux2;
		
		int subx = caux1.get(0);
		int suby = caux1.get(1);
		for (int i=0; i<DIM-2; i++){	
		   caux2 = caux1;
		   //Duplicamos el objeto para luego comparar si han cambiado cuando hacemos el subtract
		   Coordinate c = new Coordinate(caux1);
		   caux1 = caux1.subtract(lcoor.get(i+1));	  
		   subx -= (vcoor[i+1]);
		   suby -= (vcoor[i+2]);
		   
		   //subx y suby sean iguales(ME):
		   assertEquals(subx, caux1.get(0));
		   assertEquals(suby, caux1.get(1));
		   
		   //Coordinate de subtract no sea igual al Coordinate que invoca el metodo(ME)
		   assertNotEquals(c, caux1);
		}

	}

	
	/* Se comprueba, para el mÃ©todo toString(), que las Coordinate creadas en el setUp() 
	 * tienen el formato correcto.
	 */
	@Test
	public void testToString() {
		assertEquals ("(0, 0)",lcoor.get(0).toString());
		assertEquals ("(0, -70)",lcoor.get(1).toString());
		assertEquals ("(-70, -2)",lcoor.get(2).toString());
		assertEquals ("(-2, 20)",lcoor.get(3).toString());
	}

	/* Se toma una Coordinate y se comprueba todas las posibles condiciones bajo 
	 * las cuales, nuestra funciÃ³n equals() devuelve true o false
	 */
	@Test
	public void testEqualsObject() {
		Object obj = new String("(0, 0)");
		Coordinate c = lcoor.get(0);
		// equals() devuelve falso cuando le paso null
		assertFalse(c.equals(null));
		// equals() devuelve falso cuando le paso un objeto que no es de tipo Coordinate
		assertFalse(c.equals(obj));
		
		/* Sigue comprobando lo siguiente:
		 *  1. equals() devuelve false cuando el valor de alguno de los componentes de los 
		 *     Coordinate es distinto
		 *  2. equals() devuelve true cuando se compara un objeto Coordinate consigo mismo
		 *  3. equals() devuelve true cuando comparo dos objetos Coordinate distintos
		 *     y los valores de sus componentes respectivos son iguales.
		 */
		//1
		Coordinate c1 = new Coordinate(20,20);
		assertFalse(c.equals(c1));
		//2
		assertTrue(c.equals(lcoor.get(0)));
		//3
		
	}

}
