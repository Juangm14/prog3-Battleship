package model.io;

import java.lang.reflect.Constructor;

import model.Game;

public class VisualiserFactory {

	public static IVisualiser createVisualiser(String nombre, Game game) {
		Constructor<?> construc = null;
		Class<?> cls = null;
		
		try {
			cls = Class.forName("model.io." + "Visualiser" + nombre);
			if(cls != null) {
				construc = cls.getDeclaredConstructor(Game.class);
				return (IVisualiser) construc.newInstance(game);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
			
		
		return null;
	}
}
