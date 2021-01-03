package model.exceptions.score;

import model.Coordinate;
import model.exceptions.BattleshipException;

public class EmptyRankingException extends BattleshipException {
	
	public EmptyRankingException(Coordinate coordenada) {
		super(coordenada);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Ranking Vacio.";
	}
}
