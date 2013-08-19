//Guía TP 2 - Ejercicio 6
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Conversor extends MIDlet implements CommandListener {

	private Display pantalla;
	private List menu;
	private TextBox tb;
	private Alert res, error;
	private Command ant, sig, salir;
	private Opciones menu2; 
	
	public Conversor() {
		sig = new Command("Siguiente", Command.OK, 1);
		ant = new Command("Anterior", Command.BACK, 1);
		salir = new Command("Salir", Command.EXIT, 1);
		tb = new TextBox("", "", 100, TextField.ANY);
		menu = new List("Conversor - Elige tu sistema numérico", List.IMPLICIT);
		menu2 = new Opciones(this);
		res = new Alert("Resultados");
		error = new Alert("Error");
	}
	
	public void startApp() {
		tb.addCommand(sig);
		tb.addCommand(ant);
		tb.setCommandListener(this);
		res.setTimeout(Alert.FOREVER);
		error.setString("Error. Datos incorrectos.");
		error.setType(AlertType.ERROR);
		error.setTimeout(Alert.FOREVER);
		menu.append("Decimal", null);
		menu.append("Binario", null);
		menu.append("Octal", null);
		menu.append("Hexadecimal", null);
		menu.addCommand(salir);
		menu.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(menu);
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}
	
	public void MostrarResultados(String s) {
		res.setString(s);
		pantalla.setCurrent(res, menu2);
	}
	
	public void Volver() {
		pantalla.setCurrent(tb);
	}
	
	public void commandAction(Command c, Displayable d) {
		if (c == sig){
			menu2.setOrigen(menu.getSelectedIndex()); //tipo de entrada
			boolean b = menu2.ComprobarEntrada(tb.getString()); //compruebo entrada
			if (!b)
				pantalla.setCurrent(error, tb);
			else {
				menu2.setDatos(tb.getString());
				pantalla.setCurrent(menu2);
			}
		}
		else if (c == ant){
			tb.setString("");
			pantalla.setCurrent(menu);
		}
		else if (c == salir)
			destroyApp(true);
		else {
			switch (menu.getSelectedIndex()){
			case 0: //decimal
				tb.setTitle("Ingresa tu número en decimal:");
				tb.setConstraints(TextField.NUMERIC);
				break;
			case 1: //binario
				tb.setTitle("Ingresa tu número en binario:");
				tb.setConstraints(TextField.NUMERIC); //sólo 0 y 1 debería ser
				break;
			case 2: //octal
				tb.setTitle("Ingresa tu número en octal:");
				tb.setConstraints(TextField.NUMERIC); //entre 0 y 7
				break;
			case 3: //hexadecimal
				tb.setTitle("Ingresa tu número en hexadecimal:");
				tb.setConstraints(TextField.ANY);
			}
			pantalla.setCurrent(tb);
		}
	}

}
