package model.io;

import java.lang.reflect.Constructor;
import model.Game;

public class VisualiserFactory {

	public static IVisualiser createVisualiser(String nombre, Game game){
		
		Class <?>  cls;
		Constructor<?> construc;
		
		try {
			cls = Class.forName("model.io.Visualiser" + nombre);
			construc = cls.getConstructor(game.getClass());
			return (IVisualiser) construc.newInstance(game);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
