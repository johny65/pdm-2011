//Guía TP 3 - Ejercicio 1
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Mozo extends MIDlet implements Principal {

	private Display pantalla;
	private Menu entradas;
	private Menu comidas;
	private Menu postres;
	private Menu bebidas;
	private Alert al;
	private int p; //para saber la pantalla activa
	private String res;
	
	public void startApp() {
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
		al = new Alert("Información");
		al.setTimeout(Alert.FOREVER);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(entradas);
		p = 1;
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}

	
	//métodos de la interfaz Principal
	
	public void GuardarOpcion(String s) {
		switch (p){
		case 1:
			res = "Menú pedido:\n";
			res += "\nPlato de entrada: " + s;
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
			al.setString(res);
			pantalla.setCurrent(al, entradas);
			p = 1;
		}
	}
	
	public void Salir() {
		destroyApp(true);
	}

}
