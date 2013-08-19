import javax.microedition.lcdui.Graphics;

public class Rectangulo extends Figura {
	
	private int ancho, alto;
	
	public Rectangulo(int x, int y, int ancho, int alto) {
		super(x, y);
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public void paint(Graphics g) {
		
		// Clear the Canvas.
		g.setGrayScale(255);
		g.fillRect(0, 0, width, height);

		//dibujar el rectángulo
		if (fCrece && x + ancho < width && y + alto < height)
			Crecer();
		else //si tocó un límite deja de crecer
			fCrece = false;
		if (fTiembla)
			Temblar();
		if (fColorea)
			Colorear();
		g.setColor(color);
		g.fillRect(x, y, ancho, alto);
		g.setColor(0, 0, 0);
		g.drawRect(x, y, ancho, alto);
		
	}
	
	public void Crecer() {
		ancho++;
		alto++;
	};
	
	public boolean esValido() {
		if (x < 0 || x + ancho > width)
			return false;
		if (y < 0 || y + alto > height)
			return false;
		return true;
	}
}
