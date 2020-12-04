package model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

import model.exceptions.io.BattleshipIOException;

public class PlayerFactoryPreTest {

	final String DIRFILES = "test/files/";
	
	/* Se crea un player a partir de un fichero y se comprueba que createPlayer ha creado
	 * un PlayerFile.
	 */
	@Test
	public void testCreatePlayerFile1() throws BattleshipIOException, FileNotFoundException {			
			IPlayer ip=PlayerFactory.createPlayer("Saul",DIRFILES+"testCreatePlayerFile1.in");
			assertEquals("PlayerFile",ip.getClass().getSimpleName());
	}
	
    //TODO
	/* Pasa a createPlayer un número como segundo parámetro y comprueba que se ha creado 
	 * un PlayerRandom 
	 */
	@Test
	public void testCreatePlayerRandom1() throws BattleshipIOException {
		PlayerFactory.createPlayer("Laura","50000");
	}
	
	//TODO
	/* Realiza el test que compruebe que createPlayer lanza la excepción BattleshipIOException,
	 * cuando se le pasa como segundo parámetro un fichero que no existe
	 */
	@Test
	public void testCreatePlayerFileNotExist() throws BattleshipIOException {
		PlayerFactory.createPlayer("Laura","null");
	}
	
	/* Cuando se pasa como segundo parámetro null, createPlayer debe lanzar NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void testCreatePlayerNullPointerException() throws BattleshipIOException, FileNotFoundException {
		     PlayerFactory.createPlayer("Laura",null);
	}
	
	
	//TODO
	/* Comprueba que si como segundo parámetro, no pasamos ni números ni '.', '\', '/' 
	  debe devolver null*/
	@Test
	public void testCreatePlayerNull1() throws BattleshipIOException {
		PlayerFactory.createPlayer("Laura","patata");
	}
	
}
