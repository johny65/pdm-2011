//clase Megaman

//Controla al jugador principal

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Megaman extends Sprite {
	
	private int d;
	private Mundo m;
	private Image tiro;
	
	public Megaman(Mundo m, Image i, Image t, int w, int h) {
		super(i, w, h);
		this.m = m;
		tiro = t;
		d = 2; //paso del movimiento
	}
	
	public void MoverIzquierda() {
		setFrame(1);
		if (getX() > 0)
			move(-d, 0);
	}
	
	public void MoverDerecha() {
		setFrame(0);
		if (getX() + getWidth() < m.getAncho())
			move(d, 0);
	}
	
	public Sprite Tirar() {
		Sprite nuevotiro = new Sprite(tiro, 6, 8);
		if (getFrame() == 1) //está mirando para la izquierda
			nuevotiro.setPosition(getX(), getY()-nuevotiro.getY());
		else //está mirando para la derecha
			nuevotiro.setPosition(getX()+getWidth()-nuevotiro.getWidth(), getY()-nuevotiro.getY());
		return nuevotiro;
	}
	
}
