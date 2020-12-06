package model.io;

public class VisualiserFactory {

	public static IVisualiser createVisualiser(String nombre, Game game) {
		
		if(nombre == "GIF") {
			return new VisualiserGIF(game);
		}else if(nombre == "Console"){
			return new VisualiserConsole(game);
		}else {
			return null;
		}
	}
}
