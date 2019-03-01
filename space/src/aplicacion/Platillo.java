/**
 * 
 */
package aplicacion;

/**
 * Tipo de invasor
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Platillo extends InvasorA {

	/**
	 * Constructor de la clase
	 * 
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public Platillo(double x, double y) {
		super(x, y);
		this.puntos = 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aplicacion.InvasorA#cargarBalas()
	 */
	public void cargarBalas() {
		for (int i = 0; i < 100000; i++) {
			balas.push(new Aniquiladora());
		}
	}

}
