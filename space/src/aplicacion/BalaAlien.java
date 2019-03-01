/**
 * 
 */
package aplicacion;

/**
 * Balas de los aliens
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class BalaAlien extends BalaA{

	/**
	 * Constructor de la clase
	 */
	public BalaAlien() {
		this.damage = this.damageToBarrera = 10;
	}
	/* (non-Javadoc)
	 * @see aplicacion.BalaA#moverBala()
	 */
	public void moverBala() {
		this.posY += 20;
	}
}
