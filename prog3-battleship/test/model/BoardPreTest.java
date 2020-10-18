package model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardPreTest {

	static final int MAX_BOARD_SIZE = 20;
	static final int  MIN_BOARD_SIZE = 5;
	final static int DIM = 10;
	Board board;
	Ship fragata, galeon, bergantin, goleta;
	static String sboardEmpty,sboard, sboardHide1, sboardHits1,
				sboardHits2,sboardHits3, sboardHide2; //= new String();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sboardHide1 = "?????\n?????\n?????\n?????\n?????";
		
		sboardHide2 = "A ??•\n" + 
			      "A ?? \n" + 
			      "A  ??\n" + 
			      "   ?•\n" + 
			      "?•??•";
		
		sboardEmpty = "     \n     \n     \n     \n     ";
		
		sboard =	"A FFF\n" + 
					"A    \n" + 
					"A   G\n" + 
					"    G\n" + 
					"BBB G";
		
		sboardHits1 ="• FFF\n" + 
				     "•    \n" + 
				     "•   G\n" + 
				     "    G\n" + 
				     "BBB G";
		
		sboardHits2 ="• FF•\n" + 
				     "•    \n" + 
				     "•   G\n" + 
				     "    •\n" + 
				     "BBB •";
		
		sboardHits3 = "• FF•\n" + 
				      "•    \n" + 
				      "•   G\n" + 
				      "    •\n" + 
				      "B•B •";
		
	}

	@Before
	public void setUp() throws Exception {
		fragata = 	new Ship(Orientation.WEST,	'F',	"Barbanegra");
		galeon = 	new Ship(Orientation.SOUTH,	'A',	"Francis Drake");
		bergantin = new Ship(Orientation.EAST,	'B',	"Benito Soto");
		goleta = 	new Ship(Orientation.NORTH,	'G',	"Hook");
		board = 	new Board(DIM);
		
	}

	/* Comprueba los límites del tamaño en el constructor,
	 * tanto dentro como justo fuera. Comprueba que al superarlos
	 * el tamaño que toma el Board es el mínimo 
	 */
	@Test
	public void testBoardGetSize() {
		System.out.println("testBoardGetSize: ");
		//Dentro de los límites
		board = new Board(MIN_BOARD_SIZE + 1);
		assertEquals(MIN_BOARD_SIZE + 1, board.getSize());
		board = new Board(MAX_BOARD_SIZE - 1);
		assertEquals(MAX_BOARD_SIZE - 1, board.getSize());
		board = new Board(MIN_BOARD_SIZE - 1);
		assertNotEquals(MIN_BOARD_SIZE - 1, board.getSize());
		board = new Board(MAX_BOARD_SIZE + 1);
		assertNotEquals(MAX_BOARD_SIZE + 1, board.getSize());
		//fail ("Termina el test superando los límites en 1");		
	}
	
	@Test
	public void jordiTest() {
		this.board = new Board(32);
		this.board.addShip(bergantin, new Coordinate(0, 0));
		this.board.toString();
	}
	
	/* Se comprueba checkCoordinate en los límites del tamaño 
	 * del Board 
	 */
	@Test
	public void testCheckCoordinate() {
		System.out.println("testCheckCoordinate");
		final int SIZE = 15;
		Board board = new Board(SIZE);
		assertFalse(board.checkCoordinate(new Coordinate(0,SIZE)));
		assertFalse(board.checkCoordinate(new Coordinate(-1,SIZE-1)));
		assertFalse(board.checkCoordinate(new Coordinate(-1,SIZE)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE,0)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE-1,-1)));
		assertFalse(board.checkCoordinate(new Coordinate(SIZE,-1)));
		assertTrue(board.checkCoordinate(new Coordinate(0,SIZE-1)));
		assertTrue(board.checkCoordinate(new Coordinate(SIZE-1,0)));
	}

	/* Posicionamientos correctos entre Ships. Posiciona de forma correcta 
	 * los 4 ships galeon, fragata, goleta y bergantín y comprueba que  se
	 * han posicionado los Ships en el Board.
	 */
	@Test
	public void testAddShipsOk() {
		System.out.println("testAddShipsOk");
		assertTrue(board.addShip(galeon, new Coordinate(0, 1)));
		for (int i=2; i<5; i++)	
			assertNotNull("x, y = 2," + i, board.getShip(new Coordinate(2,i)));
		
		assertTrue(board.addShip(goleta, new Coordinate(2, 1)));
		for(int i=2; i<5; i++)
			assertNotNull("x, y = 4," + i, board.getShip(new Coordinate(4, i)));
		
		assertTrue(board.addShip(bergantin, new Coordinate(0, 6)));
		for(int i=1; i<3; i++)
			assertNotNull("x, y = " + i + ", 8", board.getShip(new Coordinate(i, 8)));
		
		assertTrue(board.addShip(fragata, new Coordinate(5, 1)));
		for(int i=6; i<8; i++)
			assertNotNull("x, y = " + i + ", 1", board.getShip(new Coordinate(i, 3)));
		
		//fail("Sigue comprobando addShip igualmente para fragata, goleta y bergantín");
	}

	/* Posiciona los 4 Ships fuera del tablero y comprueba que
	 * addShip devuelve false y que además no se han posicionado
	 * los Ships en el Board
	 */
	@Test
	public void testAddShipsOutOfBoard() {
		System.out.println("testAddShipsOutOfBoard");
		assertFalse("Error 1 en testAddShipsOutOfBoard", board.addShip(bergantin, new Coordinate(MIN_BOARD_SIZE * -1, MIN_BOARD_SIZE * -1)));
		assertFalse("Error 2 en testAddShipsOutOfBoard", board.addShip(bergantin, new Coordinate(MAX_BOARD_SIZE + 5, MAX_BOARD_SIZE + 5)));
		//fail("Realiza el test testAddShipsOutOfBoard()");
	}
	
	/* Posiciona un Ship junto a otro y comprueba que addShip devuelve
	 * false y que además no se ha posicionado el Ship en el Board
	 */
	@Test
	public void testAddShipNextOther() {
		System.out.println("testAddShipNextOther");
		this.board = new Board(10);
		Coordinate goletaCoor = new Coordinate(0, 0);
		Coordinate galeonCoor = new Coordinate(1, 0);
		assertTrue(board.addShip(goleta, goletaCoor));
		assertFalse(board.addShip(galeon, galeonCoor));
		assertNull(board.getShip(galeonCoor));
		
		//fail("Realiza el test testAddShipNextOther()");
	}
	
	/* Se posiciona un Ship en una Coordinate.
	 * 1- Prueba getShip en una Coordinate que no contiene al Ship
	 * 2- Prueba getShip en todas las posiciones que ocupa el Ship
	 */
	@Test
	public void testGetShip() {
		System.out.println("testGetShip");
		this.board = new Board(10);
		assertTrue(board.addShip(fragata, new Coordinate(3, 1)));
		Set<Coordinate> shipPositions = fragata.getAbsolutePositions();
		for(Coordinate it : shipPositions) {
			assertEquals(fragata, this.board.getShip(it));
		}
		//fail ("Termina el test testGetShip()");
	}
	
	
    /* Se comprueba isSeen antes y después de disparar al agua
     * en un Board sin Ships */
	@Test
	public void testIsSeen1() {
		System.out.println("testIsSeen1");
		for (int i=0; i<board.getSize(); i++)
			for (int j=0; j<board.getSize(); j++) {
				assertFalse(board.isSeen(new Coordinate(i, j)));
				board.hit(new Coordinate(i, j));
				assertTrue(board.isSeen(new Coordinate(i, j)));
			}	
	}

	//TODO testIsSeen2
  /* Posiciona un Ship en el Board y comprueba isSeen 
   * antes y después de disparar a las distintas partes del Ship.
   * Cuando el Ship se ha hundido entonces comprueba que las
   * Coordinates vecinas del Ship también se han marcado como
   * vistas */
	@Test
	public void testIsSeen2() {
		System.out.println("testIsSeen2");
		board = new Board(5);
		board.addShip(goleta, new Coordinate(2, 1));
		
		assertFalse(board.isSeen(new Coordinate(3, 1)));
		for(int i = 2; i <= 4; i++) {
			assertFalse(board.isSeen(new Coordinate(4, i)));
			board.hit(new Coordinate(4, i));
			assertTrue(board.isSeen(new Coordinate(4, i)));
		}
		assertTrue(board.isSeen(new Coordinate(3, 1)));
		
		//fail ("Realiza el test testIsSeen2()");
	}

	/* Coloca un Ship en el Board en una Coordinate. Comprueba que:
	 * 1- al disparar (hit) sobre las posiciones alrededor del Ship el 
	 *    resultado es WATER.
	 * 2- al disparar (hit) sobre las posiciones del Ship, excepto la última,
	 *    el resultado es HIT.
	 * 3- al disparar (hit) sobre la última posición del Ship, el resultado 
	 *    es DESTROYED
	 * 
	 */
	@Test
	public void testHit() {
		System.out.println("testHit");
		board = new Board(5);
		board.addShip(goleta, new Coordinate(2, 1));
		assertEquals(CellStatus.WATER, this.board.hit(new Coordinate(3, 1)));
		assertEquals(CellStatus.HIT, this.board.hit(new Coordinate(4, 2)));
		assertEquals(CellStatus.HIT, this.board.hit(new Coordinate(4, 3)));
		assertEquals(CellStatus.WATER, this.board.hit(new Coordinate(3, 4)));
		assertEquals(CellStatus.DESTROYED, this.board.hit(new Coordinate(4, 4)));
	}

	/* Comprueba que:
	 * 1- en un Board sin Ships, areAllCraftsDestroyed devuelve true.
	 * 2- al posicionar dos Ships en el Board, tras cada posicionamiento,
	 *    areAllCraftsDestroyed devuelve false.
	 * 3- tras cada disparo sobre el primer Ship, hundiéndolo, areAllCraftsDestroyed 
	 *    devuelve false.
	 * 4- tras cada disparo sobre el segundo Ship, areAllCraftsDestroyed devuelve
	 *    false, excepto tras el último disparo que debe devolver true.
	 * 5- añade un nuevo Ship, entonces areAllCraftsDestroyed debe devolver
	 *    false.
	 */
	@Test
	public void testAreAllCraftsDestroyed() {
		System.out.println("testAreAllCraftsDestroyed");
		assertTrue("numCrafts=destroyedCrafts=0", board.areAllCraftsDestroyed());
		board.addShip(galeon, new Coordinate(0,1));
		assertFalse("numCrafts=1; destroyedCrafts=0", board.areAllCraftsDestroyed());
		board.addShip(fragata, new Coordinate(3,1));
		assertFalse("numCrafts=2; destroyedCrafts=0", board.areAllCraftsDestroyed());
		
		// 3
		board.hit(new Coordinate(2, 2));
		board.hit(new Coordinate(2, 3));
		board.hit(new Coordinate(2, 4));
		assertFalse("numCrafts = 2; destroyedCrafts = 1", board.areAllCraftsDestroyed());
		
		// 4
		board.hit(new Coordinate(4, 3));
		board.hit(new Coordinate(5, 3));
		board.hit(new Coordinate(6, 3));
		assertTrue("numCraft = 2; destroyedCrafts = 2", board.areAllCraftsDestroyed());
		
		//5
		board.addShip(goleta, new Coordinate(5, 5));
		assertFalse("numCrafts = 3; destroyedCrafts = 3", board.areAllCraftsDestroyed());
		//fail ("Termina las pruebas 3, 4 y 5");
	}

	
	/* Se comprueba getNeighborhood(Ship) donde el Ship y todas sus 
	 * Coordinate vecinas están dentro de Board.
	 */
	@Test
	public void testGetNeighborhoodShipCompletelyIn1() {
		System.out.println("testGetNeighborhoodShipCompletelyIn1");
		board.addShip(fragata, new Coordinate(5, 1));
		fragata.setPosition(new Coordinate(5, 1));
		Set<Coordinate> neighborhood = board.getNeighborhood(fragata);
		assertEquals(12,neighborhood.size());
		for (int i=5; i<10; i++) {
			for (int j=2; j<4; j++) {
				if ((j==3) && (i>=6)&&(i<=8))
					assertFalse("x,y = "+i+","+j,	neighborhood.contains(new Coordinate(i, j)));
				else 
					assertTrue("x,y = "+i+","+j,	neighborhood.contains(new Coordinate(i, j)));	
			}
		}
	}
	
	/* Comprueba:
	 * 1- getNeighborhood(Ship) para un Ship que no se ha puesto en el Board 
	 *    debe devolver un Set vacío.
	 * 
	 * 2- getNeighborhood(Ship, Coordinate) donde el Ship sale de los límites
	 *    del Board. El conjunto de Coordinate vecinas solo recoge aquellas
	 *    que están dentro del Board
	 */
	@Test
	public void testGetNeighborhoodShipOutOfBounds() {
		System.out.println("testGetNeighborhoodShipOutOfBounds");
		board = new Board(5);
		assertEquals(new HashSet<Coordinate> (), this.board.getNeighborhood(goleta, new Coordinate(0, 0)));
		board.addShip(goleta, new Coordinate(2, 1));
		Set<Coordinate> esperades = new HashSet<Coordinate>();
		esperades.add(new Coordinate(3, 1));
		esperades.add(new Coordinate(4, 1));
		esperades.add(new Coordinate(3, 2));
		esperades.add(new Coordinate(3, 3));
		esperades.add(new Coordinate(3, 4));
		assertTrue(esperades.containsAll(board.getNeighborhood(goleta)));
		//fail ("Realiza el test testGetNeighborhoodShipOutOfBounds() ");
	}
	
	/* Se crea un tablero de tamaño 5 sin Ships. Se comprueba que lo devuelto
	 * por show(true) y show(false) es correcto.
	 * 
	 */
	@Test
	public void testShowBoardEmty() {
		System.out.println("testShowBoardEmty");
		board = new Board(5);
		String hideShips = board.show(false);
		assertEquals(sboardHide1, hideShips);
		String showShips = board.show(true);
		assertEquals(sboardEmpty, showShips);
	}
	
	/* Se crea un tablero de tamaño 5.
	 * 1- Se añaden los 4 ships en las posiciones indicadas en la variable estática 'sboard'
	 *    definida en setUp().
	 * 2- Se comprueba que show(false) devuelve lo mismo que la variable estática sboardHide1
	 *    y que show(true) lo mismo que el contenido de la variable estática 'sboard' 
	 */
	@Test
	public void testShowBoardWithShips() {
		System.out.println("testShowBoardWithShips");
		this.board = new Board(5);
		board.addShip(galeon, new Coordinate(-2, -1));
		board.addShip(fragata, new Coordinate(1, -2));
		board.addShip(goleta, new Coordinate(2, 1));
		board.addShip(bergantin, new Coordinate(-1, 2));
		
		assertEquals(sboardHide1, this.board.show(false));
		assertEquals(sboard, this.board.show(true));
		//fail ("Sigue añadiendo la goleta y el bergantín en sus posiciones y haz las comprobaciones indicadas");

	}	
	
	/* Añade los 4 Ships del setUp() en el Board y comprueba toString() con
	 * la salida correcta.
	 */
	@Test
	public void testToString1() {
		//fail("Añade los 4 Ships en el Board");
		System.out.println("testToString1");
		board.addShip(bergantin, new Coordinate(-1, 2));
		board.addShip(fragata, new Coordinate(1, -2));
		board.addShip(galeon, new Coordinate(-2, -1));
		board.addShip(goleta, new Coordinate(2, 1));
		assertEquals("Board 10; crafts: 4; destroyed: 0", board.toString());
	}
	
	//TODO testToString2
    /* Se toma el ejemplo del test testAreAllCraftsDestroyed().
     * 1- Destruye el galeón y comprueba que la salida que debe dar es correcta.
     * 2- Realiza disparos sobre la fragata comprobando que las salidas que debe
     *    dar son correctas.
    */
	@Test
	public void testToString2() {		
		//fail ("Realiza el test testToString2()");
		System.out.println("testToString2");
		board.addShip(galeon, new Coordinate(0, 1));
		board.addShip(fragata, new Coordinate(3, 1));
		assertEquals("Board 10; crafts: 2; destroyed: 0", board.toString());
		
		board.hit(new Coordinate(2, 2));
		board.hit(new Coordinate(2, 3));
		board.hit(new Coordinate(2, 4));
		assertEquals("Board 10; crafts: 2; destroyed: 1", board.toString());
		
		board.hit(new Coordinate(4, 3));
		board.hit(new Coordinate(5, 3));
		board.hit(new Coordinate(6, 3));
		assertEquals("Board 10; crafts: 2; destroyed: 2", board.toString());
	}

}
