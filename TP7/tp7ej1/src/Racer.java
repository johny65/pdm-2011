//Guía TP 7 - Ejercicio 1
//Alumno: Juan Bertinetti

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Racer extends MIDlet implements CommandListener {

	private Display pantalla;
	private Pista p;
	private Alert mensajes;
	private Command empezar, pausar, continuar, salir;
	private Image splash;
	private Form main;
	private boolean pausa;
	
	public Racer() {
		main = new Form("El Contramano v1.0");
		mensajes = new Alert("");
		mensajes.setTimeout(Alert.FOREVER);
		empezar = new Command("¡Empezar!", Command.OK, 1);
		pausar = new Command("Pausar", Command.OK, 1);
		continuar = new Command("Continuar", Command.OK, 1);
		salir = new Command("Salir", Command.EXIT, 1);
		try {
			splash = Image.createImage("/main.png");
		} catch (IOException e) {
			System.err.println("Error mostrando pantalla principal.");
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
			mensajes.setString("Has perdido el juego. ¡Eso te pasa por ir en contramano! Intenta otra vez");
			mensajes.setType(AlertType.INFO);
			mensajes.addCommand(empezar);
			mensajes.addCommand(salir);
			mensajes.setCommandListener(this);
			pantalla = Display.getDisplay(this);
			pantalla.setCurrent(main);
		}
		else { //reanudar
			if (pantalla.getCurrent() == p)
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
			p.stop();
			p.removeCommand(pausar);
			p.addCommand(continuar);
		}
	}
	
	protected void continuarApp() {
		p.start();
		p.removeCommand(continuar);
		p.addCommand(pausar);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == empezar){
			p = new Pista(this);
			p.addCommand(salir);
			p.addCommand(pausar);
			p.setCommandListener(this);
			pantalla.setCurrent(p);
			p.start();
		}
		else if (c == salir)
			destroyApp(true);
		else if (c == pausar)
			pauseApp();
		else if (c == continuar)
			continuarApp();
			
	}
	
	//para mostrar el mensaje de GAME OVER
	public void GameOver() {
		pantalla.setCurrent(mensajes);
	}

}
