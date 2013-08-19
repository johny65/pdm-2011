//Guía TP 6 - Ejercicio 2
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Figuritas extends MIDlet implements CommandListener {

	private Display pantalla;
	private List main;
	private Opciones op;
	private Command salir, ok;
	
	public Figuritas() {
		main = new List("Figuritas", List.IMPLICIT);
		salir = new Command("Salir", Command.EXIT, 1);
		ok = new Command("Elegir", Command.OK, 1);
	}
	
	public void startApp() {
		main.setTicker(new Ticker("Escoge una figura:"));
		main.append("Rectángulo", null);
		main.append("Círculo", null);
		main.append("Cuadrado", null);
		main.append("Triángulo isósceles", null);
		main.addCommand(salir);
		main.addCommand(ok);
		main.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(main);
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}

	public void commandAction(Command c, Displayable d) {
		if (c == salir)
			destroyApp(true);
		else if (c == ok){
			op = new Opciones(main.getSelectedIndex(), this);
			pantalla.setCurrent(op);
		}
	}
	
	//métodos de manejo:
	
	public void MostrarFigura(Figura f) {
		pantalla.setCurrent(f);
		f.start();
	}
	
	public void MostrarError(Alert al) {
		pantalla.setCurrent(al, op);
	}
	
	public void Volver() { pantalla.setCurrent(op);	}	
	public void Inicio() { pantalla.setCurrent(main); }

}
