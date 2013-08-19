import javax.microedition.lcdui.Graphics;

public class Cuadrado extends Figura {
	
	private int lado;
	
	public Cuadrado(int x, int y, int lado) {
		super(x, y);
		this.lado = lado;
	}
	
	public void paint(Graphics g) {
		
		// Clear the Canvas.
		g.setGrayScale(255);
		g.fillRect(0, 0, width, height);

		//dibujar el cuadrado
		if (fCrece && x + lado < width && y + lado < height)
			Crecer();
		else //si tocó un límite deja de crecer
			fCrece = false;
		if (fTiembla)
			Temblar();
		if (fColorea)
			Colorear();
		g.setColor(color);
		g.fillRect(x, y, lado, lado);
		g.setColor(0, 0, 0);
		g.drawRect(x, y, lado, lado);
		
	}
	
	public void Crecer() {
		lado++;
	};
	
	public boolean esValido() {
		if (x < 0 || x + lado > width)
			return false;
		if (y < 0 || y + lado > height)
			return false;
		return true;
	}
}
