package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase que representa el estado final del juego
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
@SuppressWarnings("serial")
class gameOver extends JFrame {

	private Dimension screenSize;
	private Imagenes i = new Imagenes();
	private JPanel panel;

	/**
	 * Constructor de la clase
	 */
	public gameOver() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width / 2, screenSize.height / 2);
		panel = new JPanel() {
			BufferedImage image = i.getImagen("gameover.png");

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, screenSize.width / 2, screenSize.height / 2, this);
			}
		};
		this.add(panel);
	}

}