//clase Mosca
//controla el movimiento de las moscas

import java.util.Date;
import java.util.Random;
import javax.microedition.lcdui.Graphics;

public class Mosca {

	private int x, y; //coordenadas para dibujar en pantalla
	private float fx, fy, vx, vy; //posición y dirección
	private Harry victima;
	private int w, h; //pantalla
	
	public Mosca(int x, int y, Harry H, int width, int height) {
		this.fx = x; this.fy = y;
		this.x = x; this.y = y;
		victima = H;
		w = width;
		h = height;
	}
	
	public void Dibujar(Graphics g) {
		g.setGrayScale(0);
		g.fillArc(x-2, y-2, 4, 4, 0, 360);
		g.drawArc(x-3, y-3, 2, 2, 0, 360);
		g.drawArc(x, y-3, 2, 2, 0, 360);
	}
	
	public void Mover(Mosca otra) {
		
		//si tengo la otra mosca cerca, aparecer aleatoriamente en otra
		//posición, para dar más dificultad al juego
		int mx = otra.getX();
		int my = otra.getY();
		if ((mx-x)*(mx-x) + (my-y)*(my-y) <= 4){
			x = new Random(new Date().getTime()).nextInt(w);
			y = new Random(new Date().getTime()).nextInt(h);
			fx = x; fy = y;
			return;
		}
		
		//seguir a Harry:
		int hx = victima.getX();
		int hy = victima.getY();
		vx = hx - fx;
		vy = hy - fy;
		float norm = (float) Math.sqrt(vx*vx + vy*vy);
		vx /= norm;
		vy /= norm;		
		fx += vx;
		fy += vy;	
		x = (int)fx;
		y = (int)fy;
		
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
}
