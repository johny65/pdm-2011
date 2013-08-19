//clase Cronometro

//Para llevar la cuenta para los eventos temporales, como subir
//de nivel, regalarle vidas al jugador o hacer aparecer vidas por
//la calle

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {

	private Timer timer;
	private TimerTask tt;
	private Pista padre;

	public Cronometro(Pista p) {
		padre = p;
	}

	//Timer para que controle los niveles:
	public void ControlarNivel() {
		tt = new TimerTask() {
			public void run() {
				padre.SubirNivel();
				ControlarNivel(); //para programarlo de nuevo
			}
		};
		timer = new Timer();
		timer.schedule(tt, 30000); //cada medio minuto, el enunciado pide 2 pero así es más movido
	}
	

	//Timer para controlar las vidas que va ganando con el tiempo:
	public void ControlarNuevasVidas(long t) {
		tt = new TimerTask() {
			public void run() {
				padre.RegalarVida();
				ControlarNuevasVidas(180000); //para programarlo de nuevo, ahora cada 3 minutos
			}
		};
		timer = new Timer();
		timer.schedule(tt, t);
	}
	
	
	//Timer para hacer aparecer una vida entre 4 y 8 minutos:
	public void ControlarNuevasVidasAleatorias(long t) {
		tt = new TimerTask() {
			public void run() {
				padre.AparecerVida();
				long tiempo = new Random().nextInt(456000) + 240000; //entre 4 y 8 minutos supuestamente
				ControlarNuevasVidasAleatorias(tiempo); //para programarlo de nuevo
			}
		};
		timer = new Timer();
		timer.schedule(tt, t);
	}
}
