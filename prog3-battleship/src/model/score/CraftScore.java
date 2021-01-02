package model.score;

import model.CellStatus;
import model.Craft;
import model.io.IPlayer;

public class CraftScore extends Score<Craft> {

	public CraftScore(IPlayer ip){
		super(ip);
	}
	
	public void score(Craft nave) {
		score+= nave.getValue();
	}
	
}
