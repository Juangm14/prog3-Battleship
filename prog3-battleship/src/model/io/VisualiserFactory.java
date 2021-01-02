package model.io;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import model.Game;

public class VisualiserFactory {

	public static IVisualiser createVisualiser(String nombre, Game game) {
		
		Class<?> cls = null;
		
		try {
			
			cls = Class.forName("model.io.Visualiser" + nombre);
			Constructor<?> construc = cls.getConstructor(game.getClass());
			IVisualiser iv = (IVisualiser) construc.newInstance(game);
			
			return iv;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
}
