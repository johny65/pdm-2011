//Guía TP 4 - Ejercicio 4
//Alumno: Juan Bertinetti

import javax.microedition.midlet.*;
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;

public class Login extends MIDlet implements ActionListener {

	private TextField usuario, password;
	private Command salir, ok;
	private Form login, ingreso;
	private Label l;
	
	private String user_data = "johny";
	private String pass_data = "mipass";
	
	public void startApp() {
		Display.init(this);
		
		//cargar estilo visual:
		try {
			Resources r = Resources.open("/Blackberry.res");
			UIManager.getInstance().setThemeProps(r.getTheme(r.getThemeResourceNames()[0]));
		} catch (java.io.IOException e) {
		}
		
		usuario = new TextField();
		password = new TextField();
		password.setConstraint(TextField.PASSWORD);
		salir = new Command("Salir");
		ok = new Command("Ingresar");
		l = new Label("                    ");
		
		//pantalla de login
		login = new Form("Inicio de sesión");
		login.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		login.addComponent(new Label("Nombre de usuario:"));
		login.addComponent(usuario);
		login.addComponent(new Label("Contraseña:"));
		login.addComponent(password);
		login.addComponent(l);
		login.addCommand(ok);
		login.addCommand(salir);
		login.addCommandListener(this);
		
		//pantalla ok
		ingreso = new Form("¡Bienvenido!");
		ingreso.setLayout(new BorderLayout());
		ingreso.addComponent(BorderLayout.CENTER, new Label("Bienvenido usuario "+user_data));
		ingreso.addCommand(salir);
		ingreso.addCommandListener(this);
		
		login.show();
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getCommand() == salir)
			destroyApp(true);
		else if (arg0.getCommand() == ok){
			if (user_data.equals(usuario.getText()) && pass_data.equals(password.getText())){
				l.setText("");
				ingreso.show();
			}
			else {
				l.setText("Datos incorrectos.");
				usuario.setText("");
				password.setText("");
			}
		}
	}
}
