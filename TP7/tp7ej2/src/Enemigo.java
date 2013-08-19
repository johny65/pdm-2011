//clase Enemigo

//Controla los enemigos

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Enemigo extends Sprite {

	private int frames[], current;
	private Image tiro;
	
	public Enemigo(Image img, Image t, int w, int h, int f[]) {
		super(img, w, h);
		frames = f; //puede ser animado
		setFrame(frames[0]);
		current = 0;
		tiro = t;
	}
	
	public void Animar() {
		current = (current+1) % frames.length;
		setFrame(frames[current]);
	}
	
	public Sprite Atacar() {
		Sprite t = new Sprite(tiro);
		t.setPosition(getX() + getWidth()/2 - t.getWidth()/2, getY() + getHeight());
		return t;
	}
}
