package presentacion;

/**
 * Elemento que representa las naves a utilizar
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public abstract class Nave extends Objeto {

	protected boolean vivo;
	protected Imagenes i;

	/**
	 * Constructor de la clase
	 */
	public Nave() {
		vivo = true;
	}

	/**
	 * Agregar frames al ArrayList
	 */
	protected abstract void start();

}
