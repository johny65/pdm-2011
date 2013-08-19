//clase Mundo

//La clase principal del juego, controla todo

import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;


public class Mundo extends GameCanvas implements Runnable {
	
	private SpaceInvaders juego;
	
	//configuraciones
	private int mDelay, nivel, vidas, delay_tiros, acum_closer, delay_closer;
	private boolean mov, nuevonivel;
	
	//imágenes
	private Image mega, aliens, tirobueno, tiromalo, vida;
	
	//objetos
	private Megaman heroe;
	private Nivel niveles;
	private LayerManager lm;
	private TiledLayer fondo;
	
	private Vector tiros_buenos, tiros_malos, tropa, spritesvidas;
	private Cronometro timer_tiros;
	
	
	//constructor:
	
	public Mundo(SpaceInvaders j, Image m, Image a, Image tb, Image tm, Image v) {
		
		super(true);
		juego = j;
		mDelay = 30;

		//asignar imágenes:
		mega = m;
		aliens = a;
		tirobueno = tb;
		tiromalo = tm;
		vida = v;
		
		//configuraciones iniciales:
		vidas = 4; //cantidad de vidas
		nivel = 0; //nivel actual
		nuevonivel = false; //si hay que pasar de nivel
		delay_tiros = 0; //acumulador para tener un delay entre mis disparos
		delay_closer = 2000; //tiempo en que bajan un poquito los enemigos
		acum_closer = 0; //acumulador para delay_closer
		
		//crear nuevos objetos:
		niveles = new Nivel(aliens, tiromalo);
		heroe = new Megaman(this, mega, tirobueno, 31, 40);
		heroe.setPosition(getWidth()/2 - heroe.getWidth()/2, getHeight()-50);
		lm = new LayerManager();
		lm.append(heroe);
		timer_tiros = new Cronometro(this);
		timer_tiros.ControlarAtaque();
		SubirNivel(true); //arrancar en nivel 1
		IniciarVidas();
	}
	
	
	//dibujado:
	
	private void Dibujar(Graphics g) {
		
		//acumular:
		delay_tiros += mDelay;
		acum_closer += mDelay;
		
		
		//pasó de nivel?
		if (nuevonivel){
			SubirNivel(false);
			nuevonivel = false;
		}
		
		//movimientos del teclado:
		int ks = getKeyStates();
		if ((ks & LEFT_PRESSED) != 0)
			heroe.MoverIzquierda();
		else if ((ks & RIGHT_PRESSED) != 0)
			heroe.MoverDerecha();
//		else if ((ks & UP_PRESSED) != 0){
//			//CHEAT!!!!!!!!!!!!!!!!!! (pasar de nivel con UP)
//			for (int i=0; i<tropa.size(); ++i){
//				Enemigo e = (Enemigo)tropa.elementAt(i);
//				lm.remove(e);
//			}
//			tropa.removeAllElements();
//			nuevonivel = true;
//		}
		if ((ks & FIRE_PRESSED) != 0){
			if (delay_tiros > 150){ //poner un poco de delay al tirar tiros
				Sprite tiro = heroe.Tirar();
				tiros_buenos.addElement(tiro);
				lm.insert(tiro, 0);
				delay_tiros = 0;
			}
		}
		
		//dibujar enemigos:
		for (int i=0; i<tropa.size(); ++i){
			Enemigo e = (Enemigo)tropa.elementAt(i);
			if (acum_closer > delay_closer){ //bajarlos un poco
				e.move(0, 2);
				if (e.getY()+e.getHeight() > getHeight()){
					//si llegan al piso, se pierde el juego de una
					stop();
					juego.GameOver("Has perdido el juego. Intenta otra vez.");
					return;
				}
			}
			e.Animar();
		}
		
		if (acum_closer > delay_closer)
			acum_closer = 0;
		
		//mover tiros malos:
		for (int i=0; i<tiros_malos.size(); ++i){
			Sprite t = (Sprite)tiros_malos.elementAt(i);
			t.move(0, 3);
			//ver si se fue de la pantalla
			if (t.getY() + t.getHeight() > getHeight()){
				lm.remove(t);
				tiros_malos.removeElementAt(i);
				i--;
			}
			else { //ver si me mató
				if (t.collidesWith(heroe, true)){
					lm.remove(t);
					tiros_malos.removeElementAt(i);
					i--;
					//System.out.println("ouch!");
					vidas--;
					if (vidas == 0){ //sin vidas
						stop();
						juego.GameOver("Has perdido el juego. Intenta otra vez.");
						return;
					}
					//quitar el dibujito de la vida
					Sprite v = (Sprite)spritesvidas.elementAt(vidas);
					lm.remove(v);
				}
			}
		}
		
		//mover tiros de megaman:
		for (int i=0; i<tiros_buenos.size(); ++i){
			Sprite t = (Sprite)tiros_buenos.elementAt(i);
			t.move(0, -4);
			//ver si se fue de la pantalla
			if (t.getY() + t.getHeight() < 0){
				lm.remove(t);
				tiros_buenos.removeElementAt(i);
				i--;
			}
			else {
				//ver si mató a alguno
				for (int j=0; j<tropa.size(); ++j){
					Enemigo e = (Enemigo)tropa.elementAt(j);
					if (t.collidesWith(e, false)){
						lm.remove(t);
						tiros_buenos.removeElementAt(i);
						i--;
						lm.remove(e);
						tropa.removeElementAt(j);
						if (tropa.size() == 0) //si maté a todos, pasar de nivel
							nuevonivel = true;
					}
				}
			}
		}
		
		//dale, dibujar
		lm.paint(g, 0, 0);
		
	}
	
	
	
