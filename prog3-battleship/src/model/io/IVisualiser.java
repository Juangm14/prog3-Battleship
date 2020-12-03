package model.io;

import java.io.IOException;

import model.exceptions.io.BattleshipIOException;

public interface IVisualiser {

	public void show() throws BattleshipIOException;
	
	public void close();
}
