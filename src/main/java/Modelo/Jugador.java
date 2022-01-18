package Modelo;

import java.util.ArrayList;
import java.util.HashSet;

public class Jugador extends Thread {
	
	
	int idJugador;
	HashSet<Integer> carton;
	Bombo b;
	final int TOTAL_CARTON = 5; // Cantidad de números por cartón
	final int TOTAL_BOMBO = 15; // Números posibles del bombo
	int numero;
	
	public Jugador(int idJugador) {
		super();
		this.idJugador = idJugador;
		//this.carton = carton;

	}
	
	public Jugador(int idJugador, Bombo b) {
		super();
		this.idJugador = idJugador;
		this.b = b;
		carton = new HashSet<Integer>();
		while (carton.size() < TOTAL_CARTON)
			carton.add((int) Math.floor(Math.random() * TOTAL_BOMBO) + 1);
		System.out.println("CARTON JUGADOR: " + idJugador + ", NUMEROS DEL JUGADOR: "+ carton);
	}
	
	/**
	 * Muestra el cartón por pantalla con los números que aún no han salido
	 */
	void muestraCarton() {
		System.out.print("Pendientes jugador " + idJugador + ": ");
		for (Integer integer : carton)
			System.out.print(integer + " ");
	}
	
	/**
	 * Elimina el número del cartón
	 *           
	 */
	void eliminarNum(int numero) {
		carton.remove(numero);
		System.out.println("numero eliminado por el jugador/jugadores: "+numero);
	}

	/**
	 * Método encargado de las acciones del jugador.
	 */
	public void run() {

		while (carton.size() > 0) {// mientras que existan números en el cartón
			b.consultarBola();// se consulta si hay numeros
			eliminarNum(b.ultNumero);// se elimina el ultimo numero que ha salido
			for (Integer integer : b.bombo) {
				carton.remove(integer);
			}

			muestraCarton();//Muestra los numeros que le faltan al jugador

		}
		System.out.println("Bingo del jugador " + idJugador + " ha ganado!!");
		// se termina el juego
		try {
			System.exit(0);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Jugador [nombre=" + idJugador + ", carton=" + carton + "]";
	}


	
	
	
	

}
