/**
 * 
 */
package presentacion;

/**
 * Elemento tipo Invasor, representa al cangrejo
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Cangrejo extends Invasor {
	protected Imagenes i = new Imagenes();

	/**
	 * Constructor de la clase
	 * 
	 * @param fil fila
	 * @param col columna
	 */
	public Cangrejo(double fil, double col) {
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
		// TODO Auto-generated method stub
		frames.add(i.getImagen("cangrejo0.png"));
		frames.add(i.getImagen("cangrejo1.png"));
	}
}
