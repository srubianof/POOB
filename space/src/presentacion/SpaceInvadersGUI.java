package presentacion;

import aplicacion.SpaceInvadersA;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Clase SpaceInvadersGUI se encarga de contener el juego en un JFrame
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
@SuppressWarnings("serial")
public class SpaceInvadersGUI extends JFrame {
	private Juego juego;
	@SuppressWarnings("unused")
	private boolean running, end;
	private String modo;
	SpaceInvaders ii;
	@SuppressWarnings("unused")
	private int w, h;
	private SpaceInvadersA sp;
	private final Set<Integer> pressed = new HashSet<Integer>();

	/**
	 * Constructor de la clase presentacion
	 * 
	 * @param spm  juego
	 * @param num  numero
	 * @param modo modo
	 */
	public SpaceInvadersGUI(SpaceInvaders spm, int num, String modo) {
		super("Invaders");
		this.ii = spm;
		this.modo = modo;
		sp = new SpaceInvadersA(num);
		juego = new Juego(sp, num);
		prepareElementos();
		prepareAcciones();
	}

	/**
	 * Preparo los elementos del JFrame
	 */
	public void prepareElementos() {

		setSize(new Dimension(800, 800));
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(juego, BorderLayout.NORTH);
		setBounds(0, 0, 800, 800);
		this.w = getWidth();
		this.h = getHeight();
	}

