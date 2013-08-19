//Guía TP 2 - Ejercicio 4
//Alumno: Juan Bertinetti

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Visor extends MIDlet implements CommandListener {

	private Imagen core; //clase que controla las imágenes
	private Display pantalla;
	private Image img;
	private Command sig, ant, salir;
	private Alert vista;
	
	public void startApp() {
		
		sig = new Command("Siguiente", Command.OK, 1);
		ant = new Command("Anterior", Command.BACK, 1);
		salir = new Command("Salir", Command.EXIT, 1);
		
		core = new Imagen();
		
		vista = new Alert("Visor de imágenes");
		vista.setTimeout(Alert.FOREVER);
		vista.addCommand(salir);
		vista.setCommandListener(this);
		
		//empezar presentación
		img = core.Inicio();
		vista.setImage(img);
		vista.addCommand(sig);		
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(vista);
	}
	
	protected void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}
	
	public void commandAction(Command c, Displayable d){
		if (c == salir)
			destroyApp(true);
		else if (c == sig){
			img = core.Siguiente();
			vista.setImage(img);
			if (core.Ultima())
				vista.removeCommand(sig);
			vista.addCommand(ant);
		}
		else if (c == ant){
			img = core.Anterior();
			vista.setImage(img);
			if (core.Primera())
				vista.removeCommand(ant);
			vista.addCommand(sig);
		}
	}

}
