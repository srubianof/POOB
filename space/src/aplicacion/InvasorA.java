package aplicacion;
import java.util.*;


/**
 * Clase que representa a todos los invasores del juego
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public abstract class InvasorA{
	protected double posX,posY;
	protected int vida;
	protected boolean muerto;
	protected int moveA,moveB;
	protected Stack<BalaA> balas;
	protected int puntos;
	protected int quienPega;

	/**
	 * Constructor de la clase
	 * 
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public InvasorA (double x, double y) {
		this.posX= x;
		this.posY=y;
		this.vida= 10;	
		this.moveA = 5;
		this.moveB = 6;
		this.muerto = false;
		this.balas = new Stack<BalaA>();
		
		cargarBalas();
	}
	public int getPuntos() {return this.puntos;}
	
	/**
	 *  Retorna si el invasor esta o no vivo
	 * @return si esta o no vivo
	 */
	public boolean isVivo(){
		return !(this.muerto);
	}
	
	/**
	 * Cargo balas a ser disparadas
	 */
	protected abstract void cargarBalas();
	
	/** 
	 * Actualizo la vida del invasor 
	 * @param damage dano de la bala
	 * @param qp quein pega
	 */
	public void update(int damage,int qp) {
		this.vida -= damage;
		this.quienPega=qp;
		if(this.vida  <= 0) {
			this.muerto = true;
		}
	}
	
	/**
	 * Mover invasor
	 */
	public void move() {
		if (moveA > 0) {
			this.posX += 5;
			moveA--;
			moveB--;
		} else if (moveA < 0) {

			this.posX -= 5;
			moveA++;
 			moveB++;
		}
		else {
			if(moveA ==0 && moveB==-1){
				moveA = 15;
				moveB =16;
			}
			else {
				moveA = -15;
				moveB = -16;
			}
			this.posY +=5;
		}
	}
	
	/**
	 *  Revisa si el invasor es impactado por una bala
	 * @param bala bala que puede pegar
	 */
	public void impactado(BalaA bala) {
		double xBala  = bala.getX();
		double yBala = bala.getY();
		
		if( isLess(this.posX, xBala) && isLess(this.posY,yBala) ) {
			update(bala.getDamage(),bala.returnNum());
		}
	}
	
	//x1 is less than x2
	/**
	 * Saber si me impactaron dentro de mi tamano
	 * @param x1
	 * @param x2
	 * @return boolean
	 */
	private boolean isLess(double x1, double x2) {
		//x1 of InvasorPos
		return ( x1 <= x2 &&  x2 <= (x1+50));	
	}
	/**
	 * Saco bala del arraylist para ser agregada a las balas a ser dibujadas 
	 * 
	 * @return booelan
	 */
	public BalaA ataque(){
		BalaA disparo = balas.pop();
		disparo.setPosX(this.posX+25);
		disparo.setPosY(this.posY+50);
		return disparo;	
	} 
	
	public double getPosY(){ return this.posY; }
	public double getPosX(){ return this.posX; }
	public void setPosX(double x){ this.posX =x; }
	public void setPosY(double y){ this.posY = y;}
	public int returnquienPega() {
		return this.quienPega;
	}
}
