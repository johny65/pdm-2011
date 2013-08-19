//Guía TP2 - Ejercicio 3
//Alumno: Juan Bertinetti

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Menu extends MIDlet implements CommandListener {

	private List Principal;
	private Display pantalla;
	private Pantalla2 listOficina;
	private Pantalla3 listJuegos;

	public void startApp() {
		Principal = new List("TP2 - Ej3", List.IMPLICIT);
		Principal.append("Oficina", null);
		Principal.append("Juegos", null);
		Principal.append("Salir", null);
		Principal.setCommandListener(this);
		listOficina = new Pantalla2(this);
		listJuegos = new Pantalla3(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(Principal);
	}
	
	public void pauseApp() {}
	
	public void destroyApp(boolean unconditional) {
		Principal = null;
		listOficina = null;
		listJuegos = null;
		notifyDestroyed();
	}
	
	public void Volver() {
		pantalla.setCurrent(Principal);
	}
	
	public void MostrarAlerta(Alert d, Displayable sig) {
		pantalla.setCurrent(d, sig);
	}
	
	public void commandAction(Command c, Displayable d){
		switch (Principal.getSelectedIndex()){
			case 0: //oficina
				pantalla.setCurrent(listOficina);
				break;
			case 1: //juegos
				pantalla.setCurrent(listJuegos);
				break;
			case 2: //salir
				destroyApp(true);
		}
	}
}
