package aplicacion;

/**
 * Tipo de bala 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Plasma extends BalaA {
	private int damageToBarrera;
	
	/**
	 * Constructor de la clase
	 */
	public Plasma() {
		this.damage = 20;
		this.damageToBarrera = 50;
	}
	
	/* (non-Javadoc)
	 * @see aplicacion.BalaA#getDamageBarrera()
	 */
	public int getDamageBarrera() {
		return this.damageToBarrera;}
	
	/* (non-Javadoc)
	 * @see aplicacion.BalaA#moverBala()
	 */
	public void moverBala() {
		this.posY += 20;
	}
}
