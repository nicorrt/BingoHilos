package es.iesfranciscodelosrios.Bingo;

import java.util.Scanner;

import Modelo.Bombo;
import Modelo.Carton;
import Modelo.Croupier;
import Modelo.Jugador;




public class Ejecutable {
	
	public static int numCartones;
	public static int numJugadores;
	public static int numCrupier;
	public static int bomboGrande=100;
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		Bombo b = new Bombo();
		
		System.out.println("SE ABRE EL BINGO");	
		
		do {
			System.out.println("¿Que opcion quieres usar?");	
			System.out.println("Pulsa 1 para crear cartones");
			System.out.println("Pulsa 2 para crear jugadores");
			System.out.println("Pulsa 3 para jugar");
			System.out.println("Pulsa 4 para repartir el bombo grande entre varios Croupiers");
			System.out.println("Pulsa 5 para Salir");
			opcion = teclado.nextInt();
			
			switch (opcion) {
			case 1:
				System.out.println("¿Cuantos cartones quieres crear?");
				numCartones = teclado.nextInt();
				Thread carton;
				for (int i = 1; i <= numCartones; i++) {
					carton = new Carton(i, null);
					System.out.println("el carton " + i + " esta preparado para jugar");
					carton.start();// SE LANZAN
					try {
						carton.join();
						System.out.println(carton);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			case 2:
				System.out.println("Cuantos jugadores quereis jugar?");
				numJugadores = teclado.nextInt();
				Thread[] jugadores = new Thread[numJugadores];
				for (int i = 0; i < numJugadores; i++) {
					Jugador jugador = new Jugador(i,b);
					System.out.println("el jugador " + i + " esta preparado para jugar");
					Thread th = new Thread(jugador);
					th.start();
					jugadores[i] = th;
					//jugador.start();// SE LANZAN
					try {
						jugador.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3:
				if(numJugadores==0) {
					System.out.println("Necesita crear un numero de jugadores");
				}else {
					// se crea el hilo del croupier, el cuaL cominza sacando un número
					Thread c = new Croupier(b);
					c.start();// saca las bolas el croupier
				}
				break;
			case 4:
				Bombo bomb = new Bombo();
				System.out.println("¿Cuantos croupieres van a sacar bolas?");
				numCrupier = teclado.nextInt();
				Thread[] croupieres= new Thread[numCrupier];
				System.out.println(bomb.getBomboGrande());
				for (int i = 0; i < numCrupier; i++) {
				      Thread th = new Thread(new Croupier(i, bomboGrande / numCrupier, bomb));
				      th.start();
				      croupieres[i] = th;
				    }
				
				for(Thread h: croupieres) {
					try {
						h.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.printf("Bolas sacadas del bombo: %s\n", bomb.getBomboGrande());
				break;
			case 5:
				System.exit(0);
				break;
			default:
				break;
			}
		} while(opcion!=6);
	}

}
