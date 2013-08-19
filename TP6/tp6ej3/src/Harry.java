//clase Harry:
//controla al personaje

import javax.microedition.lcdui.Graphics;

public class Harry {
	
	private int x, y, r, paso;
	protected int w, h; //pantalla
	
	public Harry(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		r = 15; //radio
		paso = 3; //paso
		w = width;
		h = height;
	}
	
	public void Dibujarse(Graphics g) {
		g.setColor(255, 224, 165);
		g.fillArc(x-r, y-r, r+r, r+r, 0, 360); //piel
		g.setColor(255, 255, 255);
		g.fillArc(x-8, y-5, 6, 6, 0, 360); //ojo izquierdo
		g.fillArc(x+3, y-5, 6, 6, 0, 360); //ojo derecho
		g.setGrayScale(0);
		g.drawArc(x-r, y-r, r+r, r+r, 0, 360); //contorno de la cabeza
		g.drawArc(x-8, y-5, 6, 6, 0, 360); //contorno ojo izquierdo
		g.fillArc(x-6, y-3, 3, 3, 0, 360); //punto ojo izquiero
		g.drawArc(x+3, y-5, 6, 6, 0, 360); //contorno ojo derecho
		g.fillArc(x+5, y-3, 3, 3, 0, 360); //punto ojo derecho
	}

	//movimientos:
	
	public void moverAbajo() { if (y+r < h) y += paso; }
	public void moverArriba() { if (y-r > 0) y -= paso; }
	public void moverDerecha() { if (x+r < w) x += paso; }
	public void moverIzquierda() { if (x-r > 0) x -= paso; }

	//colisión con las moscas:
	
	public boolean Colisiona(Mosca m) {
		int mx = m.getX();
		int my = m.getY();
		if (Math.sqrt((x-mx)*(x-mx) + (y-my)*(y-my)) <= r+2)
			return true;
		return false;
	}
	
	//datos:
	
	public int getX() { return x; }
	public int getY() { return y; }
	
}
