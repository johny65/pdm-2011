//Guía TP 4 - Ejercicio 2
//Alumno: Juan Bertinetti

import javax.microedition.midlet.*;
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;

public class IPCheck extends MIDlet implements ActionListener {

	private TextField t;
	private Command b, salir, ok;
	private Form f, info;
	private Label l;
	private IP ip;
	
	public void startApp() {
		Display.init(this);
		ip = new IP();
		t = new TextField();
		b = new Command("Verificar");
		salir = new Command("Salir");
		ok = new Command("OK");
		f = new Form("Chequea IP");
		f.setLayout(new BorderLayout());
		f.addComponent(BorderLayout.NORTH, new Label("Ingrese una IP:"));
		f.addComponent(BorderLayout.CENTER, t);
		f.addCommand(b);
		f.addCommand(salir);
		f.addCommandListener(this);
		l = new Label();
		info = new Form("Información");
		info.addComponent(l);
		info.addCommand(ok);
		info.addCommandListener(this);
		f.show();
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getCommand() == salir)
			destroyApp(true);
		else if (arg0.getCommand() == b){
			ip.setIP(t.getText());
			if (ip.ComprobarIP()){
				l.setText("Dirección IP correcta.");
				info.show();
			}
			else {
				l.setText("Dirección IP inválida.");
				info.show();
			}
		}
		else if (arg0.getCommand() == ok)
			f.show();
	}

}
