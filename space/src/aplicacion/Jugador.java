package aplicacion;

/**
 * Representa al jugador
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Jugador {
	protected int puntuacion;
	protected int vidas;

	/**
	 * Constructor de la clase
	 */
	public Jugador() {
		this.vidas = 3;
		this.puntuacion = 0;
	}

	/**
	 * Anado puntuacion al jugador
	 * 
	 * @param puntos puntos
	 */
	public void addPuntuacion(int puntos) {
		this.puntuacion += puntos;
	}

	/**
	 * Quito vidas al jugador
	 */
	public void morir() {
		this.vidas--;
	}

	public int getVidas() {
		return this.vidas;
	}

	public int getPuntos() {
		return this.puntuacion;
	}
}
