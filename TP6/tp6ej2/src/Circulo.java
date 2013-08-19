import javax.microedition.lcdui.Graphics;

public class Circulo extends Figura {
	
	private int radio, diametro;
	
	public Circulo(int x, int y, int r) {
		super(x-r, y-r);
		radio = r;
		diametro = 2*radio;
	}
	
	public void paint(Graphics g) {
		
		// Clear the Canvas.
		g.setGrayScale(255);
		g.fillRect(0, 0, width, height);

		//dibujar el círculo
		if (fCrece && x + diametro < width && y + diametro < height)
			Crecer();
		else //si tocó un límite deja de crecer
			fCrece = false;
		if (fTiembla)
			Temblar();
		if (fColorea)
			Colorear();
		g.setColor(color);
		g.fillArc(x, y, diametro, diametro, 0, 360);
		g.setColor(0, 0, 0);
		g.drawArc(x, y, diametro, diametro, 0, 360);
		
	}
	
	public void Crecer() {
		radio++;
		diametro = 2*radio;
	};
	
	public boolean esValido() {
		if (x < 0 || x + diametro > width)
			return false;
		if (y < 0 || y + diametro > height)
			return false;
		return true;
	}
}
