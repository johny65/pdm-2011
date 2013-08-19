import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class Pelotita extends Canvas implements Runnable {
	
	private boolean mTrucking;
	private int x, y, dirx, diry;
	private int width, height;
	private int radio;
	private int mDelay;
	
	public Pelotita(int dx, int dy) {
		dirx = dx;
		diry = dy;
		width = getWidth();
		height = getHeight();
		mDelay = 20;
		radio = 25;
		x = width/2 - radio/2;
		y = height/2 - radio/2;
	}
	
	public void start() {
		mTrucking = true;
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stop() {
		mTrucking = false;
	}
	
	public boolean getEstado() {
		return mTrucking;
	}

	public void paint(Graphics g) {
		
		// Clear the Canvas.
		g.setGrayScale(255);
		g.fillRect(0, 0, width, height);
		
		x += dirx;
		y += diry;
		if (x + radio > width || x < 0) dirx *= -1;
		if (y + radio > height || y < 0) diry *= -1;
		
		g.setColor(255, 255, 0);
		g.fillArc(x, y, radio, radio, 0, 360);
		g.setColor(0, 0, 0);
		g.drawArc(x, y, radio, radio, 0, 360);
		
	}
	
	public void run() {
		while (mTrucking) {
			repaint();
			try { Thread.sleep(mDelay); }
			catch (InterruptedException ie) {}
		}
	}
	
}
