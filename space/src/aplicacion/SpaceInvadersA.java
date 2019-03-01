package aplicacion;

import java.util.*;

/**
 * Parte del juego que representa lo logico
 * 
 * @author Santiago Rubiano y Yeison Gualdron
 *
 */
public class SpaceInvadersA {

	private ArrayList<NaveA> naves;
	private ArrayList<BarreraA> barreras;
	private ArrayList<BalaA> balasNaves;
	private ArrayList<BalaA> balasAliens;
	private InvasorA[][] invasores;
	private ArrayList<BalaA> disparadas;
	private ArrayList<BalaA> disparadasP;
	private ArrayList<Jugador> jugadores;
	private int col_invasores, numR;
	private int fil_invasores, numJugadores;
	private PlatilloA p;

	/**
	 * Constructor de la clase
	 * 
	 * @param numJugadores numero de jugadores
	 */
	public SpaceInvadersA(int numJugadores) {
		this.disparadas = new ArrayList<BalaA>();
		this.disparadasP = new ArrayList<BalaA>();
		this.naves = new ArrayList<NaveA>();
		this.barreras = new ArrayList<BarreraA>();
		this.col_invasores = 10;
		this.fil_invasores = 5;
		this.invasores = new InvasorA[this.fil_invasores][this.col_invasores];
		this.balasAliens = new ArrayList<BalaA>();
		this.balasNaves = new ArrayList<BalaA>();
		this.jugadores = new ArrayList<Jugador>();
		this.numJugadores = numJugadores;
		p = new PlatilloA(0, 0);
		prepareElementos(numJugadores);
	}

	/**
     * Crea matriz de invasores
	 * @param numJugadores numero de jugadores
	 */
	public void prepareElementos(int numJugadores) {
		// agrego invasores
		int invasorPosX = 70;
		double aux = 30.0;
		for (int i = 0; i < this.fil_invasores; i++) {
			for (int j = 0; j < this.col_invasores; j++) {
				if (i == 0)
					invasores[i][j] = new CalamarA(invasorPosX + (j * 70), aux);
				else if (i == 1)
					invasores[i][j] = new CangrejoA(invasorPosX + (j * 70), aux + (i * 70));
				else if (i == 2)
					invasores[i][j] = new CangrejoA(invasorPosX + (j * 70), aux + (i * 70));
				else if (i == 3)
					invasores[i][j] = new PulpoA(invasorPosX + (j * 70), aux + (i * 70));
				else
					invasores[i][j] = new PulpoA(invasorPosX + (j * 70), aux + (i * 70));
			}
		}
		// agrego barreras
		int barrerasPosX = 200;
		int barrerasPosY = 500;
		for (int k = 0; k < 3; k++) {
			barreras.add(new BarreraA(((k + 1) * barrerasPosX), barrerasPosY));
		}
		prepareJugadores(numJugadores);
	}

	@SuppressWarnings("unused")
	private void prepareJugadores(int numJugadores) {
		int posXnave = 250;
		int posYnave = 700;
		// agrego nave
		for (int t = 0; t < numJugadores; t++) {
			naves.add(new NaveA(posXnave + (t * 200), 700, t));
			jugadores.add(new Jugador());
		}
	}

	/**
	 * Creo de nuevo al jugador que ha sido impactado si hay vidas restantes
	 * 
	 * @param pos posicion
	 */
	public void resucitar(int pos) {
		if (naves.size() > 0) {
			if (naves.get(pos).isVivo() == false && jugadores.get(pos).getVidas() > 0) {
				naves.remove(pos);
				naves.add(pos, new NaveA(400, 700, pos));
			}
		}
	}

	public int numBalas(int x) {
		return naves.get(x).numBalas();
	}

	public int vidasJugador(int pos) {
		return this.jugadores.get(pos).getVidas();
	}

	public String puntosJugador(int pos) {
		return Integer.toString(jugadores.get(pos).getPuntos());
	}

	public double getPosBarreraX(int x) {
		return this.barreras.get(x).getX();
	}

	public double getPosBarreraY(int y) {
		return this.barreras.get(y).getY();
	}

