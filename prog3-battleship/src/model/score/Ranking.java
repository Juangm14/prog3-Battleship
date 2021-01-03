package model.score;

import java.util.SortedSet;
import java.util.TreeSet;

import model.exceptions.score.EmptyRankingException;

class Ranking<ScoreType extends Score<?>> {

	private SortedSet<ScoreType> scoreSet;
	
	public Ranking(){
		scoreSet = new TreeSet<>();
	}
	
	public void addScore(ScoreType sT) {
		scoreSet.add(sT);
	}
	
	public ScoreType getWinner() throws EmptyRankingException{
		
		if(!scoreSet.isEmpty()) {
			return scoreSet.first();
		}else{
			throw new EmptyRankingException();
		}
	}
	
	public SortedSet<ScoreType> getSortedRanking() {
		
		return scoreSet;
	}
}
