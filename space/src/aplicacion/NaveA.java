package aplicacion;

import java.util.*;

/**
 * Representa a las naves del juego
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class NaveA {
	protected boolean estado;
	protected Stack<BalaA> balas;
	protected double posX, posY;
	protected int vida;
	protected boolean muerto;
	private int num;

	/**
	 * Constructor de la clase
	 * 
	 * @param x   posicion en x
	 * @param y   posicion en y
	 * @param num numero
	 */
	public NaveA(double x, double y, int num) {
		balas = new Stack<BalaA>();
		this.posX = x;
		this.posY = y;
		this.vida = 10;
		this.muerto = false;
		this.num = num;
		cargarBalas(100);
	}

	public int numBalas() {
		return this.balas.size();
	}

	public int getVida() {
		return this.vida;
	}

	/**
	 * Retorna si esta o no vivo
	 * 
	 * @return si esta o no vivo
	 */
	public boolean isVivo() {
		return !(this.muerto);
	}

	/**
	 * Cargo balas a ser disparadas
	 * 
	 * @param nBalas numero de balas
	 */
	public void cargarBalas(int nBalas) {
		for (int i = 0; i < nBalas; i++) {
			balas.push(new BalaNormal(num));
		}
	}

	public void setPosX(double x) {
		this.posX += x;
		posX = posX < 1 ? 1 : posX;
		posX = posX > 740 ? 740 : posX;
	}

	public void setPosY(double y) {
		this.posY += y;
	}

	public double getPosX() {
		return this.posX;
	}

	public double getPosY() {
		return this.posY;
	}

	public boolean hayBalas() {
		return !(balas.empty());
	}

	/**
	 * Disparo de la nave
	 * 
	 * @return bala disparada
	 */
	public BalaA Disparo() {
		BalaA disparo = balas.pop();
		disparo.setPosX(this.posX);
		disparo.setPosY(this.posY - 0.05);
		return disparo;
	}

	/**
	 * Revisa si es o no impactado por una bala
	 * 
	 * @param bala bala 
	 */
	public void impactado(BalaA bala) {
		double xBala = bala.getX();
		double yBala = bala.getY();

		if (isLess(this.posX, xBala) && isLess(this.posY, yBala)) {
			update(bala.getDamage());
		}
	}

	private boolean isLess(double r1, double r2) {
		return (r1 <= r2 && r2 <= (r1 + 50));
	}

	/**
	 * Actualizo la vida por el dano de una bala
	 * 
	 * @param damage dano
	 */
	public void update(int damage) {
		this.vida -= damage;
		if (this.vida <= 0) {
			this.muerto = true;
		}
	}
}
