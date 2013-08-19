//clase Cronometro

//Para controlar el disparo de los enemigos

import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {

	private Timer timer;
	private TimerTask tt;
	private long tiempo;
	private Mundo padre;

	public Cronometro(Mundo m) {
		padre = m;
	}

	public void setTiempo(long t) {
		tiempo = t;
	}
	
	public void ControlarAtaque() {
		tt = new TimerTask() {
			public void run() {
				padre.MalosAtacan(); //avisar que pueden atacar
				ControlarAtaque(); //para programarlo de nuevo
			}
		};
		timer = new Timer();
		timer.schedule(tt, tiempo);
	}
	
}
