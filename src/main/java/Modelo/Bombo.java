package Modelo;

import java.util.HashSet;

public class Bombo {
	
	boolean hayBola = true;// indica si se ha repartido
	final int TOTAL_BOMBO = 15; // Números posibles del bombo
	HashSet<Integer> bombo; // Para almacenar los valores que van saliendo
	int ultNumero; // Último número del bombo
	int bomboGrande = 0;
	long bomboCompartido;

	/**
	 * Inicializa vacío el bombo
	 */
	public Bombo() {

		bombo = new HashSet<Integer>();

	}
	
	public Bombo(int bomboGrande) {
		super();
		this.bomboGrande = bomboGrande;
	}
	
	/**
	 * método sincronizado que comprueba si se ha sacado bola por parte del Croupier
	 */
	synchronized void prepararNum() {

		while (!hayBola) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		sacarNum2();
		imprimirBombo();
		hayBola= false;
		notifyAll();

	}
	
	/**
	 * Metodo encargado de sacar una bola, es llamado en prepararNum
	 * @return
	 */
	public int sacarNum2() {
		int bolita = 0;
		int cantidadBolas = bombo.size();
		if (cantidadBolas < TOTAL_BOMBO) {
			do {
				ultNumero = (int) Math.floor(Math.random() * TOTAL_BOMBO) + 1;
				bombo.add(ultNumero);
				bolita = ultNumero;
			} while (cantidadBolas == bombo.size());
			System.out.println("Ha salido el número: " + ultNumero);
		} else {
			System.out.println("Ya han salido todas las bolas");
		}
		return bolita;
	}
		


	/**
	 * metodo sincronizado para ver si ha salido la bola y tacharla, sino hay bola se espera, 
	 */
	synchronized void consultarBola() {
		while (hayBola) {// si no se ha repartido, espera
			try {
				System.out.println("Esperando que se lance la bola");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		hayBola=true;
		notifyAll();

	}

	/**
	 * Muestra todas las bolas que han salido hasta el momento
	 */
	void imprimirBombo(){ 
		System.out.print("Bolas sacadas hasta el momento: ");
		for (Integer integer : bombo)
			System.out.print(integer + " ");
		System.out.println();
	}

	public int getBomboGrande() {
		return bomboGrande;
	}
	
	public int sacaBomboGrande() {
		this.bomboGrande++;
		return bomboGrande;
	}
	
	
	

}
