/**
 * 
 */
package presentacion;

/**
 * Elemento tipo invasor, representa al Calamar
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Calamar extends Invasor {
	protected Imagenes i = new Imagenes();

	/**
	 * Constructor de la clase
	 * 
	 * @param fil fila
	 * @param col columna
	 */
	public Calamar(double fil, double col) {
		// TODO Auto-generated constructor stub
		this.fil = fil;
		this.col = col;

		vivo = true;
		start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.Invasor#start()
	 */
	@Override
	protected void start() {
		frames.add(i.getImagen("Calamar0.png"));
		frames.add(i.getImagen("Calamar1.png"));
	}
}
