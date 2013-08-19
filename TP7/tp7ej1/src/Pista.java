//clase Pista

//La clase principal del juego, maneja todo

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;

public class Pista extends GameCanvas implements Runnable {

	//Atributos:
	
	//para la comunicación con el midlet
	private Racer padre;
	
	//configuraciones
	private boolean mov, haymalo, hayvida, pausa;
	private int mDelay, veloc_malos;
	
	//objetos
	private Auto player, malo;
	private Calle calle;
	private Sprite vida;
	private Jugador j;
	private LayerManager lm;
	
	//imágenes
	private Image Mega, BlueSea, Vulcano, Hooney, imgcalle, imgvidas;
	private Image Replicantes[];
	
	//cronómetros para eventos temporales
	private Cronometro timerNivel, timerVidas, timerVidasAleatorias;
	
	//banderas para saber cuándo se ejecutan los eventos temporales
	private boolean nuevonivel, nuevavida;
	
	
	
	//Métodos:
	
	public Pista(Racer p){
		super(true);
		padre = p;
		mov = false;
		mDelay = 20;
		haymalo = false;
		hayvida = false;
		veloc_malos = 3; //para los distintos niveles
		nuevonivel = false;
		nuevavida = false;
		pausa = false;
		
		try {
			Mega = Image.createImage("/mega.png");
			BlueSea = Image.createImage("/bluesea.png");
			Vulcano = Image.createImage("/vulcano.png");
			Hooney = Image.createImage("/hooney.png");
			imgcalle = Image.createImage("/tiles.png");
			imgvidas = Image.createImage("/vida.png");
		} catch (IOException e) {
			System.err.println("Error cargando imagen");
			return;
		}
		
		j = new Jugador(3); //empiezo con 3 vidas
		
		Replicantes = new Image[3];
		Replicantes[0] = BlueSea; Replicantes[1] = Vulcano; Replicantes[2] = Hooney;
		
		player = new Auto(Mega, 25, 50, this);
		malo = new Auto(BlueSea, 25, 50, this);
		calle = new Calle(imgcalle);
		calle.setPosition(0, -48);
		vida = new Sprite(imgvidas, 15, 14);
		vida.setVisible(false);
		
		lm = new LayerManager();
		lm.setViewWindow(0, 0, getWidth(), getHeight()-20); //el -20 es por la "barra de estado" de abajo 
		
		lm.append(player);
		lm.append(malo);
		lm.append(vida);
		lm.append(calle);
		
		//configurar cronómetros:
		timerNivel = new Cronometro(this);
		timerNivel.ControlarNivel();
		timerVidas = new Cronometro(this);
		timerVidas.ControlarNuevasVidas(300000); //primero a los 5 minutos
		timerVidasAleatorias = new Cronometro(this);
		timerVidasAleatorias.ControlarNuevasVidasAleatorias(300000);
		
	}
	
	private void Dibujar(Graphics g){
		
		if (nuevonivel){
			ActualizarEstado(g);
			nuevonivel = false;
		}
		if (nuevavida){ //sumar una vida
			j.SumarVida();
			ActualizarEstado(g);
			nuevavida = false;
		}
		
		int ks = getKeyStates();
		if ((ks & LEFT_PRESSED) != 0)
			player.MoverIzquierda();
		else if ((ks & RIGHT_PRESSED) != 0)
			player.MoverDerecha();
		else if ((ks & UP_PRESSED) != 0)
			player.MoverArriba();
		else if ((ks & DOWN_PRESSED) != 0)
			player.MoverAbajo();
		else
			player.setFrame(0);
		
		//frenarse en el pasto:
		if (player.getX() < 30 || player.getX() >= 190)
			calle.Mover(1);
		else
			calle.Mover(2);
		
		//ver enemigos:
		if (!haymalo){
			//que venga otro!
			int i = new Random().nextInt(3);
			malo.setImage(Replicantes[i], 25, 50);
			int pos = new Random().nextInt(152)+33;
			malo.setPosition(pos, -50);
			malo.setVisible(true);
			haymalo = true;
		}
		else {
			if (!malo.MoverEnemigo(veloc_malos)){ //pasó de largo
				malo.setVisible(false);
				haymalo = false;
			}
		}

		//se chocó!!!
		if (player.collidesWith(malo, false)){
			if (j.getVidas() > 1){
				j.QuitarVida();
				reiniciar(g);
			}
			else {
				stop(); //game over
				padre.GameOver();
			}
		}
		
		//agarrar la vida
		if (hayvida){
			vida.move(0, 2);
			if (vida.getY() > getHeight()){ //pasó de largo
				hayvida = false;
				vida.setVisible(false);
			}
			else if (player.collidesWith(vida, false)){ //la agarró
				j.SumarVida();
				hayvida = false;
				vida.setVisible(false);
				ActualizarEstado(g);
			}
		}
		
		//painter's palette
		lm.paint(g, 0, 0);
		
	}
	
	public void run() {
		Graphics g = getGraphics();
		if (!pausa)
			reiniciar(g); //posicionar todo por primera vez
		else pausa = false;
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
		pausa = true;
		mov = false;
	}
	
	//vuelve todo a sus posiciones originales
	public void reiniciar(Graphics g) {
		malo.setVisible(false);
		haymalo = false;
		player.setPosition(getWidth()/2-12, getHeight()-80);
		ActualizarEstado(g);
	}
	
	//actualiza la barra de estado con el nivel y las vidas
	private void ActualizarEstado(Graphics g) {
		g.setColor(0xf9e0c2);
		g.fillRect(0, getHeight()-20, getWidth(), 20);
		Font fuente = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
		g.setColor(0);
		g.setFont(fuente);
		g.drawString("Nivel " + j.getNivel(), 5, getHeight()-18, Graphics.TOP | Graphics.LEFT);
		g.drawImage(imgvidas, getWidth()-50, getHeight()-18, Graphics.TOP | Graphics.LEFT);
		g.drawString("= "+ j.getVidas(), getWidth()-33, getHeight()-18, Graphics.TOP | Graphics.LEFT);
	}
	
	//para saber cuándo subir de nivel (le avisa el cronómetro)
	public void SubirNivel() {
		veloc_malos++;
		j.SubirNivel();
		if (j.getNivel() == 5)
			player.setDelta(4);
		else if (j.getNivel() == 10)
			player.setDelta(5);
		else if (j.getNivel() == 13)
			player.setDelta(6);
		else if (j.getNivel() == 15)
			player.setDelta(7);
		nuevonivel = true;
	}

	//para saber cuándo regalarle una vida (le avisa el cronómetro)
	public void RegalarVida() {
		nuevavida = true;
	}
	
	//hacer aparecer una vida en la pista (le avisa el cronómetro)
	public void AparecerVida() {
		int pos = new Random().nextInt(152)+33;
		vida.setPosition(pos, -15);
		vida.setVisible(true);
		hayvida = true;
	}
	
	//ancho y alto de la pantalla para controlar movimientos:
	public int getAncho() { return getWidth(); }
	public int getAlto() { return getHeight(); }
	
}
