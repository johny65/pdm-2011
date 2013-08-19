//Guía TP 3 - Ejercicio 2
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Mozo extends MIDlet implements CommandListener, Principal {

	private Display pantalla;
	private Menu entradas;
	private Menu comidas;
	private Menu postres;
	private Menu bebidas;
	private Alert al;
	private Command fin, add, ok;
	private int p, cant;
	private String res;
	
	public void startApp() {
		res = "Pedidos hechos:\n";
		cant = 1;
		String sentradas[] = {
			"Empanda árabe",
			"Picada",
			"Rabas",
			"Tarta de zapallitos"
		};
		entradas = new Menu(this, sentradas, "Entrada");
		String scomidas[] = {
			"Pizza",
			"Risotto",
			"Guiso de lentejas",
			"Estofado de pollo"
		};		 
		comidas = new Menu(this, scomidas, "Comida");
		String spostres[] = {
			"Flan",
			"Helado",
			"Budín de pan",
			"Sandía"
		};
		postres = new Menu(this, spostres, "Postre");
		String sbebidas[] = {
			"Gaseosa",
			"Jugo de naranja exprimido",
			"Vino",
			"Cerveza",
			"Agua mineral"
		};
		bebidas = new Menu(this, sbebidas, "Bebida");
		fin = new Command("Terminar", Command.OK, 1);
		add = new Command("Agregar persona", Command.OK, 1);
		ok = new Command("OK", Command.OK, 1);
		al = new Alert("Información");
		al.setTimeout(Alert.FOREVER);
		al.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(entradas);
		p = 1;
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}

	public void commandAction(Command c, Displayable d) {
		if (c == add){ //agregar otro pedido
			cant++;
			pantalla.setCurrent(entradas);
		}
		else if (c == fin){ //mostrar pedidos
			al.removeCommand(add);
			al.removeCommand(fin);
			al.addCommand(ok);
			al.setTitle("Información");
			al.setString(res);
			pantalla.setCurrent(al); 
		}
		else if (c == ok){ //reiniciar todo para empezar de nuevo si se quiere		
			cant = 1;
			res = "Pedidos hechos:\n";
			pantalla.setCurrent(entradas);
		}
	}
	
	
	//métodos de la interfaz Principal
	
	public void GuardarOpcion(String s) {
		switch (p){
		case 1:
			res += "\n\nPersona " + cant + ":\nPlato de entrada: " + s;
			break;
		case 2:
			res += "\nPlato principal: " + s;
			break;
		case 3:
			res += "\nPostre: " + s;
			break;
		case 4:
			res += "\nBebida: " + s;
		}		
	}

	public void Siguiente() {
		switch (p){
		case 1:
			pantalla.setCurrent(comidas);
			p = 2; break;
		case 2:
			pantalla.setCurrent(postres);
			p = 3; break;
		case 3:
			pantalla.setCurrent(bebidas);
			p = 4; break;
		case 4:
			al.setTitle("Consulta");
			al.setString("¿Agregar pedido de otra persona o finalizar?");
			al.removeCommand(ok);
			al.addCommand(fin);
			al.addCommand(add);
			pantalla.setCurrent(al);
			p = 1;
		}
	}
	
	public void Salir() {
		destroyApp(true);
	}

}
