//Guía TP1 - Ejercicio 8

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Calculadora extends MIDlet implements CommandListener {

	private TextBox tb;
	private Display pantalla;
	private Command salir, calc, clear;
	private Alert res;

	public void startApp() {
		tb = new TextBox("Ingrese una expresión a calcular:", "", 250, TextField.ANY);
		salir = new Command("Salir", Command.EXIT, 1);
		calc = new Command("Calcular", Command.OK, 1);
		clear = new Command("Limpiar", Command.EXIT, 1);
		res = new Alert("Resultado");
		res.setTimeout(Alert.FOREVER);
		tb.addCommand(calc);
		tb.addCommand(salir);
		tb.addCommand(clear);
		tb.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(tb);
	}
	
	public void pauseApp() {}
	
	public void destroyApp(boolean unconditional) {
		tb = null;
		salir = null;
		calc = null;
		clear = null;
		res = null;
		notifyDestroyed();
	}
	
	private void calcExpr(String s) {
		String aux, aux2, signo = "+";
		int suma = 0, ini = 0, inip, termino;
		for (int i=0; i<s.length(); ++i){ //ir separando en términos
			if (s.charAt(i) == '+' || s.charAt(i) == '-' || i == s.length()-1){
				if (i == s.length()-1) //no encontró más + (o -), tomar hasta el final
					aux = s.substring(ini);
				else
					aux = s.substring(ini, i); //sino tomar hasta el + (o -)
				//System.out.println(aux);
				ini = i+1;
				
				//resolver los * si hay
				inip = 0; termino = 1;
				for (int j=0; j<aux.length(); ++j){
					if (aux.charAt(j) == '*' || j == aux.length()-1){
						if (j == aux.length()-1) //no encontró más *
							aux2 = aux.substring(inip);
						else
							aux2 = aux.substring(inip, j); //sino tomar hasta el *
						//System.out.println("->"+aux2);
						inip = j+1;
						try {
							termino *= Integer.parseInt(aux2.trim());
						}
						catch (java.lang.NumberFormatException e) {
							res.setType(AlertType.ERROR);
							res.setString("Expresión no válida");
							pantalla.setCurrent(res, tb);
							return;
						}
					}
				}
				
				//sumar el término
				if (signo.equals("+")) suma += termino;
				else suma -= termino;
				
				//actualizar el signo para el próximo término
				if (s.charAt(i) == '+') signo = "+";
				else signo = "-";
			}
		}
		//mostrar resultado
		res.setString(s + " = " + suma);
		res.setType(AlertType.INFO);
		pantalla.setCurrent(res, tb);
		
	}
	
	public void commandAction(Command c, Displayable d){
		if (c == calc)
			calcExpr(tb.getString());
		else if (c == clear)
			tb.setString("");
		else if (c == salir)
			destroyApp(true);
	}
}
