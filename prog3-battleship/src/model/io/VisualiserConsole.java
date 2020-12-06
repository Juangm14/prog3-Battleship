package model.io;

public class VisualiserConsole implements IVisualiser{

	private Game game;
	
	public VisualiserConsole(Game game){
		
		if(game != null) {
			this.game = game;
		}else {
			throw new NullPointerException();
		}	
	}
	
	public void show() {
		System.out.println(game.toString());
	}
	
	
	public void close() {
		
	}
}
