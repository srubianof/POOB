package aplicacion;

/**
 * Tipo de Invasor
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class PlatilloA extends InvasorA {
	private int moveA, moveB;
	private int s;

	/**
	 * Constructor de la clase
	 * 
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	@SuppressWarnings("unused")
	public PlatilloA(double x, double y) {
		super(x, y);
		moveA = 145;
		moveB = 146;
		int s = 10;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Mover el platillo
	 */
	public void movep() {
		if (moveA > 0) {
			this.posX += 5;
			moveA--;
			moveB--;
		} else if (moveA < 0) {

			this.posX -= 5;
			moveA++;
			moveB++;
		} else {
			if (moveA == 0 && moveB == -1) {
				moveA = 15;
				moveB = 16;
			} else {
				moveA = -15;
				moveB = -16;
			}
		}
	}

	/**
	 * Ataque del invasor
	 * 
	 * @return bala disparada
	 */
	public BalaA ataqueP() {
		BalaA disparo = balas.pop();
		disparo.setPosX(this.posX + 25);
		disparo.setPosY(this.posY + 50);
		return disparo;

	}

	/**
	 * Si puede o no disparar
	 * 
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	private boolean puedeDisparar() {
		// TODO Auto-generated method stub
		if (s == 10) {
			s--;
			return false;
		} else {
			s = 10;
			return true;
		}
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

	/**
	 * Cargo balas del nuevo tipo a ser disparadas
	 */
	public void cargarBalasPlasma() {
		for (int i = 0; i < 100000; i++) {
			balas.push(new Plasma());
		}
	}
}
