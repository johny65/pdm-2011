//clase Escenario:
//controla todo el juego

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class Escenario extends Canvas implements Runnable {
	
	protected boolean movimiento; //si se mueve o no
	protected int mDelay; //velocidad	
	private Harry harry;
	private Mosca m1, m2;
	private int alto, ancho; //pantalla
	
	
	//constructor:
	
	public Escenario() {
		alto = getHeight();
		ancho = getWidth();
		harry = new Harry(ancho/2, alto/2, alto, ancho);
		m1 = new Mosca(ancho-5, alto-5, harry, ancho, alto);
		m2 = new Mosca(5, 5, harry, ancho, alto);
		if (isDoubleBuffered())
			System.out.println("Soporta doble buffer");
		mDelay = 25;
	}
	
	//para el funcionamiento:
	
	public void start() {
		movimiento = true;
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stop() {
		movimiento = false;
	}
	
	public void run() {
		while (movimiento) {
			m1.Mover(m2);
			m2.Mover(m1);
			if (harry.Colisiona(m1) || harry.Colisiona(m2)){
				//lo agarraron, perdió
				stop();
			}
			repaint();
			try { Thread.sleep(mDelay); }
			catch (InterruptedException ie) {}
		}
	}
	
	public void paint(Graphics g) {
		if (movimiento){ //todavía en juego
			g.setColor(132, 164, 110);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		else {
			g.setColor(144, 63, 52);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		harry.Dibujarse(g);
		m1.Dibujar(g);
		m2.Dibujar(g);	
	};

	
	//detectar movimiento del teclado:
	
	protected void keyPressed(int key) {
		int gameAction = getGameAction(key);
		switch(gameAction) {
			case UP: harry.moverArriba(); break;
			case DOWN: harry.moverAbajo(); break;
			case LEFT: harry.moverIzquierda(); break;
			case RIGHT: harry.moverDerecha(); break;
		}
	}
	
	protected void keyRepeated(int key) {
		keyPressed(key);
	}

}
