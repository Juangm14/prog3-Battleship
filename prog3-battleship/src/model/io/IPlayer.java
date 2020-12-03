package model.io;

import model.Board;
import model.Coordinate;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;

public interface IPlayer {

	public void putCrafts(Board b) throws BattleshipIOException;
	public Coordinate nextShoot(Board b) throws BattleshipIOException;
	
	public String getName();
}
