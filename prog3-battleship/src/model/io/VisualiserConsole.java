package model.io;

public class VisualiserConsole implements IVisualiser{

	private Game game;
	
	VisualiserConsole(Game game) throws NullPointerException{
		
		if(game != null) {
			this.game = game;
		}else {
			throw new NullPointerException();
		}	
	}
	
	public void show() {
		
	}
	
	
	public void close() {
		
	}
	
}
