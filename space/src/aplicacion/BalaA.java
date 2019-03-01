package aplicacion;

/**
 * Representa la bala en la parte el programa que piensa
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public abstract class BalaA {

	protected double posX;
	protected double posY;
	protected int damage;
	protected int damageToBarrera;
	protected int num;

	/**
	 * Constructor de la clase
	 */
	public BalaA() {
		this.posX = 0;
		this.posY = 0;
	}

	public int getDamageBarrera() {

		return damageToBarrera;
	}

	public void setPosX(double x) {
		this.posX = x;
	}

	public void setPosY(double y) {
		this.posY = y;
	}

	public double getX() {
		return this.posX;
	}

	public double getY() {
		return this.posY;
	}

	public int getDamage() {
		return this.damage;
	}

	/**
	 * Revisa si hay colision contra un invasor
	 * 
	 * @param i Invasor
	 * @return si hay o no colision
	 */
	public boolean rColision(InvasorA i) {
		double a = i.getPosX();
		double b = i.getPosY();
		if (posY < 800)
			return true;
		if (posX < a && posX + 3 > a && posY < b && posY + 3 < b) {
			return true;
		} else if (a < posX && a + 15 > posX && b < posY && b + 15 > posY) {
			return true;
		} else if (posX < a && posX + 3 > a && posY > b && posY < b + 15) {
			return true;
		} else if (posX > a && posX < a + 15 && posY < b && posY + 3 > b) {
			return true;
		} else
			return false;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public abstract void moverBala();

	public int returnNum() {
		return this.num;
	}
}
