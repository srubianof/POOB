package presentacion;

/**
 * Tipo de invasor
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Rana extends Objeto {

	/**
	 * Constructor de la clase
	 * @param fil fila
	 * @param col columna
	 */
	public Rana(double fil, double col) {
		// TODO Auto-generated constructor stub
		this.fil = fil;
		this.col = col;

		frames.add(i.getImagen("rana.png"));
	}

}
