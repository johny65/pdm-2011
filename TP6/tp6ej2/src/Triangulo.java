import javax.microedition.lcdui.Graphics;

public class Triangulo extends Figura {
	
	private int h, b;
	
	public Triangulo(int x, int y, int altura, int base) {
		super(x, y); //(x,y) indica la punta del triángulo
		h = altura;
		b = base;
	}
	
	public void paint(Graphics g) {
		
		// Clear the Canvas.
		g.setGrayScale(255);
		g.fillRect(0, 0, width, height);

		//dibujar el triángulo
		if (fCrece && x - b/2 > 0 && x + b/2 < width && y + h < height) 
			Crecer();
		else //si tocó un límite deja de crecer
			fCrece = false;
		if (fTiembla)
			Temblar();
		if (fColorea)
			Colorear();
		g.setColor(color);
		g.fillTriangle(x, y, x-(b/2), y+h, x+(b/2), y+h);
		g.setColor(0, 0, 0);
		g.drawLine(x, y, x-(b/2), y+h);
		g.drawLine(x-(b/2), y+h, x+(b/2), y+h);
		g.drawLine(x+(b/2), y+h, x, y);
		
	}
	
	public void Crecer() {
		h++;
		b++;
	};
	
	public boolean esValido() {
		if (x < 0 || x + b/2 > width)
			return false;
		if (y < 0 || y + h > height)
			return false;
		return true;
	}
}