	/**
	 * Preparo las acciones que recive el JFrame
	 */
	public void prepareAcciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				pressed.add(e.getKeyCode());
				if (pressed.size() > 1) {
					for (Integer c : pressed) {
						sp.moverNave(c);
						if (sp.initDisparo(c) == false) {
							sinBalas();
						}
					}
				} else {
					Integer r = e.getKeyCode();
					sp.moverNave(r);
					if (sp.initDisparo(r) == false) {
						sinBalas();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pressed.remove(e.getKeyCode());
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
	}

	/**
	 * Advertencia de que no hay mas balas a ser disparadas
	 */
	public void sinBalas() {
		JOptionPane.showMessageDialog(this, "No hay Balas :(");
	}

	/**
	 * Clase que controlara el JPanel, y todo lo que se dibuje
	 * 
	 * @author santiago
	 *
	 */
	private class Juego extends JPanel {

		Imagenes i = new Imagenes();
		private Objeto player;
		private Integer num;

		/**
		 * Constructor de la clase
		 * 
		 * @param sp (la parte del programa que se encarga de pensar)
		 */
		public Juego(SpaceInvadersA sp, int num) {
			this.num = num;
			setPreferredSize(new Dimension(800, 800));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
//			Dibujar fondo de JPanel
			g.drawImage(i.getImagen("space.png"), 0, 0, getWidth(), getHeight(), this);
			long startFrameTime = (long) (999999999 * Math.random());
			int Mx = (int) ((startFrameTime + System.nanoTime()) * Math.abs(-5) * 0.000000001) % 2;
//			Agrego un nuevo jugador grafico 

//			Dibujo a este jugador 
			for (int r = 0; r < sp.getNumNaves(); r++) {
				player = new Jugador(sp.getPosNaveX(r), sp.getPosNaveY(r), r);
				if (sp.vidasJugador(r) > 0) {
					player.draw(g, r, 60, 60);
				}
			}

			if (sp.getNumNaves() == 1) {
				drawVidasyPuntos(g);
			} else {
				drawVidasyPuntos2(g);
			}

			drawInvaders(g, Mx);
			drawBalas(g, Mx);
			drawBarreras(g);
		}

		/*
		 * Dibujo el tablero de aliens a ser eliminados
		 */
		private void drawInvaders(Graphics g, int Mx) {
			for (int i = 0; i < sp.getfil(); i++) {
				for (int j = 0; j < sp.getcol(); j++) {
					if (sp.estaVivoAlien(i, j)) {
						if (i == 0) {
							Objeto c = new Calamar(sp.getPosAlienX(i, j), sp.getPosAlienY(i, j));
							c.draw(g, 0, 60, 60);
						} else if (i == 1) {
							Objeto c = new Cangrejo(sp.getPosAlienX(i, j), sp.getPosAlienY(i, j));
							c.draw(g, 0, 60, 60);
						} else if (i == 2) {
							Objeto c = new Cangrejo(sp.getPosAlienX(i, j), sp.getPosAlienY(i, j));
							c.draw(g, Mx, 60, 60);
						} else if (i == 3) {
							Objeto c = new Pulpo(sp.getPosAlienX(i, j), sp.getPosAlienY(i, j));
							c.draw(g, Mx, 60, 60);
						} else {
							Objeto c = new Pulpo(sp.getPosAlienX(i, j), sp.getPosAlienY(i, j));
							c.draw(g, Mx, 60, 60);
						}
					}
				}
			}
		}

//		Agrego un nuevo Platillo 
//		Objeto p = new Platillo(sp.getPosPlatilloX(), sp.getPosPlatilloY());

		/*
		 * Agrego las balas a ser dibujadas
		 */
		private void drawBalas(Graphics g, int Mx) {
			for (int s = 0; s < sp.sizeBalasNaves(); s++) {
				Objeto bbbb = new Bala(sp.getPosBalaNaveX(s), sp.getPosBalaNaveY(s));
				bbbb.draw(g, 0, 20, 20);
			}

			for (int r = 0; r < sp.sizeBalasAliens(); r++) {
				Bala bala2 = new Bala(sp.getPosBalaAlienX(r), sp.getPosBalaAlienY(r));
				bala2.draw2(g, Mx, 20, 20);
			}
		}

		/**
		 * Dibujo las barreras
		 * 
		 * @param g
		 */
		private void drawBarreras(Graphics g) {
			for (int b = 0; b < sp.sizeBarreras(); b++) {
				if (sp.estaVivaBarrera(b)) {
					Barrera barrera = new Barrera(sp.getPosBarreraX(b), sp.getPosBarreraY(b));
					barrera.draw(g, 0, 60, 60);
				}
			}
		}

		/**
		 * Dibujar las vidas de la nave y los puntos de esta
		 * 
		 * @param g
		 */
		private void drawVidasyPuntos(Graphics g) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.setColor(Color.WHITE);
			g.drawString("PUNTUACION: ", 10, 30);
			g.drawString(sp.puntosJugador(0), 220, 32);
			g.drawString("VIDAS:", 575, 30);

			for (int i = 0; i < sp.vidasJugador(0); i++) {
				Objeto c = new Jugador(650 + ((i + 1) * 30), 10, i);
				c.draw(g, 0, 20, 20);
			}
			g.drawString("BALAS:", 10, 650);
			g.drawString(Integer.toString(sp.numBalas(0)), 115, 650);
		}

		private void drawVidasyPuntos2(Graphics g) {

			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			g.drawString("PUNTUACION 1: ", 10, 20);
			g.drawString("PUNTUACION 2: ", 10, 40);
			g.drawString(sp.puntosJugador(0), 220, 20);
			g.drawString(sp.puntosJugador(1), 220, 40);
			g.drawString("VIDAS 1:", 575, 20);
			g.drawString("VIDAS 2:", 575, 40);
			for (int i = 0; i < sp.vidasJugador(0); i++) {
				Objeto c = new Jugador(650 + ((i + 1) * 30), 0, 0);
				c.draw(g, 0, 20, 20);
			}
			for (int i = 0; i < sp.vidasJugador(1); i++) {
				Objeto c = new Jugador(650 + ((i + 1) * 30), 20, 1);
				c.draw(g, 1, 20, 20);
			}
			g.drawString("BALAS 1:", 10, 650);
			g.drawString(Integer.toString(sp.numBalas(0)), 115, 650);
			g.drawString("BALAS 2:", 10, 670);
			g.drawString(Integer.toString(sp.numBalas(1)), 115, 670);
		}
	}

	/**
	 * Clase que implementa runnable para poder usar e iniciar el thread
	 * 
	 * @author santiago
	 *
	 */
	private class MainLoop implements Runnable {
		private int dis = 5;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			long frameRate = 100;
			while (running && sp.jugadoresVivos()) {

				long startTime = System.currentTimeMillis();
				// update();
				sp.moverTodo();
				sp.revisarTodo();
				sp.ataqueAlien();
				if (modo == "m") {
					sp.moverNave(1);
					if (dispara()) {
						if (sp.initDisparo(1) == false) {
							sinBalas();
						}
					}
				}
				repaint();
				while (System.currentTimeMillis() - startTime < frameRate) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException ex) {
					}
				}
			}
			if (!sp.jugadoresVivos()) {
				end = true;
				SpaceInvaders ii = new SpaceInvaders();
				ii.end();
				end();
			}
		}

		/**
		 * Permite limitar la cantidad de disparos
		 * 
		 * @return boolean
		 */
		public boolean dispara() {
			dis--;
			if (dis < 0) {
				dis = 5;
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Inicio del juego y del thread
	 */
	public void start() {
		if (running) {
			return;
		}
		running = true;
		Thread thread = new Thread(new MainLoop());
		thread.start();
	}

	/**
	 * Fin del juego
	 */
	public void end() {
		this.dispose();
	}
}
