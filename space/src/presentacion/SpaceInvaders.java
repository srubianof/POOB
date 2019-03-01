package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Clase menu
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
@SuppressWarnings("serial")
class SpaceInvaders extends JFrame {
	private JPanel panel;
	private Dimension screenSize;
	private static SpaceInvaders spaceinvadersz;
	private SpaceInvadersGUI SpaceInvadersGUIz;
	private JButton play, play2, playA;
	private Imagenes i = new Imagenes();

	/**
	 * Constructor de la clase
	 */
	public SpaceInvaders() {
		super("Space Invaders Game!");

		prepareElementos();
		prepareAcciones();

	}

	/**
	 * Preparar todos los elementos de la ventana
	 */
	public void prepareElementos() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		panel = new JPanel() {
			BufferedImage image = i.getImagen("fondo.png");

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, screenSize.width / 2, screenSize.height / 2, this);
			}
		};

		ImageIcon icon = new ImageIcon(i.getImagen("start.png"));
		play = new JButton("", icon);
		play.setBounds(0, 0, 229, 70);
		play.setBorderPainted(false);
		play.setContentAreaFilled(false);
		play.setFocusPainted(false);
		play.setOpaque(false);
		panel.add(play);

		ImageIcon icon2 = new ImageIcon(i.getImagen("start2.png"));
		play2 = new JButton("", icon2);
		play2.setBounds(0, 0, 229, 70);
		play2.setBorderPainted(false);
		play2.setContentAreaFilled(false);
		play2.setFocusPainted(false);
		play2.setOpaque(false);
		panel.add(play2);

		ImageIcon icon3 = new ImageIcon(i.getImagen("startA.png"));
		playA = new JButton("", icon3);
		playA.setBounds(0, 0, 229, 70);
		playA.setBorderPainted(false);
		playA.setContentAreaFilled(false);
		playA.setFocusPainted(false);
		playA.setOpaque(false);
		panel.add(playA);

		setSize(screenSize.width / 2, screenSize.height / 2);

		this.setContentPane(panel);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		setLocationRelativeTo(null);

	}

	/**
	 * Preparar acciones a ser ejecutadas
	 */
	public void prepareAcciones() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				salga();
			}
		});
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugar(1, "");
			}
		});
		play2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugar(2, "");
			}
		});
		playA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugar(2, "m");
			}
		});
	}

	/**
	 * Si se quiere salir de la ventana, solicitar confirmacion
	 */
	public void salga() {
		int c = JOptionPane.showConfirmDialog(null, "Desea salir?", "EXIT", JOptionPane.YES_NO_OPTION);
		if (JOptionPane.YES_OPTION == c) {
			System.exit(1);
		}
	}

	/**
	 * Inicializar juego
	 */
	public void jugar(int numero, String c) {
		SpaceInvadersGUIz = new SpaceInvadersGUI(this, numero, c);
		SpaceInvadersGUIz.setVisible(true);
		SpaceInvadersGUIz.start();
		spaceinvadersz.setVisible(false);
	}

	/**
	 * Fin del juego
	 */
	public void end() {
		gameOver go = new gameOver();
		go.setVisible(true);
		spaceinvadersz.setVisible(false);
	}

	public static void main(String[] args) {
		spaceinvadersz = new SpaceInvaders();
		spaceinvadersz.setVisible(true);
	}

}