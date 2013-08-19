//Guía TP 7 - Ejercicio 2
//Alumno: Juan Bertinetti

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class SpaceInvaders extends MIDlet implements CommandListener {

	private Display pantalla;
	private Mundo mundo;
	private Alert mensajes;
	private Command empezar, pausar, continuar, salir;
	private Image splash, mega, aliens, tirobueno, tiromalo, vida;
	private Form main;
	private boolean pausa;
	
	//constructor:
	public SpaceInvaders() {
		main = new Form("Mega Invaders");
		mensajes = new Alert("");
		mensajes.setTimeout(Alert.FOREVER);
		empezar = new Command("¡Empezar!", Command.OK, 1);
		pausar = new Command("Pausar", Command.OK, 1);
		continuar = new Command("Continuar", Command.OK, 1);
		salir = new Command("Salir", Command.EXIT, 1);
		
		
		//cargar imágenes una sola vez acá para siempre:
		
		try {
			splash = Image.createImage("/splash.png");
			mega = Image.createImage("/megaman.png");
			aliens = Image.createImage("/aliens.png");
			tirobueno = Image.createImage("/tiro.png");
			tiromalo = Image.createImage("/eter.png");
			vida = Image.createImage("/vida.png");
		} catch (IOException e) {
			System.err.println("Error al cargar imágenes.");
			return;
		}

		pausa = false;
	}
	
	public void startApp() {
		if (!pausa){ //inicio
			main.addCommand(empezar);
			main.addCommand(salir);
			main.setCommandListener(this);
			main.append(splash);
			mensajes.setTitle("GAME OVER");
			mensajes.setType(AlertType.INFO);
			mensajes.addCommand(empezar);
			mensajes.addCommand(salir);
			mensajes.setCommandListener(this);
			pantalla = Display.getDisplay(this);
			pantalla.setCurrent(main);
		}
		else { //reanudar
			if (pantalla.getCurrent() == mundo)
				continuarApp();
			pausa = false;
		}
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {
		pausa = true;
		if (pantalla.getCurrent() != main){
			mundo.stop();
			mundo.removeCommand(pausar);
			mundo.addCommand(continuar);
		}
	}
	
	protected void continuarApp() {
		mundo.start();
		mundo.removeCommand(continuar);
		mundo.addCommand(pausar);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == empezar){
			mundo = new Mundo(this, mega, aliens, tirobueno, tiromalo, vida);
			mundo.addCommand(salir);
			mundo.addCommand(pausar);
			mundo.setCommandListener(this);
			pantalla.setCurrent(mundo);
			mundo.start();
		}
		else if (c == salir)
			destroyApp(true);
		else if (c == pausar)
			pauseApp();
		else if (c == continuar)
			continuarApp();
			
	}
	
	//para mostrar el mensaje de GAME OVER
	public void GameOver(String msg) {
		mensajes.setString(msg);
		pantalla.setCurrent(mensajes);
	}

}
