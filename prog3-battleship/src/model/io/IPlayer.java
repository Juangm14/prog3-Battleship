package model.io;

import model.Coordinate;
import model.exceptions.io.BattleshipIOException;

public interface IPlayer {

	public default void putCrafts() throws BattleshipIOException{
		
		
	}
	
	public default Coordinate nextShoot() throws BattleshipIOException{
		
		
	}
	
	public default String getName() {
		
	}
}
