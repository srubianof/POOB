package aplicacion;

/**
 * Tipo de invasor
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class PulpoA extends InvasorA {

	/**
	 * Constructor de la clase
	 * 
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public PulpoA(double x, double y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		this.puntos = 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aplicacion.InvasorA#cargarBalas()
	 */
	public void cargarBalas() {
		for (int i = 0; i < 100000; i++) {
			balas.push(new BalaAlien());
		}

	}
}
