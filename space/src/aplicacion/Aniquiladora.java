package aplicacion;

/**
 * Tipo de Bala de nombre aniquiladora
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Aniquiladora extends BalaA {
	
	public Aniquiladora() {
		this.damage = 50;
		this.damageToBarrera = 50;
	}
	/* (non-Javadoc)
	 * @see aplicacion.BalaA#moverBala()
	 */
	public void moverBala() {
		this.posY += 20;
	}
	/* (non-Javadoc)
	 * @see aplicacion.BalaA#returnNum()
	 */
	@Override
	public int returnNum() {
		// TODO Auto-generated method stub
		return 0;
	}
}
