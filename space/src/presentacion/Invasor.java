package presentacion;

/**
 * Representa los invasores del juego
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
abstract class Invasor extends Objeto {
	protected boolean vivo;
	protected Imagenes i;

	/**
	 * Constructor de los invasores
	 */
	public Invasor() {
		vivo = true;
	}

	/**
	 * Agregar frames al ArrayList
	 */
	protected abstract void start();

}
