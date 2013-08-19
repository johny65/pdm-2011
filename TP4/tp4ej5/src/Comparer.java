//Guía TP 4 - Ejercicio 5
//Alumno: Juan Bertinetti

import javax.microedition.midlet.*;
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;

public class Comparer extends MIDlet implements ActionListener {

	private TextField cadena1, cadena2;
	private Command salir, ok, volver;
	private Form f, res;
	private Label l;
	
	public void startApp() {
		
		Display.init(this);		
		cadena1 = new TextField();
		cadena2 = new TextField();
		salir = new Command("Salir");
		ok = new Command("Comparar");
		volver = new Command("Volver");
		f = new Form("Comparar cadenas");
		f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		f.addComponent(new Label("Ingrese cadena 1:"));
		f.addComponent(cadena1);
		f.addComponent(new Label("Ingrese cadena 2:"));
		f.addComponent(cadena2);
		f.addCommand(ok);
		f.addCommand(salir);
		f.addCommandListener(this);
		res = new Form("Resultados");
		l = new Label();
		res.addComponent(l);
		res.addCommand(volver);
		res.addCommandListener(this);
		f.show();
		
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {}
	
	public float GradoParecido(String s1, String s2) {
		int m = Math.min(s1.length(), s2.length());
		int total = Math.max(s1.length(), s2.length());
		int aciertos = 0;
		for (int i=0; i<m; ++i){
			if (s1.charAt(i) == s2.charAt(i))
				aciertos++;
		}
		float p = aciertos*100/total;
		return p;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getCommand() == salir)
			destroyApp(true);
		else if (arg0.getCommand() == volver)
			f.show();
		else if (arg0.getCommand() == ok){
			if (!(cadena1.getText().length() == 0 && cadena2.getText().length() == 0)){
				//no permito las 2 vacías pero sí una
				l.setText("Grado de parecido: "+GradoParecido(cadena1.getText(), cadena2.getText())+"%");
				l.setTickerEnabled(true);
				res.show();
			}
		}
	}
}
