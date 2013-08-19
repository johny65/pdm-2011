//Guía TP1 - Ejercicio 4

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Mundo extends MIDlet implements CommandListener {

	private TextBox formulario;	

	public void startApp() {
		formulario = new TextBox("TP1 - Ej4", "Hola mundo J2ME", 15, TextField.UNEDITABLE);
		formulario.addCommand(new Command("Salir", Command.EXIT, 1));
		formulario.setCommandListener(this);
		Display pantalla = Display.getDisplay(this);
		pantalla.setCurrent(formulario);
	}
	
	public void pauseApp() {}
	
	public void destroyApp(boolean unconditional) {
		formulario = null;
	}
	
	public void commandAction(Command c, Displayable d){
		destroyApp(true);
		notifyDestroyed();
	}
}
