/**
 * 
 */
package aplicacion;

/**
 * @author 2127790
 *
 */
public class Rana extends InvasorA{

	/**
	 * Constructor de la clase
	 * @param x pos en x
	 * @param y pos en y
	 */
	public Rana(double x,double y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		this.puntos = 100;
	}
	public void cargarBalas() {
		for(int i =  0; i <100000; i++ ) {
				balas.push(new BalaAlien());
		}
	}
	public void move() {
		if (moveA > 0) {
			this.posX += 5;
			if(moveA > 7) {
				this.posY += 3;
			}
			else {
				this.posY -= 3;
			}
			
			moveA--;
			moveB--;
		} else if (moveA < 0) {
			this.posX -= 5;
			if(moveA > -7) {
				this.posY += 3;
			}
			else {
				this.posY -= 3;
			}
			
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
			this.posY += 3;
		}
	}
}
