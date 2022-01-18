package Modelo;

import java.util.ArrayList;
import java.util.HashSet;

public class Carton extends Thread {
	
	private final int TOTAL_CARTON=5; 
	private final int TOTAL_BOMBO=20;
	private int idCarton;
	private HashSet<Integer> numeros;

	public Carton(int idCarton, HashSet<Integer> numeros) {
		super();
		this.idCarton = idCarton;
		this.numeros = numeros;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		numeros = new HashSet<Integer>();
		while (numeros.size() < TOTAL_CARTON) {
			numeros.add((int) Math.floor(Math.random() * TOTAL_BOMBO) + 1);
		System.out.println("CARTON " +idCarton+": " + numeros );
		}
	}


	@Override
	public String toString() {
		return "Carton [idCarton=" + idCarton + ", numeros que te han tocado = " + numeros + "]";
	}

}
