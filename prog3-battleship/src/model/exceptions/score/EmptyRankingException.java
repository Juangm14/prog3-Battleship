package model.exceptions.score;

import model.exceptions.BattleshipException;

public class EmptyRankingException extends BattleshipException {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Ranking is empty.";
	}
}
