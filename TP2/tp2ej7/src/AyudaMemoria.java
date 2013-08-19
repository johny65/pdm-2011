//Guía TP 2 - Ejercicio 7
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.util.Vector;

public class AyudaMemoria extends MIDlet implements CommandListener {

	private Display pantalla;
	private Vector notas;
	private List menu;
	private Command editar, ok, cancelar, salir;
	private TextBox tb;
	private Alert ver;
	private int cant, actual;
	private boolean pausado;
	
	public AyudaMemoria() {
		cant = 10;
		pausado = false;
		notas = new Vector(cant);
		menu = new List("Ayuda Memoria", List.IMPLICIT);
		salir = new Command("Salir", Command.EXIT, 1);
		editar = new Command("Editar", Command.OK, 1);
		cancelar = new Command("Cancelar", Command.BACK, 1);
		ok = new Command("OK", Command.OK, 1);
		tb = new TextBox("", "", 500, TextField.ANY);
		ver = new Alert("");
	}
	
	public void startApp() {
		if (!pausado){
			menu.addCommand(salir);
			menu.addCommand(editar);
			menu.setCommandListener(this);
			ver.setTimeout(Alert.FOREVER);
			tb.addCommand(ok);
			tb.addCommand(cancelar);
			tb.setCommandListener(this);
			//inicializar notas vacías:
			for (int i=0; i<cant; ++i){
				menu.append("Nota sin título "+(i+1), null);
				notas.addElement("");
			}
			pantalla = Display.getDisplay(this);
			pantalla.setCurrent(menu);
		}
		else
			pausado = false;
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {
		pausado = true;
	}

	public void commandAction(Command c, Displayable d) {
		actual = menu.getSelectedIndex();
		if (c == salir)
			destroyApp(true);
		else if (c == editar){
			tb.setTitle("Ingrese nuevo título:");
			tb.setMaxSize(30);
			tb.setString(menu.getString(actual));
			pantalla.setCurrent(tb);
		}
		else if (c == ok){
			if (tb.getTitle().equals("Ingrese nuevo título:")){
				menu.set(actual, tb.getString(), null);
				tb.setTitle("Editando la nota");
				tb.setMaxSize(500);
				tb.setString((String)notas.elementAt(actual));
			}
			else {
				notas.setElementAt(tb.getString(), actual);
				pantalla.setCurrent(menu);
			}	
		}
		else if (c == cancelar)
			pantalla.setCurrent(menu);
		else { //mostrar la nota
			ver.setTitle(menu.getString(actual));
			ver.setString((String)notas.elementAt(actual));
			pantalla.setCurrent(ver, menu);
		}
	}

}
