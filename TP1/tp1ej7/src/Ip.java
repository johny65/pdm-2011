//Guía TP1 - Ejercicio 7

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Ip extends MIDlet implements CommandListener {

	private TextBox tb;
	private Display pantalla;
	private Command salir, comprobar;
	private Alert res;

	public void startApp() {
		tb = new TextBox("Ingrese una dirección IP:", "", 15, TextField.ANY);
		salir = new Command("Salir", Command.EXIT, 1);
		comprobar = new Command("Comprobar IP", Command.OK, 1);
		res = new Alert("Resultado");
		res.setTimeout(Alert.FOREVER);
		tb.addCommand(comprobar);
		tb.addCommand(salir);
		tb.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(tb);
	}

	public void pauseApp() {}

	public void destroyApp(boolean unconditional) {
		tb = null;
		salir = null;
		comprobar = null;
		res = null;
		notifyDestroyed();
	}

	private void ipError() {
		res.setType(AlertType.ERROR);
		res.setString("La IP ingresada es incorrecta.");
		pantalla.setCurrent(res, tb);
	}

	private void ipOk() {
		res.setType(AlertType.INFO);
		res.setString("La IP ingresada es correcta.");
		pantalla.setCurrent(res, tb);
	}

	private void ComprobarIP(String s) {
		String aux;
		// primer filtro: contar los puntos
		int puntos = 0;
		for (int i = 0; i < s.length(); ++i){
			if (s.charAt(i) == '.')
				puntos++;
		}
		if (puntos != 3) {
			ipError();
			return;
		}
		// comprobar los números:
		int ini = 0, fin, num;
		for (int i = 0; i < 4; ++i) {
			if (i != 3)
				fin = s.indexOf('.', ini);
			else
				fin = s.length();
			aux = s.substring(ini, fin);
			if (aux.length() > 3) {
				ipError();
				return;
			}
			try {
				num = Integer.parseInt(aux);
			} catch (java.lang.NumberFormatException e) {
				// no son números!
				num = -1;
			}
			//System.out.println(num);
			if (num < 0 || num > 255) {
				ipError();
				return;
			}
			ini = fin + 1;
		}
		ipOk();
	}

	public void commandAction(Command c, Displayable d) {
		if (c == comprobar)
			ComprobarIP(tb.getString());	
		else if (c == salir)
			destroyApp(true);
	}
}