	//cosas del thread:
	
	public void run() {
		Graphics g = getGraphics();
		while (mov) {
			Dibujar(g);
			flushGraphics();
			try { Thread.sleep(mDelay); }
			catch (InterruptedException ie) {
				System.err.println(ie.getMessage());
			}
		}
	}
	
	public void start() {
		mov = true;
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stop() {
		mov = false;
	}
	
	
	//función para acomodar todo para un nivel nuevo:
	
	protected void SubirNivel(boolean pri) {
		nivel++;
		if (nivel > 5){ //ganó el juego
			stop();
			juego.GameOver("¡Felicitaciones! ¡Salvaste a la Tierra de la invasión!");
			return;
		}
		if (!pri){ //si no se llama a esta función por primera vez, vaciar todo
			lm.remove(fondo);
			for (int i=0; i<tiros_malos.size(); ++i)
				lm.remove((Sprite)tiros_malos.elementAt(i));
			for (int i=0; i<tiros_buenos.size(); ++i)
				lm.remove((Sprite)tiros_buenos.elementAt(i));
		}
		fondo = niveles.getFondoNivel(nivel);
		lm.append(fondo);
		tiros_malos = new Vector();
		tiros_buenos = new Vector();
		tropa = niveles.getEnemigosNivel(nivel);
		for (int i=0; i<tropa.size(); ++i){
			Sprite e = (Sprite)tropa.elementAt(i);
			lm.insert(e, 0);
		}
		timer_tiros.setTiempo(2000-250*(nivel-1)); //los enemigos disparan más rápido
		delay_closer -= 350; //los enemigos bajan más rápido
	}
	
	
	//función para hacer que un enemigo ataque (la invoca el cronómetro):
	
	public void MalosAtacan() {
		int cualquiera = new Random().nextInt(tropa.size());
		Enemigo e = (Enemigo)tropa.elementAt(cualquiera);
		Sprite t = e.Atacar();
		tiros_malos.addElement(t);
		lm.insert(t, 0);
	}

	
	//función para inicializar el visor de vidas:
	
	private void IniciarVidas() {
		spritesvidas = new Vector();
		for (int i=0; i<vidas; ++i){
			Sprite v = new Sprite(vida, 16, 15);
			v.setPosition(getWidth()-(i+1)*v.getWidth(), 0);
			spritesvidas.addElement(v);
			lm.insert(v, 0);
		}
	}
	
	//ancho y alto de la pantalla para controlar movimientos:
	public int getAncho() { return getWidth(); }
	public int getAlto() { return getHeight(); }
	
}
