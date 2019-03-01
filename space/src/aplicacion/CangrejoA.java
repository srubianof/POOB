package aplicacion;

/**
 * Representa al cangrejo del juego
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class CangrejoA extends InvasorA {

	/** Constructor de la clase
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public CangrejoA(double x, double y) {
		super(x, y);
		this.puntos = 30;
	}
	/* (non-Javadoc)
	 * @see aplicacion.InvasorA#cargarBalas()
	 */
	public void cargarBalas() {
		for(int i =  0; i <100000; i++ ) {
				balas.push(new BalaAlien());
		}
	}
}