	/**
	 * Metodo para que aliens disparen
	 * 
	 * @return balas disparadas
	 */
	private ArrayList<BalaA> dispareAliens() {
		disparadas.clear();

		int xRelativePos, yRelativePos;
		//
		for (int k = 0; k < 0 + (int) (Math.random() * (1.3)); k++) {
			xRelativePos = 0 + (int) (Math.random() * (fil_invasores));
			yRelativePos = 0 + (int) (Math.random() * (col_invasores));
			if (invasores[xRelativePos][yRelativePos].isVivo()) {
				disparadas.add(invasores[xRelativePos][yRelativePos].ataque());
			}
			this.numR = -100 + (int) (Math.random() * ((100 - (-100)) + 1));
		}
		return disparadas;
	}

	/**
	 * Metodo para que el platillo dispare
	 * 
	 * @return
	 */
	private ArrayList<BalaA> dispareAlienPlatillo() {
		disparadasP.clear();
		// disparadasP.add(p.ataqueP());
		return disparadasP;
	}

	private void moverPlatillo() {
		p.movep();
	}

	public int getfil() {
		return this.fil_invasores;
	}

	public int getNumNaves() {
		return this.naves.size();
	}

	public double getPosNaveX(int pos) {
		return this.naves.get(pos).getPosX();
	}

	public double getPosNaveY(int pos) {
		return this.naves.get(pos).getPosY();
	}

	public int getcol() {
		return this.col_invasores;
	}

	public double getPosAlienX(int x, int y) {
		return this.invasores[x][y].getPosX();
	}
	// hola

	public double getPosAlienY(int x, int y) {
		return this.invasores[x][y].getPosY();
	}

	public boolean estaVivoAlien(int x, int y) {
		return this.invasores[x][y].isVivo();
	}

	public boolean estaVivaBarrera(int x) {
		return this.barreras.get(x).isVivo();
	}

	public int sizeBarreras() {
		return this.barreras.size();
	}

	/**
	 * Mover la nave a partir de las teclas presionadas
	 * 
	 * @param e codigo de tecla
	 */
	public void moverNave(Integer e) {
		if (e == 39) {
			naves.get(0).setPosX(15);
		} else if (e == 37) {
			naves.get(0).setPosX(-15);
		} else if (e == 65) {
			naves.get(1).setPosX(-15);
		} else if (e == 68) {
			naves.get(1).setPosX(15);
		} else if (e == 1) {
			naves.get(1).setPosX(numR);
		}
	}

	/**
	 * Muevo todos los elementos del juego
	 */
	public void moverTodo() {
		moverBalasAliens();
		for (int j = 0; j < balasNaves.size(); j++) {
			balasNaves.get(j).moverBala();
		}
		moverAliens();
		moverPlatillo();
	}

	/**
	 * balas en el espacio quedan en el espacio luego de ser disparadas por los
	 * invasores
	 */
	private void moverBalasAliens() {
		for (int w = 0; w < balasAliens.size(); w++) {
			balasAliens.get(w).moverBala();
		}
	}

