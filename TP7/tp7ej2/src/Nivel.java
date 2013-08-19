//clase Nivel

//Arma los distintos niveles

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

public class Nivel {
	
	private Image img, aliens, tiro;
	
	public Nivel(Image i, Image tm) {
		try {
			img = Image.createImage("/fondos.png");
		} catch (IOException e) {
			System.err.println("Error al cargar fondos.");
		}
		aliens = i;
		tiro = tm;
	}
	

	//estas funciones arman los distintos niveles (el fondo y la disposición
	//de los enemigos):
	
	public TiledLayer getFondoNivel(int nivel) {
		TiledLayer tl = new TiledLayer(1, 5, img, 240, 64);
		switch (nivel){
		case 1:
			tl.setCell(0, 0, 1);
			tl.setCell(0, 1, 3);
			tl.setCell(0, 2, 5);
			tl.setCell(0, 3, 7);
			tl.setCell(0, 4, 10);
			break;
		case 2:
			tl.setCell(0, 0, 1);
			tl.setCell(0, 1, 3);
			tl.setCell(0, 2, 5);
			tl.setCell(0, 3, 7);
			tl.setCell(0, 4, 12);
			break;
		case 3:
			tl.setCell(0, 0, 2);
			tl.setCell(0, 1, 4);
			tl.setCell(0, 2, 6);
			tl.setCell(0, 3, 8);
			tl.setCell(0, 4, 10);
			break;
		case 4:
			tl.setCell(0, 0, 2);
			tl.setCell(0, 1, 4);
			tl.setCell(0, 2, 6);
			tl.setCell(0, 3, 8);
			tl.setCell(0, 4, 14);
			break;
		case 5:
			tl.setCell(0, 0, 9);
			tl.setCell(0, 1, 11);
			tl.setCell(0, 2, 13);
			tl.setCell(0, 3, 15);
			tl.setCell(0, 4, 14);
			break;
		}
		return tl;
	}
	
	public Vector getEnemigosNivel(int nivel) {
		Vector tropa = new Vector();
		switch (nivel){
		case 2: {
			int sec2[] = {0, 1, 2, 3, 4, 5};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec2);
				e.setPosition(24*i, 72);
				tropa.addElement(e);
			}
		}	
		case 1: {
			int sec[] = {7};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec);
				e.setPosition(24*i, 15);
				tropa.addElement(e);
			}
			int sec3[] = {6};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec3);
				e.setPosition(24*i, 34);
				tropa.addElement(e);
			}
			int sec2[] = {0, 1, 2, 3, 4, 5};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec2);
				e.setPosition(24*i, 53);
				tropa.addElement(e);
			}
			break;
		}
		case 4: {
			int sec2[] = {0, 1, 2, 3, 4, 5};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec2);
				e.setPosition(24*i, 91);
				tropa.addElement(e);
			}
		}
		case 3: {
			int sec[] = {7};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec);
				e.setPosition(24*i, 15);
				tropa.addElement(e);
			}
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec);
				e.setPosition(24*i, 34);
				tropa.addElement(e);
			}
			int sec3[] = {6};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec3);
				e.setPosition(24*i, 53);
				tropa.addElement(e);
			}
			int sec2[] = {0, 1, 2, 3, 4, 5};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec2);
				e.setPosition(24*i, 72);
				tropa.addElement(e);
			}
			break;
		}
		case 5: {
			int sec[] = {7};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec);
				e.setPosition(24*i, 15);
				tropa.addElement(e);
			}
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec);
				e.setPosition(24*i, 34);
				tropa.addElement(e);
			}
			int sec3[] = {6};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec3);
				e.setPosition(24*i, 53);
				tropa.addElement(e);
			}
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec3);
				e.setPosition(24*i, 72);
				tropa.addElement(e);
			}
			int sec2[] = {0, 1, 2, 3, 4, 5};
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec2);
				e.setPosition(24*i, 91);
				tropa.addElement(e);
			}
			for (int i=0; i<10; ++i){
				Enemigo e = new Enemigo(aliens, tiro, 24, 18, sec2);
				e.setPosition(24*i, 110);
				tropa.addElement(e);
			}
		}
		}
		return tropa;
		
	}
	
}
