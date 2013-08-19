import javax.microedition.lcdui.*;

public class Pantalla2 extends List implements CommandListener {
	
	private Menu mid; //comunicación con Principal
	private Alert al;
	
	public Pantalla2(Menu m) {
		super("Oficina", List.IMPLICIT);
		mid = m;
		al = new Alert("Información");
		al.setType(AlertType.INFO); 
		al.setTimeout(2000);
		append("Block de Notas", null);
		append("Agenda", null);
		append("Volver a Principal", null);
		setCommandListener(this);
	}
	
	public void commandAction(Command c, Displayable d){
		switch (getSelectedIndex()){
			case 0: //block de notas
				al.setString("Se eligió Block de Notas");
				mid.MostrarAlerta(al, this);
				break;
			case 1: //agenda
				al.setString("Se eligió Agenda");
				mid.MostrarAlerta(al, this);
				break;
			case 2: //volver
				mid.Volver();
				break;
		}
	}
	
}
