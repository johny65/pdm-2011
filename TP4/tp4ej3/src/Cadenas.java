//Guía TP 4 - Ejercicio 3
//Alumno: Juan Bertinetti

import javax.microedition.midlet.*;
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;

public class Cadenas extends MIDlet implements ActionListener {

	private TextField tf;
	private Command add, salir, ok, cancelar;
	private Form principal, enter;
	
	public void startApp() {
		Display.init(this);		
		tf = new TextField();
		salir = new Command("Salir");
		ok = new Command("Ok");
		add = new Command("Agregar");
		cancelar = new Command("Cancelar");
		principal = new Form("Lista de cadenas");
		principal.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		principal.addCommand(add);
		principal.addCommand(salir);
		principal.addCommandListener(this);
		enter = new Form("Ingrese una nueva cadena:");
		enter.setLayout(new BorderLayout());
		enter.addComponent(BorderLayout.CENTER, tf);
		enter.addCommand(ok);
		enter.addCommand(cancelar);
		enter.addCommandListener(this);
		principal.show();
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getCommand() == salir)
			destroyApp(true);
		else if (arg0.getCommand() == add){
			tf.setText("");
			enter.show();
		}
		else if (arg0.getCommand() == cancelar)
			principal.show();
		else if (arg0.getCommand() == ok){
			boolean existe = false;
			for (int i=0; i<principal.getContentPane().getComponentCount(); ++i){
				Label temp = (Label)principal.getContentPane().getComponentAt(i);
				if (temp.getText().equals(tf.getText())){
					//ya existe, borrarla
					principal.getContentPane().removeComponent(temp);
					existe = true;
					break;
				}
			}
			if (!existe) //no existe, agregarla
				principal.addComponent(new Label(tf.getText()));
			principal.show();
		}

	}
}
