package presentacion;

/**
 * Elemento tipo nave que representa al jugador 1
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Jugador extends Nave {
	protected Imagenes i = new Imagenes();

	/**
	 * Constructor Jugador 1
	 * 
	 * @param fil fila fila
	 * @param col columna columna
	 * @param num numero
	 */
	public Jugador(double fil, double col, int num) {
		// TODO Auto-generated constructor stub
		this.fil = fil;
		this.col = col;
		vivo = true;
		start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see presentacion.Nave#start()
	 */
	@Override
	protected void start() {
		// TODO Auto-generated method stub
		frames.add(i.getImagen("Ship.png"));
		frames.add(i.getImagen("Ship2.png"));
	}

}
