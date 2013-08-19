//Guía TP 3 - Ejercicio 3
//Alumno: Juan Bertinetti

import java.io.IOException;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Platos extends MIDlet implements CommandListener {

	private Display pantalla;
	private Alert al;
	private List lista;
	private Command salir, info;
	private Image img;
	private Ticker tic;
	
	//informaciones:
	
	String platos[] = {
		"Empanadas árabes",
		"Pizza",
		"Risotto",
		"Rabas"
	};
	String imgs[] = {
		"/arabes.png",
		"/pizza.png",
		"/risotto.png",
		"/rabas.png"
	};
	String precios[] = {
		"$4,90 c/u",
		"$15",
		"$17",
		"$30"
	};
	String desc[] = {
		"Empanadas rellenas con carne picada a mano, tomate, pimiento y cebolla",
		"Pizza con masa casera y horneada en horno a leña, todos los tipos al mismo precio (también hay sin queso)",
		"Comida tradicional italiana a base de arroz con azafrán y otras especias, plato abundante",
		"Calamares fritos rebozados en harina en forma de anillos"
	};
	
	public void startApp() {
		salir = new Command("Salir", Command.EXIT, 1);
		info = new Command("Ver información", Command.OK, 1);
		al = new Alert("");
		al.setTimeout(Alert.FOREVER);
		tic = new Ticker("");
		lista = new List("Información sobre platos", List.IMPLICIT);
		for (int i=0; i<platos.length; ++i)
			lista.append(platos[i], null);
		lista.addCommand(salir);
		lista.addCommand(info);
		lista.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(lista);
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}

	public void commandAction(Command c, Displayable d) {
		if (c == salir)
			destroyApp(true);
		else if (c == info){
			int s = lista.getSelectedIndex();
			tic.setString(desc[s]);
			al.setTitle(platos[s]);
			al.setTicker(tic);
			img = null; //para borrar la anterior
			try {
				img = Image.createImage(imgs[s]);
			} catch (IOException e) {
				System.out.println("Error al abrir imagen");
				img = null;
			}
			al.setImage(img);
			al.setString("Plato: "+platos[s]+"\nPrecio: "+precios[s]);
			pantalla.setCurrent(al, lista);		
		}
	}

}
