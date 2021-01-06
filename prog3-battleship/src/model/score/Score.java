package model.score;
import model.io.IPlayer;

public abstract class Score<T> implements Comparable<Score<T>>{   
		
	protected int score;
	private IPlayer player;
	
	Score(IPlayer ip) {
		
		if(ip!=null) {
			score = 0;
			player = ip;
		}else {
			throw new NullPointerException();
		}	
	}
	
	public abstract void score(T sc1);
	
	public String toString() {
		
		return player.getName().toString() + ": "+ score;
	}

	public int compareTo(Score<T> sc1) {
		
        if(score < sc1.getScore()) {
            return 1;
        }else if(score > sc1.getScore()) {
            return -1;
        }else{
            return player.getName().compareTo(sc1.player.getName());
        }
	}
	
	public int getScore() {
		return score;
	}
	
	
}
	

