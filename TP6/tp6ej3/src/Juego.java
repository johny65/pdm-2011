//Guía TP 6 - Ejercicio 3
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Juego extends MIDlet implements CommandListener {

	private Display pantalla;
	private List menu;
	private Escenario es;
	private Command reset, salir;
	
	public Juego() {
		menu = new List("Harry el Sucio", List.IMPLICIT);
		reset = new Command("Reintentar", Command.OK, 1);
		salir = new Command("Salir", Command.EXIT, 1);		
	}
	
	public void startApp() {	
		menu.append("Iniciar juego", null);
		menu.append("Salir", null);
		menu.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(menu);
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}
	
	protected void Nuevo() {
		//inicio nuevo juego
		es = null;
		es = new Escenario();
		es.addCommand(salir);
		es.addCommand(reset);
		es.setCommandListener(this);
		pantalla.setCurrent(es);
		es.start();
	}

	public void commandAction(Command c, Displayable d) {
		if (c == salir)
			destroyApp(true);
		else if (c == reset)
			Nuevo();
		else {
			if (menu.getSelectedIndex() == 0)
				Nuevo();
			else destroyApp(true);
		}
	}

}
