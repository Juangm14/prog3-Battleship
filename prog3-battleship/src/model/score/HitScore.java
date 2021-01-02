package model.score;

import model.CellStatus;
import model.io.IPlayer;

public class HitScore extends Score<CellStatus> {

	HitScore(IPlayer ip) {
		super(ip);
	}
	
	public void score(CellStatus cell) {
		
		if(cell.equals(CellStatus.HIT)) {
			score++;
		}else if(cell.equals(CellStatus.HIT)) {
			score++;
		}
	}
	
	
}
