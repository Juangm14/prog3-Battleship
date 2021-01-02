package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Craft objects.
 */
public class CraftFactory{
	
	/**
	 * Creates a new Craft object.
	 *
	 * @param tipo the tipo
	 * @param o the o
	 * @return the craft
	 */
	public static Craft createCraft(String tipo, Orientation o) {
		
		Class<?> cls = null;
			
		try {
			cls = Class.forName("model." + tipo);
			Constructor<?> construc = cls.getConstructor(o.getClass());
			Craft barco = (Craft) construc.newInstance(o);
			
			return barco;
			
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
