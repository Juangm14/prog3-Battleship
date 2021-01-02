package model.io;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;

public interface IPlayer {

	public void putCrafts(Board b) throws IllegalArgumentException, BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException;
	
	public Coordinate nextShoot(Board b) throws BattleshipIOException, CoordinateAlreadyHitException, InvalidCoordinateException;
	
	public CellStatus getLastShotStatus();
	
	public String getName();
}
