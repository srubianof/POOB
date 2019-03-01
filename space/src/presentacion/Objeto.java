package presentacion;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.*;

/**
 * Elemento que sera dibujado
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class Objeto {
	protected double fil, col;
	protected Imagenes i = new Imagenes();
	protected ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();

	public Objeto() {
		fil = col = 0;
	}

	/**
	 * Metodo para dibujar a partir de un ArrayList de frames
	 * 
	 * @param g    Graphics
	 * @param i		numero
	 * @param tamX tamano en x
	 * @param tamY tamano en y
	 */
	public void draw(Graphics g, int i, int tamX, int tamY) {
		g.drawImage(frames.get(i), (int) fil, (int) col, tamX, tamY, null);
	}
}
