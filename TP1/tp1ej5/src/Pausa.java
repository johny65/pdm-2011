//Guía TP1 - Ejercicio 5

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Pausa extends MIDlet implements CommandListener {

	private TextBox formulario;
	private boolean pausado = false;
	private int cuenta = 0;
	
	public void startApp() {
		if (pausado){ //reanudar
			if (cuenta == 1)
				formulario.setString("La aplicación ha sido suspendida 1 vez.");
			else
				formulario.setString("La aplicación ha sido suspendida " + cuenta + " veces.");
			pausado = false;
		}
		else { //inicio normal
			formulario = new TextBox("TP1 - Ej5", "¡Hola Mundo! Probá pausarme...", 100, TextField.UNEDITABLE);
			formulario.addCommand(new Command("Salir", Command.EXIT, 1));
			formulario.setCommandListener(this);
			Display pantalla = Display.getDisplay(this);
			pantalla.setCurrent(formulario);
		}
	}
	
	public void pauseApp() {
		pausado = true;
		cuenta++;
	}
	
	public void destroyApp(boolean unconditional) {
		formulario = null;
	}
	
	public void commandAction(Command c, Displayable d){
		destroyApp(true);
		notifyDestroyed();
	}
	
}
