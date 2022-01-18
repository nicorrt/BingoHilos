package Modelo;

import es.iesfranciscodelosrios.Bingo.Ejecutable;

public class Croupier extends Thread {
	
	Bombo bombo;
	public int idCroupier;
	public int bolasPorCroupier;
	public int bolasTotales = 0;



	//Constructor para jugar al bingo
	public Croupier(Bombo bombo) {
		this.bombo = bombo;
	}	
	//Constructor para realizar la accion 4
	public Croupier(int idCroupier, int bolasTotales, Bombo bolas) {
		super();
		this.idCroupier = idCroupier;
		this.bolasPorCroupier = bolasTotales;
		this.bombo = bolas;
	}

	/**
	 * El croupier saca un número cada 2 segundos
	 */
	public void run() {
		if(Ejecutable.numJugadores>0) {
			try {

				for (int i = 0; i < bombo.TOTAL_BOMBO; i++) {// va sacando todas las bolas hasta terminar
					Thread.sleep(2000);
					System.out.println("Lanza la bola");
					bombo.prepararNum();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {

			   for (int i = 0; i < bolasPorCroupier; i++) {
				      this.bombo.sacaBomboGrande();
				      System.out.println(bolasTotales);
				      bolasTotales++;

				    }
				    System.out.printf("Croupier %d terminado, cuenta: %d bolas\n", idCroupier, getBolasTotales());
		}

	}

	public int getBolasTotales() {
		return bolasTotales;
	}


}
