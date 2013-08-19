//Guía TP 5 - Ejercicio 5
//Alumno: Juan Bertinetti

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Cotizador extends MIDlet implements CommandListener, Runnable {

	private Display pantalla;
	private Command ver, salir;
	private TextField tfcompra, tfventa;
	private Form main;
	
	public void startApp() {
		ver = new Command("Actualizar", Command.OK, 1);
		salir = new Command("Salir", Command.EXIT, 1);
		tfcompra = new TextField("Compra:", "", 100, TextField.ANY | TextField.UNEDITABLE);
		tfventa = new TextField("Venta:", "", 100, TextField.ANY | TextField.UNEDITABLE);
		main = new Form("Cotizador");
		main.append("Cotización del dólar:");
		main.append(tfcompra);
		main.append(tfventa);
		main.addCommand(ver);
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
		else if (c == ver){
			tfcompra.setString("Actualizando...");
			tfventa.setString("Actualizando...");
			Thread t = new Thread(this);
			t.start();
		}
	}

	public void run() {
		String datos = "";
		//descargar datos:
		try {
			String url = "http://www.google.com/gwt/x?u=http%3A%2F%2Fwww.bna.com.ar%2Fbp%2Fbp_cotizaciones.asp%3Fop%3Dm&noimg=1&btnGo=Ir&source=wax&ie=UTF-8&oe=UTF-8";
			HttpConnection conexion = (HttpConnection)Connector.open(url);
			if (conexion.getResponseCode() != HttpConnection.HTTP_OK)
				return;
			byte[] data = null;
			int chunk = 512;
			DataInputStream in = new DataInputStream(conexion.openInputStream());
			data = new byte[chunk];
			while (in.read(data, 0, chunk) != -1){
				datos += new String(data);
			}
		} catch (IOException e) {
			System.err.println("Error");
			datos = "";
		}

		//parsear datos:
		int pos = datos.indexOf("Dolar");
		if (pos != -1){
			int blank1 = datos.indexOf(" ", pos) + 1;
			int blank2 = datos.indexOf(" ", blank1);
			int blank3 = datos.indexOf(" ", blank2+1);
			String compra = datos.substring(blank1, blank2);
			String venta = datos.substring(blank2+1, blank3);
			tfcompra.setString(compra);
			tfventa.setString(venta);
		}
		else {
			tfcompra.setString("Error");
			tfventa.setString("Error");
		}
	}

}
