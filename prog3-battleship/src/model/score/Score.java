package model.score;

import java.util.Objects;

import model.io.IPlayer;

public abstract class Score<T> implements Comparable<Score<T>>{   
		
		
	protected int score;
	protected IPlayer player;
	
	Score(IPlayer ip) {
		
		Objects.requireNonNull(ip);
		score = 0;
		player = ip;
		
	}
	
	public String toString() {
		
		return player.getName().toString() + ": "+ score;
	}

	public int  compareTo(Score<T> sc1) {
		
		return this.compareTo(sc1);
	}
	
	public int getScore() {
		return score;
	}
}
	

