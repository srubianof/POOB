package aplicacion;

/**
 * Representa en la parte que piensa las barreras del juego
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class BarreraA {
	private double posX, posY;
	private int vida;
	private boolean muerto;

	/**
	 * Constructor de la clase
	 * 
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public BarreraA(double x, double y) {
		this.posX = x;
		this.posY = y;
		this.vida = 30;
		this.muerto = false;
	}

	public int getVida() {
		return this.vida;
	}

	/**
	 * Revisa si esta barrera esta o no viva
	 * 
	 * @return si esta o no viva
	 */
	public boolean isVivo() {
		return !(this.muerto);
	}

	public double getX() {
		return this.posX;
	}

	public double getY() {
		return this.posY;
	}

	/**
	 * Revisa si esta barrera ha sido impactada o no por una bala
	 * 
	 * @param bala bala que puede impactar
	 */
	public void impactado(BalaA bala) {
		double xBala = bala.getX();
		double yBala = bala.getY();
		if (isLess(this.posX, xBala) && isLess(this.posY, yBala)
				|| isLess(this.posX, xBala + 18) && isLess(this.posY, yBala)
				|| isLess(this.posX, xBala) && isLess(this.posY, yBala + 18)
				|| isLess(this.posX, xBala + 18) && isLess(this.posY, yBala + 18)) {
			update(bala.getDamageBarrera());
		}
	}

	private boolean isLess(double r1, double r2) {
		return (r1 <= r2 && r2 <= (r1 + 50));
	}

	/**
	 * Actualizar la vida de la barrera
	 * 
	 * @param damage dano de la bala que golpeo
	 */
	public void update(int damage) {

		this.vida -= damage;
		if (this.vida <= 0) {
			this.muerto = true;
		}
	}
}