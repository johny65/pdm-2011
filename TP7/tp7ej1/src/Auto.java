//clase Auto

//Clase para controlar los autitos (el del jugador y los enemigos)

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Auto extends Sprite {
	
	private Pista p;
	private int d; //paso
	
	public Auto(Image sprite, int w, int h, Pista p){
		super(sprite, w, h);
		this.p = p;
		d = 3; //paso inicial
	}
	
	public void MoverIzquierda() {
		setFrame(2);
		if (getX() > 25)
			move(-d, 0);
	}
	
	public void MoverDerecha() {
		setFrame(1);
		if (getX() + getWidth() < p.getAncho() - 20)
			move(d, 0);
	}
	
	public void MoverArriba() {
		if (getY() > 0){
			setFrame(0);
			move(0, -d);	
		}
	}
	
	public void MoverAbajo() {
		if (getY() + getHeight() < p.getAlto() - 20){
			setFrame(0);
			move(0, d);
		}
	}
	
	//sólo para los enemigos:
	public boolean MoverEnemigo(int dy) {
		if (getY() < p.getAlto() - 20){
			move(0, dy);
			return true;
		}
		return false;
	}
	
	//cambiar el paso
	public void setDelta(int d2) { d = d2; }
	
}
