package model.io;

import java.io.IOException;

import model.exceptions.io.BattleshipIOException;

public interface IVisualiser {

	public void show() throws IOException;
	
	public void close();
}
