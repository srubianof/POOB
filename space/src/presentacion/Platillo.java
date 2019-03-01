/**
 * 
 */
package presentacion;

/**
 * Tipo de invasor
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Platillo extends Invasor {
	protected Imagenes i = new Imagenes();

	/**
	 * Constructor de la clase
	 * @param fil fila 
	 * @param col columna
	 */
	public Platillo(double fil, double col) {
		// TODO Auto-generated constructor stub
		this.fil = fil;
		this.col = col;

		vivo = true;
		start();
	}

	@Override
	protected void start() {
		frames.add(i.getImagen("Platillo.png"));
	}

}
