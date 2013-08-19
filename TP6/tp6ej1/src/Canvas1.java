//Guía TP 6 - Ejercicio 1
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Canvas1 extends MIDlet implements CommandListener {

	private Display pantalla;
	private Pelotita p;
	private Command control, salir;
	
	public void startApp() {
		salir = new Command("Salir", Command.EXIT, 1);
		control = new Command("On/Off", Command.OK, 1);
		p = new Pelotita(2, 3); //probar con varios valores
		p.addCommand(control);
		p.addCommand(salir);
		p.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(p);
		p.start();
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}

	public void commandAction(Command c, Displayable d) {
		if (c == salir)
			destroyApp(true);
		else if (c == control){
			if (p.getEstado())
				p.stop();
			else
				p.start();
		}
	}

}
