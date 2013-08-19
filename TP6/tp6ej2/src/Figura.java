//Clase Figura
//Para una figura genérica

import java.util.Date;
import java.util.Random;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class Figura extends Canvas implements Runnable {
	
	protected boolean movimiento; //si se mueve o no
	protected int x, y; //posición
	protected int width, height; //pantalla
	protected int mDelay; //velocidad
	protected int color; //color
	
	//efectos:
	protected boolean fTiembla;
	protected boolean fCrece;
	protected boolean fColorea;

	//flags para el temblar
	protected boolean tf; private int ta, temblor;
	
	
	//constructor:
	
	public Figura(int x, int y) {
		this.x = x;
		this.y = y;
		width = getWidth();
		height = getHeight();
		//valores por defecto:
		color = 16711680; //rojo
		mDelay = 50;
		temblor = 2;
		//efectos desactivados por defecto:
		fTiembla = false;
		fCrece = false;
		fColorea = false;
		tf = false;
	}
	
	//configuraciones:
	
	public void setDelay(int d) { mDelay = d; }
	public void setTemblor(int t) { temblor = t; }
	//setear los efectos
	public void setCrece() { fCrece = true;	}
	public void setColorea() { fColorea = true; }
	public void setTiembla() { fTiembla = true; }

	
	//efectos:
	
	public void Crecer() {}; //depende de cada tipo de figura
	
	public void Temblar() {
		int r = 0;
		if (!tf) { //me muevo aleatoriamente a un lugar
			//r = new Random(new Date().getTime()).nextInt(4); //de esta forma anda horrible
			r = new Random(new Date().getTime()).nextInt(Integer.MAX_VALUE) % 4;
			ta = r;
			tf = true;
		}
		else { //vuelvo al origen
			switch (ta){
				case 0: r = 1; break;
				case 1: r = 0; break;
				case 2: r = 3; break;
				case 3: r = 2; break;
			}
			tf = false;
		}
		switch (r) { //ver cómo me muevo, 0: arriba, 1: abajo, 2: izquierda, 3: derecha
			case 0: y-=temblor; break;
			case 1: y+=temblor; break;
			case 2: x-=temblor; break;
			case 3: x+=temblor; break;
		}
		
	}
	
	public void Colorear() {
		//color: 0x00RRGGBB
		//color máximo: FFFFFF = 16777215;
		color = new Random(new Date().getTime()).nextInt(Integer.MAX_VALUE) % 16777216;
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
			repaint();
			try { Thread.sleep(mDelay); }
			catch (InterruptedException ie) {}
		}
	}
	
	public void paint(Graphics g) {}; //depende de cada figura
	public boolean esValido() { return false; }; //depende de cada figura
	
}
