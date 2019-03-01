package presentacion;

/**
 * Elemento que representa las barreras de juego
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Barrera extends Objeto {
	/**
	 * Constructor de la clase
	 * 
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public Barrera(double x, double y) {
		this.fil = x;
		this.col = y;
		frames.add(i.getImagen("image.png"));
	}
}
