package model.score;

import java.util.Objects;

import model.exceptions.BattleshipException;
import model.io.IPlayer;

public abstract class Score<T> implements Comparable<Score<T>>{   
		
		
	protected int score;
	protected IPlayer player;
	
	Score(IPlayer ip) {
		
		if(ip!=null) {
			score = 0;
			player = ip;
		}else {
			throw new NullPointerException();
		}	
	}
	
	public String toString() {
		
		return player.getName().toString() + ": "+ score;
	}

	public int compareTo(Score<T> sc1) {
		
        if(score < sc1.getScore()) {
            return -1;
        }else if(score > sc1.getScore()) {
            return 0;
        }else{
            return player.getName().compareTo(sc1.player.getName());
        }
	}
	
	public int getScore() {
		return score;
	}
}
	

