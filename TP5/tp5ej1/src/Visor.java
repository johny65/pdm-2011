//Guía TP 5 - Ejercicio 1
//Alumno: Juan Bertinetti

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Visor extends MIDlet implements CommandListener {

	private int limit = 500;
	private Display pantalla;
	private List main;
	private TextBox t;
	private Alert al;
	private Command abrir, salir, sig;
	private InputStream arch;
	
	public void startApp() {
		abrir = new Command("Abrir", Command.OK, 1);
		salir = new Command("Salir", Command.EXIT, 1);
		sig = new Command("Siguiente", Command.OK, 1);
		t = new TextBox(null, null, limit, TextField.ANY);
		t.addCommand(sig);
		t.addCommand(salir);
		t.setCommandListener(this);
		main = new List("Visor de textos", List.IMPLICIT);
		main.append("txt1.txt", null);
		main.append("txt2.txt", null);
		main.addCommand(abrir);
		main.addCommand(salir);
		main.setCommandListener(this);
		al = new Alert("Error", "No hay más páginas para mostrar.", null, AlertType.ERROR);
		al.setTimeout(2000);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(main);
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public void AbrirArchivo(String s){
		arch = getClass().getResourceAsStream(s);		
	}
	
	public void LeerArchivo() {
		String text = "";
		int r;
		for (int i=0; i<limit; ++i){
			try {
				r = arch.read();
			} catch (IOException e) {
				r = -1;
			}
			if (r != -1)
				text += (char)r;
			else
				break;
		}
		if (text.length() == 0)
			pantalla.setCurrent(al, t);
		else
			t.setString(text);
	}
	
	protected void pauseApp() {}

	public void commandAction(Command c, Displayable d) {
		if (c == salir)
			destroyApp(true);
		else if (c == abrir){
			AbrirArchivo(main.getString(main.getSelectedIndex()));
			LeerArchivo();
			pantalla.setCurrent(t);
		}
		else if (c == sig)
			LeerArchivo();
	}

}
