//Guía TP 2 - Ejercicio 5
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.IOException;
import java.util.Random;

public class Piedras extends MIDlet implements CommandListener {

	private Display pantalla;
	private List menu;
	private Command salir, ver;
	private Alert alMaq, alPunt;
	private int puntaje, puntajemaq;
	private Image imgPiedra, imgPapel, imgTijera;
	private Random r;
	boolean pausado;
	
	public Piedras() {
		pausado = false;
	}
	
	public void startApp() {
		
		if (!pausado){
			puntaje = 0;
			puntajemaq = 0;
			r = new Random();
			
			//cargar imágenes
			try {
				imgPiedra = Image.createImage("/piedra.png");
				imgPapel = Image.createImage("/papel.png");
				imgTijera = Image.createImage("/tijera.png");
			} catch (IOException e) {
				//e.printStackTrace();
				System.err.println("Error al abrir imagen");
				imgPiedra = null;
				imgPapel = null;
				imgTijera = null;
			}
			
			//alertas
			alPunt = new Alert("Puntajes", "", null, AlertType.INFO);
			alPunt.setTimeout(Alert.FOREVER);
			alMaq = new Alert("Resultado", "", null, AlertType.INFO);
			alMaq.setTimeout(Alert.FOREVER);
			
			//pantalla
			salir = new Command("Salir", Command.EXIT, 1);
			ver = new Command("Ver puntajes", Command.ITEM, 1);
			menu = new List("Escoge tu opción:", List.IMPLICIT);
			menu.setTicker(new Ticker("Piedra, papel o tijeras"));
			menu.append("Piedra", imgPiedra);
			menu.append("Papel", imgPapel);
			menu.append("Tijera", imgTijera);
			menu.addCommand(salir);
			menu.addCommand(ver);
			menu.setCommandListener(this);
			pantalla = Display.getDisplay(this);
			pantalla.setCurrent(menu);
		}
		else pausado = false;

	}
	
	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public void pauseApp() {
		pausado = true;
	}

	private void Jugar(int Op) {
		//0: piedra, 1: papel, 2: tijera
		int maq = r.nextInt(3);
		String s = "";
		switch (maq){
		case 0:
			s = "¡La máquina escogió... PIEDRA!";
			alMaq.setImage(imgPiedra);
			switch (Op){
			case 0:
				//piedra-piedra
				s += "\n\nContra tu piedra... ¡Hubo un empate!";
				break;
			case 1:
				//piedra-papel
				s += "\n\nContra tu papel... ¡Ganaste!";
				puntaje++;
				break;
			case 2:
				//piedra-tijera
				s += "\n\nContra tu tijera... ¡Perdiste!";
				puntajemaq++;
			}
			break;
		case 1:
			s = "¡La máquina escogió... PAPEL!";
			alMaq.setImage(imgPapel);
			switch (Op){
			case 0:
				//papel-piedra
				s += "\n\nContra tu piedra... ¡Perdiste!";
				puntajemaq++;
				break;
			case 1:
				//papel-papel
				s += "\n\nContra tu papel... ¡Hubo un empate!";
				break;
			case 2:
				//papel-tijera
				s += "\n\nContra tu tijera... ¡Ganaste!";
				puntaje++;
			}
			break;
		case 2:
			s = "¡La máquina escogió... TIJERA!";
			alMaq.setImage(imgTijera);
			switch (Op){
			case 0:
				//tijera-piedra
				s += "\n\nContra tu piedra... ¡Ganaste!";
				puntaje++;
				break;
			case 1:
				//tijera-papel
				s += "\n\nContra tu papel... ¡Perdiste!";
				puntajemaq++;
				break;
			case 2:
				//tijera-tijera
				s += "\n\nContra tu tijera... ¡Hubo un empate!";
				break;
			}
			break;
		}
		s += "\n\nPuntaje actual:\nVos: " + puntaje + "\nMáquina: " + puntajemaq;
		alMaq.setString(s);
		pantalla.setCurrent(alMaq, menu);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == ver){
			alPunt.setString("Tu puntaje: "+puntaje+"\nMáquina: "+puntajemaq);
			pantalla.setCurrent(alPunt, menu);
		}
		else if (c == salir)
			destroyApp(true);
		else
			Jugar(menu.getSelectedIndex());
	}

}
