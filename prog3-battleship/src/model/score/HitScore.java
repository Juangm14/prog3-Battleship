package model.score;

import model.CellStatus;
import model.io.IPlayer;

public class HitScore extends Score<CellStatus> {

	public HitScore(IPlayer ip) {
		super(ip);
	}
	
	public void score(CellStatus cell) {
		
		if(cell == CellStatus.HIT || cell == CellStatus.DESTROYED) {
			score++;
		}
	}
}
