/**
 * 
 */
package aplicacion;

/**
 * Representa un tipo regular de bala
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class BalaNormal extends BalaA {

	/**
	 * Constructor de la clase
	 * 
	 * @param num numero
	 */
	public BalaNormal(int num) {
		this.damage = this.damageToBarrera = 10;
		this.num = num;
	}

	public void moverBala() {
		this.posY -= 20;
	}

	public int returnNum() {
		return this.num;
	}

}
