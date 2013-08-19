//Clase Menu, sirve para entradas, comidas, postres y bebidas
//según la información brindada en el constructor

import javax.microedition.lcdui.*;

public class Menu extends List implements CommandListener {
	
	private Principal padre;
	private Command sig, salir;
	private String[] opciones;
	
	public Menu(Principal p, String[] op, String tit) {
		super(tit, List.EXCLUSIVE);
		opciones = op;
		padre = p;
		for (int i=0; i<opciones.length; ++i)
			append(opciones[i], null);
		sig = new Command("Siguiente", Command.OK, 1);
		salir = new Command("Salir", Command.EXIT, 1);
		addCommand(sig);
		addCommand(salir);
		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == sig){
			padre.GuardarOpcion(getString(getSelectedIndex()));
			padre.Siguiente();
		}
		else if (c == salir)
			padre.Salir();
	}
	
}
