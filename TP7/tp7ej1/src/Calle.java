import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

public class Calle extends TiledLayer {
	
	private int delta; //para controlar cuándo reubicarla
	
	public Calle(Image img) {
		super(5, 7, img, 48, 48);
		int mapa[] = {
			2, 1, 1, 1, 3,
			2, 1, 1, 1, 3,
			2, 1, 1, 1, 3,
			2, 1, 1, 1, 3,
			2, 1, 1, 1, 3,
			2, 1, 1, 1, 3,
			2, 1, 1, 1, 3
		};
		for (int i = 0; i < mapa.length; i++){
			int c = i%5;
			int f = (i-c)%7;
			setCell(c, f, mapa[i]);
		}
		delta = 0;
	}
	
	public void Mover(int dy) {
		if (delta >= 48){
			delta = 0;
			setPosition(0, -48);
		}
		else {
			move(0, dy);
			delta+=dy;
		}
	}

}
