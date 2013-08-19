import javax.microedition.lcdui.*;

public class Opciones extends List implements CommandListener {

	private Command conv, volver;
	private int origen; //0: dec, 1: bin, 2: oct, 3: hex
	private String datos, origenS;
	private Conversor papa;
	
	public Opciones(Conversor c) {
		super("Escoge las conversiones:", List.MULTIPLE);
		papa = c;
		conv = new Command("Convertir", Command.OK, 1);
		volver = new Command("Volver", Command.BACK, 1);
		append("A decimal", null);
		append("A binario", null);
		append("A octal", null);
		append("A hexadecimal", null);
		addCommand(conv);
		addCommand(volver);
		setCommandListener(this);
	}

	public void setOrigen(int i) {
		origen = i; //sistema numérico origen
		switch (origen){
		case 0:
			origenS = "decimal"; break;
		case 1:
			origenS = "binario"; break;
		case 2:
			origenS = "octal"; break;
		case 3:
			origenS = "hexadecimal";
		}
	}
	
	public void setDatos(String s) {
		datos = s; //datos a convertir
	}

	//comprueba que la cadena sea un dato en el sistema numérico esperado (origen)
	public boolean ComprobarEntrada(String s) {
		if (s.length() == 0)
			return false;
		switch (origen){
		case 0: //decimal
			for (int i=0; i<s.length(); ++i)
				if (s.charAt(i) > '9' || s.charAt(i) < '0')
					return false;
			break;
		case 1: //binario
			for (int i=0; i<s.length(); ++i)
				if (s.charAt(i) != '0' && s.charAt(i) != '1')
					return false;
			break;
		case 2: //octal
			for (int i=0; i<s.length(); ++i)
				if (s.charAt(i) > '7' || s.charAt(i) < '0')
					return false;
			break;
		case 3: //hexa
			s = s.toUpperCase();
			for (int i=0; i<s.length(); ++i)
				if (!(s.charAt(i) <= '9' && s.charAt(i) >= '0') && !(s.charAt(i) <= 'F' && s.charAt(i) >= 'A'))
					return false;	
		}
		return true;
	}
	
	private String Convertir(int out) {
		
		//reconocer entrada
		int entero = 0;
		switch (origen){
		case 0:
			entero = Integer.parseInt(datos);
			break;
		case 1:
			entero = Integer.parseInt(datos, 2);
			break;
		case 2:
			entero = Integer.parseInt(datos, 8);
			break;
		case 3:
			entero = Integer.parseInt(datos, 16);
		}
		
		//obtener salida
		String to = "";
		switch (out){
		case 0:
			to = Integer.toString(entero, 10);
			break;
		case 1:
			to = Integer.toString(entero, 2);
			break;
		case 2:
			to = Integer.toString(entero, 8);
			break;
		case 3:
			to = Integer.toString(entero, 16);
			to = to.toUpperCase();
		}
		return to;
	}
	
	public void commandAction(Command c, Displayable d) {
		if (c == volver)
			papa.Volver();
		else if (c == conv){
			boolean op[] = new boolean[4];
			String res = "Tu número en "+origenS+": "+datos+"\n";
			getSelectedFlags(op);
			if (op[0])
				res += "\nEn decimal: "+Convertir(0);
			if (op[1])
				res += "\nEn binario: "+Convertir(1);
			if (op[2])
				res += "\nEn octal: "+Convertir(2);
			if (op[3])
				res += "\nEn hexadecimal: "+Convertir(3);
			papa.MostrarResultados(res);
		}
		
	}
	
}
