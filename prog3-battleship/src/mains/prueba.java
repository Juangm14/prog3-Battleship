package mains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import model.Orientation;

public class prueba {

	public static void main(String[] args) {
        String[] valores = {"uno", ".", ".", "tres", ".", "cuatro", ".", ".", ".", "dos", "siete"};
        ArrayList<String> tuPutaMadre= new ArrayList<>();
        
        
        for (int i = 0; i < valores.length; i++) {
        	//System.out.println("Valores " + valores[i]);
        	
        	if(valores[i] != ".") {
        		tuPutaMadre.add(valores[i]);
        	}
        		
        }
        
        for(String t:tuPutaMadre) {
        	System.out.println(t);
        }
	}
        
        
}