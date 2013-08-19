import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;

public class Opciones extends Form implements CommandListener {

	private ChoiceGroup efectos;
	private Command ok, volver1, volver2, salir;
	private int figura;
	private Figura f;
	private Alert err;
	private Figuritas midlet;
	private TextField tfT, tfV;
	boolean fx[];
	
	public Opciones(int tipo, Figuritas m) {
		super("Opciones:");
		midlet = m;
		figura = tipo;
		fx = new boolean[3];
		append(new TextField("x0", "", 10, TextField.NUMERIC));
		append(new TextField("y0", "", 10, TextField.NUMERIC));
		switch (figura){
			case 0: //rectángulo
				setTicker(new Ticker("Agregando nuevo rectángulo"));
				append(new TextField("Ancho", "", 10, TextField.NUMERIC));
				append(new TextField("Alto", "", 10, TextField.NUMERIC));
				break;
			case 1: //círculo
				setTicker(new Ticker("Agregando nuevo círculo"));
				append(new TextField("Radio", "", 10, TextField.NUMERIC));
				break;
			case 2: //cuadrado
				setTicker(new Ticker("Agregando nuevo cuadrado"));
				append(new TextField("Lado", "", 10, TextField.NUMERIC));
				break;
			case 3: //triángulo
				setTicker(new Ticker("Agregando nuevo triángulo"));
				append(new TextField("Base", "", 10, TextField.NUMERIC));
				append(new TextField("Altura", "", 10, TextField.NUMERIC));
				break;
		}
		efectos = new ChoiceGroup("Elige los efectos a aplicar:", ChoiceGroup.MULTIPLE);
		efectos.append("Multicolor", null);
		efectos.append("Sorpresa", null);
		efectos.append("Terremoto", null);
		append(efectos);
		tfT = new TextField("Temblor", "5", 10, TextField.NUMERIC);
		tfV = new TextField("Refresco", "50", 10, TextField.NUMERIC);
		append("Otras opciones:");
		append(tfT);
		append(tfV);
		ok = new Command("Aceptar", Command.OK, 1);
		volver1 = new Command("Volver", Command.BACK, 1);
		volver2 = new Command("Volver", Command.BACK, 1);
		salir = new Command("Salir", Command.EXIT, 1); //vuelve a elegir otra figura
		addCommand(ok);
		addCommand(volver1);
		setCommandListener(this);
		err = new Alert("Error");
		err.setString("Los datos ingresados son inválidos. Verifigue que la figura quede dentro de la pantalla.");
		err.setTimeout(Alert.FOREVER);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == salir)
			midlet.destroyApp(true);
		else if (c == volver1) //elegir otra figura
			midlet.Inicio();
		else if (c == volver2) //vuelve a las opciones
			midlet.Volver();
		if (c == ok){ //acá todo el código poderoso
			int t, v;
			try {
				int x0 = Integer.parseInt(((TextField)get(0)).getString());
				int y0 = Integer.parseInt(((TextField)get(1)).getString());
				t = Integer.parseInt(tfT.getString());
				v = Integer.parseInt(tfV.getString());
				switch (figura){
					case 0: {
						int ancho = Integer.parseInt(((TextField)get(2)).getString());
						int alto = Integer.parseInt(((TextField)get(3)).getString());
						f = new Rectangulo(x0, y0, ancho, alto);
						break;
					}
					case 1: {
						int radio = Integer.parseInt(((TextField)get(2)).getString());
						f = new Circulo(x0, y0, radio);
						break;
					}
					case 2: {
						int lado = Integer.parseInt(((TextField)get(2)).getString());
						f = new Cuadrado(x0, y0, lado);
						break;
					}
					case 3: {
						int base = Integer.parseInt(((TextField)get(2)).getString());
						int alto = Integer.parseInt(((TextField)get(3)).getString());
						f = new Triangulo(x0, y0, alto, base);
					}
				}
			}
			catch (NumberFormatException e) {
				midlet.MostrarError(err);
				return;
			}
				
			if (f.esValido()){
				efectos.getSelectedFlags(fx);
				if (fx[0])
					f.setColorea();
				if (fx[1])
					f.setCrece();
				if (fx[2])
					f.setTiembla();
				f.setTemblor(t);
				f.setDelay(v);
				f.addCommand(salir);
				f.addCommand(volver2);
				f.setCommandListener(this);
				midlet.MostrarFigura(f);
			}
			else {
				midlet.MostrarError(err);
				return;
			}

		} //acá termina el command ok
		
	}
}
