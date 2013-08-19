//Guía TP 5 - Ejercicio 4
//Alumno: Juan Bertinetti

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class CheckURL extends MIDlet implements CommandListener, Runnable {

	private Display pantalla;
	private Form main;
	private Command check, salir;
	
	public void startApp() {
		check = new Command("Comprobar", Command.OK, 1);
		salir = new Command("Salir", Command.EXIT, 1);
		main = new Form("URL Checker");
		main.append(new TextField("Ingrese una URL:", "http://", 100, TextField.ANY));
		main.append(new StringItem("Resultado:\n", ""));
		main.addCommand(check);
		main.addCommand(salir);
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
		else if (c == check){
			((StringItem)main.get(1)).setText("Comprobando...");
			Thread t = new Thread(this);
			t.start();
		}
			
	}

	public void run() {
		try {
			String url = ((TextField)main.get(0)).getString();
			HttpConnection conexion = (HttpConnection)Connector.open(url);
			if (conexion.getResponseCode() == HttpConnection.HTTP_NOT_FOUND)
				((StringItem)main.get(1)).setText("¡Error! La URL no existe.");
			else
				((StringItem)main.get(1)).setText("¡Éxito! URL correcta.");
			conexion.close();
		} catch (IOException e) {
			((StringItem)main.get(1)).setText("¡Error! La URL no existe.");
		}
		
	}

}
