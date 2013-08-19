//Guía TP1 - Ejercicio 6

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Sumador extends MIDlet implements CommandListener {

	private Display pantalla;
	private int num1, num2, numres;
	private TextBox tb;
	private Command salir, sig, calc;
	private Alert res;

	public void startApp() {
		tb = new TextBox("Ingrese número 1:", "", 100, TextField.NUMERIC);
		salir = new Command("Salir", Command.EXIT, 1);
		sig = new Command("Siguiente", Command.OK, 1);
		calc = new Command("Sumar", Command.OK, 1);
		res = new Alert("Resultado");
		res.setTimeout(Alert.FOREVER);
		res.setType(AlertType.INFO);
		tb.addCommand(sig);
		tb.addCommand(salir);
		tb.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(tb);
	}
	
	public void pauseApp() {}
	
	public void destroyApp(boolean unconditional) {
		tb = null;
		salir = null;
		sig = null;
		calc = null;
		res = null;
		notifyDestroyed();
	}
	
	public void commandAction(Command c, Displayable d){
		if (c == sig){
			num1 = Integer.parseInt(tb.getString());
			tb.setTitle("Ingrese número 2:");
			tb.setString("");
			tb.removeCommand(sig);
			tb.addCommand(calc);
		}
		else if (c == calc){
			num2 = Integer.parseInt(tb.getString());
			numres = num1 + num2;
			res.setString(""+num1 + " + " + num2 + " = " + numres);
			pantalla.setCurrent(res, tb);
			
			//limpiar todo
			tb.setTitle("Ingrese número 1:");
			tb.setString("");
			tb.removeCommand(calc);
			tb.addCommand(sig);
		}
		else if (c == salir)
			destroyApp(true);
	}
}
