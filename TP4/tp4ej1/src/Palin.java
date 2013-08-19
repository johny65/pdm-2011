//Guía TP 4 - Ejercicio 1
//Alumno: Juan Bertinetti

import javax.microedition.midlet.*;
import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;

public class Palin extends MIDlet implements ActionListener {

	private TextField t;
	private Command b, salir, ok;
	private Form f, info;
	private Label l;
	
	public void startApp() {
		Display.init(this);
		t = new TextField();
		b = new Command("Verificar");
		salir = new Command("Salir");
		ok = new Command("OK");
		f = new Form("Palíndromos");
		f.setLayout(new BorderLayout());
		f.addComponent(BorderLayout.NORTH, new Label("Ingrese una cadena:"));
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

	public boolean EsPalindromo(String s) {
		s = s.toLowerCase();
		int i=0, j=s.length()-1;
		while (i<=j){
			if (s.charAt(i) != s.charAt(j)) return false;
			i++;
			j--;
		}
		return true;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getCommand() == salir)
			destroyApp(true);
		else if (arg0.getCommand() == b){
			boolean si = EsPalindromo(t.getText());
			if (si){
				l.setText("Es un palíndromo.");
				info.show();
			}
			else {
				l.setText("No es un palíndromo.");
				info.show();
			}
		}
		else if (arg0.getCommand() == ok)
			f.show();
	}

}