	/**
	 * Mueve aliens
	 */
	private void moverAliens() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				invasores[i][j].move();
			}
		}
	}

	/**
	 * Revisar los nuevos estados de todos los elementos del juego
	 */
	public void revisarTodo() {
		// invasores

		for (int w = 0; w < naves.size(); w++) {

			resucitar(w);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					int posBalaX = -1;
					boolean bEstate = invasores[i][j].isVivo();
					boolean aEstate = invasores[i][j].isVivo();
					for (int k = 0; k < balasNaves.size(); k++) {
						invasores[i][j].impactado(balasNaves.get(k));
						if (invasores[i][j].isVivo() == false) {
							posBalaX = k;
							aEstate = invasores[i][j].isVivo();
						}
					}
					if (bEstate == true && aEstate == false) {
						balasNaves.remove(posBalaX);
						// ataqueAlien();
						if (invasores[i][j].returnquienPega() == 1) {
							jugadores.get(0).addPuntuacion(invasores[i][j].getPuntos());
						} else if (invasores[i][j].returnquienPega() == 2) {
							jugadores.get(1).addPuntuacion(invasores[i][j].getPuntos());
						}
					}
				}
				revisarNave(w);
				revisarBarrera();
			}
		}
	}

	/**
	 * Ataque del alien, hace que dispare
	 */
	public void ataqueAlien() {
		ArrayList<BalaA> balasAlienPlatillo = dispareAlienPlatillo();
		ArrayList<BalaA> balasAliens2 = dispareAliens();
		for (int q = 0; q < balasAliens2.size(); q++) {
			balasAliens.add(balasAliens2.get(q));
		}
		for (int q = 0; q < balasAlienPlatillo.size(); q++) {
			balasAliens.add(balasAlienPlatillo.get(q));
		}
	}

	/**
	 * Reviso el estado de la barrera
	 */
	@SuppressWarnings("unused")
	public void revisarBarrera() {
		ArrayList<BarreraA> bars = new ArrayList<BarreraA>();
		for (int b = 0; b < barreras.size(); b++) {
			boolean xEstado = barreras.get(b).isVivo();
			boolean yEstado = barreras.get(b).isVivo();
			int posBalaX = -1;
			int aVida = barreras.get(b).getVida();

			for (int n = 0; n < balasAliens.size(); n++) {
				barreras.get(b).impactado(balasAliens.get(n));
				if (barreras.get(b).getVida() < aVida) {
					posBalaX = n;
					yEstado = false;
				}
			}
			if (barreras.get(b).getVida() < aVida) {
				balasAliens.remove(posBalaX);
			}
			if (barreras.get(b).isVivo()) {
				bars.add(barreras.get(b));
			}
		}
		barreras = bars;
	}

	public int sizeBalasNaves() {
		return this.balasNaves.size();
	}

	public int sizeBalasAliens() {
		return this.balasAliens.size();
	}

	public double getPosBalaNaveX(int x) {
		return this.balasNaves.get(x).getX();
	}

	public double getPosBalaNaveY(int y) {
		return this.balasNaves.get(y).getY();
	}

	public double getPosBalaAlienX(int x) {
		return this.balasAliens.get(x).getX();
	}

	public double getPosBalaAlienY(int y) {
		return this.balasAliens.get(y).getY();
	}

	/**
	 * Reviso el estado de la nave
	 * 
	 * @param pos
	 */
	private void revisarNave(int pos) {
		ArrayList<BalaA> enSpace = new ArrayList<BalaA>();

		boolean beforeN = naves.get(pos).isVivo();
		boolean afterN = naves.get(pos).isVivo();
		int posBalaAlien = 0;
		int vida0 = naves.get(pos).getVida();
		for (int q = 0; q < balasAliens.size(); q++) {
			int vida1 = naves.get(pos).getVida();
			naves.get(pos).impactado(balasAliens.get(q));
			if (vida1 > naves.get(pos).getVida()) {
				posBalaAlien = q;
			}
			if (naves.get(pos).isVivo() == false) {
				afterN = naves.get(pos).isVivo();
			}
		}
		if (vida0 > naves.get(pos).getVida()) {
			balasAliens.remove(posBalaAlien);
		}
		for (int u = 0; u < balasAliens.size(); u++) {
			if (balasAliens.get(u).getY() < 750) {
				enSpace.add(balasAliens.get(u));
			}
		}
		balasAliens = enSpace;
		if (beforeN && !(afterN)) {
			jugadores.get(pos).morir();

		}
	}

	/**
	 * Hace que las naves disparen a partir del codigo del teclado
	 * 
	 * @param e codigo de teclado
	 * @return boolean
	 */
	public boolean initDisparo(Integer e) {
		if (e == 38) {
			if (naves.get(0).numBalas() > 0) {
				BalaA aux = naves.get(0).Disparo();
				aux.setPosX(naves.get(0).getPosX() + 20);
				aux.setPosY(naves.get(0).getPosY());
				aux.setNum(1);
				balasNaves.add(aux);
				return true;
			} else
				return false;
		} else if (e == 87) {
			if (naves.get(1).numBalas() > 0) {
				BalaA aux = naves.get(1).Disparo();
				aux.setPosX(naves.get(1).getPosX() + 20);
				aux.setPosY(naves.get(1).getPosY());
				aux.setNum(2);
				balasNaves.add(aux);
				return true;
			} else
				return false;
		} else if (e == 1) {
			if (naves.get(1).numBalas() > 0) {
				BalaA aux = naves.get(1).Disparo();
				aux.setPosX(naves.get(1).getPosX() + 20);
				aux.setPosY(naves.get(1).getPosY());
				aux.setNum(2);
				balasNaves.add(aux);
				return true;
			} else
				return false;
		} else
			return true;
	}

	public double getPosPlatilloX() {
		return p.getPosX();
	}

	public double getPosPlatilloY() {
		return p.getPosY();
	}

	/**
	 * Reviso si los jugadores siguen con vida
	 * 
	 * @return boolean
	 */
	public boolean jugadoresVivos() {
		if (this.numJugadores == 1) {
			return (vidasJugador(0) > 0);
		} else {
			return (vidasJugador(0) > 0 == vidasJugador(1) > 0);
		}
	}
}
