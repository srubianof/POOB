package presentacion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Elemento que representa las balas del juego
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Bala extends Objeto {
	private ArrayList<BufferedImage> frames2 = new ArrayList<BufferedImage>();

	/**
	 * Constructor de la clase
	 * 
	 * @param x posicion en x
	 * @param y posicion en y
	 */
	public Bala(double x, double y) {
		this.fil = x;
		this.col = y;
		carga();
	}

	/**
	 * Agregar las imagenes al ArrayList
	 */
	public void carga() {
		frames.add(i.getImagen("Ship_shot.png"));
		frames2.add(i.getImagen("bala0.png"));
		frames2.add(i.getImagen("bala1.png"));
	}

	/**
	 * Dibujar las imagenes
	 * 
	 * @param g    Graphics
	 * @param i    numero
	 * @param tamX tamano en x
	 * @param tamY tamano en y
	 */
	public void draw2(Graphics g, int i, int tamX, int tamY) {
		g.drawImage(frames2.get(i), (int) fil, (int) col, tamX, tamY, null);
	}
}
